package Projeto.TestesAutomatizados.service.dto.mapper;

import Projeto.TestesAutomatizados.service.dto.SerieDTO;
import Projeto.TestesAutomatizados.service.model.Serie;

public class SerieMapper {

    public static SerieDTO toSerieDTO(Serie serie) {
        SerieDTO serieDTO = new SerieDTO(
                serie.getTitulo(), serie.getGenero(),
                serie.getTemporadas(), serie.getAnoLancamento()
        );

        return serieDTO;
    }
}


