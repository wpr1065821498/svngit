package cn.itsource._04many2many;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="t_student")
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
