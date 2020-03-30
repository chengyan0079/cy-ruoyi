<template>
  <a-card :bordered="false">
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="48">
          <a-col :md="5" :sm="15">
            <a-form-item label="IP地址">
              <a-input placeholder="请输入" v-model="queryParam.ipaddr"/>
            </a-form-item>
          </a-col>
          <a-col :md="5" :sm="15">
            <a-form-item label="登陆名称">
              <a-input placeholder="请输入" v-model="queryParam.userName"/>
            </a-form-item>
          </a-col>
          <a-col :md="8" :sm="24">
            <span class="table-page-search-submitButtons">
              <a-button type="primary" @click="$refs.table.refresh(true)">查询</a-button>
              <a-button style="margin-left: 8px" @click="() => queryParam = {}">重置</a-button>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- <div class="table-operator"> -->
      <!-- <a-button type="primary" icon="plus" @click="$refs.modal.add()">新建</a-button>
      <a-dropdown v-if="selectedRowKeys.length > 0"> -->
      <!-- <a-button v-if="addEnable" type="primary" icon="plus" @click="$refs.modal.add()">新建</a-button> -->
     <!--  <a-dropdown v-if="removeEnable&&selectedRowKeys.length > 0"> -->
        <!-- <a-menu slot="overlay">
          <a-menu-item key="1"><a-icon type="delete" />删除</a-menu-item>
          <a-menu-item key="2"><a-icon type="lock" />禁用</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">
          批量操作 <a-icon type="down" />
        </a-button>
        <a-button type="danger" icon="delete" @click="delByIds(selectedRowKeys)">删除</a-button>
      </a-dropdown>
     </div> -->
    <s-table
      size="default"
      ref="table"
      rowKey="userId"
      :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
      :columns="columns"
      :data="loadData"
    >
      <!-- <div
        slot="expandedRowRender"
        slot-scope="record"
        style="margin: 0">
        <a-row
          :gutter="24"
          :style="{ marginBottom: '12px' }">
          <a-col :span="12" v-for="(role, index) in record.permissions" :key="index" :style="{ marginBottom: '12px' }">
            <a-col :lg="4" :md="24">
              <span>{{ role.permissionName }}：</span>
            </a-col>
            <a-col :lg="20" :md="24" v-if="role.actions.length > 0">
              <a-tag color="cyan" v-for="(action, k) in role.actions" :key="k">{{ action.describe }}</a-tag>
            </a-col>
            <a-col :span="20" v-else>-</a-col>
          </a-col>
        </a-row>
      </div> -->
      <!-- <span slot="status" slot-scope="text,record">
        <a-switch :checked="record.status==0" @change="onChangeStatus(record)"/>
      </span> -->
      <span slot="action" slot-scope="text, record">
        <!-- <a v-if="editEnabel" @click="handleEdit(record)">编辑</a>
         <a-divider type="vertical" />
        <a v-if="editEnabel" @click="handleScope(record)">数据权限</a>
        <a-divider type="vertical" />
        <a v-if="removeEnable" @click="delByUserId(record.userId)">强退</a> -->
        <a-popconfirm title="是否要删除此行？" v-if="removeEnable" @confirm="delByUserId(record.userId)">
          <a>强退</a>
        </a-popconfirm>
      </span>
    </s-table>
    <role-modal ref="modal" @ok="handleOk" />
    <role-scope-modal ref="scopemodal" @ok="handleOk" />
  </a-card>
</template>

<script>
import { STable } from '@/components'
import { getOnlineUserList, delOnlineUser } from '@/api/system'
import { checkPermission } from '@/utils/permissions'
export default {
  name: 'TableList',
  components: {
    STable
  },
  data () {
    return {
      description: '',
      visible: false,
      labelCol: {
        xs: { span: 24 },
        sm: { span: 5 }
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 }
      },
      form: this.$form.createForm(this),
      mdl: {},
      permissions: [],
      // 高级搜索 展开/关闭
      advanced: false,
      // 查询参数
      queryParam: {},
      // 表头
      columns: [
        {
          title: '用户编号',
          dataIndex: 'userId'
        },
        {
          title: 'Token',
          dataIndex: 'token'
        },
        {
          title: '登陆名称',
          dataIndex: 'loginName'
        },
        {
          title: '部门名称',
          dataIndex: 'deptName'
        },
        {
          title: '登陆IP地址',
          dataIndex: 'ipaddr'
        },
        {
          title: '登陆地点',
          dataIndex: 'loginLocation'
        },
        {
          title: '浏览器',
          dataIndex: 'browser'
        },
        {
          title: '操作系统',
          dataIndex: 'os'
        },
        {
          title: '登陆时间',
          dataIndex: 'loginTime',
          sorter: true
        }, {
          title: '操作',
          width: '200px',
          dataIndex: 'action',
          scopedSlots: { customRender: 'action' }
        }
      ],
      // 加载数据方法 必须为 Promise 对象
      loadData: parameter => {
        return getOnlineUserList(Object.assign(parameter, this.queryParam))
      },
      selectedRowKeys: [],
      selectedRows: [],
      //  addEnable: checkPermission('system:role:add'),
      // editEnabel: checkPermission('monitor:online:forceLogout')
      removeEnable: checkPermission('monitor:online:forceLogout')
    }
  },
  created () {
  },
  methods: {
    onSelectChange (selectedRowKeys) {
      console.log('selectedRowKeys changed: ', selectedRowKeys)
      this.selectedRowKeys = selectedRowKeys
    },
    onChange (selectedRowKeys, selectedRows) {
      this.selectedRowKeys = selectedRowKeys
      this.selectedRows = selectedRows
    },
    handleOk () {
      this.$refs.table.refresh(true)
      console.log('handleSaveOk')
    },
    delByUserId (userId) {
      delOnlineUser(userId).then(res => {
        if (res.code === 0) {
          this.$message.success(`强退成功`)
          this.handleOk()
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  },
  watch: {
    /*
      'selectedRows': function (selectedRows) {
        this.needTotalList = this.needTotalList.map(item => {
          return {
            ...item,
            total: selectedRows.reduce( (sum, val) => {
              return sum + val[item.dataIndex]
            }, 0)
          }
        })
      }
      */
  }
}
</script>
