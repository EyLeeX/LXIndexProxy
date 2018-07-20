package mfw.index.search.service.spring;


import mfw.index.search.service.iface.SearchAdminService;
import mfw.index.search.service.iface.SearchLogService;
import mtime.lark.net.rpc.RpcClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 提供 Spring 自动注入默认服务代理功能。
 */
@Configuration
@Lazy
@Order(Ordered.LOWEST_PRECEDENCE)
public class IndexSearchServiceProxyAutoConfig {

    private String svr = "cmc.index.admin.search.service";

    @Bean
    @ConditionalOnMissingBean
    public SearchLogService searchLogServiceProxy() {
        return RpcClient.get(svr, SearchLogService.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public SearchAdminService searchAdminServiceProxy() {
        return RpcClient.get(svr, SearchAdminService.class);
    }
}
