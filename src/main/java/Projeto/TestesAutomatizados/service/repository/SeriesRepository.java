package Projeto.TestesAutomatizados.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Projeto.TestesAutomatizados.service.model.Serie;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
}