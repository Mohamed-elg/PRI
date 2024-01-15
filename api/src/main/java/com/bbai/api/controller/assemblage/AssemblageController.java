package com.bbai.api.controller.assemblage;

import com.bbai.api.model.assemblage.Assemblage;
import com.bbai.api.repository.assemblage.AssemblageRepository;
import com.bbai.api.service.assemblage.AssemblageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AssemblageController {
    @Autowired
    AssemblageService assemblageService;

    @PostMapping("/assemblage")
    public ResponseEntity<Assemblage> postAssemblage(@RequestBody Assemblage assemblage){
        try {
            return new ResponseEntity<>(assemblageService.saveAssemblage(assemblage), HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
