package edu.tcu.cs.monnigmeteoritecatalog.sample;

import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SampleService {
    private final SampleRepository sampleRepository;

    private final IdWorker idWorker;

    public SampleService(SampleRepository sampleRepository, IdWorker idWorker) {
        this.sampleRepository = sampleRepository;
        this.idWorker = idWorker;
    }

    //function for internal use for finding samples by internal ids
//    public Sample findById(String sampleId){
//        return this.sampleRepository.findById(sampleId)
//                .orElseThrow(() -> )
//    }
}
