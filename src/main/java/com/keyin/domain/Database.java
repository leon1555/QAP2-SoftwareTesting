package com.keyin.domain;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.donor.BloodDonor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    public List<AppointmentSlot> getAppointmentSlots() {
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
        return appointmentSlots;
    }

    public BloodDonor getDonor(int id) {
        HashMap<Integer, BloodDonor> bloodDonors = new HashMap<Integer, BloodDonor>();
        BloodDonor targetBloodDonor = bloodDonors.get(id);
        return targetBloodDonor;
    }
}
