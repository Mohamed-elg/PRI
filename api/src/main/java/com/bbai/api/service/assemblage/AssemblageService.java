package com.bbai.api.service.assemblage;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.repository.assemblage.AssemblageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssemblageService {
    @Autowired
    AssemblageRepository assemblageRepository;

    public Assemblage saveAssemblage(Assemblage assemblage){
        return assemblageRepository.save(assemblage);
    }

}
