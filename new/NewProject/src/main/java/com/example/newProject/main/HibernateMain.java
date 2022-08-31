package com.example.newProject.main;

import java.util.Date;

import org.hibernate.Session;

import com.example.newProject.model.Employee;
import com.example.newProject.util.HibernateUtil;

public class HibernateMain {

    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setName("raihan");
        emp.setRole("SE");
        emp.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session = HibernateUtil.getSessionFactory().openSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.persist(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }

}
