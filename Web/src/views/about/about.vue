<template>
<div class="app-container">
    <h1>前端参考资料</h1>
    <ul>
      <li><a href="http://es6.ruanyifeng.com/">ECMAScript 6 入门</a></li>
      <li><a href="https://router.vuejs.org/zh-cn/essentials/getting-started.html">vue.js官方入门手册中文版</a></li>
      <li><a href="https://router.vuejs.org/zh-cn/">vue router路由</a></li>
      <li><a href="https://vuex.vuejs.org/zh-cn/getters.html">vuex 状态管理</a></li>
      <li><a href="https://segmentfault.com/a/1190000006178770">webpack入门</a></li>
      <li><a href="https://segmentfault.com/a/1190000011881720">EsLint 静态代码检查</a></li>
      <li><a href="http://www.cnblogs.com/wisewrong/p/6255817.html">Vue爬坑之路系列文章</a></li>
      <li><a href="https://www.kancloud.cn/yunye/axios/234845">axios使用说明</a></li>
      <li><a href="http://element-cn.eleme.io/#/zh-CN/component/container">饿了么前端UI组件 2.3.4</a></li>
      <li><a href="http://echarts.baidu.com/tutorial.html#5%20分钟上手%20ECharts">Echarts报表组件</a></li>
    </ul>
    <h1>后端参考资料</h1>
    <ul>
      <li>Spring Boot（<a href="http://www.jianshu.com/p/1a9fd8936bd8" rel="nofollow">查看Spring Boot学习&amp;使用指南</a>）</li>
      <li>MyBatis（<a href="http://www.mybatis.org/mybatis-3/zh/index.html" rel="nofollow">查看官方中文文档</a>）</li>
      <li>MyBatisb通用Mapper插件（<a href="https://mapperhelper.github.io/docs/" rel="nofollow">查看官方中文文档</a>）</li>
      <li>MyBatis PageHelper分页插件（<a href="https://pagehelper.github.io/" rel="nofollow">查看官方中文文档</a>）</li>
      <li>Druid Spring Boot Starter（<a href="https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/">查看官方中文文档</a>）</li>
      <li>Fastjson（<a href="https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5">查看官方中文文档</a>）</li>
    </ul>
</div>
</template>
<script>
export default {
  name: 'about',
  methods: {
    test() {
      alert(1)
    }
  }
}
</script>
<style scoped lang="scss">
.markdown-body {
    font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Helvetica,Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
    font-size: 16px;
    line-height: 1.5;
    word-wrap: break-word;
}
a {
    color: #0366d6;
    text-decoration: none;
}

.markdown-body h1 {
    padding-bottom: 0.3em;
    font-size: 1.5em;
    border-bottom: 1px solid #eaecef;
}
</style>
