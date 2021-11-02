package com.keyin.domain.appointment;

import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class BloodDonationAppointmentTest {

    @Test
    public void testMethod() {

        AppointmentSlot appointmentSlot1 = new AppointmentSlot(1, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 0), LocalTime.of(11, 30), "AB negative");
        AppointmentSlot appointmentSlot2 = new AppointmentSlot(2, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 15), LocalTime.of(11, 45), "O positive");

        BloodDonor testDonor = new BloodDonor(1, "Bojack", "Horseman",
                LocalDate.of(1971, Month.MARCH, 25), "O positive");

        BloodDonationAppointment appointment = new BloodDonationAppointment();
        appointment.setId("MOCK-123");
        appointment.setBloodType(testDonor.getBloodType());
        appointment.setDonorId(testDonor.getId());
        appointment.setDate(appointmentSlot2.getDate());
        appointment.setStartTime(appointmentSlot2.getStartTime());
        appointment.setEndTime(appointmentSlot2.getEndTime());
        appointment.setLocation(appointmentSlot2.getLocation());
        boolean firsttimer;
        if(testDonor.getLastDonationDate() != null) {
            firsttimer = true;
        } else {
            firsttimer = false;
        }
        appointment.setFirsttimeDonor(firsttimer);
        System.out.println(appointment.toString());

    }

}
