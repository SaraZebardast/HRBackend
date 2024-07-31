package com.example.HRproject.repository;

import com.example.HRproject.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    @Query("SELECT i FROM InventoryEntity i WHERE " +
            "(:assignmentType = '' OR i.assignmentType = :assignmentType)")
    List<InventoryEntity> findByAssignmentType(@Param("assignmentType") String assignmentType);
}