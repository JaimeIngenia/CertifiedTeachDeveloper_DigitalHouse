package com.example.demo.controller;

import com.example.demo.model.Dentist;
import com.example.demo.service.DentistService;
import org.springframework.ui.Model;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class IndexController {

    private PatientService patientService;

    private DentistService dentistService;

    public IndexController(PatientService patientService, DentistService dentistService){
        this.patientService = patientService;
        this.dentistService = dentistService;
    }

    @GetMapping
    public String findPatientBtEmail(Model model, @RequestParam("email") String email,
                                     @RequestParam("id") Integer id){

        Patient patient = patientService.findByEmail(email);
        Dentist dentist = dentistService.findById(id);

        model.addAttribute("name",patient.getName());
        model.addAttribute("lastName",patient.getLastName());

        // Agregar la vista que se corresponde con Odontólogo

        model.addAttribute("nameDentist", dentist.getName());
        model.addAttribute("lastNameDentist", dentist.getLastName());
        model.addAttribute("registration", dentist.getRegistration());



        return "index";
    }

}
