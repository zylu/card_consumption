<template>
  <div>
    <svg-icon icon-class="question" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'RuoYiDoc',
  data() {
    return {
      url: 'https://huohuzhihui.yuque.com/books/share/c3b7f19c-be04-4b3f-8c3d-82a4a34db854?# 《使用手册》'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
