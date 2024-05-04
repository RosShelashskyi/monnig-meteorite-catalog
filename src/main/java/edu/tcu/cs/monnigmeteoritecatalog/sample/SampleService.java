package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.loan.Loan;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.Entry;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryRepository;
import edu.tcu.cs.monnigmeteoritecatalog.samplehistory.EntryService;
import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SampleService {
    private final SampleRepository sampleRepository;

    private final IdWorker idWorker;

    private final EntryRepository entryRepository;

    private final EntryService entryService;


    public SampleService(SampleRepository sampleRepository, IdWorker idWorker, EntryRepository entryRepository, EntryService entryService) {
        this.sampleRepository = sampleRepository;
        this.idWorker = idWorker;
        this.entryRepository = entryRepository;
        this.entryService = entryService;
    }

    //function for internal use for finding samples by internal ids
    public Sample findById(String sampleId){
        return this.sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ObjectNotFoundException("sample", sampleId));
    }

    //function for finding all samples, we'll probably need it internally
    public List<Sample> findAll(){
        return this.sampleRepository.findAll();
    }

    //save function for adding samples to sampleRepository
    public Sample save(Sample newSample){
        newSample.setSample_ID(idWorker.nextId() + ""); //uses idWorker for generating ids
        return this.sampleRepository.save(newSample);
    }

    //function for updating samples. It's pretty lengthy because of all the attributes
    public Sample update(String sampleId, Sample update){
        return this.sampleRepository.findById(sampleId)
                .map(oldSample -> {
                    oldSample.setName(update.getName());
                    oldSample.setMonnig_number(update.getMonnig_number());
                    oldSample.setSample_class(update.getSample_class());
                    oldSample.setSample_group(update.getSample_group());
                    oldSample.setCountry(update.getCountry());
                    oldSample.setSample_weight_g(update.getSample_weight_g());
                    oldSample.setDate_found_year(update.getDate_found_year());
                    //not including history in this since history is meant to track updates
                    //and it doesn't make sense to update history with this api call
                    return this.sampleRepository.save(oldSample);
                }).orElseThrow(() -> new ObjectNotFoundException("sample", sampleId));
    }

    public void delete(String sampleId){
        Sample sampleToBeDeleted = this.sampleRepository.findById(sampleId)
                .orElseThrow(() -> new ObjectNotFoundException("sample", sampleId));
        if(sampleToBeDeleted.getLoan() != null) {
            // remove the sample from the loan
            sampleToBeDeleted.getLoan().removeSampleFromLoan(sampleToBeDeleted.getMonnig_number());
            sampleToBeDeleted.setLoan(null);
        }
        // remove sample history
        sampleToBeDeleted.removeHistory();
        this.sampleRepository.deleteById(sampleId);
    }
}
