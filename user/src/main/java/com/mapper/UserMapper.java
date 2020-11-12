package com.mapper;

import com.common.base.MyMapper;
import com.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: yinminxin
 * @create: 2020-11-06
 **/
@Repository
public interface UserMapper extends MyMapper<User,Long> {

    @Select("SELECT * FROM user u WHERE u.state = 0 AND user_name = #{userName} AND pass_word = #{passWord}")
    User findByUserNameAndPassWord(@Param("userName") String userName,
                                   @Param("passWord") String passWord);

}
