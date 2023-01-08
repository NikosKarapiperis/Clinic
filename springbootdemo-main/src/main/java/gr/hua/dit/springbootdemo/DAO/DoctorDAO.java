package gr.hua.dit.springbootdemo.DAO;

import gr.hua.dit.springbootdemo.entities.Doctors;

import java.util.List;

public interface DoctorDAO {
    //method for take all doctors
    public List<Doctors> findAll();

    public void save(Doctors doctors);//method for save a doctor

    public Doctors findById(int id);//method for find a doctor with your id

    public void delete(int id);//method for delete a doctor
}
