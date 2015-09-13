package com.skt.web.alpha.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.skt.web.alpha.dao.PushRegistrationDao;
import com.skt.web.alpha.model.PushRegistration;
import com.skt.web.common.exception.ApplicationException;

@Service
public class PushRegistrationServiceImpl implements PushRegistrationService {
	private static final Logger LOG = Logger
			.getLogger(PushRegistrationServiceImpl.class);

	private static final int MULTICAST_SIZE = 1000;

	private static final Executor threadPool = Executors.newFixedThreadPool(5);

	private Sender sender;
	// Browser App Access Key
	private static final String ACCESS_KEY = "AIzaSyDXvCl_735hYGGKhDNiYDmK9h-jzACRuaQ";

	@Autowired
	private PushRegistrationDao pushRegistrationDao;

	public void setPushRegistrationDao(PushRegistrationDao pushRegistrationDao) {
		this.pushRegistrationDao = pushRegistrationDao;
	}

	@Override
	public void send(List<String> devices, String message)
			throws ApplicationException {
		if (devices.isEmpty()) {
			LOG.info("Message ignored as there is no device registered!");
			return;
		} else {
			sender = new Sender(ACCESS_KEY);
			Message msg = new Message.Builder().addData("message", message)
					.build();
			if (devices.size() == 1) {
				String deviceId = devices.get(0);
				Result result = null;
				try {
					result = sender.send(msg, deviceId, 1);
				} catch (IOException e) {
					e.printStackTrace();
					throw new ApplicationException(e.getMessage());
				}
				System.out.println("Sent message to one device: " + result);
			} else {
				int total = devices.size();
				List<String> partialDevices = new ArrayList<>(total);
				int counter = 0;
				int tasks = 0;
				for (String device : devices) {
					counter++;
					partialDevices.add(device);
					int partialSize = partialDevices.size();
					if (partialSize == MULTICAST_SIZE || counter == total) {
						asyncSend(partialDevices, msg);
						partialDevices.clear();
						tasks++;
					}
				}
				System.out.println("Asynchronously sending " + tasks
						+ " multicast messages to " + total + " devices");
			}
		}
	}

	private void asyncSend(List<String> partialDevices, final Message message) {
		// make a copy
		final List<String> devices = new ArrayList<>(partialDevices);
		threadPool.execute(new Runnable() {
			public void run() {
				MulticastResult multicastResult;
				try {
					multicastResult = sender.send(message, devices, 5);
				} catch (IOException e) {
					LOG.info("Error posting messages", e);
					return;
				}
				List<Result> results = multicastResult.getResults();
				// analyze the results
				for (int i = 0; i < devices.size(); i++) {
					String regId = devices.get(i);
					Result result = results.get(i);
					String messageId = result.getMessageId();
					if (messageId != null) {
						LOG.info("Succesfully sent message to device: " + regId
								+ "; messageId = " + messageId);
						String canonicalRegId = result
								.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than on registration id:
							// update it
							LOG.info("canonicalRegId " + canonicalRegId);
							// Datastore.updateRegistration(regId,
							// canonicalRegId);
						}
					} else {
						String error = result.getErrorCodeName();
						if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
							// application has been removed from device -
							// unregister it
							LOG.info("Unregistered device: " + regId);
							// Datastore.unregister(regId);
						} else {
							LOG.info("Error sending message to " + regId + ": "
									+ error);
						}
					}
				}
			}
		});
	}

	@Override
	public void createPushRegistration(PushRegistration push)
			throws ApplicationException {
		pushRegistrationDao.save(push);
	}

	@Override
	public List<String> getAllRegisteredDevice() throws ApplicationException {
		return pushRegistrationDao.getAllRegisteredDevice();
	}

	@Override
	public boolean findByKey(String regKey, int userId)
			throws ApplicationException {
		return pushRegistrationDao.findBy(regKey, userId);
	}

	@Override
	public List<String> findAllKeysByUserId(int userId)
			throws ApplicationException {
		return pushRegistrationDao.findAllKeysByUserId(userId);
	}

	@Override
	public List<String> findAllKeysByUserIds(List<Integer> userIds)
			throws ApplicationException {
		return pushRegistrationDao.findAllKeysByUserIds(userIds);
	}

	@Override
	public Result send(String receiverPushId, String message)
			throws ApplicationException {
		if (receiverPushId == null || receiverPushId.isEmpty()) {
			throw new ApplicationException(
					"Unable to send pushMessage as receiverPushId is empty.");
		} else if (message == null || message.isEmpty()) {
			throw new ApplicationException(
					"Unable to send pushMessage as message is empty.");
		} else {
			System.out.println("Sending message: " + message
					+ " to receiverPushId: " + receiverPushId);
			sender = new Sender(ACCESS_KEY);
			Message pushMessage = new Message.Builder().addData("message",
					message).build();
			Result result;
			try {
				result = sender.send(pushMessage, receiverPushId, 1);
				System.out.println("Result after sending pushMessage is: "
						+ result);
			} catch (IOException e) {
				e.printStackTrace();
				throw new ApplicationException(
						"Unable to send pushMessage due to exception: "
								+ e.getMessage());
			}
			return result;
		}
	}
	
}