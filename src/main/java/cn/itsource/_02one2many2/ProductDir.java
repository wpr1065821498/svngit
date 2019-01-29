package cn.itsource._02one2many2;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ProductDir {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    /**
     * mappedBy: 我当前一方放弃维护关系，由对方domain中dir这个字段来维护
     */
    @OneToMany(mappedBy = "dir")
    private Set<Product> products = new HashSet<>();

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
