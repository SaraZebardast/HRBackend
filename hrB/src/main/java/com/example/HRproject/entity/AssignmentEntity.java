package com.example.HRproject.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "assignment")

public class AssignmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personnel_id", nullable = false)
    private PersonnelEntity personnel;

    @ManyToOne
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryEntity inventory;

    private Date assignmentDate;
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private PersonnelEntity assignedBy;

    @ManyToOne
    @JoinColumn(name = "returned_to")
    private PersonnelEntity returnedTo;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonnelEntity getPersonnel() {
        return personnel;
    }

    public void setPersonnel(PersonnelEntity personnel) {
        this.personnel = personnel;
    }

    public InventoryEntity getInventory() {
        return inventory;
    }

    public void setInventory(InventoryEntity inventory) {
        this.inventory = inventory;
    }

    public Date getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public PersonnelEntity getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(PersonnelEntity assignedBy) {
        this.assignedBy = assignedBy;
    }

    public PersonnelEntity getReturnedTo() {
        return returnedTo;
    }

    public void setReturnedTo(PersonnelEntity returnedTo) {
        this.returnedTo = returnedTo;
    }
}