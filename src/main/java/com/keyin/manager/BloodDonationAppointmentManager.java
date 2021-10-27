package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;

import java.util.List;

public class BloodDonationAppointmentManager {
    private Database database;

    public BloodDonationAppointmentManager(Database database) {
        this.database = database;
    }

    public BloodDonationAppointment bookAppointment(int bloodDonorId) {
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();

        List<AppointmentSlot> appointmentSlotList = database.getAppointmentSlots();



        for (AppointmentSlot appointmentSlot: appointmentSlotList) {

        }

        return bloodDonationAppointment;
    }
}
