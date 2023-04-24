package com.example.accesskeybackend.template.service.protocol;

import com.example.accesskeybackend.exception.ValidateSiteDomainException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.net.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Log4j2
public class ProtocolCheckService {

    private static final String EXCEPTION_TEMPLATE = "Входная строка: %s, ошибка: %s";
    private static final String REGEXP_NOT_FIND_MATCHES = "Входная строка не содержит домена";
    private static final String REGEXP_URL = "(?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z0-9][a-z0-9-]{0,61}[a-z0-9]";

    public boolean isSupportProtocolIpv6(String siteUrl) {
        try {
            Pattern compile = Pattern.compile(REGEXP_URL);
            Matcher matcher = compile.matcher(siteUrl);
            if (!matcher.find()) {
                throw new ValidateSiteDomainException(REGEXP_NOT_FIND_MATCHES);
            }
            String finalDomain = matcher.group(0);
            return Arrays.stream(InetAddress.getAllByName(finalDomain))
                    .anyMatch(inetAddress -> inetAddress instanceof Inet6Address);
        } catch (UnknownHostException e) {
            log.error(String.format(EXCEPTION_TEMPLATE, siteUrl, e.getMessage()), e);
            return false;
        }
    }
}
