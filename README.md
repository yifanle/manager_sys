# manager_sys
:heart: 仓库管理系统，一个毕设的案例

webapp中的页面及JS文件太多未上传,文件在结尾:sparkles:

运行环境：
>tomcat-8.5.45
>maven-3.3.9
>java-1.8

运用框架：
>spring-mvc
>spring-security
>spring
>mybatis、pageHelper
>vue.js

系统概述：<br><br>
1、通过spring security实现的RBAC权限的模型基础上实现权限、角色、资源的管理,实现根据数据库动态分配权限的功能，对未登录及未授权的操作进行拦截。在用户管理中心，管理员可添加用户代替了自行注册方式。<br><br>
2、实现基本的业务要求：商品、订单、库存之间的操作及关联关系，即对库存可进行入库、修改、补充库存、删除的操作，商品管理可从库存中添加商品并且为商品添加描述，订单管理及错列出的商品中进行购买产生订单，更新库存，若库存少于100则对绑定的邮箱发送库存预警信息。<br><br>
3、通过Spring的AOP的前置通知及后置通知实现系统中每一步操作的记录。<br><br>
4、首页展示每种商品的销量统计图、每日销售统计图<br><br>

系统预览：<br><br>
>首页:smiley_cat:<br>

![首页](https://img-blog.csdnimg.cn/20210204165650835.png)

>添加产品:smiley_cat:<br>

![添加产品](https://img-blog.csdnimg.cn/20210204170010891.png)

>权限详情:smiley_cat:<br>

![权限详情](https://img-blog.csdnimg.cn/20210204170305447.png)

>日志管理:smiley_cat:<br>

![日志管理](https://img-blog.csdnimg.cn/202102041704436.png)

喜欢请为我点个star:star:拒绝下次一定，从你我做起！:cherry_blossom:<br><br>
完整代码获取链接：https://wws.lanzous.com/iyWyRlb3qof :coffee: 
