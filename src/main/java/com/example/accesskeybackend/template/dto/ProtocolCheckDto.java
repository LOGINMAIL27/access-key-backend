package com.example.accesskeybackend.template.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Дто возврата результата поддержки Ipv6
 */
@Schema(description = "Дто возврата результата поддержки Ipv6")
@Getter
@AllArgsConstructor
public class ProtocolCheckDto {

    @Schema(description = "Результат проверки")
    private Boolean success;
}
