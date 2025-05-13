package projeto.testesautomatizados.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.testesautomatizados.dto.CriarSerieRequestDTO;
import projeto.testesautomatizados.dto.AtualizarSerieDTO;
import projeto.testesautomatizados.dto.SerieDTO;
import projeto.testesautomatizados.dto.mapper.CriarSerieRequestMapper;
import projeto.testesautomatizados.model.Serie;
import projeto.testesautomatizados.service.AtualizarSerieService;
import projeto.testesautomatizados.service.BuscarSeriesService;
import projeto.testesautomatizados.service.CriarSerieService;
import projeto.testesautomatizados.service.ExcluirSerieService;

import java.util.List;

@RestController
@RequestMapping("/series")

public class SerieController {

    private final CriarSerieService criarSerieService;
    private final AtualizarSerieService atualizarSerieService;
    private final BuscarSeriesService buscarSeriesService;
    private final ExcluirSerieService excluirSerieService;

    public SerieController(CriarSerieService criarSerieService, AtualizarSerieService atualizarSerieService, BuscarSeriesService buscarSeriesService, ExcluirSerieService excluirSerieService) {
        this.criarSerieService = criarSerieService;
        this.atualizarSerieService = atualizarSerieService;
        this.buscarSeriesService = buscarSeriesService;
        this.excluirSerieService = excluirSerieService;
    }

    @PostMapping
    public ResponseEntity<Serie> criarSerie(@RequestBody @Valid CriarSerieRequestDTO dto) {
        Serie novaSerie = criarSerieService.criarSerie(CriarSerieRequestMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSerie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarSerieDTO dto) {
        Serie serieAtualizada = atualizarSerieService.atualizar(id, dto);
        return ResponseEntity.ok(serieAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        excluirSerieService.excluir(id);
        return ResponseEntity.ok("Série com ID " + id + " foi excluída com sucesso.");
    }

    @GetMapping
    public List<SerieDTO> buscarTodasAsSeries() {
        return buscarSeriesService.buscarTodasAsSeries();
    }

    @GetMapping("/{id}")
    public Serie buscarSeriePorId(@PathVariable (value = "id") Long id) {
        return buscarSeriesService.buscarSeriePorId(id);
    }

}
