package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private long ID;
    private long clientID;
    private long vehicleID;

    public Reservation(long ID, long clientID, long vehicleID, LocalDate begin, LocalDate end) {
        this.ID = ID;
        this.clientID = clientID;
        this.vehicleID = vehicleID;
        this.begin = begin;
        this.end = end;
    }
    public Reservation() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return ID == that.ID && clientID == that.clientID && vehicleID == that.vehicleID && Objects.equals(begin, that.begin) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, clientID, vehicleID, begin, end);
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getClientID() {
        return clientID;
    }

    public void setClientID(long clientID) {
        this.clientID = clientID;
    }

    public long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(long vehicleID) {
        this.vehicleID = vehicleID;
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

    private LocalDate begin;
    private LocalDate end;

}
