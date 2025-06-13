package com.ecommerce.mappers;

import com.ecommerce.dto.Address.AddressDTO;
import com.ecommerce.dto.PurchaseOrder.PurchaseOrderResponseDTO;
import com.ecommerce.dto.PurchaseOrderDetail.PurchaseOrderDetailDTO;
import com.ecommerce.dto.User.UserDTO;
import com.ecommerce.entities.Address;
import com.ecommerce.entities.PurchaseOrder;
import com.ecommerce.entities.PurchaseOrderDetail;
import com.ecommerce.entities.User;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderMapper {
    public static PurchaseOrderResponseDTO toDTO(PurchaseOrder order, List<PurchaseOrderDetail> details) {
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
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setBirthDate(user.getBirthDate());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            dto.setUser(userDTO);
        }

        // Map Address
        Address address = order.getAddress();
        if (address != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(address.getStreet());
            addressDTO.setNumber(address.getNumber());
            addressDTO.setApartment(address.getApartment());
            addressDTO.setAptNumberAndFloor(address.getAptNumberAndFloor());
            addressDTO.setProvince(address.getProvince());
            addressDTO.setLocality(address.getLocality());
            addressDTO.setPostal(address.getPostal());
            addressDTO.setUserId(address.getUser().getId());
            dto.setAddress(addressDTO);
        }

        List<PurchaseOrderDetailDTO> detailsDTO = new ArrayList<>();

        for (PurchaseOrderDetail detail : details) {
            PurchaseOrderDetailDTO detailDTO = new PurchaseOrderDetailDTO();
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
}