package projeto.testesautomatizados.service;

import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.repository.SeriesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CriarSerieServiceTest {

    CriarSerieService service;
    SeriesRepository repository;

    @BeforeEach
    public void setUp() {
        repository = Mockito.mock(SeriesRepository.class);
        service = new CriarSerieService(repository);
    }

    @DisplayName("Dado uma série válida, deve criar essa série com sucesso")
    @Test
    void deveCriarSerieComSucesso() {
        Serie serieValida = umaSerie();
        Serie serieSalvaNoBanco = umaSerie();
        serieSalvaNoBanco.setId(1L);

        Mockito.when(repository.save(serieValida)).thenReturn(serieSalvaNoBanco);

        Serie resultado = service.criarSerie(serieValida);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(serieSalvaNoBanco, resultado);
        Assertions.assertEquals("Breaking Bad", resultado.getTitulo());
        Assertions.assertEquals("Drama", resultado.getGenero());
        Mockito.verify(repository, Mockito.times(1)).save(serieValida);
    }

    @DisplayName("Dado uma série nula, deve lançar exceção ao tentar criar")
    @Test
    void deveLancarExcecaoQuandoSerieForNula() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.criarSerie(null);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @DisplayName("Dado uma série sem título, deve lançar exceção ao tentar criar")
    @Test
    void deveLancarExcecaoQuandoTituloForVazio() {
        Serie serieSemTitulo = umaSerie();
        serieSemTitulo.setTitulo("");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.criarSerie(serieSemTitulo);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @DisplayName("Dado uma série vazia (sem campos preenchidos), deve lançar exceção")
    @Test
    void deveLancarExcecaoQuandoSerieEstiverVazia() {
        Serie serieVazia = new Serie();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.criarSerie(serieVazia);
        });

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    private Serie umaSerie() {
        return new Serie(null, "Breaking Bad", "Drama", 5, "Vince Gilligan");
    }
}
