package Projeto.TestesAutomatizados.service;

import org.springframework.stereotype.Service;
import Projeto.TestesAutomatizados.dto.SerieDTO;
import Projeto.TestesAutomatizados.dto.mapper.SerieMapper;
import Projeto.TestesAutomatizados.exception.SerieNaoEncontradaException;
import Projeto.TestesAutomatizados.model.Serie;
import Projeto.TestesAutomatizados.repository.SeriesRepository;
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
