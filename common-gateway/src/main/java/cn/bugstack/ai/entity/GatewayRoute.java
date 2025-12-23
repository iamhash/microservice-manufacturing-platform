package cn.bugstack.ai.entity;

import lombok.Data;

@Data
public class GatewayRoute {
    private String routeId;
    private String uri;
    private String predicates;
    private String filters;
}
