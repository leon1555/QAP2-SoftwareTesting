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
import java.time.Month;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class BloodDonationAppointmentManagerTest {
    @Mock
    private Database mockDatabase;

    @Test
    public void testBloodDonorTooYoung() {
        BloodDonor bloodDonorTooYoung = new BloodDonor();
        bloodDonorTooYoung.setFirstName("Jamie");
        bloodDonorTooYoung.setLastName("Cornick");
        bloodDonorTooYoung.setBloodType("A");
        bloodDonorTooYoung.setDateOfBirth(LocalDate.of( 2006 , Month.FEBRUARY , 11 ));
        bloodDonorTooYoung.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
             bloodDonorTooYoung
        );

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("donor too young"));
        }

        //Assertions.fail("Did not hit expected Exception!");
    }

    @Test
    public void testBloodDonorInvalidType() {
        BloodDonor bloodDonorTooYoung = new BloodDonor();
        bloodDonorTooYoung.setFirstName("Jamie");
        bloodDonorTooYoung.setLastName("Cornick");
        bloodDonorTooYoung.setBloodType("A");
        bloodDonorTooYoung.setDateOfBirth(LocalDate.of( 1985 , Month.FEBRUARY , 11 ));
        bloodDonorTooYoung.setId(1);

        Mockito.when(mockDatabase.getDonor(1)).thenReturn(
                bloodDonorTooYoung
        );

        ArrayList<AppointmentSlot> appointmentSlots = new ArrayList<AppointmentSlot>();

        AppointmentSlot appointmentSlot = new AppointmentSlot();
        appointmentSlot.setId(1);
        appointmentSlot.setLocation("123 Water St. st. John's NL");
        appointmentSlot.setBloodType("B");
        appointmentSlots.add(appointmentSlot);

        Mockito.when(mockDatabase.getAppointmentSlots()).thenReturn(appointmentSlots);

        BloodDonationAppointmentManager bloodDonationAppointmentManager =
                new BloodDonationAppointmentManager(mockDatabase);

        try {
            BloodDonationAppointment bloodDonationAppointment = bloodDonationAppointmentManager.bookAppointment(1);
        } catch (InvalidDonationSchedulingException e) {
            Assertions.assertTrue(e.getMessage().equalsIgnoreCase("invalid blood type"));
        }

        //Assertions.fail("Did not hit expected Exception!");
    }
}
