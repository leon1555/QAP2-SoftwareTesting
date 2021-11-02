package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BloodDonationAppointmentManagerTest {


    @Mock
    private Database mockDatabase;

    @Test
    public void happyPath() throws InvalidDonationSchedulingException {
        BloodDonor happyBloodDonor = new BloodDonor(1, "Bojack", "Horseman",
                LocalDate.of(1971, Month.MARCH, 25), "O positive");

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(happyBloodDonor);

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot1 = new AppointmentSlot(1, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 0), LocalTime.of(11, 30), "AB negative");
        AppointmentSlot appointmentSlot2 = new AppointmentSlot(2, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 15), LocalTime.of(11, 45), "O positive");
        AppointmentSlot appointmentSlot3 = new AppointmentSlot(3, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 30), LocalTime.of(12, 00), "O negative");

        appointmentSlots.add(appointmentSlot1);
        appointmentSlots.add(appointmentSlot2);
        appointmentSlots.add(appointmentSlot3);

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        Assertions.assertEquals(bloodDonationAppointment.getDate(), appointmentSlot2.getDate());


    }

    @Test
    public void testBloodDonorTooYoung() {
        BloodDonor bloodDonorTooYoung = new BloodDonor(99, "Shirley", "Temple",
                LocalDate.of(2009, Month.MARCH, 25), "A positive");

        Mockito.when(mockDatabase.getDonor(99)).thenReturn(
             bloodDonorTooYoung
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(99);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equals("The donor is under 18 years of age."));
        }
    }

    @Test
    public void testBloodDonorTooOld() {
        BloodDonor bloodDonorTooOld = new BloodDonor(23, "Fran", "Lebowitz",
                LocalDate.of(1940, Month.MARCH, 25), "O negative");
        Mockito.when(mockDatabase.getDonor(23)).thenReturn(bloodDonorTooOld);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(23);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equals("The donor is 80 years of age or older."));
        }
    }

    @Test
    public void testAppointmentsAtLeast56DaysApart() {
        BloodDonor bloodDonorTooEarly = new BloodDonor(23, "Fran", "Lebowitz",
                LocalDate.of(1950, Month.MARCH, 25), "O negative",
                LocalDate.of(2021, Month.OCTOBER, 31));
        Mockito.when(mockDatabase.getDonor(23)).thenReturn(bloodDonorTooEarly);

        AppointmentSlot appointmentSlot1 = new AppointmentSlot(1, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 0),
                LocalTime.of(11, 30), "AB negative");
        AppointmentSlot appointmentSlot2 = new AppointmentSlot(2, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 15),
                LocalTime.of(11, 45), "O positive");
        AppointmentSlot appointmentSlot3 = new AppointmentSlot(3, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 30),
                LocalTime.of(12, 00), "O negative");
        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();
        appointmentSlots.add(appointmentSlot1);
        appointmentSlots.add(appointmentSlot2);
        appointmentSlots.add(appointmentSlot3);
        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(23);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equals("Donor must wait at least 56 days until next donation."));
        }


    }

    @Test
    public void testBloodDonorInvalidType() {
        BloodDonor bloodDonorInValidBloodType = new BloodDonor(137, "Count", "Frightenstein",
                LocalDate.of(1770, Month.OCTOBER, 31), "Bat Blood");

        Mockito.when(mockDatabase.getDonor(137)).thenReturn(
                bloodDonorInValidBloodType
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot1 = new AppointmentSlot(1, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 0), LocalTime.of(11, 30), "AB negative");
        AppointmentSlot appointmentSlot2 = new AppointmentSlot(2, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 15), LocalTime.of(11, 45), "O positive");
        AppointmentSlot appointmentSlot3 = new AppointmentSlot(3, "Billy Van's Totally Legit Clinic",
                LocalDate.of(2021, Month.NOVEMBER, 29), LocalTime.of(11, 30), LocalTime.of(12, 00), "O negative");

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(137);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equals("No appointments available for this blood type."));
        }

//        Assertions.fail("Did not hit expected Exception!");
    }
}
