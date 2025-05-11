package Projeto.TestesAutomatizados.service;

import Projeto.TestesAutomatizados.service.dto.SerieDTO;
import Projeto.TestesAutomatizados.service.exception.SerieNaoEncontradaException;
import Projeto.TestesAutomatizados.service.model.Serie;
import Projeto.TestesAutomatizados.service.repository.SeriesRepository;
import Projeto.TestesAutomatizados.service.service.BuscarSeriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BuscarSeriesServiceTest {

    private BuscarSeriesService service;
    private SeriesRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(SeriesRepository.class);
        service = new BuscarSeriesService(repository);
    }

    @Test
    void deveRetornarSerieBuscadaPorIdComSucesso() {
        Long id = 1L;
        Serie serie = new Serie(1L, "Ação", "Gênero Ação", 2021, 5);
        when(repository.findById(id)).thenReturn(Optional.of(serie));
        Serie serieRetornada = service.buscarSeriePorId(id);
        assertNotNull(serieRetornada);
        assertEquals(id, serieRetornada.getId());
        assertEquals("Ação", serieRetornada.getTitulo());
    }

    @Test
    void deveLancarExcecaoQuandoSerieNaoEncontrada() {
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        SerieNaoEncontradaException exception = assertThrows(
                SerieNaoEncontradaException.class, () -> service.buscarSeriePorId(id));
        assertNotNull(exception);
        assertEquals("Série com ID " + id + " não encontrada", exception.getMessage());
    }

    @Test
    void deveBuscarTodasAsSeriesComSucesso() {
        Serie serie1 = new Serie(1L, "Ação", "Gênero Ação", 2021, 5);
        Serie serie2 = new Serie(2L, "Comédia", "Gênero Comédia", 2022, 3);
        when(repository.findAll()).thenReturn(Arrays.asList(serie1, serie2));
        List<SerieDTO> seriesDTO = service.buscarTodasAsSeries();
        assertNotNull(seriesDTO);
        assertEquals(2, seriesDTO.size());
        assertEquals("Ação", seriesDTO.get(0).getTitulo());
        assertEquals("Comédia", seriesDTO.get(1).getTitulo());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverSeries() {
        when(repository.findAll()).thenReturn(Arrays.asList());
        List<SerieDTO> seriesRetornadas = service.buscarTodasAsSeries();
        assertNotNull(seriesRetornadas);
        assertTrue(seriesRetornadas.isEmpty());
    }
}
