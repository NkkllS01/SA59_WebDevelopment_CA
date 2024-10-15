package sg.edu.nus.ophone.model;

import jakarta.validation.constraints.NotBlank;

public class ShipRequest {
    private String name;
    private String email;
    @NotBlank(message = "Please enter your address.")
    private String address;
    @NotBlank(message = "Please enter the city you reside in.")
    private String city;
    @NotBlank(message = "Please enter your postal code.")
    private String postalCode;

    public ShipRequest() {}

    public ShipRequest(String address, String city, String postalCode) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
