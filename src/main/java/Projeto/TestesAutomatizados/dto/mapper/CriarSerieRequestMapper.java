package Projeto.TestesAutomatizados.dto.mapper;

import Projeto.TestesAutomatizados.dto.CriarSerieRequestDTO;
import Projeto.TestesAutomatizados.model.Serie;

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
