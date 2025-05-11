package Projeto.TestesAutomatizados.service.service;

import org.springframework.stereotype.Service;
import Projeto.TestesAutomatizados.service.model.Serie;
import Projeto.TestesAutomatizados.service.repository.SeriesRepository;

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
