package gr.hua.dit.springbootdemo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctors")
public class Doctors  {
    //primary key on table doctors
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="fname")//column name in db
    @NotBlank(message = "Please enter the first name")
    @Size(max = 20, message = "Name should not be greater than 20 characters")
    private String firstName;

    @Column(name="lname")//column name in db
    @NotBlank(message = "Please enter the last name")
    @Size(max = 20, message = "Name should not be greater than 20 characters")
    private String lastName;

    @Column(name="email", unique = true)
    @Email(message = "Please enter a valid email Id")
    @Size(max = 50)
    private String email;

    @Column(name = "specialty")
    @NotBlank(message="Please enter a specialty")
    @Size(max = 30, message = "Specialty should not be greater than 30 characters")
    private String specialty;

    @OneToMany(mappedBy = "doctors",
                cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonManagedReference
    private List<Patient> patients;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Appointment> appointmentList;

    //default constructor
    public Doctors(){

    }

    //constructor with inputs and without column id
    public Doctors(String firstName,String lastName,String email,String specialty){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.specialty = specialty;
    }

    //setters and getters
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Appointment> getAppointmentListDoctor() {
        return appointmentList;
    }

    public void setAppointmentListDoctor(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public void addAppointment(Appointment appointment) {
        if(appointmentList == null) {
            appointmentList = new ArrayList<>();
        }

        appointmentList.add(appointment);


    }

    public void removeAppointment(Appointment appointment){
        if(appointmentList != null){
            appointmentList.remove(appointment);
        }
    }

    //override toString method
    public String toString(){
        return "Doctor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email +
                ", specialty" + specialty+ "]";
    }
}
