package javaproject.testfinalproject.model.DB_and_DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class DAO {

    @Autowired
    JdbcTemplate jdbcTemplate;
}
