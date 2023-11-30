package com.bbai.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bbai.api.model.mainModel;

@RestController
@RequestMapping("/api")
public class mainController {

    @GetMapping
    ResponseEntity<mainModel> root() {
        mainModel main = new mainModel();
        main.setMessage("Hello World ! Api is working");
        main.setOnline(true);
        return new ResponseEntity<>(main, HttpStatus.OK);
    }
}
