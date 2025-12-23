package cn.bugstack.ai.service;

import cn.bugstack.ai.entity.GatewayRoute;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GatewayRouteService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public List<RouteDefinition> loadRoutes() {
        String sql = "select * from gateway_route where enabled = 1";
        List<GatewayRoute> routes = jdbcTemplate.query(sql, (rs, i) -> {
            GatewayRoute r = new GatewayRoute();
            r.setRouteId(rs.getString("route_id"));
            r.setUri(rs.getString("uri"));
            r.setPredicates(rs.getString("predicates"));
            r.setFilters(rs.getString("filters"));
            return r;
        });

        return routes.stream().map(this::toRouteDefinition).collect(Collectors.toList());
    }

    private RouteDefinition toRouteDefinition(GatewayRoute r) {
        RouteDefinition rd = new RouteDefinition();
        rd.setId(r.getRouteId());
        rd.setUri(URI.create(r.getUri()));

        try {
            List<PredicateDefinition> predicates =
                    objectMapper.readValue(r.getPredicates(),
                            new TypeReference<List<PredicateDefinition>>() {});
            rd.setPredicates(predicates);

            if (r.getFilters() != null) {
                List<FilterDefinition> filters =
                        objectMapper.readValue(r.getFilters(),
                                new TypeReference<List<FilterDefinition>>() {});
                rd.setFilters(filters);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rd;
    }


}
