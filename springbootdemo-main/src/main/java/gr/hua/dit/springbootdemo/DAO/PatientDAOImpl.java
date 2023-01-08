package gr.hua.dit.springbootdemo.DAO;

import gr.hua.dit.springbootdemo.entities.Doctors;
import gr.hua.dit.springbootdemo.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
;
import java.util.List;

@Repository
public class PatientDAOImpl implements PatientDAO{
    @Autowired
    EntityManager entityManager;




    @Override
    @Transactional
    public List<Patient> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from Patient", Patient.class);
        List<Patient> patients = query.getResultList();
        return patients;
    }

    @Override
    @Transactional
    public Patient findById(int id) {
          return entityManager.find(Patient.class,id);
    }

    @Override
    @Transactional
    public void save(Patient patient) {
       Patient patient1 = entityManager.merge(patient);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Patient patient = entityManager.find(Patient.class,id);
        entityManager.remove(patient);
    }
}
