<template>
  <!-- eslint-disable vue/require-component-is -->
  <component v-bind="linkProps(to)">
    <slot/>
  </component>
</template>

<script>
import { isExternal } from '@/utils/validate'
// 对链接的操作，如果经过validate判断为外部链接则新建标签打开
export default {
  props: {
    to: {
      type: String,
      required: true
    }
  },
  methods: {
    // 判断是否为外部链接
    linkProps (url) {
      if (isExternal(url)) {
        return {
          is: 'a',
          href: url,
          // 新建空白标签
          target: '_blank',
          //  用户外部链接的安全性
          rel: 'noopener'
        }
      }
      // 如果是内部链接直接通过路由跳转
      return {
        is: 'router-link',
        to: url
      }
    }
  }
}
</script>
