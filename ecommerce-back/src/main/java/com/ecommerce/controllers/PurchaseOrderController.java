package com.ecommerce.controllers;

import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.services.PurchaseOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchaseOrders")
public class PurchaseOrderController extends BaseController<PurchaseOrder, Long> {
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        super(purchaseOrderService);
    }
}