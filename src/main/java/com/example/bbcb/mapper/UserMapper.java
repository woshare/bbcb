package com.example.bbcb.mapper;

import com.example.bbcb.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users")
    List<User> findAllUsers();
}
