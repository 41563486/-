<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <transition-group name="breadcrumb">
      <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">
        <span v-if="item.redirect==='noRedirect'||index==levelList.length-1" class="no-redirect">{{
            item.meta.title
          }}</span>
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </el-breadcrumb-item>
    </transition-group>
  </el-breadcrumb>
</template>

<script>
import * as pathToRegexp from 'path-to-regexp'
//  面包屑组件用来表示当前页面的位置,为页面左上角的导航
export default {
  data () {
    return {
      levelList: null
    }
  },
  watch: {
    $route (route) {
      // 重定向页面的时候不要刷新面包屑
      // if you go to the redirect page, do not update the breadcrumbs
      // 当前路径是否用 redirect开头
      if (route.path.startsWith('/redirect/')) {
        return
      }
      this.getBreadcrumb()
    }
  },
  created () {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb () {
      // only show routes with meta.title
      // 匹配具有meta.title的项
      let matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      const first = matched[0]

      // 判断第一个页面是否是主页，不是就添加一个主页
      // 链接成一个新数组
      if (!this.isDashboard(first)) {
        matched = [{ path: '/dashboard', meta: { title: '主页' } }].concat(matched)
      }
      // 最终的页面梯级列表
      this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
    },
    // 判断是否是仪表盘页面
    isDashboard (route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase()
    },
    // 解决路径编译问题
    pathCompile (path) {
      // To solve this problem https://github.com/PanJiaChen/vue-element-admin/issues/561
      const { params } = this.$route
      var toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    //
    handleLink (item) {
      const { redirect, path } = item
      //  判断是否含有重定向请求,如果有则返回，如果没有则处理编译后的路径
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    }
  }
}
</script>

<style lang="scss" scoped>
// 面包屑的样式
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>
