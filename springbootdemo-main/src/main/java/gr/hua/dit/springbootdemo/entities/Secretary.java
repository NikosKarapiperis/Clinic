package gr.hua.dit.springbootdemo.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "secretary")
public class Secretary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "secretary_id")
    private int id;

    @NotBlank(message = "Please enter the first name")
    @Size(max = 20, message = "Name should not be greater than 20 characters")
    @Column(name="firstName")
    private String firstName;

    @NotBlank(message = "Please enter the first name")
    @Size(max = 20, message = "Name should not be greater than 20 characters")
    @Column(name="lastName")
    private String lastName;

    @Column(name="email", unique = true)
    @Email(message = "Please enter a valid email Id")
    @Size(max = 50)
    private String email;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonManagedReference
    private List<Payment> paymentList;

    //Secretary-Appointment relationship
    @OneToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Appointment> appointments;

    //default constructor
    public Secretary(){

    }

    //constructor with inputs and without column id
    public Secretary(String firstName,String lastName,String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment(Appointment appointment) {
        if(appointments == null) {
            appointments = new ArrayList<>();
        }

        appointments.add(appointment);

    }
    @Override
    public String toString() {
        return "Secretary [id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName + ", email=" + email + "]";
    }



}
