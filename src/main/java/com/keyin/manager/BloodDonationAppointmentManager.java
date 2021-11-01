package com.keyin.manager;

import com.keyin.domain.Database;
import com.keyin.domain.appointment.AppointmentSlot;
import com.keyin.domain.appointment.BloodDonationAppointment;
import com.keyin.domain.donor.BloodDonor;

import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


public class BloodDonationAppointmentManager {
    private Database database;

    public BloodDonationAppointmentManager(Database database) {
        this.database = database;
    }

    public BloodDonationAppointment bookAppointment(int bloodDonorId) throws InvalidDonationSchedulingException {
        BloodDonationAppointment bloodDonationAppointment = new BloodDonationAppointment();

        BloodDonor bloodDonor = database.getDonor(bloodDonorId);
        // Ensure donor doesn't already have an appointment scheduled
        if(bloodDonor.getNextAppointmentDate() != null) {
            throw new InvalidDonationSchedulingException(("The donor already has an appointment scheduled"));
        }
        // Ensure donor is at least 18 years old and under 80 years old
        if(bloodDonor.getAge() < 18) {
            throw new InvalidDonationSchedulingException("The donor is under 18 years of age.");
        }
        if(bloodDonor.getAge() > 79) {
            throw new InvalidDonationSchedulingException(("The donor is 80 years of age or older."));
        }

        // Set the donor id and blood type for the appointment based on the donor's profile
        bloodDonationAppointment.setDonorId(bloodDonor.getId());
        bloodDonationAppointment.setBloodType(bloodDonor.getBloodType());

        // Determine whether the donor is a first-time donor. (If there is no last appointment date, it's assumed that
        // the donor is first-time.)
        if(bloodDonor.getLastDonationDate() == null){
            bloodDonationAppointment.setFirsttimeDonor(true);
        } else {
            bloodDonationAppointment.setFirsttimeDonor((false));
        }


        // Find an appointment that is valid for the donor's blood type
        List<AppointmentSlot> appointmentSlotList = database.getAppointmentSlots();

        for (AppointmentSlot appointmentSlot: appointmentSlotList) {
            if (appointmentSlot.getBloodType().equalsIgnoreCase(bloodDonor.getBloodType())) {
                if (((DAYS.between(bloodDonor.getLastDonationDate(), appointmentSlot.getDate())) >= 56
                        && (DAYS.between(bloodDonor.getLastDonationDate(), appointmentSlot.getDate())) <= 365)
                        || (bloodDonationAppointment.isFirsttimeDonor())) {
                    bloodDonationAppointment.setLocation(appointmentSlot.getLocation());
                    bloodDonationAppointment.setDate(appointmentSlot.getDate());
                    bloodDonationAppointment.setStartTime((appointmentSlot.getStartTime()));
                    bloodDonationAppointment.setEndTime((appointmentSlot.getEndTime()));
                    bloodDonationAppointment.setId("MOCK-APPOINTMENT-123");
                } else if (DAYS.between(bloodDonor.getLastDonationDate(), appointmentSlot.getDate()) < 56) {
                    throw new InvalidDonationSchedulingException(("Donor must wait at least 56 days until next donation."));
                } else {
                    throw new InvalidDonationSchedulingException(("Last donation was over a year ago."));
                }
            }
            if (bloodDonationAppointment.getStartTime() == null) {
                throw new InvalidDonationSchedulingException("No appointments available for this blood type.");
            }
        }

        return bloodDonationAppointment;
    }
}
