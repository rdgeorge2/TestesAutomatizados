package projeto.testesautomatizados.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode ser vazio.")
    @Column(nullable = false, unique = true)
    private String titulo;

    @NotBlank(message = "O gênero não pode ser vazio.")
    @Column(nullable = false)
    private String genero;

    @NotNull(message = "O número de temporadas não pode ser nulo.")
    @Column(nullable = false)
    private Integer temporadas;

    @NotNull(message = "O ano de lançamento não pode ser nulo.")
    @Column(nullable = false)
    private Integer anoLancamento;

    @NotBlank(message = "O criador não pode ser vazio.")
    @Column(nullable = false)
    private String criador;

     public Serie(Long id, String titulo, String genero, Integer temporadas, String criador) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.anoLancamento = anoLancamento;
        this.criador = criador;
    }

      public Serie(String titulo, String genero, Integer temporadas, Integer anoLancamento, String criador) {
        this.titulo = titulo;
        this.genero = genero;
        this.temporadas = temporadas;
        this.anoLancamento = anoLancamento;
        this.criador = criador;
    }

      public Serie() {}

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", temporadas=" + temporadas +
                ", anoLancamento=" + anoLancamento +
                ", criador='" + criador + '\'' +
                '}';
    }
}
