package cn.itsource._04many2many;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_teacher")
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    /*
     name = "teacher_student":对应中间表的名字
      @JoinColumn(name = "teacher_id"  当前domain对应中间表中外键的列名
      inverseJoinColumns = @JoinColumn(name = "student_id")  对方domain对应中间表中外键的列名
    */
    @JoinTable(name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
