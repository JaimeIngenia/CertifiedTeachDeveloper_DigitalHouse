package com.dh.DentalClinicMVC.controller;

import com.dh.DentalClinicMVC.model.Dentist;
import com.dh.DentalClinicMVC.service.DentistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    private DentistService dentistService;

    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }


    //localhost:8080/dentist/{id}
    @GetMapping("/{id}")
    public Dentist findById(@PathVariable Integer id) {
        return dentistService.findById(id);
    }


    //vamos a guardar un nuevo odontólogo en la BD
    @PostMapping
    public Dentist save(@RequestBody Dentist dentist) {
        return dentistService.save(dentist);
    }

    //vamos a actualizar los datos de un odontologo
    @PutMapping
    public void update(@RequestBody Dentist dentist) {
        dentistService.updateDentist(dentist);
    }

    //vamos a borrar un odontólogo
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        dentistService.deleteDentist(id);
    }

    //vamos a listar todos los odontólogos
    @GetMapping
    public List<Dentist> findAll() {
        return dentistService.findAll();
    }
}
