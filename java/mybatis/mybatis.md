# Mybatis

### 框架介绍

MyBatis官网地址：<http://www.mybatis.org/mybatis-3/> 
> 什么是mybatis框架
>
> MyBatis 是一款优秀的持久层框架，可以对数据库进行增删改查的操作。底层封装了jdbc，开发者只需要关注sql的本身即可，不需要处理加载驱动，建立连接，创建statement，关闭连接的过程。

`tip:`框架主要解决的是：能够实现技术的整合；提高开发的效率，降低难度；

### Mybatis的优点(底层封装了jdbc)

+ 注册驱动
+ 创建jdbc中使用的Connection，Statement，ResultSet
+ 执行sql语句，得到ResultSet对象
+ 处理ResultSet，将数据转为java对象
+ 关闭资源
+ 关闭sql语句和java代码的解藕

###  1、ORM

#### 1.1介绍

+ ORM(Object Relational Mapping): 指的是对象关系映射
+ 将持久化数据和实体对象的映射模式，为了解决面向对象与关系型数据库存在的互不匹配的现象的技术。

#### 1.2 映射关系如下图

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gx68hg9ur8j30pc0po3zx.jpg" style="zoom:45%;" />

### 2、原始的jdbc操作

####  2.1查询

![](https://tva1.sinaimg.cn/large/008i3skNgy1gx68ntzn65j30lo0ex3z9.jpg)

#### 2.2插入

![](https://tva1.sinaimg.cn/large/008i3skNgy1gx68o99w8xj30rb0emtbe.jpg)

#### 2.3原始 JDBC 的操作问题分析 

+ 频繁创建和销毁数据库的连接会造成系统资源浪费从而影响系统性能。
+ sql 语句在代码中硬编码，如果要修改 sql 语句，就需要修改 java 代码，造成代码不易维护。
+ 查询操作时，需要手动将结果集中的数据封装到实体对象中。
+ 增删改查操作需要参数时，需要手动将实体对象的数据设置到 sql 语句的占位符。

+ 原始 JDBC 的操作问题解决方案 
  + 使用数据库连接池初始化连接资源。 
  + 将sql语句抽取到配置文件中。
  + 使用反射等底层的技术，将实体与表进行属性和字段的自动映射。

### 3、Mybatis 快速入门

#### <u>3.1 开发步骤</u>

1⃣️ 添加mybatis的相关依赖

2⃣️ 创建实体类对象

3⃣️编写数据库表

4⃣️编写核心配置文件

5⃣️编写映射文件

6⃣️编写测试类

#### 3.2 入门案例

##### 3.2.1 首先创建一个maven工程，在pom文件中加入依赖

```xml
 <dependencies>
        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.5</version>
        </dependency>
        <!-- mysql 驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.32</version>
        </dependency>

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>

        <!-- 添加slf4j日志api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.20</version>
        </dependency>
        <!-- 添加logback-classic依赖 -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>
        <!-- 添加logback-core依赖 -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
        </dependency>
    </dependencies>
```

##### 3.2.2创建实体类对象

```java
/**
 * @author wf
 * @date 2021/12/6 2 实体类对象
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private Date birthday;
    private String sex;
    private String address;
}
```

##### 3.2.3创建数据库表并导入相关数据

```sql
DROP TABLE IF EXISTS `user`;
# 创建表语句
CREATE TABLE `user`
(
    `id`       int(11)     NOT NULL auto_increment,
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `birthday` datetime     default NULL COMMENT '生日',
    `sex`      char(1)      default NULL COMMENT '性别',
    `address`  varchar(256) default NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
  # 插入表数据
insert into `user`(`id`, `username`, `birthday`, `sex`, `address`)
values (41, '老王', '2018-02-27 17:47:08', '男', '北京'),
       (42, '小二王', '2018-03-02 15:09:37', '女', '北京金燕龙'),
       (43, '小二王', '2018-03-04 11:34:34', '女', '北京金燕龙'),
       (45, '传智播客', '2018-03-04 12:04:06', '男', '北京金燕龙'),
       (46, '老王', '2018-03-07 17:37:26', '男', '北京'),
       (48, '小马宝莉', '2018-03-08 11:44:00', '女', '北京修正');
```

##### 3.2.4编写核心配置文件的(mybatis-config.xml)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!-- mybatis的dtd约束-->
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- 根标签-->
<configuration>
    <!--environments配置数据库环境，环境可以有多个。default属性指定使用的是哪个-->
    <environments default="development">
        <!--environment配置数据库环境  id属性唯一标识-->
        <environment id="development">
            <!-- transactionManager事务管理。  type属性，采用JDBC默认的事务-->
            <transactionManager type="JDBC"/>
            <!-- dataSource数据源信息   type属性 连接池-->
            <dataSource type="POOLED">
                <!-- property获取数据库连接的配置信息 -->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///db1?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <!-- mappers引入映射配置文件 -->
    <mappers>
        <!-- mapper 引入指定的映射配置文件   resource属性指定映射配置文件的名称 -->
        <mapper resource="cn/ymxdy/mapper/UserMapper.xml"/>
        <!--package 引入指定的接口文件 name属性指的是接口文件的路径-->
        <package name="cn.ymxdy.mapper.UserDao"/>
    </mappers>
</configuration>
```

##### 3.2.5编写映射文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--MyBatis的DTD约束-->
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--
    mapper：核心根标签
    namespace属性：名称空间  --- 要和对应的接口进行绑定
-->
<mapper namespace="cn.ymxdy.dao.UserDao">
<!--根据id进行删除-->
  
   <!--
        delete：删除功能的标签
        id属性：唯一标识
        resultType属性：指定结果映射对象类型
        parameterType属性：指定参数映射对象类型
    -->
    <delete id="deleteById" parameterType="int">
        delete
        from user
        where id = #{id};
    </delete>
</mapper>

```

##### 3.2.6编写测试类

```java
public class MybatisTest {

    @Test
    public void deleteTest() throws IOException {
        //    加载配置文件
        String resources = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resources);
        // 构建工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //创建数据库会话
        SqlSession sqlSession = sessionFactory.openSession(true);

        UserDao userDao = sqlSession.getMapper(UserDao.class);
        int result = userDao.deleteById(45);
        if (result > 0) {
            System.out.println("删除成功～～～");
        } else {
            System.out.println("删除失败～～～");
        }
        sqlSession.close();
    }
}
```

### 4、Myabtis的相关api

#### 4.1 Resources

+ org.apache.ibatis.io.Resources ： 加载资源文件的工具类
+ 核心方法

| 返回值      | 方法名                               | 说明                                     |
| ----------- | ------------------------------------ | ---------------------------------------- |
| InputStream | getResourceAsStream(String fileName) | 通过类加载器返回指定资源的字节输入流对象 |

#### 4.2 构建器SqlSessionFactoryBuilder

+ org.apache.ibatis.session.SqlSessionFactoryBuilder 获取sqlSessionFactory 工厂对象的功能类
+ 核心方法

| 返回值      | 方法名                               | 说明                                     |
| ----------- | ------------------------------------ | ---------------------------------------- |
| InputStream | getResourceAsStream(String fileName) | 通过类加载器返回指定资源的字节输入流对象 |

+ 通过加载mybatis的核心文件的输入流的形式构建一个SqlSessionFactory对象

```java
String resource = "org/mybatis/builder/mybatis-config.xml"; 
InputStream inputStream = Resources.getResourceAsStream(resource); 
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); 
SqlSessionFactory factory = builder.build(inputStream);
```
#### 4.3 工厂对象SqlSessionFactory

+ org.apache.ibatis.session.SqlSessionFactory：获取 SqlSession 构建者对象的工厂接口。
+ 核心api
+ | 返回值      | 方法名                               | 说明                                     |
| ----------- | ------------------------------------ | ---------------------------------------- |
|SqlSession  | openSession() |获取sqlsession构建对象，并且手动提交事务 |
|SqlSession  | openSession(boolean autoCommit) |获取sqlsession构建对象，如果参数是true，那么就是自动提交事务 |

#### 4.4 SqlSession会话对象
+ org.apache.ibatis.session.SqlSession：构建者对象接口。用于执行 SQL、管理事务、接口代理。
+ 核心api
+ | 返回值      | 方法名                               | 说明                                     |
| ----------- | ------------------------------------ | ---------------------------------------- |
|List<E> | selectList(String statment,Object paramter) |执行查询语句，返回list集合|
|T  | slectOne(String statment,Object paramter) |执行查询语句，返回一个结果集对象 |
|int  | insert(String statment,Object paramter) |执行插入，返回影响的行数 |
|int  | update(String statment,Object paramter) |执行更新，返回影响的行数 |
|int  | delete(String statment,Object paramter) |执行删除，返回影响的行数 |
|void| commit() |提交事务 |
|void | rollback() |回滚事务 |
|T|getMapper(Class<T> cls) |获取接口代理实现类对象 |
|void | close()|释放资源 |

`tip:`SqlSession 实例在 MyBatis 中是非常强大的一个类。在这里你会看到所有执行语句、提交或回滚事务和获取映射器实例的方法。

### 5、Mybatis 配置文件

#### 3.1 mybatis的核心配置文件的介绍(Resources下)

> mybatis的核心XML 配置文件中包含了对 MyBatis 系统的核心设置，包括获取数据库连接实例的数据源（DataSource）以及决定事务作用域和控制方式的事务管理器（TransactionManager）

```xml
<!-- mybatis的约束文件-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!-- 根标签-->
<configuration>
<!-- 环境-->
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <!--配置数据源  创建连接对象Connection对象-->
      <dataSource type="POOLED">
       <!--数据驱动--> 
        <property name="driver" value="${driver}"/>
         <!--连接数据库的url--> 
        <property name="url" value="jdbc:mysql://localhost:3306/数据库名称?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true"/>
         <!--数据库的用户名-->  
        <property name="username" value="${username}"/>
           <!--数据库的密码-->  
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <!--
 		指定其他文件的位置
		目的： 找到其他文件中的sql语句;
	-->
  <mappers>
    <!--
				使用mapper的resource属性置顶mapper文件的路径，
				这个路径是从根路径开始的
				注意：	
			mapper中的resource中的路径一定要用斜杠进行分割
		-->
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```
