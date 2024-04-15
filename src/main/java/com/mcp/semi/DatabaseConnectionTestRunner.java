package com.mcp.semi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTestRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnectionTestRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
        System.out.println("Database connection OK!");
    }
}