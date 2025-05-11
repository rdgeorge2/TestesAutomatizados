package Projeto.TestesAutomatizados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Projeto.TestesAutomatizados.repository.SeriesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExcluirSerieService {

    private static final Logger logger = LoggerFactory.getLogger(ExcluirSerieService.class);
    private final SeriesRepository repository;

    @Autowired
    public ExcluirSerieService(SeriesRepository repository, BuscarSeriesService buscarSeriesService) {
        this.repository = repository;
    }

    public void excluir(Long id) {
        repository.findById(id).ifPresentOrElse(serie -> {
            repository.delete(serie);
            logger.info("Série com ID {} excluída com sucesso.", id);
        }, () -> {
            logger.error("Erro: Série com ID {} não encontrada.", id);
            throw new RuntimeException("Série com ID " + id + " não encontrada.");
        });
    }
}