package com.hoopie.joao.hoopie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityPOJO {

    @SerializedName("ages")
    @Expose
    private String ages;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("neighbourhood")
    @Expose
    private String neighbourhood;
    @SerializedName("placeName")
    @Expose
    private String placeName;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private Address address;

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean equals(ActivityPOJO activityPOJO) {
        return getAges().equals(activityPOJO.getAges()) &&
                getCategory().equals(activityPOJO.getCategory()) &&
                getEndDate().equals(activityPOJO.getEndDate()) &&
                getId().equals(activityPOJO.getId()) &&
                getImageURL().equals(activityPOJO.getImageURL()) &&
                getName().equals(activityPOJO.getName()) &&
                getNeighbourhood().equals(activityPOJO.getNeighbourhood()) &&
                getPlaceName().equals(activityPOJO.getPlaceName()) &&
                getStartDate().equals(activityPOJO.getStartDate()) &&
                getDescription().equals(activityPOJO.getDescription()) &&
                getAddress().equals(activityPOJO.getAddress());
    }

    public class Address {

        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("latitude")
        @Expose
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;
        @SerializedName("postcode")
        @Expose
        private String postcode;
        @SerializedName("streetName")
        @Expose
        private String streetName;
        @SerializedName("streetNumber")
        @Expose
        private String streetNumber;
        @SerializedName("town")
        @Expose
        private String town;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getStreetNumber() {
            return streetNumber;
        }

        public void setStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public boolean equals(Address address) {
            return getCountry().equals(address.getCountry()) &&
                    getLatitude().equals(address.getLatitude()) &&
                    getLatitude().equals(address.getLongitude()) &&
                    getPostcode().equals(address.getPostcode()) &&
                    getStreetName().equals(address.getStreetName()) &&
                    getStreetNumber().equals(address.getStreetNumber()) &&
                    getTown().equals(address.getTown());
        }

    }

}
