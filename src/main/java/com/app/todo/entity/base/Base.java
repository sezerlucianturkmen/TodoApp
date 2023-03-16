package com.app.todo.entity.base;

import com.app.todo.entity.enums.State;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    @Nonnull
    @Column(name = "state")
    private State state;

    @PrePersist
    public void prePersist() {
        setCreatedAt(new Date());
        setUpdatedAt(new Date());
        setState(State.ACTIVE);
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Base base = (Base) o;
        return id != null && Objects.equals(id, base.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
