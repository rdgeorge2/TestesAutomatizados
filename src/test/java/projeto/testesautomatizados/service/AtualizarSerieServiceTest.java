package projeto.testesautomatizados.service;

import projeto.testesautomatizados.dto.AtualizarSerieDTO;
import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;
import projeto.testesautomatizados.service.AtualizarSerieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AtualizarSerieServiceTest {

    private AtualizarSerieService atualizarSerieService;
    private SeriesRepository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(SeriesRepository.class);
        atualizarSerieService = new AtualizarSerieService(repository);
    }

    @DisplayName("Deve lançar exceção quando o título for vazio")
    @Test
    void deveLancarExcecaoQuandoTituloForVazio() {
        Long id = 2L;
        AtualizarSerieDTO dto = new AtualizarSerieDTO();
        dto.setTitulo("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> atualizarSerieService.atualizar(id, dto));

        assertEquals("O título da série não pode ser vazio", exception.getMessage());
    }

    @DisplayName("Deve lançar exceção quando a série não for encontrada")
    @Test
    void deveLancarExcecaoQuandoSerieNaoForEncontrada() {
        Long id = 2L;
        AtualizarSerieDTO dto = new AtualizarSerieDTO();
        dto.setTitulo("Novo Título");

        SerieNaoEncontradaException exception = assertThrows(SerieNaoEncontradaException.class,
                () -> atualizarSerieService.atualizar(id, dto));

        assertEquals("Série com ID 2 não encontrada.", exception.getMessage());
    }

    @DisplayName("Deve atualizar a série com sucesso")
    @Test
    void deveAtualizarSerieComSucesso() {
        Long id = 1L;
        Serie serieExistente = new Serie();
        serieExistente.setId(id);
        serieExistente.setTitulo("Título Antigo");

        AtualizarSerieDTO dto = new AtualizarSerieDTO();
        dto.setTitulo("Título Atualizado");

        Mockito.when(repository.findById(id)).thenReturn(java.util.Optional.of(serieExistente));
        Mockito.when(repository.save(serieExistente)).thenReturn(serieExistente);

        Serie resultado = atualizarSerieService.atualizar(id, dto);

        assertNotNull(resultado);
        assertEquals("Título Atualizado", resultado.getTitulo());
    }
}
