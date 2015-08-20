package com.skt.web.common.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.skt.web.common.exception.ApplicationException;
import com.skt.web.common.model.BaseModel;

public class BaseDaoImpl<T extends BaseModel> extends HibernateDaoSupport
		implements BaseDao<T> {
	/**
	 * Logger to log the exceptions.
	 */
	private static final Logger LOG = Logger.getLogger(BaseDaoImpl.class);

	/**
	 * Construct a new BaseDao object.
	 */
	public BaseDaoImpl() {
	}

	/**
	 * Persists the given object to the database.
	 * 
	 * @param domainObj
	 *            - The domain object to save.
	 * @throws ApplicationException
	 *             - the exception.
	 * @return - The newly saved object.
	 */
	@Override
	public T persist(T domainObj) throws ApplicationException {
		try {
			LOG.debug("BaseDaoImpl->persist-> object details"
					+ domainObj.toString());

			getHibernateTemplate().persist(domainObj);
			return domainObj;
		} catch (DataAccessException ex) {
			LOG.error("BaseDaoImpl - error occured while persisting object "
					+ domainObj + "-->" + ex.toString());
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}

	@Override
	public T merge(T domainObj) throws ApplicationException {
		try {
			LOG.debug("BaseDaoImpl->merge-> object details"
					+ domainObj.toString());

			domainObj = getHibernateTemplate().merge(domainObj);
			return domainObj;
		} catch (DataAccessException ex) {
			LOG.error("BaseDaoImpl - error occured while merge object "
					+ domainObj + "-->" + ex.toString());
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}

	/**
	 * Saves the given object to the database.
	 * 
	 * @param domainObj
	 *            - The domain object to save.
	 * @throws ApplicationException
	 *             - the exception.
	 * @return - The newly saved object.
	 */
	@Override
	public T save(T domainObj) throws ApplicationException {
		try {
			LOG.debug("BaseDaoImpl->save-> object details"
					+ domainObj.toString());

			getHibernateTemplate().save(domainObj);
			return domainObj;
		} catch (DataAccessException ex) {
			LOG.error("BaseDaoImpl - error occured while saving object "
					+ domainObj + "-->" + ex.toString());
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}

	/**
	 * Method to update a record for the given domain object.
	 * 
	 * @param domainObj
	 *            - The domain object to update.
	 * @throws ApplicationException
	 *             - The exception.
	 */
	@Override
	public void update(T domainObj) throws ApplicationException {
		try {
			LOG.debug("BaseDaoImpl->update-> object details"
					+ domainObj.toString());

			getHibernateTemplate().update(domainObj);
		} catch (DataAccessException ex) {
			LOG.error("BaseDaoImpl - error occured while saving object "
					+ domainObj + "-->" + ex.toString());
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}

	/**
	 * Method to delete the given domain object from the database.
	 * 
	 * @param domainObj
	 *            - the domain object to be deleted from the db.
	 * @throws ApplicationException
	 *             - the detailed exception.
	 */
	@Override
	public void delete(T domainObj) throws ApplicationException {
		try {
			LOG.debug("BaseDaoImpl->delete-> object details"
					+ domainObj.toString());

			getHibernateTemplate().delete(domainObj);
		} catch (DataAccessException ex) {
			LOG.error("BaseDaoImpl - error occured while saving object "
					+ domainObj + "-->" + ex.toString());
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}

	/**
	 * This method will create a collection of objects (if not already available
	 * in the db); else it will update the existing records in the db for the
	 * given collection.
	 * 
	 * @param objList
	 *            - The list of objects to be saved/updated.
	 * @throws ApplicationException
	 *             - The detailed exception.
	 */
	@Override
	public void saveOrUpdateAll(List<T> objList) throws ApplicationException {
		try {
			for (T t : objList) {
				getHibernateTemplate().saveOrUpdate(t);
			}
		} catch (DataAccessException ex) {
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}

	/**
	 * Finds a db record for a specific domain object and its id.
	 * 
	 * @param domainObj
	 *            - The domain object to be retrieved.
	 * @param id
	 *            - the specific id/key of the domain object to be retrieved.
	 * @return Object - The specific domain object.
	 * @throws ApplicationException
	 *             - The detailed exception.
	 */
	@Override
	public T findById(Class<T> domainObj, int id) throws ApplicationException {
		try {
			return getHibernateTemplate().get(domainObj, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getLocalizedMessage());
		}
	}

	/**
	 * Retrieves the entire list of records for a given domain object.
	 * 
	 * @param domainClass
	 *            - the entity/domain object class.
	 * @return List - The list of records for the given domain class.
	 * @throws ApplicationException
	 *             - The detailed exception.
	 */
	@Override
	public List<T> findAll(Class<T> domainClass) throws ApplicationException {
		try {
			return getHibernateTemplate().loadAll(domainClass);
		} catch (DataAccessException e) {
			throw new ApplicationException(e.getLocalizedMessage());
		}
	}

	public Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	@Autowired
	public void setBaseSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void saveOrUpdate(T domainObj) throws ApplicationException {
		try {
			LOG.debug("BaseDaoImpl->saveOrUpdate-> object details"
					+ domainObj.toString());

			getHibernateTemplate().saveOrUpdate(domainObj);
		} catch (DataAccessException ex) {
			LOG.error("BaseDaoImpl - error occured while saving object "
					+ domainObj + "-->" + ex.toString());
			throw new ApplicationException(ex.getLocalizedMessage());
		}
	}
}
