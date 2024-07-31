package com.example.HRproject.service;

import com.example.HRproject.entity.AssignmentEntity;
import com.example.HRproject.entity.InventoryEntity;
import com.example.HRproject.entity.PersonnelEntity;
import com.example.HRproject.repository.AssignmentRepository;
import com.example.HRproject.repository.InventoryRepository;
import com.example.HRproject.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<AssignmentEntity> getAssignmentsByPersonnel(Long personnelId) {
        return assignmentRepository.findByPersonnelId(personnelId);
    }

    public AssignmentEntity createAssignment(Long personnelId, Long inventoryId, Long assignedById, Date assignmentDate) {
        Optional<PersonnelEntity> personnel = personnelRepository.findById(personnelId);
        Optional<InventoryEntity> inventory = inventoryRepository.findById(inventoryId);
        Optional<PersonnelEntity> assignedBy = personnelRepository.findById(assignedById);

        if (personnel.isPresent() && inventory.isPresent() && assignedBy.isPresent()) {
            AssignmentEntity assignment = new AssignmentEntity();
            assignment.setPersonnel(personnel.get());
            assignment.setInventory(inventory.get());
            assignment.setAssignedBy(assignedBy.get());
            assignment.setAssignmentDate(assignmentDate);
            return assignmentRepository.save(assignment);
        }
        return null;
    }

    public AssignmentEntity returnAssignment(Long assignmentId, Long returnedToId, Date returnDate) {
        Optional<AssignmentEntity> assignmentOpt = assignmentRepository.findById(assignmentId);
        Optional<PersonnelEntity> returnedTo = personnelRepository.findById(returnedToId);

        if (assignmentOpt.isPresent() && returnedTo.isPresent()) {
            AssignmentEntity assignment = assignmentOpt.get();
            assignment.setReturnDate(returnDate);
            assignment.setReturnedTo(returnedTo.get());
            return assignmentRepository.save(assignment);
        }
        return null;
    }
}