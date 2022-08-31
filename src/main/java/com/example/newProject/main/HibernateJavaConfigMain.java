package com.example.newProject.main;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.example.newProject.model.Employee1;
import com.example.newProject.util.HibernateUtil;

public class HibernateJavaConfigMain {

    public static void main(String[] args) {
        Employee1 emp = new Employee1();
        emp.setName("Lisa");
        emp.setRole("Manager");
        emp.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.persist(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionJavaConfigFactory().close();
    }

}
