# Survey-Creater


## 简介
项目用于问卷调查及结果分析，使用[SurveyJS](https://github.com/surveyjs/surveyjs)与[ECharts](https://www.echartsjs.com/index.html)、[V-Charts](https://codesandbox.io/s/z69myovqzx)等技术。外搭粗糙的用户管理功能。


## 运行环境

建议使用**IDEA**编译运行，搭配**Google Chrome**较新版本使用。

 - JDK 1.8
 - Maven -
 - SpringBoot 2.0+
 - Mysql 8.0
 - Mybatis Plus 2.3
 - redis 3.2.10 -s 6379
 - jedis 2.9.1
 - lombok 1.18.4
 - jjwt 0.9.1
 - javamail 1.4
...
 
 前端

- vue 2.0+
- Echarts 4.1.0
- SurveyJS 1.0.73
- v-charts
- vue-fullscreen
...


## 项目结构

根目录下两个文件夹里分别是前端项目和后端项目，实际前端项目已放到后端项目上，可分开运行也可只用**Survey-Creater**部分，需搭配**redis**和**mysql**运行。

 - 后端主框架使用**SpringBoot**

项目结构：<br/>
![](http://ww1.sinaimg.cn/large/006azB5Sly1g1h7dxenrzj30by0jzq3n.jpg)

 - 数据库操作使用**MybatisPlus**

 - 前端是**Vue.js**
项目结构:<br/>
![](http://ww1.sinaimg.cn/large/006azB5Sly1g0zteook51j308i0ieglz.jpg)


## Run

如果不想要前端部分的代码，直接把该项目的**Survey-Creater**部分放到编译器下运行，自己提前运行一个Redis服务

运行后首页登陆页面位置在

	localhost:8080/index.html

如果，需要前后端分离的形式

同样按上面的步骤运行后台服务，前台打开[Survey-Creater-view/survey-creater](https://github.com/HolyDogs/Survey-Creater/tree/master/Survey-Creater-view/survey-creater "This path skips through empty directories")目录，命令行运行(得提前装好nodejs)

		npm install
然后运行

		npm run dev

首页位置在

		localhost:8081/


![](http://ww1.sinaimg.cn/large/006azB5Sly1g0zstirvn8j31hc0q1gov.jpg)



----





## 项目示例
![](http://ww1.sinaimg.cn/large/006azB5Sly1g14sb52ncsj31h80p341i.jpg)

![](http://ww1.sinaimg.cn/large/006azB5Sly1g14sdrf30tj31hb0pcgqt.jpg)

![](http://ww1.sinaimg.cn/large/006azB5Sly1g0zt38m3dyj31hc0pt140.jpg)

![](http://ww1.sinaimg.cn/large/006azB5Sly1g0zt46bx6hj31hc0kxdo4.jpg)

![](http://ww1.sinaimg.cn/large/006azB5Sly1g0zt5bkbdqj31hc0gfaby.jpg)

## 相关文档

>SurveyJS https://surveyjs.io/Documentation/Library/

>Echarts https://www.echartsjs.com/tutorial.html#5%20%E5%88%86%E9%92%9F%E4%B8%8A%E6%89%8B%20ECharts

>V-Charts https://v-charts.js.org/#/


## 友情链接

>My Blog http://xfff.xyz
