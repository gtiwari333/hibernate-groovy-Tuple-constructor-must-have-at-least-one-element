package app;

import groovy.transform.CompileStatic;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CompileStatic
class CtrlJava {

    @Autowired
    EntityManager entityManager;

    @GetMapping("/java")
    void javaWorks() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
        Root<Employee> root = criteriaQuery.from(Employee.class);

        //both of the following runs without any error
        criteriaQuery.select(builder.tuple(root.get("id").alias("employeeId")));
        criteriaQuery.select(builder.tuple(root.get("id").alias("employeeId"), root.get("name").alias("employeeName")));
    }

}