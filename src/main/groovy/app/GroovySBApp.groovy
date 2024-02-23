package app

import jakarta.persistence.EntityManager
import jakarta.persistence.Tuple
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Root
import org.hibernate.query.sqm.SqmExpressible
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class GroovySBApp {


    static void main(String[] args) {
        new SpringApplication(GroovySBApp.class).run(args);
    }


    @RestController
    static class CtrlGroovy {
        @Autowired
        EntityManager entityManager

        @GetMapping("/groovy")
        void groovyFails() {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
            Root<Employee> root = criteriaQuery.from(Employee.class);

            /*


            Getting:

            org.hibernate.query.SemanticException: Tuple constructor must have at least one element
            at org.hibernate.query.sqm.tree.expression.SqmTuple.<init>(SqmTuple.java:52) ~[hibernate-core-6.4.4.Final.jar:6.4.4.Final]
            at org.hibernate.query.sqm.internal.SqmCriteriaNodeBuilder.tuple(SqmCriteriaNodeBuilder.java:664) ~[hibernate-core-6.4.4.Final.jar:6.4.4.Final]
            at org.hibernate.query.sqm.internal.SqmCriteriaNodeBuilder.tuple(SqmCriteriaNodeBuilder.java:655) ~[hibernate-core-6.4.4.Final.jar:6.4.4.Final]
            at org.codehaus.groovy.vmplugin.v8.IndyInterface.fromCache(IndyInterface.java:321) ~[groovy-4.0.18.jar:4.0.18]
            at app.GroovySBApp$CtrlGroovy.groovyFails(GroovySBApp.groovy:35) ~[main/:na]

            its working with Hibernate 5.1.2 that spring 2.7.18 resolves

             */

            try {
                criteriaQuery.select(builder.tuple(root.get("id").alias("employeeId") as SqmExpressible));
            } catch (Exception e) {
                println "Exception from criteriaQuery.select(builder.tuple(root.get(\"id\").alias(\"employeeId\")));"
                e.printStackTrace()
            }
            /*
            following works
             */
            try {
                criteriaQuery.select(builder.tuple(root.get("id").alias("employeeId"), root.get("name").alias("employeeName")));
            } catch (Exception e) {
                println("NO error here...")
                e.printStackTrace()
            }
        }

    }

}


