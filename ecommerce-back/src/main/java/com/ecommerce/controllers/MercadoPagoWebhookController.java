package com.ecommerce.controllers;

import com.ecommerce.services.PurchaseOrderService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class MercadoPagoWebhookController {

    private final PurchaseOrderService purchaseOrderService;

    @Value("${mercadopago.access-token}")
    private String mercadoPagoAccessToken;

    @PostMapping
    public ResponseEntity<String> receiveNotification(@RequestParam Map<String, String> queryParams) {
        try {
            String type = queryParams.get("type");
            if (!"payment".equals(type)) {
                return ResponseEntity.ok("No es un pago, ignorado.");
            }

            String paymentId = queryParams.get("data.id");
            if (paymentId == null) {
                return ResponseEntity.badRequest().body("Falta data.id");
            }

            // Configurar acceso a MercadoPago
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);

            // Obtener información del pago desde MP
            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(Long.parseLong(paymentId));

            if ("approved".equals(payment.getStatus())) {
                String externalReference = payment.getExternalReference(); // ID externo que vos podés guardar (ej: ID de orden)

                // Actualizar orden como pagada
                purchaseOrderService.marcarComoPagada(Long.parseLong(externalReference));
            }

            return ResponseEntity.ok("Notificación procesada");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el webhook");
        }
    }

}
