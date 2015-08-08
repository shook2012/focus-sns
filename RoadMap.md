> FocusSNS 1.1.0 版本，同时项目将使用新的名称 [Focus SNS](http://code.google.com/p/focus-sns/)，接下来一段时间项目会逐步过度到 [Focus SNS](http://code.google.com/p/focus-sns/)， 1.1.0 版本的主要更新内容如下：

  1. 弃用 URLRewrite ，使用一个全局的 Route Controller，使得页面的流转更清晰
  1. 使用 [Spring4Me](http://code.google.com/p/spring4me/) 替代原有的 platform 部分代码，目前 [Spring4Me](http://code.google.com/p/spring4me/) 也已是 Google Code 的一个开源项目
  1. 优化表单提交跳转流程，并增强服务端的基于 Annotation 的表单验证
  1. 为了未来更容易的国际化，视图有 JSP 转为 FreeMarker

> 总的来说这是一次较大的更新，目的是为了简化二次开发，让更多的开发者可以很容易的参与进来！

> 
---


> Enterprise Connect 1.0.3 发布，主要包括如下更新：

  1. 浏览器兼容 firefox chrome ie7～8
  1. 微博优化，支持表情，图片，链接及图片的放大显示
  1. 新增 platform-social 工程，用来支持 sina 腾讯的 oauth 认证，及微博同步
  1. 将 Enterprise Connect 的 platform 部分代码进行了一次整理，并创建了一个新的项目 [Spring4Me](http://code.google.com/p/spring4me/)

> 
---


> Enterprise Connect 1.0.2 发布，主要包括如下更新：

  1. 完善 Activity Stream，目前已支持 Profile Calendar Blog Document Discussion Team Knowledge   Gallery
  1. 完善邮件通知功能
  1. 解决上一版本中存在的问题
  1. 编写开发指南

> 
---


> Enterprise Connect 1.0.1 发布，关于Enterprise Connect的安装部署及开发，请阅读Wiki文档...

> 主要修改了1.0.0 版本中存在的一些Bug，并简化了后台的权限管理。

> Enterprise Connect 1.0.1 版本在源代码根目录下的 [src/databases](http://enterpriseconnect.googlecode.com/svn/trunk/src/databases/) 下增加了一个 mysql 脚本，mysql 用户只需要

> 导入该脚本就可以使用最简单的 Enterprise Connect。