package com.epam.autum.selection.dao.implementation.mysql;

import com.epam.autum.selection.dao.interfaces.IUserDAO;
import com.epam.autum.selection.database.WrapperConnection;
import com.epam.autum.selection.entity.User;
import com.epam.autum.selection.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Tapac on 02.01.2017.
 */
public class UserDAO implements IUserDAO{

    private static Logger log = LogManager.getLogger(UserDAO.class);

    private WrapperConnection connection;
    private static UserDAO instance;

    private static final String USER_ID = "user_id";
    private static final String NAME = "name";
    private static final String SEX = "sex";
    private static final String BIRTH = "birth";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_ROLE_ID = "user_role_id";

    private static final String SELECT_ALL = "SELECT user_id,name,email,password,sex,birth,address,phone,user_role_id FROM user";
    private static final String SELECT_BY_ID = "SELECT name,sex,birth,phone,address,email,password,user_role_id FROM user WHERE user_id=?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT user_id,name,password,sex,birth,address,phone,user_role_id FROM user WHERE email=?";
    private static final String SELECT_PASSWORD_BY_EMAIL = "SELECT password FROM user WHERE email=?";

    private static final String INSERT_USER = "INSERT INTO user(name,sex,phone,address,email,password,user_role_id) VALUES(?,?,?,?,?,?,2)";

    private static final String DELETE_BY_ID = "DELETE FROM user WHERE user_id=?";

    private static final String UPDATE_USER =
            "UPDATE user SET name=?, sex=?, birth=?, phone=?, address=?, email=?, password=? WHERE user_id=?";

    private UserDAO(WrapperConnection connection) {
        this.connection = connection;
    }

    public static UserDAO getInstance(WrapperConnection connection) {
        if (instance == null)
            instance = new UserDAO(connection);
        return instance;
    }

    @Override
    public boolean create(User entity) throws DAOException {
        boolean created = false;
        try (PreparedStatement st = connection.prepareStatement(INSERT_USER)) {
            st.setString(1, entity.getName());
            st.setString(2, entity.getSex());
            st.setString(3, entity.getPhone());
            st.setString(4, entity.getAddress());
            st.setString(5, entity.getEmail());
            st.setString(6, entity.getPassword());
            int res = st.executeUpdate();
            if (res > 0) {
                created = true;
                HelperDAO.getInstance().updateId(connection, entity);
                log.info("User " + entity + " created");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return created;
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> allUsers = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(USER_ID);
                String name = rs.getString(NAME);
                String sex = rs.getString(SEX);
                Date birth = rs.getDate(BIRTH);
                String phone = rs.getString(PHONE);
                String address = rs.getString(ADDRESS);
                String email = rs.getString(EMAIL);
                String pass = rs.getString(PASSWORD);
                int role_id = rs.getByte(USER_ROLE_ID);

                User user = new User(id,name,email,pass,sex,birth,address,phone,role_id);
                allUsers.add(user);
            }
            log.info("All users retrieved");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return allUsers;
    }

    @Override
    public Optional<User> findEntityById(int id) throws DAOException {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_BY_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString(NAME);
                String sex = rs.getString(SEX);
                Date birth = rs.getDate(BIRTH);
                String phone = rs.getString(PHONE);
                String address = rs.getString(ADDRESS);
                String email = rs.getString(EMAIL);
                String pass = rs.getString(PASSWORD);
                int role_id = rs.getByte(USER_ROLE_ID);
                user = new User(id,name,email,pass,sex,birth,address,phone,role_id);
                log.info("User [id = " + id + "] found");
            } else {
                log.info("User [id = " + id + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean update(User entity) throws DAOException {
        boolean update = false;
        try (PreparedStatement st = connection.prepareStatement(UPDATE_USER)) {
            st.setString(1, entity.getName());
            st.setString(2, entity.getSex());
            st.setDate(3, entity.getBirth());
            st.setString(4, entity.getPhone());
            st.setString(5, entity.getAddress());
            st.setString(6, entity.getEmail());
            st.setString(7, entity.getPassword());
            st.setInt(8, entity.getId());

            int updated = st.executeUpdate();
            if (updated > 0) {
                update = true;
                log.info("User " + entity + " updated");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return update;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        int rows;
        try (PreparedStatement st = connection.prepareStatement(DELETE_BY_ID)) {
            st.setInt(1, id);
            rows = st.executeUpdate();
            if (rows > 0) {
                log.info("User [id = " + id + "] deleted");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return rows > 0;
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws DAOException, SQLException {
        String pass = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_PASSWORD_BY_EMAIL)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                pass = rs.getString(PASSWORD);
                log.info("Password by email = " + email + " retrieved");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(pass);
    }

    @Override
    public Optional<User> findEntityByEmail(String email) throws DAOException {
        User user = null;
        try (PreparedStatement st = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(USER_ID);
                String name = rs.getString(NAME);
                String sex = rs.getString(SEX);
                Date birth = rs.getDate(BIRTH);
                String phone = rs.getString(PHONE);
                String address = rs.getString(ADDRESS);
                String pass = rs.getString(PASSWORD);
                int role_id = rs.getByte(USER_ROLE_ID);
                user =new User(id,name,email,pass,sex,birth,address,phone,role_id);
                log.info("User [email = " + email + "] found");
            } else {
                log.info("User [email = " + email + "] not found");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }

    public boolean isEmailFree(String email) throws DAOException {
        boolean isFree = false;
        try (PreparedStatement st = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            isFree = !rs.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isFree;
    }
}
