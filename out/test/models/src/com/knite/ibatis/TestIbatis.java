package com.carl.ibatis;

import com.carl.ibatis.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2018/6/15.
 */
public class TestIbatis {
    public static void main(String[] args){
        //mybatis的配置文件
        String resource = "conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = TestIbatis.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.carl.ibatis.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);


        String sts = "com.carl.ibatis.mapping.userMapper.addUserSubt";
        Map<String, Object> data = new HashMap<String, Object>();
//        data.put("tableSuf", 201805);
//        data.put("id", id);
//        data.put("name", "TTT");
        Paramer p = new Paramer();
        p.setId(1);
        p.setName("Test");
        data.put("data", p);
        session.insert(sts, p);
//        p.getTableSuf();
        for(int i=2;i<100;i++) {
            p.setId(i);
            session.insert(sts, p);
        }
        session.commit();
        session.close();
    }

}
