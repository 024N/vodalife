package com.vodafone.vodalife.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recycling_points") // MongoDB'de "recycling_points" koleksiyonunda saklanacak
public class RecyclingPoint {

    @Id
    private String id; // MongoDB'nin otomatik oluşturduğu ID
    private String name;
    private Coordinate coordinate;
    private String phone;
    private String address;
    private String description;
    private String workingHours;
    private List<String> acceptedItems;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public List<String> getAcceptedItems() {
        return acceptedItems;
    }

    public void setAcceptedItems(List<String> acceptedItems) {
        this.acceptedItems = acceptedItems;
    }

    public static class Coordinate {
        private double latitude;
        private double longitude;

        // Getters and Setters
        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}