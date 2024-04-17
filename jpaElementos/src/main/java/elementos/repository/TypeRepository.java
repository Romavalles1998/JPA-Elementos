package elementos.repository;
import elementos.entity.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TypeRepository extends CrudRepository<Type, Long> {

}