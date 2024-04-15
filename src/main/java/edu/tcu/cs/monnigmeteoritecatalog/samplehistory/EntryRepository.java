package edu.tcu.cs.monnigmeteoritecatalog.samplehistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, String> {
}
