package projeto.testesautomatizados.service;

import projeto.testesautomatizados.dto.AtualizarSerieDTO;
import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

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

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

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

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(serieExistente));
        Mockito.when(repository.save(serieExistente)).thenReturn(serieExistente);

        Serie resultado = atualizarSerieService.atualizar(id, dto);

        assertNotNull(resultado);
        assertEquals("Título Atualizado", resultado.getTitulo());
    }

    @DisplayName("Deve atualizar todos os campos da série corretamente")
    @Test
    void deveAtualizarTodosOsCamposDaSerie() {
        Long id = 3L;
        Serie serieExistente = new Serie();
        serieExistente.setId(id);
        serieExistente.setTitulo("Série Antiga");
        serieExistente.setGenero("Ação");
        serieExistente.setTemporadas(2);
        serieExistente.setAnoLancamento(2010);
        serieExistente.setCriador("Criador Antigo");

        AtualizarSerieDTO dto = new AtualizarSerieDTO(
                "Série Atualizada", "Drama", 4, 2020, "Novo Criador");

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(serieExistente));
        Mockito.when(repository.save(Mockito.any(Serie.class))).thenReturn(serieExistente);

        Serie resultado = atualizarSerieService.atualizar(id, dto);

        assertNotNull(resultado);
        assertEquals("Série Atualizada", resultado.getTitulo());
        assertEquals("Drama", resultado.getGenero());
        assertEquals(4, resultado.getTemporadas());
        assertEquals(2020, resultado.getAnoLancamento());
        assertEquals("Novo Criador", resultado.getCriador());
    }
}
