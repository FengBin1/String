package com.hsy.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.hsy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {
    public static void main(String[] args) {
        try {
            // 加载 MyBatis 配置文件
            String resource = "SqlMapConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 创建 SqlSessionFactory 对象
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            // 获取 SqlSession 对象
            try (SqlSession session = factory.openSession()) {
                // 添加用户
                User user = new User();
                user.setName("冯斌");
                insertUser(session, user);
                // 查询单个用户
                User singleUser = findUserById(session, 1);
                System.out.println("查询单个用户结果：" + singleUser);
                // 查询多个用户（模糊查询）
                List<User> users = findUsersByNameLike(session, "李");
                System.out.println("查询多个用户结果：" + users);
                // 更新用户信息
                User updatedUser = new User();
                updatedUser.setId(1);
                updatedUser.setName("Updated Name");
                updateUser(session, updatedUser);
                // 删除用户
                deleteUser(session, 8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void insertUser(SqlSession session, User user) {
        int insertCount = session.insert("insertUser", user);
        session.commit();
        if (insertCount > 0) {
            System.out.println("新用户添加成功！");
        } else {
            System.out.println("新用户添加失败！");
        }
    }
    public static User findUserById(SqlSession session, int id) {
        return session.selectOne("findUserById", id);
    }
    public static List<User> findUsersByNameLike(SqlSession session, String name) {
        return session.selectList("findUserByNameLike", "%" + name + "%");
    }
    public static void updateUser(SqlSession session, User user) {
        int updateCount = session.update("updateUser", user);
        session.commit();
        if (updateCount > 0) {
            System.out.println("用户信息更新成功！");
        } else {
            System.out.println("用户信息更新失败！");
        }
    }
    public static void deleteUser(SqlSession session, int id) {
        int deleteCount = session.delete("deleteUser", id);
        session.commit();
        if (deleteCount > 0) {
            System.out.println("用户删除成功！");
        } else {
            System.out.println("用户删除失败！");
        }
    }
}
