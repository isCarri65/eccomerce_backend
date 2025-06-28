package com.ecommerce.services;

import com.ecommerce.dto.productVariant.CreateProductVariantDTO;
import com.ecommerce.dto.productVariant.ProductVariantAdminDTO;
import com.ecommerce.entities.Color;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.entities.Size;
import com.ecommerce.mappers.ProductVariantAdminMapper;
import com.ecommerce.repositories.ColorRepository;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.repositories.ProductVariantRepository;
import com.ecommerce.repositories.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ProductVariantService extends BaseService<ProductVariant, Long> {
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantAdminMapper productVariantAdminMapper;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final ProductRepository productRepository;

    public ProductVariantService(ProductVariantRepository productVariantRepository, ProductVariantAdminMapper productVariantAdminMapper, SizeRepository sizeRepository, ColorRepository colorRepository, ProductRepository productRepository) {
        super(productVariantRepository);
        this.productVariantRepository = productVariantRepository;
        this.productVariantAdminMapper = productVariantAdminMapper;
        this.sizeRepository = sizeRepository;
        this.colorRepository = colorRepository;
        this.productRepository = productRepository;
    }
    public Boolean hasStockAvalibleByProducId(Long id) {
        return productVariantRepository.existsByProductIdAndQuantityGreaterThanAndStateTrue(id, 0);
    }


    @Transactional
    public ProductVariantAdminDTO createVariant(Long productId, CreateProductVariantDTO createDTO) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        ProductVariant variant = productVariantAdminMapper.CDTOtoEntity(createDTO);

        Size size = sizeRepository.findById(createDTO.getSizeId())
                .orElseThrow(() -> new EntityNotFoundException("Size not found"));

        Color color = colorRepository.findById(createDTO.getColorId())
                .orElseThrow(() -> new EntityNotFoundException("Color not found"));

        System.out.println("color = " + color + ", size = "+size.getValue());

        // 3. Establecer relaciones con entidades REALES
        variant.setSize(size);
        variant.setColor(color);
        variant.setProduct(product);

        System.out.println("variant = " + variant.getQuantity());
        product.addVariant(variant);

        System.out.println("product = ");
        productRepository.save(product);
        System.out.println("paso 3 ");
        return productVariantAdminMapper.toDTO(variant);
    }
}
