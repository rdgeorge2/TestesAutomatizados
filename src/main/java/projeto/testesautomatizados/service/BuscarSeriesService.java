package projeto.testesautomatizados.service;

import org.springframework.stereotype.Service;
import projeto.testesautomatizados.dto.SerieDTO;
import projeto.testesautomatizados.dto.mapper.SerieMapper;
import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BuscarSeriesService {

    private final SeriesRepository seriesRepository;


    public BuscarSeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<SerieDTO> buscarTodasAsSeries() {
        return seriesRepository.findAll().stream()
                .map(serie -> SerieMapper.toSerieDTO(serie)).collect(Collectors.toList());
    }

    public Serie buscarSeriePorId(Long id) {
        Optional<Serie> serieOptional = seriesRepository.findById(id);
        return serieOptional.orElseThrow(() -> new SerieNaoEncontradaException("Série com ID " + id +
                " não encontrada"));
    }
}
