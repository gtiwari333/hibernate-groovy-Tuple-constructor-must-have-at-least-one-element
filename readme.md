### Repo for Bug https://hibernate.atlassian.net/browse/HHH-17771

CriteriaBuilder.tuple(Path ) is calling wrong method from SqmCriteriaNodeBuilder

For Groovy -> its calling org.hibernate.query.sqm.internal.SqmCriteriaNodeBuilder.tuple(org.hibernate.query.sqm.SqmExpressible<R>, org.hibernate.query.sqm.tree.expression.SqmExpression<?>...)

For Java -> its calling org.hibernate.query.sqm.internal.SqmCriteriaNodeBuilder.tuple(jakarta.persistence.criteria.Selection<?>[])


### How to Reproduce:

- Run the GroovySBApp
- Run http://localhost:8081/groovy to see the error from Groovy code
- Run http://localhost:8081/java to see no error generated from Java code

