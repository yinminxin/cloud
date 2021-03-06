package com.feign;

import com.common.result.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 权限接口
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@FeignClient("user-service")
public interface PermissionPass {

    @PostMapping("/user/userCon/hasPassPermission")
    @ResponseBody
    ResponseVO hasPassPermission(@RequestBody String str);
}
