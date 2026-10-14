package com.iagomassucato.apicommon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class AuditableEntity extends AbstractEntity {

    @Column(nullable = false, updatable = false)
    private Long createdBy;

    @Column(nullable = false)
    private Long updatedBy;

    protected AuditableEntity(Long createdBy) {
        super();
        this.createdBy = validateCreatedBy(createdBy);
        this.updatedBy = this.createdBy;
    }

    public void updateUpdatedBy(Long updatedBy) {
        this.updatedBy = validateCreatedBy(updatedBy);
    }

    private Long validateCreatedBy(Long createdBy) {
        if (createdBy == null) {
            throw new IllegalArgumentException("createdBy is required");
        }
        if (createdBy <= 0) {
            throw new IllegalArgumentException("createdBy must be greater than 0");
        }
        return createdBy;
    }
}

