package projeto.testesautomatizados.dto;

import jakarta.validation.constraints.*;

public class AtualizarSerieDTO {

    @Size(min = 2, message = "O título precisa ter 2 caracteres")
    private String titulo;

    @Size(min = 3, message = "O gênero precisa ter no mínimo 3 caracteres")
    private String genero;

    @Min(value = 1, message = "a série precisa ter no mínimo 1 temporada")
    private Integer temporada;

    @Min(value = 1900, message = "Ano inválido, precisa sem maior ou igual 1900")
    @Max(value = 2100, message = "Ano inválido, precisa sem menor ou igual 2100")
    private Integer anoLancamento;

    public AtualizarSerieDTO() {}

    public AtualizarSerieDTO(String titulo, String genero, Integer temporada, Integer anoLancamento) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporada = temporada;
        this.anoLancamento = anoLancamento;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Integer getTemporadas() { return temporada; }
    public void setTemporadas(Integer temporada) { this.temporada = temporada; }

    public Integer getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(Integer anoLancamento) { this.anoLancamento = anoLancamento; }
}
