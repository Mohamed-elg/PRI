package com.bbai.api.controller;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@RestController
@RequestMapping("/api/")
public class MainController {

    @GetMapping("/")
    public ApiResponse mainRoute() {
        ApiResponse response = new ApiResponse();
        response.setCode(200);
        response.setData("Hello World ! API is working");
        return response;
    }

    @JsonPropertyOrder({ "code", "data" })
    static class ApiResponse {
        private int Code;
        private String data;

        public int getCode() {
            return Code;
        }

        public void setCode(int code) {
            this.Code = code;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
