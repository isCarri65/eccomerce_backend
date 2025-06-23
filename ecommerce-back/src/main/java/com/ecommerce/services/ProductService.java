package com.ecommerce.services;

import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;

import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.Product.ProductListDTO;
import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGallery;
import com.ecommerce.mappers.ProductAdminMapper;
import com.ecommerce.mappers.ProductGalleryAdminMapper;

import com.ecommerce.mappers.ProductGalleryMapper;
import com.ecommerce.mappers.ProductMapper;
import com.ecommerce.repositories.ProductGalleryRepository;
import com.ecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService extends BaseService<Product, Long>{
    private final ProductRepository productRepository;
    private final ProductAdminMapper productAdminMapper;
    private final DiscountService discountService;
    private final ProductMapper productMapper;
    private final ProductGalleryService productGalleryService;
    private final ProductVariantService productVariantService;

    public ProductService(ProductRepository productRepository,
                          ProductAdminMapper productAdminMapper,
                          DiscountService discountService,
                          ProductMapper productMapper,
                          ProductGalleryService productGalleryService,
                          ProductVariantService productVariantService) {
        super(productRepository);
        this.productRepository = productRepository;
        this.productAdminMapper = productAdminMapper;
        this.discountService = discountService;
        this.productMapper = productMapper;
        this.productGalleryService = productGalleryService;
        this.productVariantService = productVariantService;
    }

    public ProductDTO getProductPublicById(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product not found"));
        List<ProductGallery> galleries = productGalleryService.getAllByProductId(productId);
        ProductDTO productDTO = productMapper.toDTO(product);
        productDTO.setProductGalleries(productGalleryService.listEntityToDTO(galleries));
        return productDTO;
    }

    public ProductAdminDTO createProductWhitGalleries(CreateProductDTO createProductDTO) {
        Product product = productRepository.save(productAdminMapper.CDTOtoEntity(createProductDTO));
        List<CreateProductGalleryDTO> productGalleryDTOS = createProductDTO.getProductGalleries();
        for (CreateProductGalleryDTO createProductGalleryDTO : productGalleryDTOS) {
            createProductGalleryDTO.setProductId(product.getId());
        }
        productGalleryService.createEntitiesWhitDTOS(productGalleryDTOS);
        return productAdminMapper.toDTO(product);
    }

    public ProductListDTO getProductListDTO(Product product) {
        ProductListDTO dto = new ProductListDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setGenre(String.valueOf(product.getGenre()));
        dto.setStockAvailable(productVariantService.hasStockAvalibleByProducId(product.getId()));

        Optional<ProductGallery> productGallery = productGalleryService.findProductGalleryMainByProductId(product.getId());
        productGallery.ifPresent(gallery -> dto.setImageUrl(gallery.getImageUrl()));
        BigDecimal basePrice = product.getSellPrice();

        Optional<DiscountRule> discountOpt = discountService.getBestApplicableDiscount(product);

        if (discountOpt.isPresent()) {
            DiscountRule discount = discountOpt.get();
            BigDecimal discountAmount = basePrice.multiply(BigDecimal.valueOf(discount.getPercentage() / 100));
            dto.setPrice(basePrice.subtract(discountAmount));
            dto.setOriginalPrice(basePrice);
            dto.setDiscountPercentage(discount.getPercentage());
        } else {
            dto.setPrice(basePrice);
            dto.setOriginalPrice(null);
            dto.setDiscountPercentage(null);
        }

        return dto;
    }
    // En ProductService:

}
