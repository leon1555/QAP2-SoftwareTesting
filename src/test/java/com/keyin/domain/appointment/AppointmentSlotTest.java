package com.keyin.domain.appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class AppointmentSlotTest {
    public static void main(String[] args) {
        AppointmentSlot appointmentSlot5 = new AppointmentSlot(5, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(12, 0),
                LocalTime.of(12, 30), "AB negative");

        System.out.println(appointmentSlot5);
    }
}
