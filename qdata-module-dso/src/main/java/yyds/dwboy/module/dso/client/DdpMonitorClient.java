package yyds.dwboy.module.dso.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "dso-monitor", url = "${ddp.api.url}")
public interface DdpMonitorClient {

    @GetMapping("/api/v1/clusters/count")
    Integer getClusterCount(@RequestHeader("Authorization") String token);

    @GetMapping("/api/v1/alerts/unread")
    Integer getUnreadAlertCount(@RequestHeader("Authorization") String token);
}
