package com.epam.autum.selection.jdbc.dao.interfaces;

import com.epam.autum.selection.exception.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public interface IDAO<T>{

    /**
     * Create new record in table.
     *
     * @return true if the entity was inserted, false otherwise
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    boolean create(T entity) throws DAOException;

    /**
     * Retrieves all entities in the system.
     *
     * @return list of all entities
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    List<T> findAll() throws DAOException;

    /**
     * Retrieves an entity with a specific id.
     *
     * @param id id of the entity to find
     * @return an entity with the given id
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    Optional<T> findEntityById(int id) throws DAOException;

    /**
     * Deletes the entity with a specific id from the system.
     *
     * @param id id of the entity to delete
     * @return true if the entity was deleted, false otherwise
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    boolean delete(int id) throws DAOException;

    /**
     * Updates the entity with a specific id.
     *
     * @param entity - new entity
     * @return true if the entity was updated, false otherwise
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    boolean update(T entity) throws DAOException;

}
