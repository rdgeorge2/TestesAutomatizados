package projeto.testesautomatizados.dto;

import jakarta.validation.constraints.*;

public class AtualizarSerieDTO {

    @Size(min = 2, message = "O título precisa ter 2 caracteres")
    private String titulo;

    @Size(min = 3, message = "O gênero precisa ter no mínimo 3 caracteres")
    private String genero;

    @Min(value = 1, message = "A série precisa ter no mínimo 1 temporada")
    private Integer temporada;

    @Min(value = 1900, message = "Ano inválido, precisa ser maior ou igual a 1900")
    @Max(value = 2100, message = "Ano inválido, precisa ser menor ou igual a 2100")
    private Integer anoLancamento;

    private String criador;

    public AtualizarSerieDTO() {}

    public AtualizarSerieDTO(String titulo, String genero, Integer temporada, Integer anoLancamento, String criador) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporada = temporada;
        this.anoLancamento = anoLancamento;
        this.criador = criador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getTemporadas() {
        return temporada;
    }

    public void setTemporadas(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }
}
