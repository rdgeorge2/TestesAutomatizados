package projeto.testesautomatizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.testesautomatizados.model.Serie;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
}