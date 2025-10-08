package com.ecommerce.controllers;
import com.ecommerce.dto.CompraRequestDTO;
import com.ecommerce.dto.CompraResponseDTO;
import com.ecommerce.entities.*;
import com.ecommerce.services.AddressService;
import com.ecommerce.services.PurchaseOrderDetailService;
import com.ecommerce.services.PurchaseOrderService;
import com.ecommerce.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPException;
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
    private final UserService userService;
    private final AddressService addressService;

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;

    @PostMapping("/mp")
    @CrossOrigin("*")
    public ResponseEntity<String> mp(@RequestBody CompraRequestDTO body) throws Exception {
        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        User user = userService.getCurrentUser();
        Address address = addressService.findById(body.getIdAddres());

        CompraResponseDTO compra = purchaseOrderDetailService.generarOrdenCompra(body.getProductos(), user, address);
        List<PurchaseOrderDetail> detalles = compra.getDetalles();
        Long orderId = compra.getOrderId();

        List<PreferenceItemRequest> items = new ArrayList<>();
        for (PurchaseOrderDetail detalle : detalles) {
            Product producto = detalle.getProductVariant().getProduct();
            PreferenceItemRequest item = PreferenceItemRequest.builder()
                    .id(detalle.getId().toString())
                    .title(producto.getName())
                    .description(producto.getDescription())
                    .quantity(detalle.getQuantity())
                    .currencyId("ARS")
                    .unitPrice(detalle.getUnitPrice())
                    .build();
            items.add(item);
        }
        //MALA PRACTICA DE PROGRAMACIÓN LAS URL SIEMPRE VAN EN ENV O ARCHIVO CONFIG
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("http://localhost:5173/paymentSuccess")
                .pending("http://localhost:5173/")
                .failure("http://localhost:5173/paymentFailure")
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
                    .externalReference(orderId.toString())
                    .build();
            System.out.println(new ObjectMapper().writeValueAsString(preferenceRequest));

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);
            return ResponseEntity.ok("{\"preferenceId\":\"" + preference.getId() + "\"}");

    }
}
