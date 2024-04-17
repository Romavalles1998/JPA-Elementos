package elementos.entity;

import jakarta.persistence.*;

@Entity
public class Elemento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String ejemplo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_type")
    private Type type;

    public Elemento(){

    }
    public Elemento(String name, String ejemplo) {
        this.name = name;
        this.ejemplo = ejemplo;
    }

    public Elemento(Long id, String name, String ejemplo) {
        this.id = id;
        this.name = name;
        this.ejemplo = ejemplo;
    }

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

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String color) {
        this.ejemplo = color;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return name + " - " + ejemplo;
    }
}
