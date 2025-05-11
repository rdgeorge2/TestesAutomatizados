package Projeto.TestesAutomatizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Projeto.TestesAutomatizados.model.Serie;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
}