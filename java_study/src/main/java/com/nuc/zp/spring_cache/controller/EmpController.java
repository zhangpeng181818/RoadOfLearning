package com.nuc.zp.spring_cache.controller;

import com.nuc.zp.spring_cache.domain.Employee;
import com.nuc.zp.spring_cache.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable Integer id){
        return empService.getEmp(id);
    }
}
