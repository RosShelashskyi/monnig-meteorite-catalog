package edu.tcu.cs.monnigmeteoritecatalog.monnigcurator;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CuratorService {

    private final CuratorRepository curatorRepository;

    public CuratorService(CuratorRepository curatorRepository) {
        this.curatorRepository = curatorRepository;
    }

    public List<Curator> findAll(){
        return this.curatorRepository.findAll();
    }
}
