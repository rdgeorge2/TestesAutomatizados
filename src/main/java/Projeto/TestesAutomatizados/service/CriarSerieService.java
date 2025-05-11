package Projeto.TestesAutomatizados.service;

import org.springframework.stereotype.Service;
import Projeto.TestesAutomatizados.model.Serie;
import Projeto.TestesAutomatizados.repository.SeriesRepository;

@Service
public class CriarSerieService {

    private final SeriesRepository repository;

    public CriarSerieService(SeriesRepository repository) {
        this.repository = repository;
    }

    public Serie criarSerie(Serie serie) {
        return repository.save(serie);
    }
}
