package com.emart.emartapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private final OffsetDateTime created;

    @Column() // Allow null for the modified field
    private OffsetDateTime modified;

    @Column() // Allow null for the deleted field
    private OffsetDateTime deleted;

    @JsonIgnore
    @Column(nullable = false)
    private boolean isDeleted = false;

    protected BaseEntity() {
        this.created = OffsetDateTime.now();
        this.modified = this.created;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modified = OffsetDateTime.now();
    }
}
