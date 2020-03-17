<template>
  <page-view :avatar="avatar" :title="false">
    <div slot="headerContent">
      <div class="title">{{ timeFix }}，{{ user.userName }}<span class="welcome-text">，{{ welcome }}</span></div>
      <div>高级后端工程师</div>
    </div>
    <div slot="extra">
      <a-row class="more-info">
        <a-col :span="8">
          <head-info :title="$t('dashboard.workplace.project')" content="56" :center="false" :bordered="false"/>
        </a-col>
        <a-col :span="8">
          <head-info :title="$t('dashboard.workplace.teamRank')" content="8/24" :center="false" :bordered="false"/>
        </a-col>
        <a-col :span="8">
          <head-info :title="$t('dashboard.workplace.views')" content="2,223" :center="false" />
        </a-col>
      </a-row>
    </div>

    <div>
      <a-row :gutter="24">
        <a-col :xl="16" :lg="24" :md="24" :sm="24" :xs="24">
          <a-card :bordered="false" title="CY-RuoYi">
            <p><strong>Spring Cloud Alibaba</strong> 版本的若依后台管理系统，集成 <strong>SpringCloud Alibaba + Dubbo + MybatisPlus + Mysql + Redis</strong>，自定义token实现授权，直接存到Redis。</p>
            <p><strong>Github地址： </strong><a href="https://github.com/chengyan0079/cy-ruoyi.git" target="_blank">https://github.com/chengyan0079/cy-ruoyi.git</a></p>
            <p><strong>中间件：</strong></p>
            <ul>
              <li>注册中心：Nacos</li>
              <li>配置中心：Nacos</li>
              <li>限流熔断：Sentinel</li>
              <li>消息队列：RocketMQ</li>
              <li>分布式事务：Seata</li>
              <li>分布式调用链：SkyWalking</li>
              <li>分库分表：Mycat</li>
              <li>分布式任务调度：XXL-Job</li>
              <li>日志收集：ELK</li>
              <li>容器：Docker</li>
            </ul>       
          </a-card>
        </a-col>
        <a-col
          style="padding: 0 12px"
          :xl="8"
          :lg="24"
          :md="24"
          :sm="24"
          :xs="24">
          <a-card title="作者微信" style="margin-bottom: 24px" :bordered="false" :body-style="{padding: 0}">
            <h3 style="margin: 12px 26px"></h3>
            <img style="width:100%" src="../../assets/wechat.png"/>
          </a-card>
        </a-col>
      </a-row>
    </div>
  </page-view>
</template>

<script>
import { timeFix } from '@/utils/util'
import { mapState } from 'vuex'
import { PageView } from '@/layouts'
import HeadInfo from '@/components/tools/HeadInfo'
import DetailList from '@/components/tools/DetailList'
import { getRoleList, getServiceList } from '@/api/manage'
import { Radar } from '@/components'
import { Timeline } from 'ant-design-vue'
const TimelineItem = Timeline.Item
const DetailListItem = DetailList.Item
const DataSet = require('@antv/data-set')

export default {
  name: 'Workplace',
  components: {
    PageView,
    HeadInfo,
    DetailListItem,
    TimelineItem,
    Radar
  },
  data () {
    return {
      timeFix: timeFix(),
      avatar: '',
      user: {},

      projects: [],
      loading: true,
      radarLoading: true,
      activities: [],
      teams: [],

      // data
      axis1Opts: {
        dataKey: 'item',
        line: null,
        tickLine: null,
        grid: {
          lineStyle: {
            lineDash: null
          },
          hideFirstLine: false
        }
      },
      axis2Opts: {
        dataKey: 'score',
        line: null,
        tickLine: null,
        grid: {
          type: 'polygon',
          lineStyle: {
            lineDash: null
          }
        }
      },
      scale: [{
        dataKey: 'score',
        min: 0,
        max: 80
      }],
      axisData: [
        { item: '引用', a: 70, b: 30, c: 40 },
        { item: '口碑', a: 60, b: 70, c: 40 },
        { item: '产量', a: 50, b: 60, c: 40 },
        { item: '贡献', a: 40, b: 50, c: 40 },
        { item: '热度', a: 60, b: 70, c: 40 },
        { item: '引用', a: 70, b: 50, c: 40 }
      ],
      changeList: [
        { title: '1.1.0-SNAPSHOT 2020-03-12',
          color: 'green',
          list: [
            '根据流程文件部署流程定义',
            'fix:用户管理调用所有部门接口',
            '工作流程追踪高亮连线',
            '工作流高亮已执行环节',
            '增加工作流activiti支持',
            'fix:更新path等为空时无效',
            'menu增加路径、重定向、隐藏等字段',
            '增加捐赠内容板块',
            'fix:代码生成调优',
            'fix: gen bugs gitee !9',
            '动态菜单优化'
          ]
        },
        { title: '1.0.2-SNAPSHOT 2019-09-30',
          color: 'green',
          list: [
            '优化角色授权实现联动和半选',
            '用户管理增加部门检索功能',
            '代码生成增加树形模板',
            '代码生成模板适配vue',
            '分布式文件系统',
            '修复代码生成前缀无法自动去除',
            '操作日志增加请求方法',
            '移除jwt依赖',
            'swagger接口文档支持',
            '前端i18n国际化支持',
            '增加vue代码生成（部分）',
            'vue-cropper头像组件'
          ]
        },
        { title: '1.0.1-SNAPSHOT 2019-08-23',
          color: 'green',
          list: [
            '新增ruoyi 4.0代码生成功能，未修改模板',
            '修复数据权限导致不能分页的bug',
            '增加oss文件上传',
            '增加参数配置管理',
            '升级spring-alibaba-cloud版本到2.1.x',
            '优化读写分离逻辑'
          ]
        },
        { title: '1.0.0-SNAPSHOT 2019-07-25',
          color: 'green',
          list: [
            'maven坐标修改为com.ruoyi.cloud',
            '升级springboot版本到2.1.6',
            '升级springcloud版本到Greenwich.SR2',
            '用户管理部门不应该选中父节点',
            '菜单授权联动bug（受限于ant-design）,取消父子联动',
            '数据权限',
            '移除多余的依赖声明',
            '增加vue代码生成（部分）',
            '修复登陆记录调取token的bug'
          ]
        }
      ],
      radarData: []
    }
  },
  computed: {
    ...mapState({
      nickname: (state) => state.user.nickname,
      welcome: (state) => state.user.welcome
    }),
    userInfo () {
      return this.$store.getters.userInfo
    }
  },
  created () {
    this.user = this.userInfo
    this.avatar = this.userInfo.avatar

    getRoleList().then(res => {
      // console.log('workplace -> call getRoleList()', res)
    })

    getServiceList().then(res => {
      // console.log('workplace -> call getServiceList()', res)
    })
  },
  mounted () {
    this.getTeams()
    this.initRadar()
  },
  methods: {
    getTeams () {
      this.$http.get('/workplace/teams')
        .then(res => {
          this.teams = res.result
          this.loading = false
        })
    },
    initRadar () {
      this.radarLoading = true

      this.$http.get('/workplace/radar')
        .then(res => {
          const dv = new DataSet.View().source(res.result)
          dv.transform({
            type: 'fold',
            fields: ['个人', '团队', '部门'],
            key: 'user',
            value: 'score'
          })

          this.radarData = dv.rows
          this.radarLoading = false
        })
    }
  }
}
</script>

<style lang="less" scoped>
.changList{
  margin-top: -14px;
}
.members {
    a {
      display: block;
      margin: 12px 0;
      line-height: 24px;
      height: 24px;
      .member {
        font-size: 14px;
        color: rgba(0, 0, 0, .65);
        line-height: 24px;
        max-width: 100px;
        vertical-align: top;
        margin-left: 12px;
        transition: all 0.3s;
        display: inline-block;
      }
      &:hover {
        span {
          color: #1890ff;
        }
      }
    }
  }
</style>
