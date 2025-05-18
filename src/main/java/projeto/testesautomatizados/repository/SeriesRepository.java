package projeto.testesautomatizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.testesautomatizados.model.Serie;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Serie, Long> {

    List<Serie> findByGenero(String genero);

    Optional<Serie> findByTituloIgnoreCase(String titulo);

    List<Serie> findAll();

    Optional<Serie> findById(Long id);

    void deleteById(Long id);

    <S extends Serie> S save(S entity);
}
