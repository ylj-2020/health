## 健康项目

## (零)概述

- 项目采用SOA架构(Servic Oriented Architec)
- 有后台管理，前台H5, 服务提供方，用dobbo RPC框架进行整合运行
- 用maven进行分模块开发，聚合部署(如图中，common模块为单独项目，最后该模块会被其余3个工程以依赖，3个工程各自构建时都会打包common.jar到自己的工程里去)

## (一)架构

![image-20210113161042779](https://tva1.sinaimg.cn/large/008eGmZEly1gmoly9o3lcj30zk0oeacg.jpg)



## (二)技术选型

### 分页插件

>PageHelper自动适配了页号(前端传过来currentPage=1, pageSize=10。拦截后织入会变成limit 0,10)
>
>使用ThreadLocal来存储当前请求的分页请求参数(currentPage, pageSize), 和分页结果参数(total,pages)
>
>Page<?> 对象创建时候会去threadlocal中获取分页的相关所有参数。

- PageHelper的Mybatis插件

- 在Mybatis的核心配置文件中注册插件

    ```xml
    <plugins>
      <!-- com.github.pagehelper 为 PageHelper 类所在包名 -->
      <plugin interceptor="com.github.pagehelper.PageHelper">
        <!-- 设置数据库类型 Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL 六种数据库-->
        <property name="dialect" value="mysql"/>
      </plugin>
    </plugins>
    ```

- 使用

    ```java
    //方式一(不推荐， 自己创建Page对象，创建时候会自动从threadlocal中拿分页数据)
    //这一句是将分页参数绑定到ThreadLocal中，将分页参数和当前线程绑定
    PageHelper.startPage(pb.getCurrentPage(), pb.getPageSize());
    //执行查询时候，Pagehelper会拦截后动态增强 实际执行的是
    //1. select count(*) from t_checkitem; 获取total条数，将结果存到threadLocal
    //2. select * from t_checkitem limit currentPage, pageSize;
    List<Checkitem> list = checkitemMapper.selectAll();
    //分装PageHelper中的分页对象,其中的分页相关参数会从threadLocal中获取
    PageInfo<Checkitem> pi = new PageInfo<Checkitem>(list);
    
    //2. 方式二: 自动封装Page对象(原理就是从threadlocal中去拿total，currentPage，pageSize值)
    Page<Checkitem> pg = checkitemMapper.findPageByCondition(pb.getQueryString());
    return new PageResult(pg.getTotal(), pg.getResult());
    ```

    



## (三)业务



#### 预约管理模块

- 预约管理模块

    1. 针对系统管理员： 以上三项进行增删改查。
    2. 针对用户： 预约管理(1. 已预约查看 2.可预约设置(每天可预约人数))

- H5和backend都调用service_provider在dubbo上发布的服务。

- 检查项管理，检查组管理，检查套餐管理。

    业务场景：用户选择1个检查套餐，如孕检套餐，那么1个孕检套餐下有多个检查组，如针对胎儿的唐氏筛查检查组，针对母体的孕期三高检查组等等，每个检查组中又细分为多个检查项，如唐氏筛查检查组中就需要有 1.hcg检查项 2. 苯丙酮检查项 3. 游离雌三醇检查项等等。。。

    
