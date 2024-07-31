package com.example.HRproject.repository;

import com.example.HRproject.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    List<AssignmentEntity> findByPersonnelId(Long personnelId);
    List<AssignmentEntity> findByInventoryId(Long inventoryId);
}