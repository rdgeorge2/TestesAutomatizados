package projeto.testesautomatizados.dto.mapper;

import projeto.testesautomatizados.dto.CriarSerieRequestDTO;
import projeto.testesautomatizados.model.Serie;

public class CriarSerieRequestMapper {

    public static Serie toEntity(CriarSerieRequestDTO dto) {
        Long id = null;
        Serie serie = new Serie(id, dto.getTitulo(), dto.getGenero(), dto.getTemporadas(), dto.getCriador());
        return serie;
    }
}
