package gr.hua.dit.springbootdemo.repository;

import gr.hua.dit.springbootdemo.entities.ERole;
import gr.hua.dit.springbootdemo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
