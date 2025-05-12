package projeto.testesautomatizados.service;

import org.springframework.stereotype.Service;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;

@Service
public class CriarSerieService {

    private final SeriesRepository repository;

    public CriarSerieService(SeriesRepository repository) {
        this.repository = repository;
    }

    public Serie criarSerie(Serie serie) {
        if (serie == null) {
            throw new IllegalArgumentException("A série não pode ser nula");
        }

        if (serie.getTitulo() == null || serie.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título da série não pode ser vazio");
        }

        return repository.save(serie);
    }
}

