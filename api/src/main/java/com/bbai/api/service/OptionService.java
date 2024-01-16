package com.bbai.api.service;

import com.bbai.api.dto.OptionDTO;
import com.bbai.api.model.Option;
import com.bbai.api.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {
    @Autowired
    OptionRepository optionRepository;

    public List<Option> optionFromDTO(List<OptionDTO> optionDTOS){
        List<Option> options = new ArrayList<>();
        for(OptionDTO optionDTO:optionDTOS){
            Optional<Option> option = optionRepository.findByLabel(optionDTO.getLibelle());
            Option optionToSave = new Option();

            if(option.isEmpty()){
                optionToSave.setLabel(optionDTO.getLibelle());
                optionToSave = optionRepository.save(optionToSave);
            }else{
                optionToSave = option.get();
            }

            options.add(optionToSave);
        }

        return options;
    }
}
