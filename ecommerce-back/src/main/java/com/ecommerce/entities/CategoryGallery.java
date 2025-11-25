package com.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallery_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CategoryGallery extends Base{
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "public_id")
    private String publicId;
    
    private String name;
    
    @Builder.Default
    @Column(name = "is_main")
    private Boolean isMain = false;
    
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
}
