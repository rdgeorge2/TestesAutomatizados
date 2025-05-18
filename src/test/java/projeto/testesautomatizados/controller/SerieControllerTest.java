package projeto.testesautomatizados.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import projeto.testesautomatizados.dto.AtualizarSerieDTO;
import projeto.testesautomatizados.dto.CriarSerieRequestDTO;
import projeto.testesautomatizados.dto.SerieDTO;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.service.AtualizarSerieService;
import projeto.testesautomatizados.service.BuscarSeriesService;
import projeto.testesautomatizados.service.CriarSerieService;
import projeto.testesautomatizados.service.ExcluirSerieService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SerieControllerTest {

    @InjectMocks
    SerieController controller;

    @Mock
    CriarSerieService criarSerieService;

    @Mock
    AtualizarSerieService atualizarSerieService;

    @Mock
    BuscarSeriesService buscarSeriesService;

    @Mock
    ExcluirSerieService excluirSerieService;

    final String PATH = "/series";

    CriarSerieRequestDTO request;
    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        request = new CriarSerieRequestDTO("Breaking Bad", "Drama", 5, 2008, "Vince Gilligan");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @DisplayName("Deve buscar todas as séries com sucesso")
    @Test
    void deveBuscarTodasAsSeriesComSucesso() throws Exception {
        SerieDTO serie1 = new SerieDTO("Breaking Bad", "Drama", 5, 2008, "Vince Gilligan");
        SerieDTO serie2 = new SerieDTO("Stranger Things", "Ficção", 4, 2016, "Irmãos Duffer");
        List<SerieDTO> series = List.of(serie1, serie2);

        Mockito.when(buscarSeriesService.buscarTodasAsSeries()).thenReturn(series);

        mockMvc.perform(get(PATH).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(series)))
                .andDo(print());
    }

    @DisplayName("Deve buscar série por ID com sucesso")
    @Test
    void deveBuscarSeriePorIdComSucesso() throws Exception {
        Long id = 1L;
        Serie serie = new Serie(id, "Breaking Bad", "Drama", 5, "Vince Gilligan");

        Mockito.when(buscarSeriesService.buscarSeriePorId(id)).thenReturn(serie);

        mockMvc.perform(get(PATH + "/" + id).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(serie)))
                .andDo(print());
    }

    @DisplayName("Deve criar uma série com sucesso")
    @Test
    void deveCriarUmaSerieComSucesso() throws Exception {
        Serie serieCriada = new Serie(1L, "Breaking Bad", "Drama", 5, "Vince Gilligan");

        Mockito.when(criarSerieService.criarSerie(any())).thenReturn(serieCriada);

        mockMvc.perform(post(PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(serieCriada)))
                .andDo(print());
    }

    @DisplayName("Deve retornar BadRequest quando criar série inválida")
    @Test
    void deveRetornarBadRequestQuandoCriarSerieInvalida() throws Exception {
        request.setTitulo(null);

        mockMvc.perform(post(PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @DisplayName("Deve atualizar uma série com sucesso")
    @Test
    void deveAtualizarUmaSerieComSucesso() throws Exception {
        Long id = 1L;
        AtualizarSerieDTO atualizarSerieDTO = new AtualizarSerieDTO("The Wire", "Drama Policial", 5, 2002, "David Simon");
        Serie serieAtualizada = new Serie(id, "The Wire", "Drama Policial", 5, "David Simon");

        Mockito.when(atualizarSerieService.atualizar(Mockito.eq(id), any())).thenReturn(serieAtualizada);

        mockMvc.perform(put(PATH + "/" + id)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizarSerieDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(serieAtualizada)))
                .andDo(print());
    }

    @DisplayName("Deve excluir uma série com sucesso")
    @Test
    void deveExcluirUmaSerieComSucesso() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete(PATH + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string("Série com ID " + id + " foi excluída com sucesso."))
                .andDo(print());

        Mockito.verify(excluirSerieService, times(1)).excluir(id);
    }
}
