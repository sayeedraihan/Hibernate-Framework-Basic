package com.example.newProject.dao;

import com.example.newProject.model.Employee;
import com.example.newProject.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AllEmployeeReadQuery {
    public static Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    public static Transaction transaction = session.beginTransaction();
    public static List<Employee> employees;
    public static List<Employee> allEmployeeReadQuery() {
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            employees = query.getResultList();
        } catch (Exception e) {
            System.out.println("Something went wrong. " + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            transaction.commit();
            session.close();
            return employees;
        }
    }
}
