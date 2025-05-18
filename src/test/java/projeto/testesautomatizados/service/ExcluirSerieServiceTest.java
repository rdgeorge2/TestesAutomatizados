package projeto.testesautomatizados.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExcluirSerieServiceTest {

    private ExcluirSerieService excluirSerieService;
    private SeriesRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(SeriesRepository.class);
        excluirSerieService = new ExcluirSerieService(repository);
    }

    @DisplayName("Deve lançar exceção quando a série não for encontrada")
    @Test
    void deveLancarExcecaoQuandoSerieNaoEncontrada() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        SerieNaoEncontradaException exception = assertThrows(SerieNaoEncontradaException.class,
                () -> excluirSerieService.excluir(id));

        assertEquals("Série com ID 1 não encontrada.", exception.getMessage());
    }

    @DisplayName("Não deve chamar delete quando a série não for encontrada")
    @Test
    void naoDeveChamarDeleteQuandoBuscaFalhar() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(SerieNaoEncontradaException.class, () -> excluirSerieService.excluir(id));

        verify(repository, times(0)).delete(any());
    }

    @DisplayName("Deve lançar exceção quando o ID for nulo")
    @Test
    void deveLancarExcecaoQuandoIdForNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> excluirSerieService.excluir(null));

        assertEquals("ID não pode ser nulo", exception.getMessage());
    }

    @DisplayName("Deve chamar o método delete na ordem correta quando a série for encontrada")
    @Test
    void deveChamarDeleteNaOrdemCorretaQuandoSerieEncontrada() {
        Long id = 1L;
        Serie serie = new Serie(id, "Breaking Bad", "Drama", 5, "Vince Gilligan");

        when(repository.findById(id)).thenReturn(java.util.Optional.of(serie));

        excluirSerieService.excluir(id);

        InOrder inOrder = inOrder(repository);
        inOrder.verify(repository).findById(id);
        inOrder.verify(repository).delete(serie);
    }
}
