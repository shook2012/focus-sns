  1. FocusSNS 中文乱码？
> FocusSNS是Linux下开发，在Linux环境下，我系统的编码统一都是 UTF-8 环境。因此经常有朋友在 windows 下使用会出现各种编码问题，例如源代码导入 eclipse 时会发现乱码，部署完时也发现了乱码。关于源代码导入 eclipse 时出现的乱码你可以通过改变 workspace 的编码为 UTF-8 来解决，而数据库中文乱码的话，你需要将mysql的系统默认编码设为 UTF-8。
```
关于MySql默认编码设置，你只需要在 my.conf 或 my.init 文件里找到 [mysqld] 块，
添加 default-character-set = utf8 即可。
```
> 你也可以试试在Tomcat启动脚本中加入一个文件编码的启动选项 JAVA\_OPTS="-Dfile.encoding=UTF-8"