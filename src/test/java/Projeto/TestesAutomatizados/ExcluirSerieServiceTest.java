package Projeto.TestesAutomatizados;

import Projeto.TestesAutomatizados.exception.SerieNaoEncontradaException;
import Projeto.TestesAutomatizados.repository.SeriesRepository;
import Projeto.TestesAutomatizados.service.BuscarSeriesService;
import Projeto.TestesAutomatizados.service.ExcluirSerieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class ExcluirSerieServiceTest {

    ExcluirSerieService excluirSerieService;
    SeriesRepository repository;
    BuscarSeriesService buscarSeriesService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(SeriesRepository.class);
        buscarSeriesService = Mockito.mock(BuscarSeriesService.class);
        excluirSerieService = new ExcluirSerieService(repository, buscarSeriesService);
    }

    @DisplayName("Deve encontrar a série no banco de dados e excluí-la")
    @Test
    void deveExcluirSerieQuandoEncontrada() {
        Long id = 1L;

        excluirSerieService.excluir(id);

        Mockito.verify(buscarSeriesService, Mockito.times(1)).buscarSeriePorId(id);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);

        InOrder inOrder = Mockito.inOrder(buscarSeriesService, repository);
        inOrder.verify(buscarSeriesService).buscarSeriePorId(id);
        inOrder.verify(repository).deleteById(id);
    }

    @DisplayName("Deve lançar exceção ao não encontrar a série")
    @Test
    void deveLancarExcecaoQuandoSerieNaoEncontrada() {
        Long id = 1L;

        Mockito.doThrow(new SerieNaoEncontradaException("Série com ID " + id + " não encontrada"))
                .when(buscarSeriesService).buscarSeriePorId(id);

        SerieNaoEncontradaException exception = Assertions.assertThrows(SerieNaoEncontradaException.class,
                () -> excluirSerieService.excluir(id));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Série com ID 1 não encontrada", exception.getMessage());
    }
}