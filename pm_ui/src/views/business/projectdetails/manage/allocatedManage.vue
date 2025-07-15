<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
    </el-form>-->

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
      <el-table-column label="项目管理占比" align="center" prop="def1">
        <template #default="scope">
          {{ formatPercentage(scope.row.def1) }}
        </template>
      </el-table-column>
      <el-table-column label="管理组内部比例" align="center" prop="internalPct">
        <template #default="scope">
          {{ formatPercentage(scope.row.internalPct) }}
        </template>
      </el-table-column>
      <el-table-column label="管理角色占总项目比例" align="center" prop="projPct">
        <template #default="scope">
          {{ formatPercentage(scope.row.projPct) }}
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
        <el-form-item label="项目ID" prop="prjId" style="display: none" >
          <el-input v-model="form.prjId" type="hidden" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName" label-width="150" label-position="left">
          <el-input v-model="form.projectName" disabled />
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
<!--        <el-form-item label="姓名" prop="name" label-width="150">
          <el-input v-model="form.name" clearable prefix-icon="Search" placeholder="请输入姓名" />
        </el-form-item>-->
        <el-form-item label="姓名" prop="name" label-width="150">
          <el-select
              v-model="form.name"
              placeholder="请输入并选择姓名"
              clearable
              prefix-icon="Search"
              filterable
              remote
              :remote-method="searchUsers"
              :loading="userLoading"    style="width: 100%"
          >
            <el-option
                v-for="user in userList"
                :key="user.userId"
                :label="user.nickName"
                :value="user.nickName"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="项目管理占比" prop="def1" label-width="150">
          <el-input v-model="form.def1" placeholder="请输入项目管理占比" />
        </el-form-item>
        <el-form-item label="管理组内部比例" prop="internalPct" label-width="150">
          <el-input v-model="form.internalPct" placeholder="请输入管理组内部比例" />
        </el-form-item>
        <el-form-item label="管理角色占总项目比例" prop="projPct" label-width="160">
            <span>{{ formatPercentage(form.projPct) }}</span>
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

<script setup name="ManagementRatio">
import { listManagementRatio, getManagementRatio, delManagementRatio, addManagementRatio, updateManagementRatio } from "@/api/business/ManagementRatio.js";
import { allocatedManageList } from "@/api/business/projectdetails";
import { listProject } from "@/api/business/project";
import { useRoute } from "vue-router";
import {reactive} from "vue";

const route = useRoute();
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
// 验证值是否在 0~1 范围内
const validateRange = (rule, value, callback) => {
  const numValue = parseFloat(value);
  if (isNaN(numValue)) {
    callback(new Error("请输入有效的数字"));
  } else if (numValue < 0 || numValue > 100) {
    callback(new Error("值必须在 0~100 之间"));
  } else {
    callback();
  }
};

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
    def1: [
      { required: true, message: "项目管理占比不能为空", trigger: "blur" },
      { validator: validateRange, trigger: "blur" } // 验证范围 0~1
    ],
    internalPct: [
      { required: true, message: "管理组内部比例不能为空", trigger: "blur" },
      { validator: validateRange, trigger: "blur" } // 验证范围 0~1
    ],
    projPct: [
      { required: true, message: "管理角色占总项目比例不能为空", trigger: "blur" },
      { validator: validateRange, trigger: "blur" } // 验证范围 0~1
    ]
  }
});

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  projectName: null,
  role: null,
  name: null,
  prjId: route.params.prjId // 项目ID，由路由传递
});

const {  form, rules } = toRefs(data);

/** 查询管理组比例分配列表(已分配) */
function getList() {
  loading.value = true;
  allocatedManageList(queryParams).then(response => {
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
  //自动填充项目ID和项目名称
  const prjId = route.params.prjId;
  const currentProject = projectList.value.find(project => project.id == prjId);
  form.value.prjId = prjId;
  form.value.projectName = currentProject.projectName;

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
import { ElMessageBox } from "element-plus";
/** 提交按钮 */
function submitForm() {
  proxy.$refs["ManagementRatioRef"].validate(valid => {
    if (!valid) {
      proxy.$modal.msgError("数据验证不通过");
      return; // 如果表单校验失败，直接返回
    }

    // 全局校验

    if (!validateInternalPctSum()) {
      // 弹出确认框，提供两个按钮选项
      ElMessageBox.confirm(
          "管理组内部比例的和不等于 100%，是否忽略检查？",
          "提示",
          {
            confirmButtonText: "忽略 100% 检查，就按当前值",
            cancelButtonText: "重新修改当前值",
            type: "warning"
          }
      )
      .then(() => {
        // 用户选择“忽略 100% 检查”，继续提交
        handleContinueSubmit();
      })
      .catch(() => {
        // 用户选择“重新修改当前值”，中断提交流程，异常和点否非promise，都走这里。
        return;
      });


      return;
    }

    // if (!validateProjPctSum()) {
    //   proxy.$modal.msgError("管理角色占总项目比例的和必须等于项目管理占比，误差小于 0.001");
    //   return;
    // }

    // 提交后台数据
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
  });
}

/** 忽略检查后继续提交 */
function handleContinueSubmit() {


  // 提交后台数据
  if (form.value.id != null) {
    updateManagementRatio(form.value)
        .then(response => {
          console.log("更新成功", response);
          proxy.$modal.msgSuccess("修改成功");
          open.value = false; // 关闭大对话框
          getList();
        })
        .catch(error => {
          console.error("更新失败", error);
        });
  } else {
    addManagementRatio(form.value)
        .then(response => {
          console.log("新增成功", response);
          proxy.$modal.msgSuccess("新增成功");
          open.value = false; // 关闭大对话框
          getList();
        })
        .catch(error => {
          console.error("新增失败", error);
        });
  }
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

/** 实现输入用户名称时实时搜索 */
import { listUser } from "@/api/system/user";
const userLoading = ref(false);
const userList = ref([]);
function searchUsers(query) {
  if (query !== '') {
    userLoading.value = true;
    // 使用已封装的 listUser 接口
    listUser({ nickName: query }).then(response => {
      userList.value = response.rows;
      userLoading.value = false;
    });
  } else {
    userList.value = [];
  }
}

// 设置百分比显示
const formatPercentage = (value) => {
  return `${(value * 1).toFixed(2)}%`;
};

/*查询所有条件对象*/
const loadAllParams = reactive({
  pageNum: 1,
  pageSize: 100000
})

/**
 * 获取项目列表
 */
const projectList = ref([]);
function getProjectList(){
  listProject(loadAllParams).then(response => {
    projectList.value = response.rows;
  });
};
getProjectList();
getList();



// 验证管理组内部比例的和是否为 1
const validateInternalPctSum = () => {
  const currentName = form.value.name; // 当前对话框显示的项目的 name

  // 过滤掉当前对话框显示的项目
  const filteredList = ManagementRatioList.value.filter(item => item.name !== currentName);

  // 计算剩余项目的 internalPct 总和
  const sum = filteredList.reduce((acc, item) => acc + parseFloat(item.internalPct || 0), 0);

  // 加上当前对话框中 form 对象的 internalPct 值
  const currentInternalPct = parseFloat(form.value.internalPct || 0);
  const totalInternalPct = sum + currentInternalPct;

  // 比较总和是否等于 100（允许误差小于 1）
  return Math.abs(totalInternalPct - 100) < 1;
};


// // 验证管理角色占总项目比例的和是否等于项目管理占比
// const validateProjPctSum = () => {
//
//
//
//   const currentName = form.value.name; // 当前对话框显示的项目的 name
//
//   // 过滤掉当前对话框显示的项目
//   const filteredList = ManagementRatioList.value.filter(item => item.name !== currentName);
//
//   // 计算剩余项目的 projPct 总和
//   const sum = filteredList.reduce((acc, item) => acc + parseFloat(item.projPct || 0), 0);
//
//   // 加上当前对话框中 form 对象的 projPct 值
//   const currentProjPct = parseFloat(form.value.projPct || 0);
//   const totalProjPct = sum + currentProjPct;
//
//   // 获取表单所填的项目管理占比
//   const totalDef1 = parseFloat(form.value.def1 || 0);
//
//   // 比较总和是否等于 def1 值（允许误差小于 0.001）
//   return Math.abs(totalProjPct - totalDef1) < 0.001;
// };
import { watch } from "vue";

// 监听 form.def1 和 form.internalPct 的变化，动态计算 form.projPct
watch(
    () => [form.value.def1, form.value.internalPct], // 监听的响应式数据
    ([newDef1, newInternalPct]) => {
      // 如果两个值都有效，则计算 projPct
      if (newDef1 && newInternalPct) {
        const def1Value = parseFloat(newDef1);
        const internalPctValue = parseFloat(newInternalPct);

        if (!isNaN(def1Value) && !isNaN(internalPctValue)) {
          form.value.projPct = (def1Value * internalPctValue / 100).toFixed(2); // 计算结果保留两位小数
        }
      }
    },
    { immediate: true } // 立即执行一次，确保初始值正确
);


</script>
