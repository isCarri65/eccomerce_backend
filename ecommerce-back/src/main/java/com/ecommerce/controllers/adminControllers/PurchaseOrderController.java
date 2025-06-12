package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.services.PurchaseOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/purchaseOrders")
public class PurchaseOrderController extends BaseController<PurchaseOrder, Long> {
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        super(purchaseOrderService);
    }
}
