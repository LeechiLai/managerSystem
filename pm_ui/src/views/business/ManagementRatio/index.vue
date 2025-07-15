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
      <el-form-item label="角色" prop="role">
        <el-select v-model="queryParams.role" placeholder="请选择角色" clearable>
          <el-option
            v-for="dict in project_manage_role"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['business:ManagementRatio:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:ManagementRatio:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:ManagementRatio:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:ManagementRatio:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ManagementRatioList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
      <el-table-column label="项目ID" align="center" prop="prjId" />
      <el-table-column label="项目名称" align="center" prop="projectName" width="300" :show-overflow-tooltip="true" />
      <el-table-column label="角色" align="center" prop="role" width="120">
        <template #default="scope">
          <dict-tag :options="project_manage_role" :value="scope.row.role"/>
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="项目管理占比" align="center" prop="def1" width="150" >
        <template #default="scope">
          {{ formatPercentage(scope.row.def1) }}
        </template>
      </el-table-column>
      <el-table-column label="项目设计占比" align="center" prop="def2" width="150" >
        <template #default="scope">
          {{ formatPercentage(scope.row.def2) }}
        </template>
      </el-table-column>
      <el-table-column label="管理组内部比例" align="center" prop="internalPct" width="150" >
        <template #default="scope">
          {{ formatPercentage(scope.row.internalPct) }}
        </template>
      </el-table-column>
      <el-table-column label="管理角色占总项目比例" align="center" prop="projPct" width="160">
        <template #default="scope">
          {{ formatPercentage(caculateProjPct(scope.row.def1, scope.row.internalPct)) }}
        </template>
      </el-table-column>
<!--      <el-table-column label="自定义字段1" align="center" prop="def1" />
      <el-table-column label="自定义字段2" align="center" prop="def2" />
      <el-table-column label="自定义字段3" align="center" prop="def3" />
      <el-table-column label="自定义字段4" align="center" prop="def4" />
      <el-table-column label="自定义字段5" align="center" prop="def5" />-->
      <el-table-column label="备注" align="center" prop="remark" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="handleUpdate(scope.row)" v-hasPermi="['business:ManagementRatio:edit']">修改</el-button>
          <el-button link type="primary" @click="handleDelete(scope.row)" v-hasPermi="['business:ManagementRatio:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改管理组比例分配对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="ManagementRatioRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目ID" prop="prjId" label-width="150" label-position="left">
          <el-input v-model="form.prjId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName" label-width="150" label-position="left">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="角色" prop="role" label-width="150">
          <el-select v-model="form.role" placeholder="请选择角色">
            <el-option
              v-for="dict in project_manage_role"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名" prop="name" label-width="150">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="项目管理占比" prop="def1" label-width="150">
          <el-input v-model="form.def1" placeholder="请输入项目管理占比" />
        </el-form-item>
        <el-form-item label="项目设计占比" prop="def2" label-width="150" >
          <el-input v-model="form.def2" placeholder="请输入项目设计占比" />
        </el-form-item>
        <el-form-item label="管理组内部比例" prop="internalPct" label-width="150">
          <el-input v-model="form.internalPct" placeholder="请输入管理组内部比例" />
        </el-form-item>
        <el-form-item label="管理角色占总项目比例" prop="projPct" label-width="160">
            <template #default="scope">
              {{ formatPercentage(caculateProjPct(form.def1, form.internalPct)) }}
            </template>
        </el-form-item>



<!--        <el-form-item label="自定义字段1" prop="def1">
          <el-input v-model="form.def1" placeholder="请输入自定义字段1" />
        </el-form-item>
        <el-form-item label="自定义字段2" prop="def2">
          <el-input v-model="form.def2" placeholder="请输入自定义字段2" />
        </el-form-item>
        <el-form-item label="自定义字段3" prop="def3">
          <el-input v-model="form.def3" placeholder="请输入自定义字段3" />
        </el-form-item>
        <el-form-item label="自定义字段4" prop="def4">
          <el-input v-model="form.def4" placeholder="请输入自定义字段4" />
        </el-form-item>
        <el-form-item label="自定义字段5" prop="def5">
          <el-input v-model="form.def5" placeholder="请输入自定义字段5" />
        </el-form-item>-->
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

<script setup name="ManagementRatio">
import { listManagementRatio, getManagementRatio, delManagementRatio, addManagementRatio, updateManagementRatio } from "@/api/business/ManagementRatio";

const { proxy } = getCurrentInstance();
const { project_manage_role } = proxy.useDict('project_manage_role');

const ManagementRatioList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

// projPct= def1 * internalPct
const caculateProjPct = ( val1,val2 ) => {
  form.value.projPct = val1 * val2 / 100;
  return val1 * val2 / 100;
}


//def2 = 100 - def1
const caculateDef2 = ( val ) => {
  form.value.def2 = 100 - val;
  return 100 - val;
}


const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    projectName: null,
    role: null,
    name: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询管理组比例分配列表 */
function getList() {
  loading.value = true;
  listManagementRatio(queryParams.value).then(response => {
    ManagementRatioList.value = response.rows;
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
    prjId: null,
    projectName: null,
    role: null,
    name: null,
    internalPct: null,
    projPct: null,
    def1: null,
    def2: null,
    def3: null,
    def4: null,
    def5: null,
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  };
  proxy.resetForm("ManagementRatioRef");
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
  title.value = "添加管理组比例分配";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getManagementRatio(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改管理组比例分配";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ManagementRatioRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateManagementRatio(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addManagementRatio(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除管理组比例分配编号为"' + _ids + '"的数据项？').then(function() {
    return delManagementRatio(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/ManagementRatio/export', {
    ...queryParams.value
  }, `ManagementRatio_${new Date().getTime()}.xlsx`)
}

// 设置百分比显示
const formatPercentage = (value) => {
  return `${(value * 1).toFixed(2)}%`;
};

getList();
</script>
