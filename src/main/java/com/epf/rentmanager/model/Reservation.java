package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private long ID;
    private Client client;
    private Vehicle vehicle;

    private LocalDate begin;
    private LocalDate end;

    public Reservation(long ID, Client client, Vehicle vehicle, LocalDate begin, LocalDate end) {
        this.ID = ID;
        this.client = client;
        this.vehicle = vehicle;
        this.begin = begin;
        this.end = end;
    }

    public Reservation() {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "ID=" + ID +
                ", client=" + client +
                ", vehicle=" + vehicle +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return ID == that.ID && Objects.equals(client, that.client) && Objects.equals(vehicle, that.vehicle) && Objects.equals(begin, that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, client, vehicle, begin, end);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
