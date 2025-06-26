package yyds.dwboy.module.dso.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yyds.dwboy.module.dso.client.DdpMonitorClient;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 运维平台入口控制器
 */
@RestController
@RequestMapping("/dso")
public class DdpOperationController {

    private final DdpMonitorClient ddpMonitorCli;
    private final String ddpUrl;

    public DdpOperationController(DdpMonitorClient ddpMonitorCli,
                                  @Value("${ddp.ui.url}") String ddpUrl) {
        this.ddpMonitorCli = ddpMonitorCli;
        this.ddpUrl = ddpUrl;
    }

    /**
     * 跳转到 DataSophon 页面
     */
    @GetMapping("/redirect")
    @PreAuthorize("hasAuthority('dso:view')") // 需要 dso:view 权限
    public void redirectToDataSophon(@RequestHeader("Authorization") String token,
                                     HttpServletResponse response) throws IOException {
        String redirectUrl = ddpUrl + "?auth_token=" + token;
        response.sendRedirect(redirectUrl);
    }

    /**
     * 获取集群数量
     */
    @GetMapping("/metrics/clusters")
    @PreAuthorize("hasAuthority('dso:view')") // 需要 dso:view 权限
    public ResponseEntity<Integer> getClusterCount(@RequestHeader("Authorization") String token) {
        Integer count = ddpMonitorCli.getClusterCount(token);
        return ResponseEntity.ok(count);
    }

    /**
     * 获取未读告警数
     */
    @GetMapping("/metrics/alerts")
    @PreAuthorize("hasAuthority('dso:alert:view')") // 需要 dso:alert:view 权限
    public ResponseEntity<Integer> getUnreadAlertCount(@RequestHeader("Authorization") String token) {
        Integer count = ddpMonitorCli.getUnreadAlertCount(token);
        return ResponseEntity.ok(count);
    }
}
