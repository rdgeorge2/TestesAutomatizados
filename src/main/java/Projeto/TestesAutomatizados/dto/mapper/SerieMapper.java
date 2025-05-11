package Projeto.TestesAutomatizados.dto.mapper;

import Projeto.TestesAutomatizados.dto.SerieDTO;
import Projeto.TestesAutomatizados.model.Serie;

public class SerieMapper {

    public static SerieDTO toSerieDTO(Serie serie) {
        SerieDTO serieDTO = new SerieDTO(
                serie.getTitulo(), serie.getGenero(),
                serie.getTemporadas(), serie.getAnoLancamento()
        );

        return serieDTO;
    }
}


