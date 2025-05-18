package projeto.testesautomatizados.dto.mapper;

import projeto.testesautomatizados.dto.SerieDTO;
import projeto.testesautomatizados.model.Serie;

public class SerieMapper {

    public static SerieDTO toSerieDTO(Serie serie) {
        return new SerieDTO(
                serie.getTitulo(),
                serie.getGenero(),
                serie.getTemporadas(),
                serie.getAnoLancamento(),
                serie.getCriador()
        );
    }
}
