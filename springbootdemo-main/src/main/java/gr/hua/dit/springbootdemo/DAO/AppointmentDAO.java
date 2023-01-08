package gr.hua.dit.springbootdemo.DAO;

import gr.hua.dit.springbootdemo.entities.Appointment;

import java.util.List;

public interface AppointmentDAO {

    public List<Appointment> findAll();

    public Appointment book(Appointment appointment);

    public Appointment findById(int id);

    public void delete(int id);//method for delete an appointment
}
