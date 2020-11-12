package com.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.common.result.ResponseVO;
import com.feign.PermissionPass;
import com.service.UserClientService;
import org.aspectj.lang.annotation.RequiredTypes;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 登录权限过滤器
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Component
public class LoginFilter implements GlobalFilter, Ordered {

    @Autowired
    private UserClientService userClientService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        List<String> token = headers.get("token");
        URI uri = request.getURI();
        System.out.println(token);
        System.out.println(uri.getPath());
        Map<String, String> map = new HashMap<>();
        map.put("token",token.get(0));
        map.put("url",uri.getPath());
        String param = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        ResponseVO responseVO = userClientService.hasPassPermission(param);
        if ("200".equals(responseVO.getCode())) {
            return chain.filter(exchange);
        }

        ServerHttpResponse response = exchange.getResponse();
        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //过滤器中跨域需要自己处理
        //设置body
        String result = JSON.toJSONString(responseVO);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(result.getBytes());

        return response.writeWith(Mono.just(bodyDataBuffer));

//        exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
