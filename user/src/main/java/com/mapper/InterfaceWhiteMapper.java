package com.mapper;

import com.common.base.MyMapper;
import com.entity.InterfaceWhite;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @description: 接口白名单Mapper
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Repository
public interface InterfaceWhiteMapper extends MyMapper<InterfaceWhite, Long> {

}
