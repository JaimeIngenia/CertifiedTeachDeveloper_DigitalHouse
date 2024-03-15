package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Patient;
import com.dh.DentalClinicMVC.service.PatientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    //un endpoint que nos permita agregar un paciente
    @PostMapping
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    //un endpoint que nos permita actualizar un paciente que ya esté registrado
    @PutMapping
    public void update(@RequestBody Patient patient) {
        patientService.update(patient);
    }

}
