package org.oda.resource.request;

import org.oda.resource.request.validation.AlreadyRegisteredDescriptionAddress;

public class AddAddressRequest {
    @AlreadyRegisteredDescriptionAddress
    private String description;
    private String address;
    private String number;
    private String complement;
    private String cep;
    private String state;
    private String city;
    private String coordinateLongitude;
    private String coordinateLatitude;

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCoordinateLongitude() {
        return coordinateLongitude;
    }

    public String getCoordinateLatitude() {
        return coordinateLatitude;
    }
}
