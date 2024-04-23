package edu.tcu.cs.monnigmeteoritecatalog.monniguser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MonnigUser, Integer> {
    Optional<MonnigUser> findByUsername(String username);
}
