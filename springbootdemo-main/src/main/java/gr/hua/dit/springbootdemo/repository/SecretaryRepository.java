package gr.hua.dit.springbootdemo.repository;

import gr.hua.dit.springbootdemo.entities.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecretaryRepository extends JpaRepository<Secretary,Integer> {

    //Optional<Secretary> delete(int id);
}
