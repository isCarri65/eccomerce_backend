package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.entities.User;
import com.ecommerce.services.PurchaseOrderDetailService;
import com.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/protected/purchaseOrderDetails")
public class PurchaseOrderDetailProtectedController {

    private final PurchaseOrderDetailService purchaseOrderDetailService;
    private final UserService userService;

    public PurchaseOrderDetailProtectedController(PurchaseOrderDetailService service, UserService userService) {
        this.purchaseOrderDetailService = service;
        this.userService = userService;
    }


    @GetMapping("/getAllByOrderId/{id}")
    public ResponseEntity<List<PurchaseOrderDetail>> getAllByOrderId(@PathVariable Long id) throws Exception {
        User user = userService.getCurrentUser();

        return ResponseEntity.ok(purchaseOrderDetailService.getAllByOrderId(id, user.getId()));
    }

    /*
    @PostMapping("/create")
    public ResponseEntity<PurchaseOrderDetailDTO> createPurchaseOrderDetail(@RequestBody CreatePurchaseOrderDetailDTO createPurchaseOrderDetailDTO) throws Exception {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(purchaseOrderDetailService.createWhitDTO(createPurchaseOrderDetailDTO, user.getId()));
    }*/


}