package com.ecommerce.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Getter
public abstract class Base implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    protected boolean deleted = false;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}