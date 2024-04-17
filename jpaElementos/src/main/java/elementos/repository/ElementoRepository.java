package elementos.repository;

import elementos.entity.Elemento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ElementoRepository extends CrudRepository<Elemento, Long> {
    List<Elemento> findElementosByNameContains(String name);
    List<Elemento> findElementosByNameStartingWith(String name);

    List<Elemento> findElementosByNameAndEjemplo(String name, String e);

    List<Elemento> findElementosByNameStartingWithAndEjemploContains(String name, String e);

    Elemento save(Elemento elemento); // Crear o Actualizar
    void deleteById(Long id);   // Eliminar por ID
}