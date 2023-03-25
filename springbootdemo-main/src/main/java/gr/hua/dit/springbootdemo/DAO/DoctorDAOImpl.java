package gr.hua.dit.springbootdemo.DAO;

import gr.hua.dit.springbootdemo.entities.Doctors;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DoctorDAOImpl implements DoctorDAO{
    @Autowired
    private EntityManager entityManager;//for connect with database


    @Override
    @Transactional
    public List<Doctors> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Doctors ", Doctors.class);
        List<Doctors> doctors = query.getResultList();
        return doctors;
    }


    @Override
    @Transactional
    public void save(Doctors doctors) {
        Doctors doctors1 = entityManager.merge(doctors);
    }

    @Override
    @Transactional
    public Doctors findById(int id) {
        return entityManager.find(Doctors.class,id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Doctors doctors = entityManager.find(Doctors.class,id);
        doctors.removePatients();//before delete doctor, remove all his patients
        entityManager.remove(doctors);
    }
}
