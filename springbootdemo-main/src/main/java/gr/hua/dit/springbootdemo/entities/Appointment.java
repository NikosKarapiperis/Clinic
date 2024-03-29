package gr.hua.dit.springbootdemo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="date")
    private String date;

    @Column(name="doctor_speciality")
    private String doctorSpeciality;

    @Column(name="appointment_type")
    private String appointmentType;

    @Column(name = "medicalDiagnosis")
    private String medicalDiagnosis;

    @Column(name = "medicinePrescribed")
    private String medicinePrescribed;



    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})//cascades without delete type
    @JoinColumn(name = "patient_id")//foreign key
    @JsonBackReference
    private Patient patient;




    public Appointment(){

    }

    public Appointment(String date,String doctorSpeciality,String appointmentType,Patient patient){
        this.date = date;
        this.doctorSpeciality = doctorSpeciality;
        this.appointmentType = appointmentType;
        this.patient = patient;

    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMedicalDiagnosis() {
        return medicalDiagnosis;
    }

    public void setMedicalDiagnosis(String medicalDiagnosis) {
        this.medicalDiagnosis = medicalDiagnosis;
    }

    public String getMedicinePrescribed() {
        return medicinePrescribed;
    }

    public void setMedicinePrescribed(String medicinePrescribed) {
        this.medicinePrescribed = medicinePrescribed;
    }

    //method for add this appointment in doctor's appointmentList
    public void addDoctor(Doctors doctor) {
        doctor.getAppointmentListDoctor().add(this);
    }

    //method for add this appointment in patient's appointmentList
    public void addPatient(Patient patient) {
        this.patient = patient;
        patient.getAppointmentList().add(this);
    }
}
