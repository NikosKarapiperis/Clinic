package gr.hua.dit.springbootdemo.repository;

import gr.hua.dit.springbootdemo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
