<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
              v-model="deptName"
              placeholder="请输入部门名称"
              clearable
              prefix-icon="Search"
              style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
              :data="deptOptions"
              :props="{ label: 'label', children: 'children' }"
              :expand-on-click-node="false"
              :filter-node-method="filterNode"
              ref="deptTreeRef"
              node-key="id"
              highlight-current
              default-expand-all
              @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="姓名" prop="nickName">
            <el-input
                v-model="queryParams.nickName"
                placeholder="请输入姓名"
                clearable
                style="width: 240px"
                @keyup.enter="handleQuery"
            />
          </el-form-item>

          <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
<!--          <el-col :span="1.5">
            <el-button
                type="warning"
                plain
                icon="Download"
                @click="handleExport"
                v-hasPermi="['system:user:export']"
            >导出</el-button>
          </el-col>-->
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" ></right-toolbar>
        </el-row>
        <el-table
            v-loading="loading"
            :data="userList"
            @selection-change="handleSelectionChange"
            @row-click="handleRowClick"
            @expand-change="handleExpandChange"
        >
<!--          <el-table-column type="selection" width="50" align="center" />-->
          <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
<!--          <el-table-column type="expand">-->
<!--            <template #default="scope">-->
<!--              <el-table :data="scope.row.projects" style="width: 100%" class="expand-table">-->
<!--                <el-table-column label="项目名称" prop="projectName" align="right"  min-width="20" />-->
<!--                <el-table-column label="奖金" prop="ProjectBonus" align="center"  min-width="100" />-->
<!--              </el-table>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column type="expand">
            <template #default="props">
<!--              <el-table :data="bonusList.value" v-loading="bonusLoading.value" border style="width: 100%">-->
              <el-table :data="bonusData" class="expand-table" v-loading="bonusLoading.value" :key="tableKey">
                <el-table-column label="项目名称" prop="projectName" align="center" />
                <el-table-column label="奖金金额" prop="bonus" align="center">
                  <template #default="subScope">
                    {{ subScope.row.bonus !== null ? subScope.row.bonus.toFixed(2) : '0.00' }}
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column label="部门" align="center" key="deptName" prop="dept.deptName"  :show-overflow-tooltip="true" />
          <el-table-column label="姓名" align="center" key="nickName" prop="nickName"  :show-overflow-tooltip="true" />
          <el-table-column label="奖金" align="center" prop="totalBonus" />
        </el-table>
        <pagination
            v-show="total > 0"
            :total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
        />
      </el-col>
    </el-row>

  </div>
</template>

<script setup name="User">
import {  listUser,  deptTreeSelect } from "@/api/system/user";
import { ref, reactive, toRefs } from "vue"
const bonusList = ref([]);
const bonusLoading = ref(false);
const tableKey = ref(0);
const bonusData = computed(() => bonusList.value);
const selectedName = ref("");
import { getBonusListByName } from "@/api/business/bonus";
// 获取奖金数据
async function fetchBonuses(name) {
  bonusLoading.value = true;
  const currentUser = userList.value.find(u => u.nickName === name);
  if (!currentUser) return;

  try {
    const res = await getBonusListByName(name);

    let data = [];
    if (typeof res === 'string') {
      try {
        data = JSON.parse(res);
      } catch {
        data = [];
      }
    } else if (Array.isArray(res)) {
      data = res;
    } else if (res && typeof res === 'object') {
      data = res.data ?? [];
    }

    if (Array.isArray(data)) {
      // 更新 bonusList（用于子表）
      bonusList.value = data.map(item => ({
        projectName: item.projectName || '未知项目',
        bonus: item.bonusAmount === null ? 0 : item.bonusAmount
      }));

      // 更新当前用户的 totalBonus（如果未预加载）
      const total = data.reduce((sum, item) => sum + (item.bonusAmount || 0), 0);
      currentUser.totalBonus = total;
    } else {
      currentUser.totalBonus = 0;
    }
  } catch (error) {
    console.error('请求失败:', error);
    currentUser.totalBonus = 0;
  } finally {
    bonusLoading.value = false;
    tableKey.value += 1; // 强制刷新表格
  }
}

// 行点击事件
function handleRowClick(row) {
  selectedName.value = row.nickName;
  fetchBonuses(row.nickName);
}
function handleExpandChange(row, expandedRows) {
  if (expandedRows.includes(row)) {
    selectedName.value = row.nickName;
    fetchBonuses(row.nickName);
  }
}
const router = useRouter();
const { proxy } = getCurrentInstance();
const userList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);
const deptName = ref("");
const deptOptions = ref(undefined);



const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    nickName: undefined,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);


/** 通过条件过滤节点  */
const filterNode = (value, data) => {
  if (!value) return true;
  return data.label.indexOf(value) !== -1;
};
/** 根据名称筛选部门树 */
watch(deptName, val => {
  proxy.$refs["deptTreeRef"].filter(val);
});
/** 查询部门下拉树结构 */
function getDeptTree() {
  deptTreeSelect().then(response => {
    deptOptions.value = response.data;
  });
};
/** 查询用户列表 */
/*function getList() {
  loading.value = true;
  listUser(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
    loading.value = false;
    userList.value = res.rows;
    total.value = res.total;
  });
};*/
/** 查询用户列表 */
function getList() {
  loading.value = true;
  listUser(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
    loading.value = false;
    // userList.value = res.rows;
    userList.value = res.rows.map(user => ({
      ...user,
      totalBonus: 0 // 新增字段
    }));
    total.value = res.total;
    // 为每个用户预加载奖金总和
    userList.value.forEach(user => {
      fetchTotalBonus(user);
    });
    const bonusData = getBonusList();
  });
};
/** 节点单击事件 */
function handleNodeClick(data) {
  queryParams.value.deptId = data.id;
  handleQuery();
};
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
};
/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = [];
  proxy.resetForm("queryRef");
  queryParams.value.deptId = undefined;
  proxy.$refs.deptTreeRef.setCurrentKey(null);
  handleQuery();
};
async function fetchTotalBonus(user) {
  try {
    const res = await getBonusListByName(user.nickName);

    let data = [];
    if (typeof res === 'string') {
      try {
        data = JSON.parse(res);
      } catch {
        data = [];
      }
    } else if (Array.isArray(res)) {
      data = res;
    } else if (res && typeof res === 'object') {
      data = res.data ?? [];
    }

    if (Array.isArray(data)) {
      const total = data.reduce((sum, item) => sum + (item.bonusAmount || 0), 0);
      user.totalBonus = total;
    } else {
      user.totalBonus = 0;
    }
  } catch (error) {
    console.error(`获取用户 ${user.nickName} 的奖金失败:`, error);
    user.totalBonus = 0;
  }
}
/** 导出按钮操作 */
/*function handleExport() {
  proxy.download("system/user/export", {
    ...queryParams.value,
  },`user_${new Date().getTime()}.xlsx`);
};*/
/** 选择条数  */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.userId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
};
/** 导入按钮操作 */
/*function handleImport() {
  upload.title = "用户导入";
  upload.open = true;
};*/


/** 生成模拟数据 */
function getBonusList() {
  return [
    {
      userId: 101,
      dept: { deptName: '研发部门' },
      nickName: '张三',
      bonus: 0,
      projects: [
        { projectId: 1, projectName: '项目1', bonus: 1000 },
        { projectId: 2, projectName: '项目2', bonus: 2000 }
      ]
    },
    {
      userId: 102,
      dept: { deptName: '研发部门' },
      nickName: '李四',
      bonus: 0,
      projects: [
        { projectId: 3, projectName: '项目3', bonus: 1500 }
      ]
    },
    {
      userId: 103,
      dept: { deptName: '研发部门' },
      nickName: '王五',
      bonus: 0,
      projects: [
        { projectId: 4, projectName: '项目4', bonus: 3000 },
        { projectId: 5, projectName: '项目5', bonus: 2500 }
      ]
    }
  ];
}


getDeptTree();
getList();

</script>


<style scoped>.expand-table th,
.expand-table td {
  font-size: 10px !important;
  color: #0000FF !important;
  font-weight: normal !important;
  line-height: 1.2 !important;
  padding: 4px 10px !important;
}
</style>
