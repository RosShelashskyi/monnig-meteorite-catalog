package edu.tcu.cs.monnigmeteoritecatalog.samplehistory;


import edu.tcu.cs.monnigmeteoritecatalog.system.exception.ObjectNotFoundException;
import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EntryService {
    private final EntryRepository entryRepository;

    private final IdWorker idWorker;


    public EntryService(EntryRepository entryRepository, IdWorker idWorker) {
        this.entryRepository = entryRepository;
        this.idWorker = idWorker;
    }

    public Entry findById(String entry_id) {
        return this.entryRepository.findById(entry_id)
                .orElseThrow(() -> new ObjectNotFoundException("entry", entry_id));
    }

    public Entry save(Entry newEntry) {
        newEntry.setEntry_id(idWorker.nextId() + "");
        return this.entryRepository.save(newEntry);
    }

    public List<Entry> findAll() {
        return this.entryRepository.findAll();
    }
    public void delete(String entry_id) {
        this.entryRepository.findById(entry_id).orElseThrow(() -> new ObjectNotFoundException("entry", entry_id));
        this.entryRepository.deleteById(entry_id);
    }
}
