package com.ecommerce.controllers;
import com.ecommerce.dto.CompraRequestDTO;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.services.PurchaseOrderDetailService;
import com.ecommerce.services.PurchaseOrderService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.resources.preference.Preference;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class MercadoPagoController {
    private final PurchaseOrderDetailService purchaseOrderDetailService;

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;

    @PostMapping("/mp")
    @CrossOrigin("*")
    public ResponseEntity<String> mp(@RequestBody CompraRequestDTO body) throws Exception {
        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

        List<PurchaseOrderDetail> detalles = purchaseOrderDetailService.generarOrdenCompra(body.getProductos());

        List<PreferenceItemRequest> items = new ArrayList<>();
        for (PurchaseOrderDetail detalle : detalles) {
            Product producto = detalle.getProductVariant().getProduct();
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .id(producto.getId().toString())
                    .title(producto.getName())
                    .description(producto.getDescription())
                    .quantity(detalle.getQuantity())
                    .currencyId("ARS")
                    .unitPrice(BigDecimal.valueOf(detalle.getUnitPrice()))
                    .build();
            items.add(item);
        }

        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("https://localhost:5173/paymentSuccess")
                .pending("https://localhost:5173/")
                .failure("https://localhost:5173/paymentFailure")
                .build();

        List<PreferencePaymentTypeRequest> excludedPaymentTypes = List.of(
                PreferencePaymentTypeRequest.builder().id("ticket").build()
        );

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                .excludedPaymentTypes(excludedPaymentTypes)
                .installments(1)
                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(backUrls)
                .paymentMethods(paymentMethods)
                .autoReturn("approved")
                .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(preferenceRequest);

        return ResponseEntity.ok("{\"preferenceId\":\"" + preference.getId() + "\"}");
    }
}
