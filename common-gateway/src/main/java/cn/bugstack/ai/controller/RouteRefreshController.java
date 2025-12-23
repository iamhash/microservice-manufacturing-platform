package cn.bugstack.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class RouteRefreshController {
    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/refresh")
    public String refresh() {
        publisher.publishEvent(new RefreshRoutesEvent(this));
        return "ok";
    }
}
