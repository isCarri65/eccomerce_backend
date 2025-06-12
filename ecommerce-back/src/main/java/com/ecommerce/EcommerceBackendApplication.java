package com.ecommerce;

import com.ecommerce.entities.*;
import com.ecommerce.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EcommerceBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceBackendApplication.class, args);
        System.out.println("Ecommerce Backend Application Started");
    }
    @Bean
    public CommandLineRunner run(ProductRepository productRepository,
                                 CategoryRepository categoryRepository,
                                 TypeRepository typeRepository,
                                 UserRepository userRepository,
                                 AddressRepository addressRepository,
                                 PurchaseOrderRepository purchaseOrderRepository,
                                 PurchaseOrderDetailRepository purchaseOrderDetailRepository,
                                 ProductVariantRepository productVariantRepository,
                                 SizeRepository sizeRepository,
                                 ColorRepository colorRepository,
                                 DiscountRepository discountRepository) {
        return args -> {

            // 1. Crear los tipos si querés
            Type tipo1 = new Type();
            tipo1.setName("Camisa");
            typeRepository.save(tipo1);

            // 2. Crear la galería
            Gallery gallery = new Gallery();
            gallery.setImage("https://example.com/image.jpg");

            // 3. Crear una categoría
            Category category = new Category();
            category.setName("Ropa Masculina");
            category.setGallery(gallery);
            category.getTypes().add(tipo1); // asociar tipo

            // Guardar la categoría (cascade = ALL guarda también gallery y types)
            categoryRepository.save(category);

            // 4. Crear el set de categorías
            Set<Category> categorias = new HashSet<>();
            categorias.add(category);

            // 5. Crear el producto
            Product producto = new Product(
                    "Remera Básica",
                    100.0,
                    150.0,
                    "Remera de algodón básica para hombre",
                    true,
                    ProductGenreENUM.MALE,
                    categorias
            );
            Product producto2 = new Product(
                    "Compleja la cosa",
                    200.0,
                    350.0,
                    "ejemplo",
                    true,
                    ProductGenreENUM.MALE,
                    categorias
            );

            // 6. Guardar el producto
            productRepository.save(producto);
            productRepository.save(producto2);
            // Crear un usuario
            User user = User.builder()
                    .name("Juan")
                    .lastName("Pérez")
                    .email("juan@example.com")
                    .password("123456") // recordá hashearla si usás login
                    .role(Role.USER)
                    .birthDate(LocalDate.of(1990, 5, 20))
                    .build();
            userRepository.save(user);

            // Crear una dirección
            Address address = Address.builder()
                    .street("Av. Siempre Viva")
                    .number(742)
                    .apartment("A")
                    .aptNumberAndFloor("1A")
                    .province("Buenos Aires")
                    .locality("CABA")
                    .postal("1000")
                    .user(user)
                    .build();
            addressRepository.save(address);
            // Crear talles
            Size size = new Size();
            size.setName("M");
            sizeRepository.save(size);

// Crear colores
            Color color = new Color();
            color.setName("Rojo");
            colorRepository.save(color);

// Crear ProductVariant
            ProductVariant variant = ProductVariant.builder()
                    .product(producto)
                    .size(size)
                    .color(color)
                    .quantity(10)
                    .state(true)
                    .build();
            productVariantRepository.save(variant);

// Crear Discount
            Discount discount = Discount.builder()
                    .percentage(20.0)
                    .startDate(LocalDate.now().minusDays(1))
                    .endDate(LocalDate.now().plusDays(10))
                    .state(true)
                    .build();
            discountRepository.save(discount);

        };
    }

}
