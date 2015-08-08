## 经常有朋友反馈 Google Code 无法访问，FocusSNS 2.0 已经迁移到 [Github](https://github.com/gavin-hu/focusns) ##


---

FocusSNS 是一个开源的主题社会化网络软件(SNS)，可以用于构建各类主题社区、企业社区、协作平台等。并采用 [AGPL3](http://www.opensource.org/licenses/agpl-v3) 授权协议。FocusSNS以前的项目名为[EnterpriseConnect](http://code.google.com/p/enterpriseconnect/).
FocusSNS is a themed social network software(SNS). It can build kinds of themed communities, enterprise communities and collaboration platforms.

关于主题SNS: 进入主题SNS后将呈现的是一些特定主题，比如技术，产品，商务，文化等。 人们基于这些兴趣主题进行社交，交流。  而论坛，wiki, 博客，文档等技术表现手段都是为更好的表现这些主题服务。 论坛，wiki等将不再出现在主菜单。典型的主题SNS网站如QQ群空间，豆瓣，搜房网, linkedin, Google group等.


FocusSNS QQ讨论群: 144832020（100人已满），86602861（200人）

我们提供基于FocusSNS的定制和运维服务, 请联系Steven Cheng, steven.cheng@opensourceforce.org 或 18621809060 .


**招募：FocusSNS仍然还有很多的改进空间，现招聘全职开发人员，请联系steven.cheng@opensourceforce.org ,  邮件请附上你对FocusSNS的认识和改进建议。**


### Features ###
  1. Social Profile
  1. Calendar
  1. Micro Blog
  1. Blog
  1. Document
  1. Discussion (Forum)
  1. Team
  1. Message
  1. Photo
  1. Question & Answer
  1. List (收藏夹)
  1. Review


---

**FocusSNS 1.1.3 版本更新内容：**
  1. 使用 jetty-maven-plugin 插件作为 Web 开发环境
  1. 默认使用 MySql 数据库，并用最近的 MySql 脚本初始化数据库
  1. 优化项目依赖，目前只依赖 Maven 中央仓库，当然 Spring4Me 还是依赖的，你需要自己下载 Spring4Me 的源码并 mvn install 一下

---

FocusSNS 1.1.2 版本发布，主要更新如下内容：
  1. 新增评论模块
  1. 支持HibernateSearch进行站内全文检索
  1. 添加了不少新的 Widget， 方便各种内容以不同的形式展示
  1. 维护一个 trunk 版本的在线 Demo,使得关心 FocusSNS 的朋友能更直接的了解 FocusSNS 的进展情况

---


FocusSNS 1.1.1 版本发布，主要更新内容如下：
  1. 新增收藏模块
  1. 优化问题模块
  1. 解决Google Code Issues 中报告的一些问题及Bug


---


FocusSNS 1.1.0 版本发布，主要更新内容如下：

  1. 弃用 URLRewrite ，使用一个全局的 Route Controller，使得页面的流转更清晰
  1. 使用 [Spring4Me](http://code.google.com/p/spring4me/) 替代原有的 platform 部分代码，目前 [Spring4Me](http://code.google.com/p/spring4me/) 也已是 Google Code 的一个开源项目
  1. 优化表单提交跳转流程，并增强服务端的基于 Annotation 的表单验证
  1. 为了未来更容易的国际化，视图有 JSP 转为 FreeMarker

> 总的来说这是一次较大的更新，目的是为了简化二次开发，让更多的开发者可以很容易的参与进来！

> 
---


Enterprise Connect 1.0.3 发布，主要包括如下更新：

  1. 浏览器兼容 firefox chrome ie7～8
  1. 微博优化，支持表情，图片，链接及图片的放大显示
  1. 新增 platform-social 工程，用来支持 sina 腾讯的 oauth 认证，及微博同步
  1. 将 Enterprise Connect 的 platform 部分代码进行了一次整理，并创建了一个新的项目 [Spring4Me](http://code.google.com/p/spring4me/)

> 
---


Enterprise Connect 1.0.2 发布，主要包括如下更新：

  1. 完善 Activity Stream，目前已支持 Profile Calendar Blog Document Discussion Team Knowledge   Gallery
  1. 完善邮件通知功能
  1. 解决上一版本中存在的问题
  1. 编写开发指南

> 
---


Enterprise Connect 1.0.1 发布，关于Enterprise Connect的安装部署及开发，请阅读Wiki文档...

  1. 主要修改了1.0.0 版本中存在的一些Bug，并简化了后台的权限管理。
  1. Enterprise Connect 1.0.1 版本在源代码根目录下的 [src/databases](http://enterpriseconnect.googlecode.com/svn/trunk/src/databases/) 下增加了一个 mysql 脚本，mysql 用户只需要
  1. 导入该脚本就可以使用最简单的 Enterprise Connect。

> 
---

![http://enterpriseconnect.googlecode.com/files/osforce-3.png](http://enterpriseconnect.googlecode.com/files/osforce-3.png)

![http://enterpriseconnect.googlecode.com/files/osforce-4.png](http://enterpriseconnect.googlecode.com/files/osforce-4.png)