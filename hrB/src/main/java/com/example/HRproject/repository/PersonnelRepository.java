package com.example.HRproject.repository;

import com.example.HRproject.entity.PersonnelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<PersonnelEntity, Long> {
    @Query("SELECT p FROM PersonnelEntity p WHERE " +
            "(:firstName = '' OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName = '' OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:tckn = '' OR CAST(p.tckn AS string) LIKE CONCAT('%', :tckn, '%')) AND " +
            "(:department = '' OR LOWER(p.department) LIKE LOWER(CONCAT('%', :department, '%')))"
            )
    List<PersonnelEntity> findByMultipleFields(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("tckn") String tckn,
            @Param("department") String department
    );

    boolean existsByTckn(long tckn);

    boolean existsByTcknAndIdNot(long tckn, Long id);}