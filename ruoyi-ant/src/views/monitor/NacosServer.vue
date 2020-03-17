<template>
  <a-card :bordered="false">
    <div v-loading="loading" :style="'height:'+ height">
      <iframe :src="src" frameborder="no" style="width: 100%;height: 100%" scrolling="auto" />
    </div>
  </a-card>
</template>

<script>
export default {
  name: 'NacosServer',
  data () {
    return {
      src: process.env.VUE_APP_NACOS_SERVER_URL + 'nacos/#/configurationManagement?dataId=&group=&appName=&namespace=',
      loading: true,
      height: document.documentElement.clientHeight - 94.5 + 'px;'
    }
  },
  mounted () {
    setTimeout(() => {
      this.loading = false
    }, 230)
    window.onresize = () => {
      this.height = document.documentElement.clientHeight - 94.5 + 'px;'
    }
  }
}
</script>
