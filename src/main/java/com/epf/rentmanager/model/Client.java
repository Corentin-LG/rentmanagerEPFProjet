package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    private long ID;
    private String lastName;
    private String forName;
    private String email;
    private LocalDate dateDeNaissance;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Client(long ID, String lastName, String forName, String email, LocalDate dateDeNaissance) {
        this.ID = ID;
        this.lastName = lastName;
        this.forName = forName;
        this.email = email;
        this.dateDeNaissance = dateDeNaissance;
    }
    public Client() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return ID == client.ID && Objects.equals(lastName, client.lastName) && Objects.equals(forName, client.forName) && Objects.equals(email, client.email) && Objects.equals(dateDeNaissance, client.dateDeNaissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, lastName, forName, email, dateDeNaissance);
    }
}
