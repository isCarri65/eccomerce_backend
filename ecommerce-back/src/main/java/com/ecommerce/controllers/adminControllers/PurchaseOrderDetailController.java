package com.ecommerce.controllers.adminControllers;

import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.services.PurchaseOrderDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/purchaseOrderDetails")
public class PurchaseOrderDetailController extends BaseController<PurchaseOrderDetail, Long> {
    public PurchaseOrderDetailController(PurchaseOrderDetailService purchaseOrderDetailService) {
        super(purchaseOrderDetailService);
    }
}
