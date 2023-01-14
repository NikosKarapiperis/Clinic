package gr.hua.dit.springbootdemo.controller;


import gr.hua.dit.springbootdemo.DAO.DoctorDAO;
import gr.hua.dit.springbootdemo.entities.Appointment;
import gr.hua.dit.springbootdemo.entities.Doctors;
import gr.hua.dit.springbootdemo.entities.Patient;
import gr.hua.dit.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private UserRepository user;


    @GetMapping("/getAll")
     List<Doctors> getAll(){
        return doctorDAO.findAll();
    }

    @PostMapping("/saveDoctor")
    @PreAuthorize("hasRole('ADMIN')")
    Doctors save(@RequestBody Doctors doctor) {
        doctor.setId(0);
        doctorDAO.save(doctor);
        return doctor;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Doctors delete(@PathVariable("id") int id){
        doctorDAO.delete(id);
        return null;
    }

    //method for update a doctor
    @PutMapping("/edit-doctor/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Doctors> updateDoctor(@PathVariable int id,@RequestBody Doctors doctorDetails){
        Doctors updateDoctor = doctorDAO.findById(id);

        //update details of doctor
        updateDoctor.setFirstName(doctorDetails.getFirstName());
        updateDoctor.setLastName(doctorDetails.getLastName());
        updateDoctor.setEmail(doctorDetails.getEmail());
        updateDoctor.setSpecialty(doctorDetails.getSpecialty());

        //save updates
        doctorDAO.save(updateDoctor);

        return ResponseEntity.ok(updateDoctor);
    }

    //Method for doctor to see all your patients
    @GetMapping("/{did}/MyPatients")
    @PreAuthorize("hasRole('DOCTOR') OR hasRole('SECRETARY')")
    public List<Patient> viewMyPatientsList(@PathVariable("did") int did){
        Doctors doctors = doctorDAO.findById(did);
        return doctors.getPatients();
    }

    //Method for doctor to see all your appointments
    @GetMapping("/{did}/MyAppointment")
    @PreAuthorize("hasRole('DOCTOR') OR hasRole('SECRETARY')")
    public List<Appointment> viewDoctorCalendar(@PathVariable() int did){
        Doctors doctors = doctorDAO.findById(did);
        return doctors.getAppointmentListDoctor();
    }
    
     //get doctor by id Rest API
    @GetMapping("/{id}")
    public ResponseEntity<Doctors> getDoctorById(@PathVariable int id){
        Doctors doctors = doctorDAO.findById(id);
        if(doctors==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        return ResponseEntity.ok(doctors);
    }


}
