package edu.tcu.cs.monnigmeteoritecatalog.system;

import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import edu.tcu.cs.monnigmeteoritecatalog.loan.LoanRepository;
import edu.tcu.cs.monnigmeteoritecatalog.loan.LoanService;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.MonnigUser;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.UserRepository;
import edu.tcu.cs.monnigmeteoritecatalog.monniguser.UserService;
import edu.tcu.cs.monnigmeteoritecatalog.sample.Sample;
import edu.tcu.cs.monnigmeteoritecatalog.sample.SampleRepository;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryRepository;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DBDataInitializer implements CommandLineRunner {

    private final SampleRepository sampleRepository;

    private final EntryRepository entryRepository;

    private final LoanService loanService;

    private final IdWorker idWorker;

    private final UserService userService;


    public DBDataInitializer(SampleRepository sampleRepository, EntryRepository entryRepository, LoanRepository loanRepository, LoanRepository loanRepository1, LoanService loanService, IdWorker idWorker, UserRepository userRepository, UserService userService) {
        this.sampleRepository = sampleRepository;
        this.entryRepository = entryRepository;
        this.loanService = loanService;
        this.idWorker = idWorker;
        this.userService = userService;
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

        // Entries
        Entry entry1 = new Entry();
        entry1.setEntry_id(idWorker.nextId() + "");
        entry1.setDate("11/1/2023");
        entry1.setCategory("Created");
        entry1.setNotes("Migrated from Old Monnig Database");

        Entry entry2 = new Entry();
        entry2.setEntry_id(idWorker.nextId() + "");
        entry2.setDate("12/5/2023");
        entry2.setCategory("Cut");
        entry2.setNotes("Cut into three subsamples");

        s1.addHistoryEntry(entry1);
        s2.addHistoryEntry(entry2);

        this.sampleRepository.save(s1);
        this.sampleRepository.save(s2);

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

        this.loanService.save(l1);
        this.loanService.save(l2);

        MonnigUser monnigUser = new MonnigUser();
        monnigUser.setUsername("carsonfreeman");
        monnigUser.setPassword("12345");
        monnigUser.setEnabled(true);
        monnigUser.setRoles("admin");

        MonnigUser monnigUser2 = new MonnigUser();
        monnigUser2.setUsername("guest");
        monnigUser2.setPassword("");
        monnigUser2.setEnabled(true);
        monnigUser2.setRoles("user");

        this.userService.save(monnigUser);
        this.userService.save(monnigUser2);

    }
}
