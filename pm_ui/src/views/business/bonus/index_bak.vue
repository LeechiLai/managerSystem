<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="userName">
        <el-input
            v-model="queryParams.userName"
            placeholder="请输入姓名"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>-->

    <el-table
        v-loading="loading"
        :data="bonusList"
        @selection-change="handleSelectionChange"
        row-key="id"
        :expand-row-keys="expandRowKeys"
    >
      <el-table-column type="expand">
        <template #default="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <div v-for="(project, index) in props.row.projects" :key="index">
              <el-form-item label="项目名">
                <span>{{ project.projectName }}</span>
              </el-form-item>
              <el-form-item label="金额">
                <span>{{ project.amount }}</span>
              </el-form-item>
            </div>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="部门" align="center" prop="userName" />
      <el-table-column label="姓名" align="center" prop="userName" />
      <el-table-column label="奖金" align="center" prop="bonus" />
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />
  </div>
</template>

<script setup name="Bonus">
import { ref, reactive, toRefs, getCurrentInstance } from "vue";

const { proxy } = getCurrentInstance();

const bonusList = ref([
  {
    id: 1,
    userName: "张三",
    projects: [
      {
        projectName: "项目1",
        amount: 2000
      },
      {
        projectName: "项目3",
        amount: 3000
      }
    ]
  },
  {
    id: 2,
    userName: "李四",
    projects: [
      {
        projectName: "项目2",
        amount: 3000
      }
    ]
  }
]);

// 计算总奖金
bonusList.value.forEach(user => {
  user.bonus = user.projects.reduce((sum, project) => sum + project.amount, 0);
});

const open = ref(false);
const loading = ref(false);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(bonusList.value.length);
const title = ref("");
const expandRowKeys = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userName: null,
  },
  rules: {}
});

const { queryParams, form, rules } = toRefs(data);

/** 查询个人奖金列表 */
function getList() {
  // 这里不需要从后端获取数据，直接使用预置的数据
  loading.value = true;
  setTimeout(() => {
    const data = [
      {
        id: 1,
        userName: "张三",
        projects: [
          {
            projectName: "项目1",
            amount: 3000
          },
          {
            projectName: "项目3",
            amount: 3000
          }
        ]
      },
      {
        id: 2,
        userName: "李四",
        projects: [
          {
            projectName: "项目2",
            amount: 3000
          }
        ]
      }
    ];

    // 计算总奖金
    data.forEach(user => {
      user.bonus = user.projects.reduce((sum, project) => sum + project.amount, 0);
    });

    bonusList.value = data;
    total.value = bonusList.value.length;
    loading.value = false;
  }, 500);
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bonusRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateBonus(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addBonus(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/bonus/export', {
    ...queryParams.value
  }, `bonus_${new Date().getTime()}.xlsx`);
}

getList();
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
