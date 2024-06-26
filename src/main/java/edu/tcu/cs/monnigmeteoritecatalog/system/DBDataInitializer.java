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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Profile("dev")
public class DBDataInitializer implements CommandLineRunner {

    private final SampleRepository sampleRepository;

    private final LoanService loanService;

    private final IdWorker idWorker;

    private final UserService userService;


    public DBDataInitializer(SampleRepository sampleRepository,LoanService loanService, IdWorker idWorker, UserService userService) {
        this.sampleRepository = sampleRepository;
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
        s2.setSample_weight_g((float)785.9);


        Sample s3 = new Sample();
        s3.setSample_ID(idWorker.nextId() + "");
        s3.setName("Abernathy");
        s3.setMonnig_number("M239.1");
        s3.setCountry("USA");
        s3.setSample_class("Ordinary Chrondrite");
        s3.setSample_group("L");
        s3.setDate_found_year("1941");
        s3.setSample_weight_g((float)453.1);

        Sample s4 = new Sample();
        s4.setSample_ID(idWorker.nextId() + "");
        s4.setName("Abu Moharek");
        s4.setMonnig_number("M787.1");
        s4.setCountry("Egypt");
        s4.setSample_class("Ordinary Chrondrite");
        s4.setSample_group("H");
        s4.setDate_found_year("1997");
        s4.setSample_weight_g((float)56.4);

        Sample s5 = new Sample();
        s5.setSample_ID(idWorker.nextId() + "");
        s5.setName("Acapulco");
        s5.setMonnig_number("M901.1");
        s5.setCountry("Mexico");
        s5.setSample_class("Primitive Achondrite");
        s5.setSample_group("Acapulcoite");
        s5.setDate_found_year("1976");
        s5.setSample_weight_g((float)0.4);

        Sample s6 = new Sample();
        s6.setSample_ID(idWorker.nextId() + "");
        s6.setName("Acfer 011");
        s6.setMonnig_number("M2106.1");
        s6.setCountry("Algeria");
        s6.setSample_class("Ordinary Chrondrite");
        s6.setSample_group("H");
        s6.setDate_found_year("1989");
        s6.setSample_weight_g((float)0.2);

        Sample s7 = new Sample();
        s7.setSample_ID(idWorker.nextId() + "");
        s7.setName("Acfer 018");
        s7.setMonnig_number("M2107.2");
        s7.setCountry("Algeria");
        s7.setSample_class("Ordinary Chrondrite");
        s7.setSample_group("H");
        s7.setDate_found_year("1989");
        s7.setSample_weight_g((float)1.6);

        Sample s8 = new Sample();
        s8.setSample_ID(idWorker.nextId() + "");
        s8.setName("Acfer 018");
        s8.setMonnig_number("M2107.1");
        s8.setCountry("Algeria");
        s8.setSample_class("Ordinary Chrondrite");
        s8.setSample_group("H");
        s8.setDate_found_year("1989");
        s8.setSample_weight_g((float)7);

        Sample s9 = new Sample();
        s9.setSample_ID(idWorker.nextId() + "");
        s9.setName("Acfer 021");
        s9.setMonnig_number("M2108.1");
        s9.setCountry("Algeria");
        s9.setSample_class("Ordinary Chrondrite");
        s9.setSample_group("H");
        s9.setDate_found_year("1989");
        s9.setSample_weight_g((float)0.3);

        Sample s10 = new Sample();
        s10.setSample_ID(idWorker.nextId() + "");
        s10.setName("Acfer 038");
        s10.setMonnig_number("M1313.1");
        s10.setCountry("Algeria");
        s10.setSample_class("Ordinary Chrondrite");
        s10.setSample_group("LL");
        s10.setDate_found_year("1989");
        s10.setSample_weight_g((float)209.1);

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
        this.sampleRepository.save(s3);
        this.sampleRepository.save(s4);
        this.sampleRepository.save(s5);
        this.sampleRepository.save(s6);
        this.sampleRepository.save(s7);
        this.sampleRepository.save(s8);
        this.sampleRepository.save(s9);
        this.sampleRepository.save(s10);

        Loan l1 = new Loan();
        l1.setLoanee_name("John Doe");
        l1.setLoanee_email("johndoe@example.com");
        l1.setLoanee_institution("Meteorite Research Institute");
        l1.setLoanee_address("123 Meteorite Street, Cityville, USA");
        l1.setLoan_start_date("2024-04-01 10:00:00");
        l1.setLoan_due_date("2024-04-15 10:00:00");
        l1.setLoan_notes("Fragile meteorite, handle with care.");

        Loan l2 = new Loan();
        l2.setLoanee_name("Jane Smith");
        l2.setLoanee_email("janesmith@example.com");
        l2.setLoanee_institution("Space Exploration Society");
        l2.setLoanee_address("456 Galaxy Avenue, Star City, Canada");
        l2.setLoan_start_date("2024-03-20 09:30:00");
        l2.setLoan_due_date("2024-04-10 09:30:00");
        l2.setLoan_notes("Rare meteorite, research project.");

        Loan l3 = new Loan();
        l3.setLoanee_name("Carson Freeman");
        l3.setLoanee_email("carsonfreeman@example.com");
        l3.setLoanee_institution("Meteorite Research Institute");
        l3.setLoanee_address("2404 Wiggly Rd");
        l3.setLoan_start_date("2024-04-01 10:00:00");
        l3.setLoan_due_date("2024-04-15 10:00:00");
        l3.setLoan_notes("Carsons Loan");

        Loan l4 = new Loan();
        l4.setLoanee_name("Ros Shelashskyi");
        l4.setLoanee_email("rosdemoemail@gmail.com");
        l4.setLoanee_institution("Space Exploration Society");
        l4.setLoanee_address("2405 Wiggly Space Rd");
        l4.setLoan_start_date("2024-07-01 10:00:00");
        l4.setLoan_due_date("2024-07-15 10:00:00");
        l4.setLoan_notes("Ros Loan");

        Loan l5 = new Loan();
        l5.setLoanee_name("Bingyang Wei");
        l5.setLoanee_email("bingyangdemoemail@gmail.com");
        l5.setLoanee_institution("Web Tech Society");
        l5.setLoanee_address("2506 Vue Dr");
        l5.setLoan_start_date("2024-03-01 10:00:00");
        l5.setLoan_due_date("2024-03-15 10:00:00");
        l5.setLoan_notes("Bingyang Loan");
        this.loanService.save(l1);
        this.loanService.save(l2);
        this.loanService.save(l3);
        this.loanService.save(l4);
        this.loanService.save(l5);



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
