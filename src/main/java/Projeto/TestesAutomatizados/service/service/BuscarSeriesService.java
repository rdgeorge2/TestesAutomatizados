package Projeto.TestesAutomatizados.service.service;

import org.springframework.stereotype.Service;
import Projeto.TestesAutomatizados.service.dto.SerieDTO;
import Projeto.TestesAutomatizados.service.dto.mapper.SerieMapper;
import Projeto.TestesAutomatizados.service.exception.SerieNaoEncontradaException;
import Projeto.TestesAutomatizados.service.model.Serie;
import Projeto.TestesAutomatizados.service.repository.SeriesRepository;
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
