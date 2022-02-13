package com.btech.ecommerce.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Category implements Serializable {

    @Size(min = 1, max = 18, message = "Campo name deve conter entre 1 e 18 caracteres")
    @Column(length = 18, nullable = false)
    private String code;

    @Size(min = 1, max = 120, message = "Campo name deve conter entre 3 e 120 caracteres")
    @Column(length = 120, nullable = false)
    private String name;

    public Category() {}

    public Category(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category that = (Category) o;

        if (!code.equals(that.code)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}

class CategoryPK() {

}