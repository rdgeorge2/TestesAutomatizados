package projeto.testesautomatizados.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import projeto.testesautomatizados.model.Serie;

import java.util.List;
import java.util.Optional;

@ActiveProfiles("integration-test")
@DataJpaTest
public class SeriesRepositoryTest {

    @Autowired
    private SeriesRepository repository;

    private Serie serie;

    @BeforeEach
    public void setUp() {

        repository.save(new Serie("Breaking Bad", "Drama", 5, 2008, "Vince Gilligan"));
        repository.save(new Serie("Stranger Things", "Ficção", 4, 2016, "Irmãos Duffer"));
        repository.save(new Serie("This Is Us", "Drama", 6, 2016, "Dan Fogelman"));
    }

    @DisplayName("Deve salvar uma série válida com sucesso")
    @Test
    void deveSalvarUmaSerieValidaComSucesso() {
        serie = new Serie("Narcos", "Crime", 3, 2015, "Chris Brancato");
        Serie serieSalva = repository.save(serie);

        Assertions.assertNotNull(serieSalva);
        Assertions.assertNotNull(serieSalva.getId());

        Optional<Serie> serieOptional = repository.findById(serieSalva.getId());
        Assertions.assertTrue(serieOptional.isPresent());
        Assertions.assertEquals(serie.getTitulo(), serieOptional.get().getTitulo());
    }


    @DisplayName("Deve buscar todas as séries com sucesso")
    @Test
    void deveBuscarTodasAsSeriesComSucesso() {
        List<Serie> series = repository.findAll();
        Assertions.assertNotNull(series);
        Assertions.assertEquals(3, series.size());
    }

    @DisplayName("Deve atualizar uma série com sucesso")
    @Test
    void deveAtualizarUmaSerieComSucesso() {

        Serie serieExistente = new Serie("MacGyver", "Aventura", 7, 1985, "Lee David Zlotoff");
        Serie serieSalva = repository.save(serieExistente);

        serieSalva.setTitulo("MacGyver - Final");
        Serie serieAtualizada = repository.save(serieSalva);

         Assertions.assertEquals(serieSalva.getId(), serieAtualizada.getId());
        Assertions.assertEquals("MacGyver - Final", serieAtualizada.getTitulo());
    }
    @DisplayName("Deve deletar uma série com sucesso")
    @Test
    void deveDeletarUmaSerieComSucesso() {
        Serie serieSalva = new Serie("MacGyver", "Aventura", 7, 1985, "Lee David Zlotoff");
        Serie serieSalvaNoRepo = repository.save(serieSalva);

        repository.deleteById(serieSalvaNoRepo.getId());
        Optional<Serie> serieRetornada = repository.findById(serieSalvaNoRepo.getId());

        Assertions.assertTrue(serieRetornada.isEmpty());
    }

    @DisplayName("Deve buscar séries por gênero com sucesso")
    @Test
    void deveBuscarSeriesPorGeneroComSucesso() {
        String genero = "Drama";
        List<Serie> seriesDrama = repository.findByGenero(genero);

        Assertions.assertNotNull(seriesDrama);
        Assertions.assertTrue(seriesDrama.size() > 0);
        seriesDrama.forEach(serie -> Assertions.assertEquals(genero, serie.getGenero()));
    }

    @DisplayName("Deve buscar série por título ignorando case")
    @Test
    void deveBuscarSeriePorTituloIgnorandoCase() {
        String titulo = "this is us";
        Optional<Serie> serieOptional = repository.findByTituloIgnoreCase(titulo);

        Assertions.assertTrue(serieOptional.isPresent());
        Assertions.assertEquals("This Is Us", serieOptional.get().getTitulo());
    }
}
