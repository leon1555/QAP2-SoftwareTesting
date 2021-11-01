package com.keyin.domain;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.donor.BloodDonor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    public List<AppointmentSlot> getAppointmentSlots() {
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
//        AppointmentSlot appointmentSlot1 = new AppointmentSlot(1, "Billy Van's Totally Legit Clinic",
//                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 0), LocalTime.of(11, 30), "AB negative");
//        AppointmentSlot appointmentSlot2 = new AppointmentSlot(2, "Billy Van's Totally Legit Clinic",
//                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 15), LocalTime.of(11, 45), "O positive");
//        AppointmentSlot appointmentSlot3 = new AppointmentSlot(3, "Billy Van's Totally Legit Clinic",
//                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 30), LocalTime.of(12, 00), "O negative");
        AppointmentSlot appointmentSlot4 = new AppointmentSlot(4, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 45),
                LocalTime.of(12, 15), "O positive");
        AppointmentSlot appointmentSlot5 = new AppointmentSlot(5, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(12, 0),
                LocalTime.of(12, 30), "AB negative");
        AppointmentSlot appointmentSlot6 = new AppointmentSlot(6, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(12, 15),
                LocalTime.of(12, 45), "O negative");

        return appointmentSlots;
    }

    public BloodDonor getDonor(int id) {
        BloodDonor bd1 = new BloodDonor(1, "Daria", "Morgendorffer",
                LocalDate.of(1981, Month.OCTOBER, 28), "O negative",
                LocalDate.of(2021, Month.MAY, 25));
        BloodDonor bd2 = new BloodDonor(2, "Bojack", "Horseman",
                LocalDate.of(1971, Month.MARCH, 25), "O positive",
                LocalDate.of(2021, Month.AUGUST, 1));
        BloodDonor bd3 = new BloodDonor(3, "Turtle", "Princess",
                LocalDate.of(1991, Month.JANUARY, 2), "AB negative",
                LocalDate.of(2021, Month.OCTOBER, 21));

        HashMap<Integer, BloodDonor> bloodDonors = new HashMap<Integer, BloodDonor>();
        bloodDonors.put(1, bd1);
        bloodDonors.put(2, bd2);
        bloodDonors.put(3, bd3);

        BloodDonor targetBloodDonor = bloodDonors.get(id);

        return targetBloodDonor;
    }
}
