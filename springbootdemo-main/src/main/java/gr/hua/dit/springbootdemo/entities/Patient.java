package gr.hua.dit.springbootdemo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "patient_fname")
    @NotBlank(message = "Please enter the first name")
    @Size(max = 20, message = "Name should not be greater than 20 characters")
    private String firstName;

    @Column(name = "patient_lname")
    @NotBlank(message = "Please enter the last name")
    @Size(max = 20, message = "Name should not be greater than 20 characters")
    private String lastName;

    @Column(name = "patient_email",unique = true)
    @Email(message = "Please enter a valid email Id")
    @Size(max = 50)
    private String email;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})//cascades without delete type
    @JoinColumn(name = "doctor_id")//foreign key
    @JsonBackReference
    private Doctors doctors;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonManagedReference
    @JoinColumn(name="patient_id")
    private List<Appointment> appointmentList;



    //default constructor
    public Patient(){

    }

    public Patient(String firstName,String lastName,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //setters and getters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Doctors getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctors doctors) {
        this.doctors = doctors;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    //method for add appointments
    public void addAppointment(Appointment appointment) {
        if(appointmentList == null) {
            appointmentList = new ArrayList<>();
        }

        appointmentList.add(appointment);

    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName + ", email=" + email + "]";
    }

}
