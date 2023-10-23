<template>
  <div>
    <script :id="randomId" type="text/plain" style="height: 300px;"></script>
  </div>
</template>

<script>
// 百度的组件 UEditor 富文本编辑器
//  用于信息编辑栏
export default {
  name: 'UE',
  props: {
    value: {
      default: function () {
        return ''
      }
    }
  },
  data () {
    return {
      randomId: 'editor_' + Math.random() * 100000000000000000,
      // 编辑器实例
      instance: null,
      ready: false
    }
  },
  watch: {
    value: function (val, oldVal) {
      if (val != null && this.ready) {
        // eslint-disable-next-line no-undef
        this.instance = UE.getEditor(this.randomId)
        this.instance.setContent(val)
      }
    }
  },
  mounted () {
    this.initEditor()
  },

  beforeDestroy () {
    if (this.instance !== null && this.instance.destroy) {
      this.instance.destroy()
    }
  },
  methods: {
    // 初始化编辑框
    initEditor () {
      // 数据更新后 dom渲染了自动运行
      this.$nextTick(() => {
        // eslint-disable-next-line no-undef
        this.instance = UE.getEditor(this.randomId)
        this.instance.addListener('ready', () => {
          this.ready = true
          this.$emit('ready', this.instance)
        })
      })
    },
    // 获取文本框内的内容
    getUEContent () {
      return this.instance.getContent()
    },
    // 设置文本内容
    setText (con) {
      // eslint-disable-next-line no-undef
      this.instance = UE.getEditor(this.randomId)
      this.instance.setContent(con)
    }
  }
}
</script>
