package com.ecommerce.entities;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Category extends Base{
    private String name;
    private String imageUrl;
    private String publicId;

    @ElementCollection
    private List<String> tags; // Ej: ["fútbol", "clubes", "deporte"]

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category other = (Category) o;
        return this.getId() != null && this.getId().equals(other.getId());
    }
}
