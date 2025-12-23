package cn.bugstack.ai.locator;

import cn.bugstack.ai.service.GatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DbRouteDefinitionLocator implements RouteDefinitionLocator {
    @Autowired
    private GatewayRouteService gatewayRouteService;
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(gatewayRouteService.loadRoutes());
    }
}
