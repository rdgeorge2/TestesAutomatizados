package projeto.testesautomatizados.dto.mapper;

import projeto.testesautomatizados.dto.CriarSerieRequestDTO;
import projeto.testesautomatizados.model.Serie;

public class CriarSerieRequestMapper {

    public static Serie toEntity(CriarSerieRequestDTO dto) {
        Serie serie = new Serie();
        serie.setTitulo(dto.getTitulo());
        serie.setGenero(dto.getGenero());
        serie.setTemporadas(dto.getTemporadas());
        serie.setAnoLancamento(dto.getAnoLancamento());
        return serie;
    }
}
