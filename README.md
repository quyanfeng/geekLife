# geekLife
此项目以idea为开发工具，基于springboot2.1.5为基础，致力于打造最优雅、最高效的单体架构！

##功能

  ### 1.引入tkmapper
    封装通用dao,代码更简洁，开发更高效
  
  ### 2.引入swagger2
    强大的接口文档插件，接口文档自动生成，使前后台对接，变得更高效，简易
  
  ### 3.引入自定义MBG
   
    3.1 实体类自动添加swagger,lombok相关属性
    3.2 dao自动添加 @Repository属性
    3.3 dao自动继承 MySqlMapper<T>,实现inserList功能
    3.4 重复执行，不会覆盖DAO及xml自定义方法
    3.5 ps:需将source目录下的maven包解压，替换原中央仓库下载下来的jar包
    
   
   
  