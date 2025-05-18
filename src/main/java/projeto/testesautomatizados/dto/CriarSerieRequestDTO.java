package projeto.testesautomatizados.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CriarSerieRequestDTO {

    @NotNull(message = "O título não pode ser nulo")
    @NotEmpty(message = "O título não pode estar vazio")
    private String titulo;

    private String genero;
    private int temporadas;
    private int anoLancamento;
    private String criador;

     public CriarSerieRequestDTO(String titulo, String genero, int temporadas, int anoLancamento, String criador) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
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

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }
}
