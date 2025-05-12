package projeto.testesautomatizados.dto;

import jakarta.validation.constraints.*;

public class CriarSerieRequestDTO {

    @NotBlank(message = "Título não pode ser vazio")
    @Size(min = 2, message = "Título deve conter pelo menos 2 caracteres")
    private String titulo;

    @NotBlank(message = "Gênero não pode ser vazio")
    @Size(min = 3, message = "Gênero deve conter pelo menos 3 caracteres")
    private String genero;

    @NotNull(message = "Número de temporadas não pode ser nulo")
    @Min(value = 1, message = "A série deve ter pelo menos 1 temporada")
    private Integer temporadas;

    @NotNull(message = "Ano de lançamento não pode ser nulo")
    @Min(value = 1900, message = "Ano de lançamento inválido")
    @Max(value = 2100, message = "Ano de lançamento inválido")
    private Integer anoLancamento;

    public CriarSerieRequestDTO() {
    }

    public CriarSerieRequestDTO(String titulo, String genero, Integer temporadas, Integer anoLancamento) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.anoLancamento = anoLancamento;
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
        return temporadas;
    }

    public void setTemporadas(Integer temporadas) {
        this.temporadas = temporadas;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
