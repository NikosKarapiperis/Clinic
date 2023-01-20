package gr.hua.dit.springbootdemo.controller;


import gr.hua.dit.springbootdemo.DAO.AppointmentDAO;
import gr.hua.dit.springbootdemo.DAO.PatientDAO;
import gr.hua.dit.springbootdemo.entities.Appointment;
import gr.hua.dit.springbootdemo.entities.Patient;
import gr.hua.dit.springbootdemo.entities.Secretary;
import gr.hua.dit.springbootdemo.repository.SecretaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/secretary")
public class SecretaryController {
    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;


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
    
     //method for update secretary
    @PutMapping("/edit-secretary/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Secretary> updateSecretary(@PathVariable int id, @RequestBody Secretary secretary){
        Secretary updateSecretary = secretaryRepository.findById(id);

        //update details of secretary
        updateSecretary.setFirstName(secretary.getFirstName());
        updateSecretary.setLastName(secretary.getLastName());
        updateSecretary.setEmail(secretary.getEmail());

        //save updates
        secretaryRepository.save(updateSecretary);

        return ResponseEntity.ok(updateSecretary);
    }
    
    //get secretary by id Rest API
    @GetMapping("/{id}")
    public ResponseEntity<Secretary> getSecretaryById(@PathVariable int id){
        Secretary secretary = secretaryRepository.findById(id);
        if(secretary==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return ResponseEntity.ok(secretary);
    }


    @GetMapping("/getAll")
    List<Secretary> getAll(){
        return secretaryRepository.findAll();
    }


}
