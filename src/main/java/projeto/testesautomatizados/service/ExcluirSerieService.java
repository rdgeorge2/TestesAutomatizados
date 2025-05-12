package projeto.testesautomatizados.service;

import projeto.testesautomatizados.exception.SerieNaoEncontradaException;
import projeto.testesautomatizados.repository.SeriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExcluirSerieService {

    private static final Logger logger = LoggerFactory.getLogger(ExcluirSerieService.class);
    private final SeriesRepository repository;

    public ExcluirSerieService(SeriesRepository repository) {
        this.repository = repository;
    }

    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        repository.findById(id).ifPresentOrElse(serie -> {
            repository.delete(serie);
            logger.info("Série com ID {} excluída com sucesso.", id);
        }, () -> {
            logger.error("Erro: Série com ID {} não encontrada.", id);
            throw new SerieNaoEncontradaException("Série com ID " + id + " não encontrada.");
        });
    }
}
