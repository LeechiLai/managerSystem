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
      <el-form-item label="工程号" prop="projectCode">
        <el-input
          v-model="queryParams.projectCode"
          placeholder="请输入工程号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目执行状态" prop="prjexecStatus" label-width="100">
        <el-select v-model="queryParams.prjexecStatus" placeholder="请选择项目执行状态" clearable>
          <el-option
            v-for="dict in prjexec_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">刷新</el-button>
      </el-form-item>
    </el-form>

<!--    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['business:PrjOptStatus:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate" v-hasPermi="['business:PrjOptStatus:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete" v-hasPermi="['business:PrjOptStatus:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['business:PrjOptStatus:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>-->

    <el-table v-loading="loading" :data="PrjOptStatusList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="项目id" align="center" prop="id" />-->
<!--      <el-table-column label="项目名称" align="left" prop="projectName" width="450" :show-overflow-tooltip="true" />-->
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true">
        <template #default="scope">
          <router-link
              :to="{
                  path: '/business/projectOverview', // 跳转的路径，根据实际路径调整
                  query: { projectName: scope.row.projectName } // 将项目名称作为查询参数传递
               }" style="text-decoration: underline; color: #1890FF;"
          >
            {{ scope.row.projectName }}
          </router-link>
        </template>
      </el-table-column>
<!--      <el-table-column label="阶段号" align="center" prop="phaseNo" width="120" :show-overflow-tooltip="true" />-->
      <el-table-column label="工程号" align="center" prop="projectCode" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="项目执行状态" align="center" prop="prjexecStatus">
        <template #default="scope">
          <dict-tag :options="prjexec_status" :value="scope.row.prjexecStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="收款比例" align="center" prop="recvRatio">
        <template #default="scope">
          <el-progress
              :percentage="Number(scope.row.recvRatio || 0)"
              :format="() => `${scope.row.recvRatio ? Number(scope.row.recvRatio).toFixed(0) : '0'}%`"
          />
        </template>
      </el-table-column>
      <el-table-column label="综合进度" align="center">
        <template #default="scope">
          <router-link
              :to="{
                    path: '/business/plan/ProfessionalAllocation',
                    query: { projectName: scope.row.projectName }
                  }"  style="text-decoration: underline;"
          >
            {{ scope.row.progress ? scope.row.progress + '%' : '0%' }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="项目负责人" align="center" >
        <template #default="scope">
          <dict-tag :options="project_manager" :value="scope.row.projectManager"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['business:PrjOptStatus:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['business:PrjOptStatus:remove']">删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改项目运行情况对话框 -->
    <el-dialog :title="title" v-model="open" width="1200px" append-to-body>
      <el-form ref="PrjOptStatusRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
<!--        <el-form-item label="阶段号" prop="phaseNo">
          <el-input v-model="form.phaseNo" placeholder="请输入阶段号" />
        </el-form-item>-->
        <el-form-item label="工程号" prop="projectCode">
          <el-input v-model="form.projectCode" placeholder="请输入工程号" />
        </el-form-item>
        <el-form-item label="项目执行状态" prop="prjexecStatus" label-width="100">
          <el-select v-model="form.prjexecStatus" placeholder="请选择项目执行状态">
            <el-option
              v-for="dict in prjexec_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收款比例" prop="recvRatio">
          <el-input v-model="form.recvRatio" placeholder="请输入收款比例" />
        </el-form-item>
        <el-form-item label="综合进度" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入综合进度" />
        </el-form-item>
        <el-form-item label="项目负责人" prop="projectManager" label-width="100">
          <el-input v-model="form.projectManager" placeholder="请输入项目负责人" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="PrjOptStatus">
import { listPrjOptStatus, getPrjOptStatus, delPrjOptStatus, addPrjOptStatus, updatePrjOptStatus } from "@/api/business/PrjOptStatus";
import { listProject } from "@/api/business/project";

const { proxy } = getCurrentInstance();
const { prjexec_status,project_manager } = proxy.useDict('prjexec_status','project_manager');

const PrjOptStatusList = ref([]);
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
    projectCode: null,
    prjexecStatus: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目运行情况列表 */
function getList() {
  loading.value = true;
  //listPrjOptStatus(queryParams.value).then(response => {
  listProject(queryParams.value).then(response => {//直接获取项目列表，不再取tb_prj_opt_status表
    PrjOptStatusList.value = response.rows;
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
    projectName: null,
    phaseNo: null,
    projectCode: null,
    prjexecStatus: null,
    recvRatio: null,
    progress: null,
    projectManager: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null,
    deptId: null,
    userId: null
  };
  proxy.resetForm("PrjOptStatusRef");
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
  title.value = "添加项目运行情况";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPrjOptStatus(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目运行情况";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["PrjOptStatusRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updatePrjOptStatus(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addPrjOptStatus(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除项目运行情况编号为"' + _ids + '"的数据项？').then(function() {
    return delPrjOptStatus(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/PrjOptStatus/export', {
    ...queryParams.value
  }, `PrjOptStatus_${new Date().getTime()}.xlsx`)
}

getList();
</script>
