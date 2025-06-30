package com.ecommerce.controllers.adminControllers;

        import com.ecommerce.entities.Address;
        import com.ecommerce.services.AddressService;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.List;

@RestController
@RequestMapping("/api/admin/addresses")
public class AddressController extends BaseController<Address, Long> {
    AddressService addressService;
    public AddressController(AddressService addressService){
        super(addressService);
        this.addressService = addressService;
    }

}
