package edu.tcu.cs.monnigmeteoritecatalog.system;

import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import edu.tcu.cs.monnigmeteoritecatalog.loan.LoanRepository;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleRepository;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SampleRepository sampleRepository;

    private final LoanRepository loanRepository;

    private final IdWorker idWorker;

    public DBDataInitializer(SampleRepository sampleRepository, LoanRepository loanRepository, IdWorker idWorker) {
        this.sampleRepository = sampleRepository;
        this.loanRepository = loanRepository;
        this.idWorker = idWorker;
    }

    @Override
    public void run(String... args) throws Exception {
        Sample s1 = new Sample();
        s1.setSample_ID(idWorker.nextId() + "");
        s1.setName("Abbott");
        s1.setMonnig_number("M398.1");
        s1.setCountry("USA");
        s1.setSample_class("Ordinary Chondrite");
        s1.setSample_group("H");
        s1.setDate_found_year("1951");
        s1.setSample_weight_g((float)325.1);

        Sample s2 = new Sample();
        s2.setSample_ID(idWorker.nextId() + "");
        s2.setName("Abee");
        s2.setMonnig_number("M499.2");
        s2.setCountry("Canada");
        s2.setSample_class("Enstatite Chondrite");
        s2.setSample_group("EH");
        s2.setDate_found_year("1952");
        s2.setSample_weight_g((float)453.1);

        sampleRepository.save(s1);
        sampleRepository.save(s2);

        Loan l1 = new Loan();
        l1.setLoan_ID(idWorker.nextId() + "");
        l1.setLoanee_name("John Doe");
        l1.setLoanee_email("johndoe@example.com");
        l1.setLoanee_institution("Meteorite Research Institute");
        l1.setLoanee_address("123 Meteorite Street, Cityville, USA");
        l1.setLoan_start_date("2024-04-01 10:00:00");
        l1.setLoan_due_date("2024-04-15 10:00:00");
        l1.setLoan_notes("Fragile meteorite, handle with care.");
        l1.setSamples_on_loan(Arrays.asList(s1));

        Loan l2 = new Loan();
        l2.setLoan_ID(idWorker.nextId() + "");
        l2.setLoanee_name("Jane Smith");
        l2.setLoanee_email("janesmith@example.com");
        l2.setLoanee_institution("Space Exploration Society");
        l2.setLoanee_address("456 Galaxy Avenue, Star City, Canada");
        l2.setLoan_start_date("2024-03-20 09:30:00");
        l2.setLoan_due_date("2024-04-10 09:30:00");
        l2.setLoan_notes("Rare meteorite, research project.");
        l2.setSamples_on_loan(Arrays.asList(s2));

        loanRepository.save(l1);
        loanRepository.save(l2);
    }
}
