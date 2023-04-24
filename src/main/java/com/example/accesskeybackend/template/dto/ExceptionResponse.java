package com.example.accesskeybackend.template.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Schema(description = "Ответ ошибок")
@AllArgsConstructor
@Getter
public class ExceptionResponse {

    @Schema(description = "массив строковых значений")
    private List<String> errors;
}
