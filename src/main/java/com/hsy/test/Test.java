package com.hsy.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.hsy.pojo.User;

public class Test {

    public static void main(String[] args) {
        //创建一个字节输入流对象
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            //创建SqlSessionFacotry对象
            SqlSessionFactory  factory = new SqlSessionFactoryBuilder().build(inputStream);
            //根据SqlSessionFacotry对象创建SqlSession对象
            SqlSession session = factory.openSession();
            //调用相应的方法执行SQL语句
            User user = session.selectOne("findUserById", 1);
            List<User> name = session.selectList("findUserByNameLike", "%李%");
            System.out.println(user);
            System.out.println(name);
            //关闭会话
            session.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}