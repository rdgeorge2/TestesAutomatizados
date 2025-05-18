package projeto.testesautomatizados.service;

import projeto.testesautomatizados.dto.AtualizarSerieDTO;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;
import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarSerieService {

    private final SeriesRepository repository;

    @Autowired
    public AtualizarSerieService(SeriesRepository repository) {
        this.repository = repository;
    }

    public Serie atualizar(Long id, AtualizarSerieDTO dto) {
        if (dto.getTitulo() == null || dto.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título da série não pode ser vazio");
        }

        return repository.findById(id).map(serie -> {
            serie.setTitulo(dto.getTitulo());

            if (dto.getGenero() != null) {
                serie.setGenero(dto.getGenero());
            }
            if (dto.getTemporadas() != null) {
                serie.setTemporadas(dto.getTemporadas());
            }
            if (dto.getAnoLancamento() != null) {
                serie.setAnoLancamento(dto.getAnoLancamento());
            }
            if (dto.getCriador() != null) {
                serie.setCriador(dto.getCriador());
            }

            return repository.save(serie);
        }).orElseThrow(() -> new SerieNaoEncontradaException("Série com ID " + id + " não encontrada."));
    }
}
