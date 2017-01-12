package com.epam.autum.selection.dao.interfaces;

import com.epam.autum.selection.entity.FacultySubject;
import com.epam.autum.selection.exception.DAOException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public interface IFacultySubjectDAO {

    /**
     * Create new record in table.
     *
     * @return true if the entity was inserted, false otherwise
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    boolean create(FacultySubject fs) throws DAOException;

    /**
     * Retrieves all entities in the system.
     *
     * @return list of all entities
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    List<FacultySubject> findAll() throws DAOException;

    /**
     * Retrieves all entities in the system.
     * @param facultyID id of entity to find all subjects
     * @return list of all subjects entities
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    List<FacultySubject> findSubjectsByFaculty(int facultyID) throws DAOException;

    /**
     * Retrieves an entity with a specific id.
     *
     * @param facultyID id of the entity to find
     * @param subjectID id of the entity to find
     *                  complex key
     * @return an entity with the given id
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    Optional<FacultySubject> findEntityById(int facultyID,int subjectID) throws DAOException;

    /**
     * Deletes the entity with a specific id from the system.
     *
     * @param subject entity to delete
     * @return true if the entity was deleted, false otherwise
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    boolean delete(FacultySubject subject) throws DAOException;

    /**
     * Updates the entity with a specific id.
     *
     * @param entity - new entity
     * @return true if the entity was updated, false otherwise
     * @throws DAOException if any exceptions occurred on the SQL layer
     */
    boolean updateMinMark(FacultySubject entity) throws DAOException;

}
