package com.example.HRproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArchiveService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArchiveService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> fetchPersonnelArchiveData() {
        String sql = "SELECT * FROM personnel_archive";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> fetchAssignmentArchiveData() {
        String sql = "SELECT * FROM assignment_archive";
        return jdbcTemplate.queryForList(sql);
    }
}