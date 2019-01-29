package cn.itsource._02one2many2;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dir_id")
    private ProductDir dir;

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

    public ProductDir getDir() {
        return dir;
    }

    public void setDir(ProductDir dir) {
        this.dir = dir;
    }
}
