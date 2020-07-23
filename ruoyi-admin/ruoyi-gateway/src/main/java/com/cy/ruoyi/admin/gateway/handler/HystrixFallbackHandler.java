package com.cy.ruoyi.admin.gateway.handler;

import com.cy.ruoyi.common.utils.enums.TradeErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

@Slf4j
@Component
public class HystrixFallbackHandler implements HandlerFunction<ServerResponse>
{
    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest)
    {
        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        originalUris.ifPresent(originalUri -> log.error(TradeErrorEnum.GATEWAY_REQUEST_FAIL.msg, originalUri));
        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("服务已被降级熔断"));
    }

//    @Override
//    public Mono<ServerResponse> handle(ServerRequest serverRequest)
//    {
//        Optional<Object> originalUris = serverRequest.attribute(GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
//        originalUris.ifPresent(originalUri -> log.error(TradeErrorEnum.GATEWAY_REQUEST_FAIL.msg, originalUri));
//        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(JSON.toJSONString(R.error("服务已被降级熔断"))));
//    }
}