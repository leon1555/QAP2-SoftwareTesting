package com.keyin.domain.appointment;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class BloodDonationAppointmentTest {

    AppointmentSlot appointmentSlot1 = new AppointmentSlot(1, "Billy Van's Totally Legit Clinic",
            LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 0),
            LocalTime.of(11, 30), "AB negative");
    AppointmentSlot appointmentSlot2 = new AppointmentSlot(2, "Billy Van's Totally Legit Clinic",
            LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 15),
            LocalTime.of(11, 45), "O positive");

    BloodDonationAppointment appointment = new BloodDonationAppointment();
    appointment.setDonorId();
    

}
