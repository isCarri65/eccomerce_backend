package com.ecommerce.services;

import com.ecommerce.dto.productVariant.CreateProductVariantDTO;
import com.ecommerce.dto.productVariant.ProductVariantAdminDTO;
import com.ecommerce.dto.productVariant.ProductVariantCartDTO;
import com.ecommerce.entities.*;
import com.ecommerce.mappers.ProductVariantAdminMapper;
import com.ecommerce.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class ProductVariantService extends BaseService<ProductVariant, Long> {
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantAdminMapper productVariantAdminMapper;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductVariantService(ProductVariantRepository productVariantRepository, ProductVariantAdminMapper productVariantAdminMapper, SizeRepository sizeRepository, ColorRepository colorRepository, ProductRepository productRepository, ProductService productService) {
        super(productVariantRepository);
        this.productVariantRepository = productVariantRepository;
        this.productVariantAdminMapper = productVariantAdminMapper;
        this.sizeRepository = sizeRepository;
        this.colorRepository = colorRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }
    public Boolean hasStockAvalibleByProducId(Long id) {
        return productVariantRepository.existsByProductIdAndQuantityGreaterThanAndStateTrue(id, 0);
    }


    public ProductVariantCartDTO getCartDTOById(Long id){
        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product variant not found"));
        ProductVariantAdminDTO adminDTO = productVariantAdminMapper.toDTO(productVariant);
        ProductVariantCartDTO cartDTO = new ProductVariantCartDTO();
        cartDTO.setId(productVariant.getId());
        cartDTO.setProductList(productService.getProductListDTO(productVariant.getProduct()));
        cartDTO.setStock(productVariant.getQuantity());

        cartDTO.setState(productVariant.getState());
        cartDTO.setColor(adminDTO.getColor());
        cartDTO.setSize(adminDTO.getSize());

        return cartDTO;
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

        productRepository.save(product);
        return productVariantAdminMapper.toDTO(variant);
    }
}
