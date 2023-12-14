package com.apapedia.webapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ChartDTO {
    private String productName;
    private long quantity;
}
