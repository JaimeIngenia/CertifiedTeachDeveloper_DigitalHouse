package com.example.demo.service;

import com.example.demo.dao.DentistDaoH2;
import com.example.demo.dao.IDao;
import com.example.demo.model.Dentist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {

    private IDao<Dentist> dentistIDao;

    public DentistService(){
        this.dentistIDao = new DentistDaoH2();
    }

    public Dentist save(Dentist dentist) {
        return dentistIDao.save(dentist);
    }

    public Dentist findById(Integer id){
        return dentistIDao.findById(id);
    }

    public void updateDentist(Dentist dentist){
        dentistIDao.update(dentist);
    }

    public void deleteDentist(Integer id){
        dentistIDao.delete(id);
    }

    public List<Dentist> findAll(){
        return dentistIDao.findAll();
    }
}
