package com.example.demo.controller;

import com.example.demo.model.Patient;
import com.example.demo.service.DentistService;
import com.example.demo.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    // Un endpoint que nos permita agregar un paciente

    @PostMapping
    public Patient save(@RequestBody Patient patient ){

        return patientService.save(patient);
    }


}
