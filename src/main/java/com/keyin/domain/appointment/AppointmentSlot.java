package com.keyin.domain.appointment;

public class AppointmentSlot {
    private int idl;
    private String location;

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    private String bloodType;

    public int getIdl() {
        return idl;
    }

    public void setId(int idl) {
        this.idl = idl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
