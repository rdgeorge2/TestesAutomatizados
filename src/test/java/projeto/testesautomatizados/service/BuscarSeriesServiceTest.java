package projeto.testesautomatizados.service;

import projeto.testesautomatizados.dto.SerieDTO;
import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 public class BuscarSeriesServiceTest {

    private BuscarSeriesService service;
    private SeriesRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(SeriesRepository.class);
        service = new BuscarSeriesService(repository);
    }

    @DisplayName("Deve retornar série 'MacGyver' buscada por ID com sucesso")
    @Test
    void deveRetornarSerieMacGyverPorIdComSucesso() {
        Long id = 1L;
        Serie serie = new Serie("MacGyver", "Série de ação dos anos 80", 7, 1985, "MacGyver (1985–1992)");
        serie.setId(id);
        when(repository.findById(id)).thenReturn(Optional.of(serie));

        Serie serieRetornada = service.buscarSeriePorId(id);

        assertNotNull(serieRetornada);
        assertEquals(id, serieRetornada.getId());
        assertEquals("MacGyver", serieRetornada.getTitulo());
    }

    @DisplayName("Deve lançar exceção ao buscar 'MacGyver' com ID inexistente")
    @Test
    void deveLancarExcecaoQuandoMacGyverNaoEncontrado() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        SerieNaoEncontradaException exception = assertThrows(
                SerieNaoEncontradaException.class, () -> service.buscarSeriePorId(id));

        assertNotNull(exception);
        assertEquals("Série com ID " + id + " não encontrada", exception.getMessage());
    }

    @DisplayName("Deve retornar todas as séries, incluindo 'MacGyver'")
    @Test
    void deveBuscarTodasAsSeriesIncluindoMacGyver() {
        Serie serie1 = new Serie("MacGyver", "Série de ação dos anos 80", 7, 1985, "MacGyver (1985–1992)");
        Serie serie2 = new Serie("Stranger Things", "Série de ficção e suspense", 4, 2016, "Stranger Things");
        when(repository.findAll()).thenReturn(Arrays.asList(serie1, serie2));

        List<SerieDTO> seriesDTO = service.buscarTodasAsSeries();

        assertNotNull(seriesDTO);
        assertEquals(2, seriesDTO.size());
        assertEquals("MacGyver", seriesDTO.get(0).getTitulo());
        assertEquals("Stranger Things", seriesDTO.get(1).getTitulo());
    }

    @DisplayName("Deve retornar lista vazia quando não houver séries cadastradas")
    @Test
    void deveRetornarListaVaziaQuandoNaoHouverSeries() {
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<SerieDTO> seriesRetornadas = service.buscarTodasAsSeries();

        assertNotNull(seriesRetornadas);
        assertTrue(seriesRetornadas.isEmpty());
    }
}
