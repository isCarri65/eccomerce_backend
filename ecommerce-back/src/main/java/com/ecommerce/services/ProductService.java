package com.ecommerce.services;

import com.ecommerce.dto.Product.CreateProductDTO;
import com.ecommerce.dto.Product.ProductAdminDTO;
import com.ecommerce.dto.Product.ProductDTO;
import com.ecommerce.dto.Product.ProductListDTO;
import com.ecommerce.dto.Product.UpdateProductDTO;
import com.ecommerce.dto.ProductFilterDTO;
import com.ecommerce.dto.ProductGallery.CreateProductGalleryDTO;
import com.ecommerce.dto.productVariant.ProductVariantDTO;
import com.ecommerce.dto.FileUploadResponse;
import com.ecommerce.entities.DiscountRule;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.ProductGallery;
import com.ecommerce.entities.ProductVariant;
import com.ecommerce.mappers.*;
import org.springframework.web.multipart.MultipartFile;


import com.ecommerce.repositories.ProductRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class ProductService extends BaseService<Product, Long> {
    private final ProductRepository productRepository;
    private final ProductAdminMapper productAdminMapper;
    private final DiscountService discountService;
    private final ProductMapper productMapper;
    private final ProductGalleryService productGalleryService;
    private final ProductVariantMapper productVariantMapper;
    private final ProductPricingCalculator productPricingCalculator;
    private final FileUploadService fileUploadService;
    private final CategoryMapper categoryMapper;

    public ProductService(ProductRepository productRepository,
                          ProductAdminMapper productAdminMapper,
                          DiscountService discountService,
                          ProductMapper productMapper,
                          ProductGalleryService productGalleryService,
                          CategoryMapper categoryMapper,
                          ProductVariantMapper productVariantMapper,
                          ProductPricingCalculator productPricingCalculator,
                          FileUploadService fileUploadService) {
        super(productRepository);
        this.productRepository = productRepository;
        this.productAdminMapper = productAdminMapper;
        this.discountService = discountService;
        this.productMapper = productMapper;
        this.productGalleryService = productGalleryService;
        this.categoryMapper = categoryMapper;
        this.productVariantMapper = productVariantMapper;
        this.productPricingCalculator = productPricingCalculator;
        this.fileUploadService = fileUploadService;
    }

    @Transactional(readOnly = true)
    public List<ProductVariantDTO> getVariantsByProductId(Long productId) {
        Product product = productRepository.safeFindByIdWithVariants(productId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        return product.getProductVariants().stream()
                .filter(ProductVariant::getState) // solo activos
                .map(productVariantMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductPublicById(Long productId) {


        Product product = productRepository.safeFindByIdWithVariants(productId).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        System.out.println(product.getProductVariants().size());
        List<ProductGallery> galleries = productGalleryService.getAllByProductId(productId);
        ProductDTO productDTO = productMapper.toDTO(product);
        productDTO.setProductGalleries(productGalleryService.listEntityToDTO(galleries));

        Optional<DiscountRule> discountOpt = discountService.getBestApplicableDiscount(product);
        BigDecimal basePrice = product.getSellPrice();
        if (discountOpt.isPresent()) {
            DiscountRule discount = discountOpt.get();
            productDTO.setDiscountPercentage(discount.getPercentage());
            productDTO.setOriginalPrice(product.getSellPrice());

            BigDecimal discountAmount = basePrice.multiply((discount.getPercentage()));
            productDTO.setPrice(basePrice.subtract(discountAmount));

        } else {
            productDTO.setPrice(basePrice);
            productDTO.setOriginalPrice(null);
            productDTO.setDiscountPercentage(null);
        }
        return productDTO;
    }

    @Transactional(readOnly = true)
    public ProductAdminDTO findProductAdminById(Long id, Function<Product, ProductAdminDTO> mapper) {
        Product entity = baseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada por su id: " + id));
        ProductAdminDTO productAdminDTO = mapper.apply(entity);
        List<ProductGallery> galleries = productGalleryService.getAllByProductId(id);
        productAdminDTO.setProductGalleries(productGalleryService.listEntityToDTO(galleries));
        return productAdminDTO;
    }

    public ProductAdminDTO createProductWhitGalleries(CreateProductDTO createProductDTO) {
        // Este método es para compatibilidad con el sistema existente
        // Ahora usa el nuevo método createProductWithImages
        return createProductWithImages(createProductDTO);
    }

    @Transactional
    public ProductDTO getProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getSellPrice());
        dto.setOriginalPrice(product.getSellPrice());
        //dto.setDiscountPercentage(productPricingCalculator.updateCalculatedAtributes(product));
        dto.setBuyPrice(product.getBuyPrice());
        dto.setSellPrice(product.getSellPrice());
        dto.setFinalPrice(product.getFinalPrice());
        dto.setStockAvailable(product.getTotalStock() > 0);
        dto.setCategories(product.getCategories().stream().map(categoryMapper::toDTO).collect(Collectors.toSet()));
        dto.setProductGalleries(productGalleryService.listEntityToDTO(productGalleryService.getAllByProductId(product.getId())));
        dto.setProductVariants(product.getProductVariants().stream().map(productVariantMapper::toDTO).collect(Collectors.toList()));
    
        dto.setDescription(product.getDescription());
        dto.setGenre(product.getGenre() != null ? product.getGenre().name() : null);
        return dto;
    }

    @Transactional
    public ProductListDTO getProductListDTO(Product product) {
        ProductListDTO dto = new ProductListDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setGenre(String.valueOf(product.getGenre()));
        dto.setStockAvailable(product.getTotalStock() > 0);

        dto.setCategories(product.getCategories().stream().map(categoryMapper::toDTO).collect(Collectors.toSet()));

        Optional<ProductGallery> productGallery = productGalleryService.findProductGalleryMainByProductId(product.getId());
        productGallery.ifPresent(gallery -> dto.setImageUrl(gallery.getImageUrl()));
        BigDecimal basePrice = product.getSellPrice();

        Optional<DiscountRule> discountOpt = discountService.getBestApplicableDiscount(product);

        if (discountOpt.isPresent()) {
            DiscountRule discount = discountOpt.get();
            BigDecimal discountAmount = basePrice.multiply((discount.getPercentage()));
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


    public Page<Product> getFilteredProducts(ProductFilterDTO filter, Pageable pageable) {
        List<Long> categoryIds = filter.getCategoryIds();

        // Limitar la cantidad de categorías a 3 si se pasa más
        if (categoryIds != null && categoryIds.size() > 10) {
            categoryIds = categoryIds.subList(0, 10);
        }
        System.out.println(filter.getGenre());
        return productRepository.findFilteredProducts(
                filter.getGenre(),
                filter.getMinPrice(),
                filter.getMaxPrice(),
                filter.getSizeId(),
                filter.getColorId(),
                categoryIds,
                categoryIds != null ? categoryIds.size() : 0L,
                filter.getTypeId(),
                filter.getSearchTerm(),
                pageable
        );
        // En ProductService:

    }

    public Page<Product> searchProductsByName(String name, Pageable pageable) {
        return productRepository.searchByNameOrDescription(name, pageable );
    }

    @Override
    @Transactional
    public Product create(Product entity) {
        super.create(entity); // acá Hibernate ya asigna createdAt
    
        productPricingCalculator.updateCalculatedAtributes(entity);
        super.update(entity.getId(), entity); // volvés a guardar los valores calculados
    
        return entity;
    }

    @Override
    @Transactional
    public Product update(Long id, Product entity) {
        if (!baseRepository.existsById(id)) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        entity.setId(id);
        productPricingCalculator.updateCalculatedAtributes(entity);
        return super.update(id, entity);
    }

    @Override
    @Transactional
    public <EntityDTO, CreateDTO, UpdateDTO> EntityDTO create(
            CreateDTO dto,
            BaseAdminMapper<Product, EntityDTO, CreateDTO, UpdateDTO> mapper) {

        Product entity = mapper.CDTOtoEntity(dto);
        productPricingCalculator.updateCalculatedAtributes(entity);
        Product saved = baseRepository.save(entity);
        return mapper.toDTO(saved);
    }
    @Override
    @Transactional
    public <EntityDTO, CreateDTO, UpdateDTO> EntityDTO update(Long id, UpdateDTO dto, BaseAdminMapper<Product, EntityDTO, CreateDTO, UpdateDTO> mapper) {
        Product entity = baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidad no encontrada para actualizar"));

        //actualizamos la entidad
        mapper.UDTOtoEntity(dto, entity);
        productPricingCalculator.updateCalculatedAtributes(entity);
        return mapper.toDTO(baseRepository.save(entity));
    }

    @Transactional
    public ProductAdminDTO createProductWithImages(CreateProductDTO dto) {
        // Crear el producto primero
        Product product = productAdminMapper.CDTOtoEntity(dto);
        Product savedProduct = baseRepository.save(product);
        productPricingCalculator.updateCalculatedAtributes(product);
        savedProduct = baseRepository.save(savedProduct);
        
        // Procesar imágenes si existen
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            processProductImages(savedProduct.getId(), dto.getImages(), dto.getImageNames(), dto.getIsMainFlags());
        }
        
        return productAdminMapper.toDTO(savedProduct);
    }
    
    @Transactional
    public ProductAdminDTO updateProductWithImages(Long id, UpdateProductDTO dto) {
        Product product = baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
        
        // Actualizar el producto
        productAdminMapper.UDTOtoEntity(dto, product);
        Product savedProduct = baseRepository.save(product);
        productPricingCalculator.updateCalculatedAtributes(product);
        savedProduct = baseRepository.save(savedProduct);
        
        // Eliminar imágenes marcadas para eliminación
        if (dto.getImagesToDelete() != null && !dto.getImagesToDelete().isEmpty()) {
            deleteProductImages(dto.getImagesToDelete());
        }
        
        // Procesar nuevas imágenes si existen
        if (dto.getImages() != null && !dto.getImages().isEmpty()) {
            processProductImages(savedProduct.getId(), dto.getImages(), dto.getImageNames(), dto.getIsMainFlags());
        }
        
        return productAdminMapper.toDTO(savedProduct);
    }
    
    private void processProductImages(Long productId, List<MultipartFile> images, List<String> imageNames, List<Boolean> isMainFlags) {
        for (int i = 0; i < images.size(); i++) {
            MultipartFile image = images.get(i);
            String imageName = (imageNames != null && i < imageNames.size()) ? imageNames.get(i) : image.getOriginalFilename();
            Boolean isMain = (isMainFlags != null && i < isMainFlags.size()) ? isMainFlags.get(i) : false;
            
            // Subir imagen a Cloudinary
            FileUploadResponse uploadResponse = fileUploadService.uploadFile(image, "products");
            
            // Crear ProductGallery
            ProductGallery gallery = ProductGallery.builder()
                    .imageUrl(uploadResponse.getImageUrl())
                    .publicId(uploadResponse.getPublicId())
                    .name(imageName)
                    .isMain(isMain)
                    .product(productRepository.findById(productId).orElseThrow())
                    .build();
            
            // Si es imagen principal, desmarcar las otras
            ProductGallery gallerySaved = productGalleryService.create(gallery);

            if (isMain) {
                productGalleryService.setAsMainImage(gallerySaved.getId(), productId);
            }

            
            productGalleryService.create(gallery);
        }
    }
    
    private void deleteProductImages(List<Long> imageIds) {
        for (Long imageId : imageIds) {
            ProductGallery gallery = productGalleryService.findById(imageId);
            if (gallery != null) {
                // Eliminar de Cloudinary
                fileUploadService.deleteFile(gallery.getPublicId());
                // Eliminar de base de datos
                productGalleryService.reallyDelete(imageId);
            }
        }
    }

}
