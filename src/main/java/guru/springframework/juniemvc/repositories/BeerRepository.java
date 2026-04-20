package guru.springframework.juniemvc.repositories;

import guru.springframework.juniemvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Integer> {
}
