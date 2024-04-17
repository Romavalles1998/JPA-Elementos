package elementos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Type(){

    }
    @OneToMany(mappedBy = "type", fetch=FetchType.EAGER)
    private List<Elemento> elementos;
    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
        this.elementos = new ArrayList<>();
    }

    public Type(String name) {
        this.name = name;
        this.elementos = new ArrayList<>();
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

    public List<Elemento> getElementos() {
        return elementos;
    }

    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
