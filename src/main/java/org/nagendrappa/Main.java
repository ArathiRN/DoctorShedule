package org.nagendrappa;

import org.nagendrappa.models.Doctor;
import org.nagendrappa.models.DoctorSchedule;
import org.nagendrappa.models.Hospital;
import org.nagendrappa.models.Patients;
import org.nagendrappa.models.Speciality;
import java.time.LocalDateTime;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.nagendrappa.models.Patients;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Create hospital
            Hospital hospital = new Hospital();
            hospital.setName("HealthCare");
            hospital.setAddress("111 Maple Ave");

            // Create speciality
            Speciality specialty1 = new Speciality();
            specialty1.setName("Primary Care");
         /*   Speciality specialty2 = new Speciality();
            specialty2.setName("Orthopedic");
            Speciality specialty3 = new Speciality();
            specialty3.setName("Pediatric");
            Speciality speciality4 = new Speciality();
            speciality4.setName("Gynecology");
            Speciality speciality5 = new Speciality();
            speciality5.setName("Cardiology");*/



            // Create doctor
            Doctor doctor1 = new Doctor();
            doctor1.setName("Dr.Prim");
            doctor1.setHospital(hospital);
            doctor1.setSpecialty(specialty1);
/*
            Doctor doctor2 = new Doctor();
            doctor2.setName("Dr.Ortho");
            doctor2.setHospital(hospital);
            doctor2.setSpecialty(specialty2);

            Doctor doctor3 = new Doctor();
            doctor3.setName("Dr.Pedic");
            doctor3.setHospital(hospital);
            doctor3.setSpecialty(specialty3);

            Doctor doctor4 = new Doctor();
            doctor4.setName("Dr.Gynic");
            doctor4.setHospital(hospital);
            doctor4.setSpecialty(specialty1);

            Doctor doctor5 = new Doctor();
            doctor5.setName("Dr.Cardic");
            doctor5.setHospital(hospital);
            doctor5.setSpecialty(specialty1);
*/

            // Create schedule to doctor
            DoctorSchedule schedule = new DoctorSchedule();
            schedule.setType("Annual Visit");
            schedule.setDateTime(LocalDateTime.now());
            schedule.setDoctor(doctor1);

            // Relation schedule - doctor
            doctor1.setSchedule(schedule);

            // Create patient
            Patients patient = new Patients();
            patient.setName("Patient1");


            // doctor to patient
            //doctor.getPatients().add(patient);
            patient.getDoctors().add(doctor1);

            // Save entities
            session.save(hospital);
            session.save(specialty1);
            session.save(doctor1);
            session.save(patient);

            // Commit transaction
            transaction.commit();

            // Retrieve y Verify entities
            session = factory.openSession();
            transaction = session.beginTransaction();

            Doctor retrievedDoctor = session.get(Doctor.class, doctor1.getId());
            System.out.println("Doctor: " + retrievedDoctor.getName() + ", Specialty: " + retrievedDoctor.getSpecialty().getName());
            System.out.println("Hospital: " + retrievedDoctor.getHospital().getName());
            System.out.println("Schedule: " + retrievedDoctor.getSchedule().getType() + ", Date/Time: " + retrievedDoctor.getSchedule().getDateTime());

/*
      for (Patient p : retrievedDoctor.getPatients()) {
        System.out.println("Patient: " + p.getName() + ", Address: " + p.getAddress());
      }
*/

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }


    }
}