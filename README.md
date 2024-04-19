#  系统部署流程

### clone项目本地

进入放置项目的文件夹，在该文件夹下打开`git Bash`，输入以下指令

```git
git clone https://github.com/love-enough/educationSystem.git
```

### 打开项目

打开`IDEA`，选择clone好的项目打开即可

### 运行数据库文件

打开数据库软件，如`Navicat`等，创建数据库`education`，右键点击该数据库，选择运行sql文件，将`education.sql`文件运行即可

### 修改配置文件

在IDEA中打开`application.properties`，主要修改数据库配置

```properties
spring.datasource.username=root
# 这里将密码改成自己的
spring.datasource.password=666554
```

### 运行项目

打开文件`EducationApplication.java`，右键点击run即可

### 访问网址说明

:tipping_hand_man:记住后台和前台不要一起等，还有个小bug没有解决，可以用admin账号先登录后台看一看 ，然后再打开前台登录页，使用guozihan账号登录前台看。

后台管理系统登录页

- http://localhost:8080/education/login
- 管理员
  - 账号：admin
  - 密码：123
- 教师
  - 账号：chenzhuoya
  - 密码：123

前台登录

- http://localhost:8080/education/front/login

- 学生

  - 账号：guozihan
  - 密码：666554

  

