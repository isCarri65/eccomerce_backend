package com.ecommerce.controllers.protectedControllers;

import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseDTO;
import com.ecommerce.entities.User;
import com.ecommerce.services.PurchaseOrderService;
import com.ecommerce.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/protected/purchaseOrders")
public class PurchaseOrderProtectedController {

    private final PurchaseOrderService purchaseOrderService;
    private final UserService userService;

    public PurchaseOrderProtectedController(PurchaseOrderService service, UserService userService) {
        this.purchaseOrderService = service;
        this.userService = userService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<PurchaseOrderResponseDTO>> getAll() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(purchaseOrderService.getAllByUserId(user.getId()));
    }

    /*
    @PostMapping("/create")
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody CreatePurchaseOrderDTO createPurchaseOrderDTO) throws Exception {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(purchaseOrderService.createWhitDTO(createPurchaseOrderDTO, user.getId()));
    }*/






}
