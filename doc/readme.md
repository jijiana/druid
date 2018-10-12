# Druid使用文档

## 下载源码

github下载地址https://github.com/jijiana/druid.git

目录结构

![1539314357783](C:\Users\IBM_AD~1\AppData\Local\Temp\1539314357783.png)

application配置连接池相关信息

```properties
# 只有下面三个是必填项（使用内嵌数据库的话这三个也可以不用填，会使用默认配置），其他配置不是必须的
spring.datasource.druid.url=jdbc:mysql://localhost:3306/db_test?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true
spring.datasource.druid.username=root
spring.datasource.druid.password=123456
# driver-class-name 非必填可根据url推断
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
#方言
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
#hibernate sql格式化
spring.jpa.properties.hibernate.format_sql = false
#hibernate sql 是否展示
spring.jpa.properties.hibernate.show_sql = false

#Druid 初始化
spring.datasource.druid.initial-size=2
#Druid 最大
spring.datasource.druid.max-active=30
#Druid 最小
spring.datasource.druid.min-idle=2
# 配置获取连接等待超时的时间
spring.datasource.druid.max-wait=60000
# 打开PSCache，并且指定每个连接上PSCache的大小
spring.datasource.druid.pool-prepared-statements=true
spring.datasource.druid.max-pool-prepared-statement-per-connection-size=5

#检测连接是否有效的sql
spring.datasource.druid.validation-query=select 1
#检测连接是否有效的sql等待超时时间
spring.datasource.druid.validation-query-timeout=60000
#当从连接池借用连接时，是否测试该连接.
spring.datasource.druid.test-on-borrow=true
#在连接归还到连接池时是否测试该连接.
spring.datasource.druid.test-on-return=true
#当连接空闲时，是否执行连接测试.
spring.datasource.druid.test-while-idle=true
#空闲连接回收的时间间隔，与test-while-idle一起使用
spring.datasource.druid.time-between-eviction-runs-millis=10000
#连接池空闲连接的有效时间
spring.datasource.druid.min-evictable-idle-time-millis=1800000
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
spring.datasource.druid.filters=stat,wall
# 通过connectProperties属性来打开mergeSql功能；慢SQL记录
spring.datasource.connectionProperties=druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

#druid 登录用户名、密码
spring.datasource.druid.stat-view-servlet.login-username=admin
spring.datasource.druid.stat-view-servlet.login-password=admin
#IP白名单(没有配置或者为空，则允许所有访问)
#spring.datasource.druid.stat-view-servlet.allow=9.186.54.153

#IP黑名单 (存在共同时，deny优先于allow)
#spring.datasource.druid.stat-view-servlet.deny=127.0.0.1
#是否启用StatFilter默认值true
spring.datasource.druid.web-stat-filter.enabled=false
#是否能够重置数据 禁用HTML页面上的“Reset All”功能
spring.datasource.druid.stat-view-servlet.reset-enable=false

#Druid 监控日志
spring.datasource.druid.filter.slf4j.enabled=true
spring.datasource.druid.filter.slf4j.connection-log-enabled=true
spring.datasource.druid.filter.slf4j.connection-close-after-log-enabled=true
spring.datasource.druid.filter.slf4j.connection-commit-after-log-enabled=true
spring.datasource.druid.filter.slf4j.connection-connect-after-log-enabled=true
spring.datasource.druid.filter.slf4j.connection-connect-before-log-enabled=true
spring.datasource.druid.filter.slf4j.connection-log-error-enabled=true
spring.datasource.druid.filter.slf4j.data-source-log-enabled=true
spring.datasource.druid.filter.slf4j.result-set-log-enabled=true
spring.datasource.druid.filter.slf4j.statement-log-enabled=true

#cookie druid
spring.datasource.druid.web-stat-filter.principal-cookie-name=admin
#session druid
spring.datasource.druid.web-stat-filter.principal-session-name=admin
spring.datasource.druid.web-stat-filter.profile-enable=true
#session 监控是否开启
spring.datasource.druid.web-stat-filter.session-stat-enable=true
#session 监控最大数量
spring.datasource.druid.web-stat-filter.session-stat-max-count=100000

#spring监控是否开启
spring.aop.proxy-target-class=true
#spring监控，hello.controller是我的控制层包名，也可以是服务层，用逗号分隔多个监控内容
spring.datasource.druid.aop-patterns=com.alibaba.druid.spring.boot.controller.*
# 是否开启sql的log，默认为: false
spring.jpa.show-sql= true
#指定DDL mode (none, validate, update, create, create-drop). 当使用内嵌数据库时，默认是create-drop，否则为none.
spring.jpa.hibernate.ddl-auto=create-drop

```

通过MainApplication启动服务

![1539315009862](C:\Users\IBM_AD~1\AppData\Local\Temp\1539315009862.png)

在浏览器输入http://127.0.0.1:8080/druid

![1539315162233](C:\Users\IBM_AD~1\AppData\Local\Temp\1539315162233.png)

点击Sign in进入Druid监控首页，包含了如下几个模块：数据源、SQL监控、SQL防火墙、Web应用、URI监控、Session监控、JSONAPI

![1539315225475](C:\Users\IBM_AD~1\AppData\Local\Temp\1539315225475.png)

**数据源**

项目中管理的所有数据源配置的详细情况

![1539323119213](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323119213.png)

**SQL监控  **

查看所有执行的sql语句

```properties
# 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
spring.datasource.druid.filters=stat,wall
```



![1539323138721](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323138721.png)

**SQL防火墙  **

druid提供了黑白名单的访问，sql防护情况。

![1539323154527](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323154527.png)

**Web应用  **

目前运行的web程序的详细信息。

![1539323171411](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323171411.png)

**URI监控  **

监控到所有的请求路径的请求次数、请求时间等其他参数。

![1539323196999](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323196999.png)

配置Web Url 过滤规则

![1539323996640](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323996640.png)

**Session监控**

当前的session状况，创建时间、最后活跃时间、请求次数、请求时间等详细参数。

```properties
spring.datasource.druid.web-stat-filter.principal-session-name=admin
spring.datasource.druid.web-stat-filter.profile-enable=true
#session 监控是否开启
spring.datasource.druid.web-stat-filter.session-stat-enable=true
#session 监控最大数量
spring.datasource.druid.web-stat-filter.session-stat-max-count=100000
```

![1539323250353](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323250353.png)

**spring监控**

controller每个方法的访问情况

```properties
#spring监控，hello.controller是我的控制层包名，也可以是服务层，用逗号分隔多个监控内容
spring.datasource.druid.aop-patterns=com.alibaba.druid.spring.boot.controller.*
```

![1539323277182](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323277182.png)

**JSONAPI **

通过api的形式访问Druid的监控接口，api接口返回Json形式数据。

![1539323355242](C:\Users\IBM_AD~1\AppData\Local\Temp\1539323355242.png)

