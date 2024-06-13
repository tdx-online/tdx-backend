package edu.hit.tdxbackend.mapper;

import edu.hit.tdxbackend.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username, password, email, address,block_address) values(#{username},#{password}, #{email}, #{address},#{blockAddress})")
    void addUser(User user);
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);
    @Select("select * from user where username = #{id}")
    User getUserById(int id);
    @Select("select * from user where username = #{username} and password=#{password}")
    User getUserByUsernameAndPassword(String username, String password);
    @Delete("delete * from user where id = #{id}")
    int deleteUserById(int id);
    @Select("select count(id) from user")
    int getTotal();
    @Select("select * from user")
    List<User> getUserList();
}
