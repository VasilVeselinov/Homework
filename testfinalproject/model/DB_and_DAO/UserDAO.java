package javaproject.testfinalproject.model.DB_and_DAO;

import javaproject.testfinalproject.model.POJO.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserDAO extends DAO {

    public void registerUser(User user) throws SQLException {
        String insertSQL = "INSERT INTO users (user_name, user_password, user_email, is_admin) VALUES (?, ?, ?, ?);";
        this.jdbcTemplate.update(
                insertSQL,
                user.getUserName(), user.getUserPassword(), user.getUserEmail(), user.getIsAdmin());
    }

    public User loginUser(User user) throws SQLException {
        String loginSQL = "SELECT id, is_admin FROM users WHERE user_name = ? AND user_password = ? AND user_email = ?;";
        SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(loginSQL, user.getUserName(), user.getUserPassword(), user.getUserEmail());
        if (rowSet.next()) {
            User loginUser = this.setUserIDAndIsAdmin(rowSet, user);
            return loginUser;
        }
        return null;
    }

    private User setUserIDAndIsAdmin(SqlRowSet resultSet, User user) {
        user.setId(resultSet.getLong("id"));
        user.setIsAdmin(resultSet.getBoolean("is_admin"));
        return user;
    }

    public int changePassword(User user) {
        String updateSQL = "UPDATE users SET user_password = ? WHERE id = ?;";
        int rowAffected = this.jdbcTemplate.update(updateSQL, user.getNewPassword(), user.getId());
        return rowAffected;
    }

    public boolean existsByUsername(String userName) {
        String existsSQL = "SELECT user_name  FROM users WHERE user_name = ?;";
        SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(existsSQL, userName);
        if (rowSet.next()) {
            return true;
        }
        return false;
    }

    public boolean existsByEmail(String userEmail) {
        String existsSQL = "SELECT user_email  FROM users WHERE user_email = ?;";
        SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(existsSQL, userEmail);
        if (rowSet.next()) {
            return true;
        }
        return false;
    }

    public String searchUserByUsernameReturnPasswordAndSetID(User user) {
        String existsSQL = "SELECT  id, user_password  FROM users WHERE user_name = ?;";
        SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(existsSQL, user.getUserName());
        if (rowSet.next()) {
            user.setId(rowSet.getLong("id"));
            String password = rowSet.getString("user_password");
            return password;
        }
        return null;
    }
}
