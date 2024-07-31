package com.example.HRproject.repository;

import com.example.HRproject.entity.InventoryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryTypeRepository extends JpaRepository<InventoryTypeEntity, Long> {
    InventoryTypeEntity findByName(String name);
}