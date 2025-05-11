package Projeto.TestesAutomatizados.service;

import Projeto.TestesAutomatizados.service.dto.AtualizarSerieDTO;
import Projeto.TestesAutomatizados.service.model.Serie;
import Projeto.TestesAutomatizados.service.repository.SeriesRepository;
import Projeto.TestesAutomatizados.service.service.AtualizarSerieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 public class AtualizarSerieServiceTest {

    private SeriesRepository repository;
    private AtualizarSerieService atualizarSerieService;

    @BeforeEach
    void setUp() {
        repository = mock(SeriesRepository.class);
        atualizarSerieService = new AtualizarSerieService(repository);
    }

    @Test
    void deveAtualizarSerieQuandoIdExistir() {
        Long id = 1L;
        Serie serieExistente = new Serie();
        serieExistente.setId(id);
        serieExistente.setTitulo("Título Antigo");

        AtualizarSerieDTO dto = new AtualizarSerieDTO();
        dto.setTitulo("Título Atualizado");

        when(repository.findById(id)).thenReturn(Optional.of(serieExistente));
        when(repository.save(any(Serie.class))).thenAnswer(i -> i.getArgument(0));

        Serie resultado = atualizarSerieService.atualizar(id, dto);

        assertNotNull(resultado);
        assertEquals("Título Atualizado", resultado.getTitulo());
        verify(repository).save(serieExistente);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        Long id = 100L;
        AtualizarSerieDTO dto = new AtualizarSerieDTO();
        dto.setTitulo("Nova Série");

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                atualizarSerieService.atualizar(id, dto)
        );

        assertEquals("Série com ID 100 não encontrada.", exception.getMessage());
        verify(repository, never()).save(any(Serie.class));
    }
}