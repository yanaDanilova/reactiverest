package com.sprinboot.webflux.reactiverest.repositories;

import com.sprinboot.webflux.reactiverest.entities.Employee;
import com.sprinboot.webflux.reactiverest.entities.TaskSchedule;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaTaskScheduleRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public void saveTaskSchedule(TaskSchedule taskSchedule){
        entityManager.persist(taskSchedule);
    }

    public void update(TaskSchedule updatedTaskSchedule, int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TaskSchedule taskSchedule = entityManager.find(TaskSchedule.class, id);
            if (taskSchedule != null) {
                entityManager.merge(updatedTaskSchedule);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException();
        }
    }
    public List<TaskSchedule> getTasksByEmployeeName (String name){
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("GetTasksByEmployeeName");
        storedProcedureQuery.registerStoredProcedureParameter("empName", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("empName", name);

        List<Object[]> resultList = storedProcedureQuery.getResultList();
        List<TaskSchedule> taskScheduleList = new ArrayList<>();

        for (Object[] result : resultList) {
            TaskSchedule taskSchedule = new TaskSchedule();
            taskSchedule.setId((int) result[0]);
            taskSchedule.setTaskDate((String) result[2]);
            taskSchedule.setAssignedTask((String) result[3]);
            taskSchedule.setTaskDetails((String) result[4]);
            taskScheduleList.add(taskSchedule);
        }
        return taskScheduleList;
    }

}
