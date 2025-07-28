package com.ecommerce;

import com.ecommerce.entities.*;
import com.ecommerce.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class EcommerceBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceBackendApplication.class, args);
        System.out.println("Ecommerce Backend Application Started");
    }
    /*
    @Bean
    public CommandLineRunner run(
            CategoryRepository categoryRepository,
            TypeRepository typeRepository,
            SizeRepository sizeRepository,
            ColorRepository colorRepository,
            ProductRepository productRepository,
            ProductVariantRepository productVariantRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            PurchaseOrderDetailRepository purchaseOrderDetailRepository
    ) {
        return args -> {


            // Crear 3 tipos
            Type type1 = Type.builder().name("Ropa").build();
            Type type2 = Type.builder().name("Calzado").build();
            Type type3 = Type.builder().name("Accesorios").build();
            typeRepository.saveAll(List.of(type1, type2, type3));

            // Crear 3 categorías
            Category category1 = Category.builder()
                    .name("Remeras")
                    .imageUrl("https://example.com/remeras.jpg")
                    .publicId("cat1")
                    .type(type1)
                    .build();

            Category category2 = Category.builder()
                    .name("Zapatillas")
                    .imageUrl("https://example.com/zapatillas.jpg")
                    .publicId("cat2")
                    .type(type2)
                    .build();

            Category category3 = Category.builder()
                    .name("Gorras")
                    .imageUrl("https://example.com/gorras.jpg")
                    .publicId("cat3")
                    .type(type3)
                    .build();

            categoryRepository.saveAll(List.of(category1, category2, category3));

            // Crear 3 colores
            Color color1 = Color.builder().name("Rojo").build();
            Color color2 = Color.builder().name("Azul").build();
            Color color3 = Color.builder().name("Verde").build();
            colorRepository.saveAll(List.of(color1, color2, color3));

            // Crear 3 talles
            Size size1 = Size.builder().value("S").sizeType(SizeTypeENUM.LETTER).build();
            Size size2 = Size.builder().value("M").sizeType(SizeTypeENUM.LETTER).build();
            Size size3 = Size.builder().value("42").sizeType(SizeTypeENUM.NUMBER).build();
            sizeRepository.saveAll(List.of(size1, size2, size3));

            // Crear 6 productos, cada uno con 3 variantes (color y talle)
            List<Product> productos = List.of(
                    Product.builder().name("Remera Blanca").description("Remera blanca de algodón").sellPrice(new BigDecimal("5000")).state(true).genre(ProductGenreENUM.UNISEX).categories(Set.of(category1)).build(),
                    Product.builder().name("Zapatillas Urbanas").description("Zapatillas cómodas para uso diario").sellPrice(new BigDecimal("15000")).state(true).genre(ProductGenreENUM.MALE).categories(Set.of(category2)).build(),
                    Product.builder().name("Gorra Estampada").description("Gorra con diseño moderno").sellPrice(new BigDecimal("3500")).state(true).genre(ProductGenreENUM.FEMALE).categories(Set.of(category3)).build(),
                    Product.builder().name("Campera Invierno").description("Campera abrigada para el frío").sellPrice(new BigDecimal("25000")).state(true).genre(ProductGenreENUM.UNISEX).categories(Set.of(category1)).build(),
                    Product.builder().name("Botines Fútbol").description("Botines para césped natural").sellPrice(new BigDecimal("20000")).state(true).genre(ProductGenreENUM.MALE).categories(Set.of(category2)).build(),
                    Product.builder().name("Mochila Urbana").description("Mochila resistente y con varios compartimentos").sellPrice(new BigDecimal("10000")).state(true).genre(ProductGenreENUM.UNISEX).categories(Set.of(category3)).build()
            );
            productRepository.saveAll(productos);

            List<ProductVariant> variantes = new ArrayList<>();
            for (Product product : productos) {
                variantes.add(ProductVariant.builder().product(product).size(size1).color(color1).quantity(10).state(true).build());
                variantes.add(ProductVariant.builder().product(product).size(size2).color(color2).quantity(8).state(true).build());
                variantes.add(ProductVariant.builder().product(product).size(size3).color(color3).quantity(5).state(true).build());
            }
            productVariantRepository.saveAll(variantes);

            System.out.println("Productos y variantes creados exitosamente.");
        };

    }

    */


}
