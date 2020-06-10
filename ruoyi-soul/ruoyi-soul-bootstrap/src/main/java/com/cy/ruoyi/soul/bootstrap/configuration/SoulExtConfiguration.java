package com.cy.ruoyi.soul.bootstrap.configuration;

import com.cy.ruoyi.soul.bootstrap.cors.CrossFilter;
import org.dromara.soul.web.support.RemoteAddressResolver;
import org.dromara.soul.web.support.XForwardedRemoteAddressResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.server.WebFilter;

/**
 * The type Soul ext configuration.
 *
 * @author xiaoyu(Myth)
 */
@Configuration
public class SoulExtConfiguration {

    /**
     * Cross filter web filter.
     * if you application has cross-domain.
     * this is demo.
     * 1. Customize webflux's cross-domain requests.
     * 2. Spring bean Sort is greater than -1.
     *
     * @return the web filter
     */
    @Bean
    @Order(-100)
    public WebFilter crossFilter() {
        return new CrossFilter();
    }

    /**
     * Remote address resolver remote address resolver.
     *
     * @return the remote address resolver
     */
    @Bean
    public RemoteAddressResolver remoteAddressResolver() {
        return new XForwardedRemoteAddressResolver(1);
    }

   /* @Bean
    public SignService signService() {
        return (requestDTO, exchange) -> new Pair<>(true, "");
    }*/
}
