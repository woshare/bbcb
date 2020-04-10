package com.example.bbcb.dao;

import com.example.bbcb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 *
 * 不得使用外键与级联，一切外键概念必须在应用层解决
 * @Repository:把dao对象作为参数，在构造函数或者setDao中赋值到service中去，这样，其不是bean
 */
@Mapper
public interface UserDao {

    public List<User> findAllUsers();

    public User findUsers(String uid);
}
