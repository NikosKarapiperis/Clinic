package gr.hua.dit.springbootdemo.DAO;

import gr.hua.dit.springbootdemo.entities.Appointment;
import gr.hua.dit.springbootdemo.entities.Doctors;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO{
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Appointment> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Appointment",Appointment.class);
        List<Appointment> appointments = query.getResultList();
        return appointments;
    }

    @Override
    @Transactional
    public Appointment book(Appointment appointment) {
        Appointment appointment1 = entityManager.merge(appointment);
        return appointment1;
    }

    @Override
    @Transactional
    public Appointment findById(int id) {
        return entityManager.find(Appointment.class,id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Appointment appointment = entityManager.find(Appointment.class,id);
        entityManager.remove(appointment);
    }
}
