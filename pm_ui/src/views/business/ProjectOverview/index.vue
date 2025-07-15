<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        <el-button icon="Refresh" @click="getList">刷新</el-button>
<!--        <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['business:ProjectOverview:export']" >导出</el-button>-->
      </el-form-item>
    </el-form>



    <el-table v-loading="loading" :data="ProjectOverviewList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
      <el-table-column label="序号" type="index" width="55" align="center" prop="id" />
<!--      <el-table-column label="执行状态" align="center" prop="prjexecStatus">
        <template #default="scope">
          <dict-tag :options="prjexec_status" :value="scope.row.prjexecStatus"/>
        </template>
      </el-table-column>-->
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true" />
      <el-table-column label="所属部门" align="center" prop="department" width="120" :show-overflow-tooltip="true" >
        <template #default="scope">
          <dict-tag :options="department" :value="scope.row.department"/>
        </template>
      </el-table-column>
      <el-table-column label="总投资" align="center" prop="totalInvestment" />
      <el-table-column label="合同金额(含税)" align="center" prop="amtIncTax" width="120"/>
      <el-table-column label="合同金额" align="center" prop="amtExcTax" :show-overflow-tooltip="true" />
      <el-table-column label="阶段合同额" align="center" prop="stageAmtIncTa" width="100"/>
      <el-table-column label="项目负责人" align="center" prop="projectManager" width="100">
        <template #default="scope">
          <dict-tag :options="project_manager" :value="scope.row.projectManager"/>
        </template>
      </el-table-column>
      <el-table-column label="分包金额" align="center" prop="subcAmtIncTax" />
      <el-table-column label="项目已收款" align="center" prop="recvdAmt" width="100"/>
      <el-table-column label="项目未收款" align="center" prop="unrecdAmt" width="100"/>
      <el-table-column label="已收款比例 " align="center" prop="recvRatio" width="100">
        <template #default="scope">
          <el-progress :percentage="scope.row.recvRatio" :color="['#f56c6c', '#e6a23c', '#6f7ad3', '#1989fa', '#5cb87a']" />
        </template>
      </el-table-column>
      <el-table-column label="以前年度收款" align="center" prop="prevYrRecv" width="110"/>
      <el-table-column label="本年度收款" align="center" prop="currYrRecv" width="100"/>
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

<script setup name="ProjectOverview">
import { listProjectOverview, getProjectOverview, delProjectOverview, addProjectOverview, updateProjectOverview } from "@/api/business/ProjectOverview";

const { proxy } = getCurrentInstance();
const { industry_director, project_scale, department, industry_subcategory, region, project_manager, prjexec_status, industry_category, business_type, contract_status, project_phase } = proxy.useDict('industry_director', 'project_scale', 'department', 'industry_subcategory', 'region', 'project_manager', 'prjexec_status', 'industry_category', 'business_type', 'contract_status', 'project_phase');

const ProjectOverviewList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    projectName: null,
    projectManager: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目概况列表 */
function getList() {
  loading.value = true;
  listProjectOverview(queryParams.value).then(response => {
    ProjectOverviewList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    yearmonth: null,
    businessType: null,
    industryCategory: null,
    industrySubcategory: null,
    region: null,
    projectScale: null,
    contractSxsj: null,
    contractStatus: null,
    prjexecStatus: null,
    department: null,
    originalDepartment: null,
    projectCode: null,
    projectPhase: null,
    contractNumber: null,
    projectName: null,
    constructionUnit: null,
    projectScope: null,
    totalInvestment: null,
    amtIncTax: null,
    amtExcTax: null,
    stageAmtIncTa: null,
    projectManager: null,
    negResp: null,
    industryDirector: null,
    execResp: null,
    certResp: null,
    eimResp: null,
    subcAmtIncTax: null,
    recvdAmt: null,
    unrecdAmt: null,
    recvRatio: null,
    prevYrRecv: null,
    currYrRecv: null,
    recvreMarks: null,
    def1: null,
    def2: null,
    def3: null,
    def4: null,
    def5: null,
    def6: null,
    def7: null,
    def8: null,
    def9: null,
    def10: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("ProjectOverviewRef");
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

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加项目概况";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProjectOverview(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目概况";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProjectOverviewRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProjectOverview(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProjectOverview(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除项目概况编号为"' + _ids + '"的数据项？').then(function() {
    return delProjectOverview(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/project/export', {
    ...queryParams.value
  }, `project_${new Date().getTime()}.xlsx`)
}

// 设置百分比显示
const formatPercentage = (value) => {
  return `${(value * 1).toFixed(2)}%`;
};

//项目运行情况跳转项目概况功能 start
import { watch } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()

watch(
    () => route.query.projectName,
    (newVal) => {
      queryParams.value.projectName = newVal || null
      handleQuery()
    },
    { immediate: true }
)

//项目运行情况跳转项目概况功能 end

getList();
</script>
