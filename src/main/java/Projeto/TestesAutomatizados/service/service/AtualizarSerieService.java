package Projeto.TestesAutomatizados.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Projeto.TestesAutomatizados.service.dto.AtualizarSerieDTO;
import Projeto.TestesAutomatizados.service.model.Serie;
import Projeto.TestesAutomatizados.service.repository.SeriesRepository;

@Service
public class AtualizarSerieService {

    private final SeriesRepository repository;

    @Autowired
    public AtualizarSerieService(SeriesRepository repository) {
        this.repository = repository;
    }

    public Serie atualizar(Long id, AtualizarSerieDTO dto) {
        return repository.findById(id).map(serie -> {
            if (dto.getTitulo() != null) {
                serie.setTitulo(dto.getTitulo());
            }
            if (dto.getGenero() != null) {
                serie.setGenero(dto.getGenero());
            }
            if (dto.getTemporadas() != null) {
                serie.setTemporadas(dto.getTemporadas());
            }
            if (dto.getAnoLancamento() != null) {
                serie.setAnoLancamento(dto.getAnoLancamento());
            }
            return repository.save(serie);
        }).orElseThrow(() -> new RuntimeException("Série com ID " + id + " não encontrada."));
    }
}
