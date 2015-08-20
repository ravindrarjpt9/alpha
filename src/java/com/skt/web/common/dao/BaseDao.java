package com.skt.web.common.dao;

import java.util.List;

import com.skt.web.common.exception.ApplicationException;
import com.skt.web.common.model.BaseModel;

/**
 * Generic base interface for DAOs. This class simplifies data access by
 * allowing a common access point for all the entities in the application to
 * execute various persistence related operations.
 * 
 * @param <T>
 *            - the concrete class.
 */
public interface BaseDao<T extends BaseModel> {
	
	/**
	 * Method to persist a new object to the database.
	 * 
	 * @param domainObj
	 *            - The entity to be persisted.
	 * @return - The new entity that has been added to the system.
	 * @throws ApplicationException
	 *             - the exception.
	 */
	T persist(T domainObj) throws ApplicationException;
	
	/**
	 * Method to merge an object to the database.
	 * 
	 * @param domainObj
	 *            - The entity to be merged.
	 * @return - The new entity that has been added to the system.
	 * @throws ApplicationException
	 *             - the exception.
	 */
	T merge(T domainObj) throws ApplicationException;
	
	/**
	 * Method to save a new object to the database.
	 * 
	 * @param domainObj
	 *            - The entity to be saved.
	 * @return - The new entity that has been added to the system.
	 * @throws ApplicationException
	 *             - the exception.
	 */
	T save(T domainObj) throws ApplicationException;

	/**
	 * Updates and existing record for the given domain object.
	 * 
	 * @param domainObj
	 *            - The domain object to be updated.
	 * @throws ApplicationException
	 *             - the exception.
	 */
	void update(T domainObj) throws ApplicationException;

	/**
	 * Deletes the given domain object from the database.
	 * 
	 * @param domainObj
	 *            - the domain object to be deleted
	 * @throws ApplicationException
	 *             - the exception.
	 */
	void delete(T domainObj) throws ApplicationException;

	/**
	 * This method will create a collection of objects (if not already available
	 * in the db); else it will update the existing records for the given
	 * collection in the db.
	 * 
	 * @param objList
	 *            - The list of objects to be saved/updated.
	 * @throws ApplicationException
	 *             - The detailed exception.
	 */
	void saveOrUpdateAll(List<T> objList) throws ApplicationException;
	
	/**
	 * This method will create a object (if not already available
	 * in the db); else it will update the existing record in the db.
	 * 
	 * @param obj
	 *            - The object to be saved/updated.
	 * @throws ApplicationException
	 *             - The detailed exception.
	 */
	void saveOrUpdate(T obj) throws ApplicationException;

	/**
	 * Finds a db record for a specific domain object and its id.
	 * 
	 * @param domainObj
	 *            - The domain object to be retrieved.
	 * @param id
	 *            - the specific id/key of the domain object to be retrieved.
	 * @return Object - The specific domain object.
	 * @throws ApplicationException
	 *             - The detailed exception
	 */
	T findById(Class<T> domainObj, int id) throws ApplicationException;

	/**
	 * Retrieves the entire list of records for a given domain object.
	 * 
	 * @param domainClass
	 *            - the entity/domain object class.
	 * @return List - The list of records for the given domain class.
	 * @throws ApplicationException
	 *             - The detailed exception.
	 */
	List<T> findAll(Class<T> domainClass) throws ApplicationException;
}
