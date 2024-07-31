package com.example.HRproject.controller;

import com.example.HRproject.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/archive")
public class ArchiveController {

    private final ArchiveService archiveService;

    @Autowired
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/personnel")
    public List<Map<String, Object>> getPersonnelArchiveData() {
        return archiveService.fetchPersonnelArchiveData();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/assignment")
    public List<Map<String, Object>> getAssignmentArchiveData() {
        return archiveService.fetchAssignmentArchiveData();
    }
}