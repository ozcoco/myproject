##**打球app开发文档**##
“打球”APP是深圳市动客信息技术有限公司精心研发的，主要专注于以球类运动为导向的移动客户端，高尔夫球是其中的重要项目。

注：
本文假设你已经有Android开发环境
本文以eclipse为例
启动Eclipse，导入Android客户端项目，请确保你当前的Android SDK是最新版。 如果编译出错，请修改项目根目录下的 project.properties 文件。 推荐使用Android 4.0 以上版本的SDK：
target=android-19

# **一、工程目录结构** ##
根目录<br>
>├ wangolf2015_new_studio  <br> 
>├ LICENCE.txt <br>
>├ README.md <br>

目录简要解释<br>
根目录<br>
>├ wangolf2015_new_studio --源代码 <br> 
>├ librayr -- alipay_lib  --wheel --library_imground<br>
>├ README.md --项目帮助及项目信息 <br>

## **二、源代码目录结构** ##
alipay_lib --支付宝<br>
wheel--天气<br> 
library_imground--圆形头像<br> 
wangolf--项目主目录<br>
>├ src  <br> 
>├ libs <br>
>├ res <br>
>├ AndroidManifest.xml <br>
>├ proguard-project.txt <br>
>└ project.properties <br>

**1、src目录** <br>
src目录用于存放工程的包及java源码文件。

下面是src目录的子目录：

> src <br>

>├ me.wangolf --存放程序全局性类的包<br>
>├ me.wangol.adapter --存放适配器的实现类的包 <br>
>├ me.wangol.base --存放基类的包<br>
>├ me.wangolf.ballprac --存放球场activity包<br>
>├ me.wangolf.bean －－存放项目所有使用的bean<br>
>├ me.wangolf.calender --日历控件相关acitivity<br>
>├ me.wangolf.city --城市选择相关acitivity<br>
>├ me.wangolf.college --新手资讯相关activity包（以前的学院）<br>
>├ me.wangolf.dao --数据库操作类包<br>
>├ me.wangolf.event --活动activity相关类的包<br>
>├ me.wangolf.factory --工厂包管理所有接口<br>
>├ me.wangolf.fragment --fragment相关类包管理各个模块的切换（废）<br>
>├ me.wangolf.newfragmenti --fragment相关类包管理各个模块的切换（新）<br>
>├ me.wangolf.practice --练习场相关activity相关的基类包<br>
>├ me.wangolf.service --服务层相关接口及实现类<br>
>├ me.wangolf.shop --商城相关activity类包<br>
>├ me.wangolf.usercenter --用户中心相关activity类包<br>

>├ com.meigao.mgolf.wxapi --微信支付回调接口类包<br>
>├ ccn.sharesdk.onekeyshare -- sharesdk分享相关类包<br>
>├ com.example.topnewgrid -- 训练营切换相关类包<br>

**2、libs目录** <br>
libs目录用于存放项目引用的第三方jar包。

libs目录里的jar包文件：

libs
>├  android-support-v4.jar --v4兼容包<br>
>├ gson-2.2.1.jar --解析json的包<br>
>├ xUtils-2.6.14.jar --网络连接的包<br>
>├ umeng-analytics-v5.4.2.jar --友盟统计的包<br>

**3、res目录** <br>
res目录存放工程用到的图片、布局、样式等资源文件。<br>
res目录的子目录：<br>

res <br>
>├ anim <br>
>├ color <br>
>├ drawable <br>
>├ drawable-hdpi <br>
>├ drawable-ldpi <br>
>├ drawable-mdpi <br>
>├ drawable-xhdpi <br>
>├ interpolator<br>
>├ layout <br>
>├ menu <br>
>├ raw <br>
>├ values <br>

**4、AndroidManifest.xml**<br>
AndroidManifest.xml用于设置应用程序的版本、主题、用户权限及注册Activity等组件及其他配置。

## **三、程序功能流程** ##
**1、APP启动流程**

AndroidManifest.xml注册的启动Activity是"me.wangolf.WeComeActivity"，然后进入到主界面，对应的

Activity是“me.wangolf.newfragment.MainActivity”


**2.程序功能**<br>
 (1)练习场预订<br>
 (2)训练营<br>
 (3)球场预订<br>
 (4)商城<br>
 (5)社区<br>
 (6)个人中心<br>
