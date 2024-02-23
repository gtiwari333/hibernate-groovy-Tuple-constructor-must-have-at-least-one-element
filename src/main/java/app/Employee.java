package app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class Employee {

    @Id
    Long id;
    String name;

    public Employee() {
        this.id = System.currentTimeMillis();
        this.name = this.id + " NAME ";
    }


}
