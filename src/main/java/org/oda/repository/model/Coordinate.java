package org.oda.repository.model;

public class Coordinate {
    private String longitude;
    private String latitude;

    public Coordinate(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coordinate() {
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
