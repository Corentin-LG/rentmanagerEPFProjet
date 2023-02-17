package com.epf.rentmanager.model;

import java.util.Objects;

public class Vehicle {
    private long id;
    private String constructor;
    private String model;
    private int placesNumber;

    public Vehicle(long id, String constructor, String model, int placesNumber) {
        this.id = id;
        this.constructor = constructor;
        this.model = model;
        this.placesNumber = placesNumber;
    }
    public Vehicle() {

    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructor='" + constructor + '\'' +
                ", model='" + model + '\'' +
                ", placesNumber=" + placesNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && placesNumber == vehicle.placesNumber && Objects.equals(constructor, vehicle.constructor) && Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, constructor, model, placesNumber);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConstructor() {
        return constructor;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }
}
