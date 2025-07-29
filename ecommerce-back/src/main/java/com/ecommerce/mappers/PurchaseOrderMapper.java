package com.ecommerce.mappers;

import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseDTO;
import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseFullDTO;
import com.ecommerce.dto.PurchaseOrderDetail.PurchaseOrderDetailDTO;
import com.ecommerce.dto.PurchaseOrderDetail.PurchaseOrderDetailFullDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PurchaseOrderMapper {
    private final AddressMapper addressMapper;
    private final UserProfileMapper userProfileMapper;
    private final ProductVariantMapper productVariantMapper;
    private final ProductMapper productMapper;

    public PurchaseOrderResponseDTO toDTO(PurchaseOrder order, List<PurchaseOrderDetail> details) {
        if (order == null) return null;

        PurchaseOrderResponseDTO dto = new PurchaseOrderResponseDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setFinalPrice(order.getFinalPrice());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setState(order.getState());

        // Map User
        User user = order.getUser();
        if (user != null) {
            dto.setUser(userProfileMapper.toDTO(user));
        }
        Address address = order.getAddress();
        if (address != null) {
            dto.setAddress(addressMapper.toDTO(address));
        }



        List<PurchaseOrderDetailDTO> detailsDTO = new ArrayList<>();

        for (PurchaseOrderDetail detail : details) {
            PurchaseOrderDetailDTO detailDTO = new PurchaseOrderDetailDTO();
            detailDTO.setId(detail.getId());
            detailDTO.setQuantity(detail.getQuantity());
            detailDTO.setUnitPrice(detail.getUnitPrice());
            detailDTO.setTotalPrice(detail.getTotalPrice());
            detailDTO.setProductVariantId(detail.getProductVariant().getId());
            detailDTO.setDiscountId(detail.getDiscount() != null ? detail.getDiscount().getId() : null);
            detailsDTO.add(detailDTO);
        }
        dto.setDetails(detailsDTO);

        return dto;
    }
    public PurchaseOrderResponseFullDTO toFullDTO(PurchaseOrder order, List<PurchaseOrderDetail> details) {
        if (order == null) return null;

        PurchaseOrderResponseFullDTO dto = new PurchaseOrderResponseFullDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setFinalPrice(order.getFinalPrice());
        dto.setPaymentMethod(order.getPaymentMethod());

        // Map User
        User user = order.getUser();
        if (user != null) {
            dto.setUser(userProfileMapper.toDTO(user));
        }
        Address address = order.getAddress();
        if (address != null) {
            dto.setAddress(addressMapper.toDTO(address));
        }



        List<PurchaseOrderDetailFullDTO> detailsDTO = new ArrayList<>();

        for (PurchaseOrderDetail detail : details) {
            PurchaseOrderDetailFullDTO detailDTO = new PurchaseOrderDetailFullDTO();
            detailDTO.setOrderId(order.getId());
            detailDTO.setId(detail.getId());
            detailDTO.setQuantity(detail.getQuantity());
            detailDTO.setUnitPrice(detail.getUnitPrice());
            detailDTO.setTotalPrice(detail.getTotalPrice());

            if (detail.getProductVariant() != null) {
                detailDTO.setProductVariantDTO(productVariantMapper.toDTO(detail.getProductVariant()));
                if (detail.getProductVariant().getProduct() != null) {
                    detailDTO.setProductDTO(productMapper.toDTO(detail.getProductVariant().getProduct()));
                }
            }

            detailDTO.setDiscountId(detail.getDiscount() != null ? detail.getDiscount().getId() : null);
            detailsDTO.add(detailDTO);
        }
        dto.setDetails(detailsDTO);

        return dto;
    }
}