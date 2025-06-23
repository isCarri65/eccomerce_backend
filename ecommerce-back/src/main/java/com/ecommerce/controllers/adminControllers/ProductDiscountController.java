package com.ecommerce.controllers.adminControllers;

import com.ecommerce.dto.ProductDiscount.CreateProductDiscountDTO;
import com.ecommerce.dto.ProductDiscount.ProductDiscountAdminDTO;
import com.ecommerce.dto.ProductDiscount.UpdateProductDiscountDTO;
import com.ecommerce.entities.ProductDiscount;
import com.ecommerce.mappers.ProductDiscountAdminMapper;
import com.ecommerce.services.ProductDiscountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/productDiscounts")
public class ProductDiscountController extends BaseController<ProductDiscount, Long, ProductDiscountAdminDTO, CreateProductDiscountDTO, UpdateProductDiscountDTO> {
    public ProductDiscountController(ProductDiscountService productDiscountService, ProductDiscountAdminMapper mapper) {
        super(productDiscountService, mapper);
    }

}
