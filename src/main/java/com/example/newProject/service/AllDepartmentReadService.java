package com.example.newProject.service;

import com.example.newProject.model.Department;

import java.util.List;

import static com.example.newProject.dao.AllDepartmentReadQuery.allDepartmentReadQuery;

public class AllDepartmentReadService {
    public static void allDepartmentReadService() {
        List<Department> departments = allDepartmentReadQuery();
        boolean begin = true;
        for(Department dept : departments) {
            if(!begin) {
                System.out.print(", " + dept.getId());
            } else {
                System.out.print(dept.getId());
                begin = false;
            }
        }
    }
}
