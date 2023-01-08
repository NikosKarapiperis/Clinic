package gr.hua.dit.springbootdemo.controller;


import gr.hua.dit.springbootdemo.DAO.AppointmentDAO;
import gr.hua.dit.springbootdemo.DAO.PatientDAO;
import gr.hua.dit.springbootdemo.entities.Appointment;
import gr.hua.dit.springbootdemo.entities.Patient;
import gr.hua.dit.springbootdemo.entities.Payment;
import gr.hua.dit.springbootdemo.entities.Secretary;
import gr.hua.dit.springbootdemo.repository.PaymentRepository;
import gr.hua.dit.springbootdemo.repository.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretary")
public class SecretaryController {
    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private PaymentRepository paymentRepository;


    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Secretary save(@RequestBody Secretary secretary){
        secretary.setId(0);
        secretaryRepository.save(secretary);
        return secretary;
    }

    //Method for delete secretary
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Secretary delete(@PathVariable("id") int id){
        secretaryRepository.deleteById(id);
        return null;
    }

    //secretary see all patients
    @GetMapping("/patientList")
    @PreAuthorize("hasRole('SECRETARY')")
    public List<Patient> viewAllPatientsList(){
        return patientDAO.findAll();
    }

    //secretary see all appointments
    @GetMapping("/appointment")
    @PreAuthorize("hasRole('SECRETARY')")
    public List<Appointment> viewSecretaryCalendar(){
        return appointmentDAO.findAll();
    }

    //secretary complete a payment
    @PostMapping("/getPaid")
    @PreAuthorize("hasRole('SECRETARY')")
    public Payment getPaid(@RequestBody Payment payment){
        payment.setId(0);
        paymentRepository.save(payment);
        return payment;
    }



}
