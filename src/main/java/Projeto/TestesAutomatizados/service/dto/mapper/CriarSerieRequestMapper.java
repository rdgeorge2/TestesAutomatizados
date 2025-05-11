package Projeto.TestesAutomatizados.service.dto.mapper;

import Projeto.TestesAutomatizados.service.dto.CriarSerieRequestDTO;
import Projeto.TestesAutomatizados.service.model.Serie;

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
