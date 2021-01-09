package tr.com.burakgul.mokapi.dto.response;

import tr.com.burakgul.mokapi.model.Address;
import tr.com.burakgul.mokapi.model.User;

import java.util.List;

public class AddressListResponse {
    private String userId;
    private List<Address> addresses;

    public void setAddressListByUserId(User user){
        this.userId = user.getId();
        this.addresses = user.getAddresses();
    }
}
