package gr.hua.dit.springbootdemo.DAO;


import gr.hua.dit.springbootdemo.entities.Patient;

import java.util.List;

public interface PatientDAO {
    //Method for return all patients
    public List<Patient> findAll();

    //Method for find a patient
    public Patient findById(int id);

    //Method for save a doctor
    public void save(Patient patient);

    //Method for delete a patient
    public void delete(int id);

}
