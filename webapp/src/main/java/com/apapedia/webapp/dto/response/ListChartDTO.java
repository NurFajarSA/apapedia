package com.apapedia.webapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListChartDTO {
    //private Map<String, Long> listChart;
    private List<ChartDTO> chartDTOList;

}
