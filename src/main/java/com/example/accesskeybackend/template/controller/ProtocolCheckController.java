package com.example.accesskeybackend.template.controller;

import com.example.accesskeybackend.template.dto.ExceptionResponse;
import com.example.accesskeybackend.template.dto.ProtocolCheckDto;
import com.example.accesskeybackend.template.service.protocol.ApplicationProtocolCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/web")
@RestController
@RequiredArgsConstructor
@Tag(name = "Protocol Check")
@Validated
public class ProtocolCheckController {

    private final ApplicationProtocolCheckService applicationProtocolCheckService;

    @GetMapping(path = "/checkIpv6Support")
    @Operation(summary = "Проверка поддержки сайтом протокола Ipv6")
    @Parameters(value = {
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Входная строка либо домен либо URI",
                    name = "siteUrl",
                    required = true
            )
    })
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Результат проверки",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ProtocolCheckDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ошибка проверки входной строки на соотвествие домену или полному URI",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ExceptionResponse.class)
                            )
                    )
            }
    )
    public ResponseEntity<ProtocolCheckDto> supportProtocolIpv6Suite(@RequestParam(name = "siteUrl") @Valid  @NotBlank(message = "входная строка не может быть пустой") String siteUrl) {
        return applicationProtocolCheckService.checkSupportProtocolIpV6(siteUrl);
    }
}
