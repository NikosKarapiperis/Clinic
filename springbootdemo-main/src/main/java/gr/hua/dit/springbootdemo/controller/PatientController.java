package gr.hua.dit.springbootdemo.controller;

import gr.hua.dit.springbootdemo.DAO.AppointmentDAO;
import gr.hua.dit.springbootdemo.DAO.DoctorDAO;
import gr.hua.dit.springbootdemo.DAO.PatientDAO;
import gr.hua.dit.springbootdemo.entities.Appointment;
import gr.hua.dit.springbootdemo.entities.Doctors;
import gr.hua.dit.springbootdemo.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;


    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public Patient save(@RequestBody Patient patient){
        patient.setId(0);
        patientDAO.save(patient);
        return patient;
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    Patient delete(@PathVariable("id") int id){
        patientDAO.delete(id);
        return null;
    }

    @PostMapping("{pid}/doctors")
    @PreAuthorize("hasRole('PATIENT')")
    //method for add a doctor in patient
    Doctors addDoctor(@PathVariable int pid, @RequestBody Doctors doctors){
        int doctorId = doctors.getId();//find the id of doctor
        Patient patient = patientDAO.findById(pid);//find the id of patient from method findById

        if(patient == null){
            //if patient does not exist, throw an error
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }

        //if doctor already exist
        if(doctorId != 0 ){
            Doctors doctors1 = doctorDAO.findById(doctorId);//find doctor
            patient.setDoctors(doctors1);//set doctor in patient
            doctorDAO.save(doctors1);
            return doctors1;
        }

        //if doctor does not exist, we create doctor
        patient.setDoctors(doctors);
        doctorDAO.save(doctors);
        return doctors;
    }

  @PostMapping("/{pid}/appointment")//method for patient to book an appointment
  @PreAuthorize("hasRole('PATIENT') OR hasRole('SECRETARY')")
  public Appointment setAppointment(@PathVariable int pid,@RequestBody Appointment appointment){
        Patient patient = patientDAO.findById(pid);
        int doctorId = patient.getDoctors().getId();//we find id of doctor of this patient
        Doctors doctors = doctorDAO.findById(doctorId);

      if(patient == null) {//if patient with pid not found
          throw new ResponseStatusException(
                  HttpStatus.NOT_FOUND, "entity not found"
          );
      }


      patient.addAppointment(appointment);//use addAppointment method for create an appointment and add this in list of appointments
      doctors.addAppointment(appointment);//add this appointment AND in doctor entity because one patient has one doctor.
      appointmentDAO.book(appointment);//save this appointment in db
      return appointment;
  }

    //Method for patient to cancel an appointment
    @DeleteMapping("/{pid}/cancelAppointment/{aid}")
    @PreAuthorize("hasRole('PATIENT') OR hasRole('SECRETARY')")
    public Appointment cancelAppointment(@PathVariable("aid") int aid, @PathVariable("pid") int pid){
        Patient patient = patientDAO.findById(pid);
        Doctors doctors = patient.getDoctors(); //We find the doctor of this patient

        //We delete this appointment from the doctor of this patient
        List<Appointment> doctorsList = doctors.getAppointmentListDoctor();
        for(int i=0; i<doctorsList.size(); i++){
            if(doctorsList.get(i).getId()==aid){
                doctorsList.remove(doctorsList.get(i));
            }
        }
        //We delete this appointment from patient
        List<Appointment> patientList = patient.getAppointmentList();
        for(int i=0; i<patientList.size(); i++){
            if(patientList.get(i).getId()==aid){
                patientList.remove(patientList.get(i));
            }
        }

        //Delete this appointment from db
        appointmentDAO.delete(aid);
        return null;
    }

    //Method for patient to see all your appointments
    @GetMapping("/{pid}/MyAppointment")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Appointment> viewPatientCalendar(@PathVariable() int pid){
        Patient patient = patientDAO.findById(pid);
        return patient.getAppointmentList();
    }


    //Method for Admin to update a patient
    @PutMapping("/edit-patient/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patientDetails){
        Patient updatePatient = patientDAO.findById(id);

        //update details of doctor
        updatePatient.setFirstName(patientDetails.getFirstName());
        updatePatient.setLastName(patientDetails.getLastName());
        updatePatient.setEmail(patientDetails.getEmail());

        //save updates
        patientDAO.save(updatePatient);

        return ResponseEntity.ok(updatePatient);
    }


}

