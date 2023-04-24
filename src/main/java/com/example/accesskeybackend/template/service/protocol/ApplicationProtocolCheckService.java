package com.example.accesskeybackend.template.service.protocol;

import com.example.accesskeybackend.template.dto.ProtocolCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationProtocolCheckService {

    private final ProtocolCheckService protocolCheckService;

    public ResponseEntity<ProtocolCheckDto> checkSupportProtocolIpV6(String siteUrl) {
        boolean resultSupport = protocolCheckService.isSupportProtocolIpv6(siteUrl);
        return ResponseEntity.ok().body(new ProtocolCheckDto(resultSupport));
    }

}
