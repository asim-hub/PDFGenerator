package com.example.pdfgenerator.service;

import com.example.pdfgenerator.model.*;
import org.springframework.beans.factory.annotation.*;
import com.example.pdfgenerator.repository.EmployeRepository;
import org.springframework.stereotype.*;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeService {
    
    @Autowired
    EmployeRepository employeRepository;

    //get all the students from the database
    public List<Employe> getAllEmployees(){
        //List<Employe> employees = new ArrayList<Employe>();
        //employees = employeRepository.findAll();
        //return (ArrayList<Employe>) employees;
        return (List<Employe>)employeRepository.findAll();
    }

    //get a specific student by its ID
    public Optional<Employe> getEmployeById(int id){
        return employeRepository.findById(id);
    }
}
