package les.donations.backendspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CATEGORIES")
@Entity
public class Category {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE")
    private boolean active;

    public Category() {}

    public Category(String code, String name, String description) throws IllegalArgumentException {
        setCode(code);
        setName(name);
        setDescription(description);
        this.active = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }

    public void setCode(String code) throws IllegalArgumentException{
        if ((code == null) || (code.isEmpty())) throw new IllegalArgumentException("The code can't be null or empty");
        this.code = code;
    }

    public void setName(String name) throws IllegalArgumentException{
        if ((name == null) || (name.isEmpty())) throw new IllegalArgumentException("The name can't be null or empty");
        this.name = name;
    }

    public void setDescription(String description) throws IllegalArgumentException{
        if ((description == null) || (description.isEmpty())) throw new IllegalArgumentException("The description can't be null or empty");
        this.description = description;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
