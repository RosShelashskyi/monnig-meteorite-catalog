package edu.tcu.cs.monnigmeteoritecatalog.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, String> {

    // Query to look for samples that share a monnig major number
    @Query(value = "SELECT m FROM Sample m WHERE m.monnig_number like %:monnigMajor%")
    List<Sample> findByMonnigMajorNum(@Param("monnigMajor") String monnigMajor);

    @Query(value = "SELECT m from Sample m WHERE " +
            "(m.name like %:name%) AND " +
            "(m.monnig_number like %:monnigNumber%) AND " +
            "(m.country like %:country%) AND " +
            "(m.sample_class like %:sample_class%) AND " +
            "(m.sample_group like %:sample_group%) AND " +
            "(m.date_found_year like %:date_found_year%)")
    Sample findByCriteria(@Param("monnigNumber") String monnigNumber,
                                @Param("name") String name,
                                @Param("country") String country,
                                @Param("sample_class") String sample_class,
                                @Param("sample_group") String sample_group,
                                @Param("date_found_year") String date_found_year);

    @Query(value = "SELECT m FROM Sample m WHERE m.monnig_number LIKE %:monnigNumber%")
    Sample findByMonnigNumber(@Param("monnigNumber") String monnigNumber);
}
