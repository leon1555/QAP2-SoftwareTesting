package com.keyin.domain;

import com.keyin.domain.appointment.AppointmentSlot;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<AppointmentSlot> getAppointmentSlots() {
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. st. John's NL");

        appointmentSlots.add(appointmentSlot);

        return appointmentSlots;
    }
}
