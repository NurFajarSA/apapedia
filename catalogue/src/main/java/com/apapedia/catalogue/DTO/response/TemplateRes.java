package com.apapedia.catalogue.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TemplateRes {
    private String message;
    private Object data;
}
