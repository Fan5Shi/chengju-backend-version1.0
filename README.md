# “成聚”活动平台-后端设计文档

## 1. 项目背景和目标

​	**项目背景**：校园中的各个社团、组织等每周都发布着新的活动，而他们往往分散在不同的平台上，辐射的范围不大，并且很容易被忽视掉。

​	**项目目标**：我们开发的“成聚”活动平台是一个可以汇集校园内各个活动的平台，人人都能发布自己的活动，人人都能找到自己的兴趣圈。

​	**项目地址**：https://github.com/Fan5Shi/chengju-backend-version1.0.git

## 2. 项目需求和框架

​	**核心需求：**  

    		- 平台简单易用
    
    		- 可以支持发布、参与和收藏活动的功能
    
    		- 平台能够支持一定量的访问、复杂操作等

​	**项目框架：**

![image](https://github.com/Fan5Shi/chengju-backend-version1.0/blob/master/img/QQ%E6%88%AA%E5%9B%BE20210120091836.png)

​	**程序流程图部分展示：**

​	![image-20210120094011628](https://github.com/Fan5Shi/chengju-backend-version1.0/blob/master/img/image-20210120094011628.png)

## 3. 技术支持

- **Java 1.8**
- **springboot** **2.3.7.RELEASE**
- **mysql 5.6.49**
- **mybatis 2.1.4**
- **redis 3.0.504**

## 4. 接口设计

swagger接口文档地址：http://localhost:8080/swagger-ui.html

![image](https://github.com/Fan5Shi/chengju-backend-version1.0/blob/master/img/image-20210119205831834.png)

![image-20210119215555743](https://github.com/Fan5Shi/chengju-backend-version1.0/blob/master/img/image-20210119215555743.png)

![image-20210119205934686](https://github.com/Fan5Shi/chengju-backend-version1.0/blob/master/img/image-20210119205934686.png)

![image-20210119205950056](https://github.com/Fan5Shi/chengju-backend-version1.0/blob/master/img/image-20210119205950056.png)

**注册接口**：("/register")

> post请求：**addUser(@RequestBody UserDO userDO)**

**登录接口**：("/login")

> post请求：**login(@RequestParam("userId") Integer userId, @RequestParam("password") String password**, **HttpServletRequest request)**

**登出接口**：("/logout")

> get请求：**logout(HttpSession session)**

**活动列表界面**：("/events")

> get请求：**getAllEvents() **

**按照时间顺序展示**：("/events/bytime")

> get请求：**getEventsByTime**

**活动列表搜索界面**：("/events/search")

> get请求：**searchByName(@RequestParam(value="name") String name)**

**活动详情页面**：("/events/{eventId}")

> get请求：**getEventById(@PathVariable(value="eventId") Integer eventId)**

**活动收藏**：("/events/{eventId}/collect")

> post请求：**addCollecter(@PathVariable(value="eventId") int eventId, HttpServletRequest request)**

**活动参与**：("/events/{eventId}/participate")

> post请求：**addParticipant(@PathVariable(value="eventId") int eventId, HttpServletRequest request)**

**发布活动页面**：("/launch")

> post请求：**addParticipateEvent(@RequestBody ParticipateDO participateDO)**

**获取个人页面**：("/mine")

> get请求：**getUserInfo(HttpServletRequest request)**

**更新个人信息**：("/mine/update")

> put请求：**updateUser(@RequestBody UserDO userDO)**

**收藏活动管理**：("/mine/collect")

> get请求：**getCollectEvents(HttpServletRequest request)**

**取消收藏**：("/mine/collect")

> delete请求：**deleteParticipateEvent(@RequestParam("eventId") Integer eventId, HttpServletRequest request)**

**已参与活动接口**：("/mine/participate")

> get请求：**getParticipateEvents(HttpServletRequest request)**

**取消参与**：("/mine/participate")

> delete请求：**deleteParticipateEvent(@RequestParam("eventId") Integer eventId, HttpServletRequest request)**

**已发布活动接口**：("/mine/launched")

> get请求：**getLaunchedEvents(HttpServletRequest request)**

**取消发布**：("/mine/launched")

> delete请求：**deleteLaunchedEvent(@RequestParam(value="eventId") Integer eventId, HttpServletRequest request)**

**更新发布活动**：("/mine/launched")

> put请求：**updateLaunchedEvent(@RequestBody EventDO eventDO)**

## 5. 数据库相关

### 用户基本信息表(user_info)

| 字段名称   | 数据类型     | 中文含义             | 索引     |
| ---------- | ------------ | -------------------- | -------- |
| user_id    | INT(50)      | 学号，数据主键       | 主键自增 |
| password   | VARCHAR(128) | 用户密码             |          |
| user_name  | VARCHAR(128) | 用户名字             |          |
| user_birth | DATETIME     | 用户生日(yyyy-MM-dd) |          |
| user_phone | VARCHAR(128) | 用户联系方式         |          |
| tmp        | VARCHAR(128) | 临时字段             |          |


### 活动信息表(event_info)

| 字段名称   | 数据类型     | 中文含义 | 索引     |
| ---------- | ------------ | -------- | -------- |
| event_id   | INT(20)      | 数据主键 | 主键自增 |
| event_name | VARCHAR(64)  | 活动名称 |          |
| event_addr | VARCHAR(128) | 活动地点 |          |
| event_time | DATETIME     | 活动时间 |          |
| event_des  | VARCHAR(128) | 活动描述 |          |
| user_id    | INT(50)      | 发布者ID |          |
| temp       | VARCHAR(128) | 临时字段 |          |


### 参与活动表(participate)

| 字段名称 | 数据类型 | 中文含义 | 索引             |
| -------- | -------- | -------- | ---------------- |
| id       | INT(20)  | 数据主键 | 主键自增         |
| event_id | INT(20)  | 活动ID   | 联合唯一键 |
| user_id  | INT(50)  | 用户ID   | 联合唯一键 |
| is_delete | VARCHAR(12) | 标记删除-标识符 ENABLE/DISABLE |  |

### 收藏活动表(collect)

| 字段名称  | 数据类型    | 中文含义                       | 索引             |
| --------- | ----------- | ------------------------------ | ---------------- |
| id        | INT(20)     | 数据主键                       | 主键自增         |
| event_id  | INT(20)     | 活动ID                         | 外键，联合唯一键 |
| user_id   | INT(50)     | 用户ID                         | 外键，联合唯一键 |
| is_delete | VARCHAR(12) | 标记删除-标识符 ENABLE/DISABLE |                  |

## 6. SQL语句

#### user_info表单

> Insert user

```sql
insert into user_info(user_id, password, user_name, user_birth, user_phone)
values
(#{userId}, #{password}, #{userName}, #{userBirth}, #{userPhone})
```

> select user

```sql
select * from user_info where user_id = #{userId} and password = #{password}

select * from user_info where user_id = #{userId}
```

> update

```sql
UPDATE user_info set user_name=#{userName}, user_phone=#{userPhone}, user_birth=#{userBirth} where user_id=#{userId}
```

#### event_info表单

> select event

```sql
// 活动列表
select e.event_name, e.event_time, e.event_addr from event_info as e

// 时间排序
select e.event_name, e.event_time, e.event_addr from event_info as e order by e.event_time

// 按照名字匹配
select e.event_name, e.event_time, e.event_addr from event_info as e where e.event_name regexp #{name}

// 查看活动详细信息
select * from event_info as e where event_id=#{eventId}

// 查看user id的所有发布活动
select e.event_name, e.event_time, e.event_addr from event_info as e where e.user_id=#{userId}
```

> insert

```sql
insert into event_info(event_name, event_addr, event_time, event_des, user_id)
values
(#{eventName}, #{eventAddr}, #{eventTime}, #{eventDes}, #{userId})
```

> delete

```sql
delete from event_info where user_id=#{userId} and event_id=#{eventId}
```

> update

```sql
UPDATE event_info set event_name=#{eventName}, event_addr=#{eventAddr}, event_time=#{eventTime}, event_des=#{eventDes} where user_id=#{userId} and event_id=#{eventId}
```

#### collect表单

> insert

```sql
insert into collect(event_id, user_id, is_delete) values (#{eventId}, #{userId}, "ENABLE")
```

> select

```sql
select e.event_name, e.event_time, e.event_addr from event_info as e join collect as c on e.event_id = c.event_id where c.user_id = #{userId} and is_delete = "ENABLE"

select u.user_name, u.user_phone from user_info as u join collect as c on u.user_id = c.user_id where c.event_id = #{eventId} and is_delete = "ENABLE"
```

> update 标记删除

```sql
update collect set is_delete = "DISABLE" where event_id = #{eventId} and user_id = #{userId}

update collect set is_delete = "DISABLE" where event_id = #{eventId}
```

> delete

```
delete from collect where is_delete = "DISABLE"
```

#### participate表单

> insert

```sql
insert into participate(event_id, user_id, is_delete) values (#{eventId}, #{userId}, "ENABLE")
```

> select

```sql
select e.event_name, e.event_time, e.event_addr from event_info as e join participate as p on e.event_id = p.event_id where p.user_id = #{userId} and is_delete = "ENABLE"

select u.user_name, u.user_phone from user_info as u join participate as p on u.user_id = p.user_id where p.event_id = #{eventId} and is_delete = "ENABLE"
```

> update 标记删除

```sql
update participate set is_delete = "DISABLE" where event_id = #{eventId} and user_id = #{userId}

update participate set is_delete = "DISABLE" where event_id = #{eventId}
```

> delete

```
delete from participate where is_delete = "DISABLE"
```
