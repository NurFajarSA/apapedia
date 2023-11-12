package com.apapedia.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateRes<T> {
    boolean isSuccess = true;
    String message;
    T content;
}
