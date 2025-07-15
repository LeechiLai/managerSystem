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
      <el-form-item label="专业" prop="profession">
        <el-select v-model="queryParams.profession" placeholder="请选择专业" clearable>
          <el-option
            v-for="dict in user_pro"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="专业负责人" prop="proLeader">
        <el-input
          v-model="queryParams.proLeader"
          placeholder="请输入专业负责人"
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
          v-hasPermi="['business:DesignRatio:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:DesignRatio:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:DesignRatio:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:DesignRatio:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="DesignRatioList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
      <el-table-column label="项目ID" align="center" prop="prjId" />
      <el-table-column label="项目名称" align="center" prop="projectName" width="300" :show-overflow-tooltip="true" />
      <el-table-column label="专业" align="center" prop="profession">
        <template #default="scope">
          <dict-tag :options="user_pro" :value="scope.row.profession"/>
        </template>
      </el-table-column>
      <el-table-column label="专业负责人" align="center" prop="proLeader" width="100" />
      <el-table-column label="项目设计占比" align="center" prop="projectDesignPct" width="150" >
        <template #default="scope">
          {{ formatPercentage(scope.row.projectDesignPct) }}
        </template>
      </el-table-column>
      <el-table-column label="设计组内部专业比例" align="center" prop="internalPct" width="150" >
        <template #default="scope">
          {{ formatPercentage(scope.row.internalPct) }}
        </template>
      </el-table-column>
      <el-table-column label="设计组占总项目比例" align="center" prop="projPct" width="150">
        <template #default="scope">
          {{ formatPercentage(caculateProjPct(scope.row.projectDesignPct, scope.row.internalPct)) }}
        </template>
      </el-table-column>
<!--     <el-table-column label="专业综合完成度" align="center" prop="def1" >-->
<!--      <template #default="scope">-->
<!--        {{ formatPercentage(scope.row.def1) }}-->
<!--      </template>-->
<!--      </el-table-column>-->
      <!--
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="130" fixed="right">
        <template #default="scope">
          <el-button link type="primary"  @click="handleUpdate(scope.row)" v-hasPermi="['business:DesignRatio:edit']">修改</el-button>
          <el-button link type="primary"  @click="handleDelete(scope.row)" v-hasPermi="['business:DesignRatio:remove']">删除</el-button>
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

    <!-- 添加或修改设计组比例分配对话框 -->
    <el-dialog :title="title" v-model="open" width="1000px" append-to-body>
      <el-form ref="DesignRatioRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目ID" prop="prjId" style="display: none">
          <el-input v-model="form.prjId" type="hidden" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName" label-width="150">
          <el-input v-model="form.projectName"  disabled/>
        </el-form-item>
        <el-form-item label="专业" prop="profession" label-width="150">
          <el-select v-model="form.profession" placeholder="请选择专业">
            <el-option
              v-for="dict in user_pro"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="专业负责人" prop="proLeader" label-width="100">
          <el-select
              v-model="form.proLeader"
              placeholder="请输入并选择专业负责人"
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
        <el-form-item label="项目设计占比" prop="projectDesignPct" label-width="150">
          <el-input v-model="form.projectDesignPct" placeholder="请输入项目设计占比（%）" />
        </el-form-item>
        <el-form-item label="设计组内部专业比例" prop="internalPct" label-width="150">
          <el-input v-model="form.internalPct" placeholder="请输入设计组内部专业比例（%）" />
        </el-form-item>
        <el-form-item label="设计组占总项目比例" prop="projPct" label-width="150">
          <template #default="scope">
            {{ formatPercentage(caculateProjPct(form.projectDesignPct, form.internalPct)) }}
          </template>
        </el-form-item>
<!--        <el-form-item label="专业综合完成度" prop="def1" label-width="150">-->
<!--          <template #default="scope">-->
<!--            {{ formatPercentage(form.def1) }}-->
<!--          </template>-->
<!--        </el-form-item>-->
        <!--
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
        <el-form-item label="备注" prop="remark" label-width="150">
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

<script setup name="DesignRatio">
import { listDesignRatio, getDesignRatio, delDesignRatio, addDesignRatio, updateDesignRatio } from "@/api/business/DesignRatio";
import {allocatedDesignList, getProjectDesignPctFromBackEnd} from "@/api/business/ProfessionalAllocation";
import { listProject } from "@/api/business/project";
import { useRoute } from "vue-router";
import {reactive} from "vue";

const route = useRoute();
console.log('route.params:', route.params);

const { proxy } = getCurrentInstance();
const { user_pro } = proxy.useDict('user_pro');

const DesignRatioList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

//设计组占总项目比例 = tb_management_ratio.自定义字段2 * 专业设计组内部比例 / 100
const caculateProjPct = ( val1,val2 ) => {
  form.value.projPct = val1 * val2 / 100;
  return val1 * val2 / 100;
}
// 验证值是否在 0~1 范围内
const validateRange = (rule, value, callback) => {
  if (value === null || value === undefined || isNaN(value)) {
    callback(new Error("请输入有效数值"));
  } else if (value < 0 || value > 100) {
    callback(new Error("值必须在 0~100 范围内"));
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
    profession: null,
    proLeader: null,
    prjId: route.params.prjId // 项目ID，由路由传递
  },
  rules: {
    prjId: [{ required: true, message: "项目不能为空", trigger: "blur" }],
    projectName: [{ required: true, message: "项目名称不能为空", trigger: "blur" }],
    profession: [{ required: true, message: "专业不能为空", trigger: "blur" }],
    proLeader: [{ required: true, message: "专业负责人不能为空", trigger: "blur" }],
    projectDesignPct: [
      { required: true, message: "项目设计占比不能为空", trigger: "blur" },
      { validator: validateRange, trigger: "blur" } // 验证范围 0~1
    ],
    internalPct: [
      { required: true, message: "设计组内部比例不能为空", trigger: "blur" },
      { validator: validateRange, trigger: "blur" } // 验证范围 0~1
    ],
    projPct: [
      { required: true, message: "设计组占总项目比例不能为空", trigger: "blur" },
      { validator: validateRange, trigger: "blur" } // 验证范围 0~1
    ]
  }
});

// 验证设计组内部比例的总和是否为 100%
const validateInternalPctSum = () => {
  const currentProfession = form.value.profession; // 当前对话框显示的专业

  // 过滤掉当前对话框显示的专业
  const filteredList = DesignRatioList.value.filter(item => item.profession !== currentProfession);

  // 计算剩余专业的 internalPct 总和
  const sum = filteredList.reduce((acc, item) => acc + parseFloat(item.internalPct || 0), 0);

  // 加上当前对话框中 form 对象的 internalPct 值
  const currentInternalPct = parseFloat(form.value.internalPct || 0);
  const totalInternalPct = sum + currentInternalPct;

  // 比较总和是否等于 100（允许误差小于 1）
  return Math.abs(totalInternalPct - 100) < 1;
};
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  projectName: null,
  profession: null,
  proLeader: null,
  prjId: route.params.prjId // 项目ID，由路由传递
});

const {  form, rules } = toRefs(data);

/** 查询设计组比例分配列表 */
/*function getList() {
  loading.value = true;
  listDesignRatio(queryParams.value).then(response => {
    DesignRatioList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}*/

/** 查询设计比例分配列表(已分配) */
function getList() {
  loading.value = true;
  allocatedDesignList(queryParams).then(async response => {

      // 遍历 response.rows 并为每个对象添加 projectDesignPct 字段
      const updatedRows = await Promise.all(response.rows.map(async row => {
        // 调用后端接口获取 projectDesignPct
        const projectDesignPct = await getProjectDesignPct(row.prjId);
        return {
          ...row,
          projectDesignPct: projectDesignPct // 添加新字段
        };
      }));
    DesignRatioList.value = updatedRows;
    total.value = response.total;
    loading.value = false;


  });
}


async function getProjectDesignPct(prjId) {
  try {
    // 调用后端接口获取数据
    const response = await getProjectDesignPctFromBackEnd(prjId);

    // 假设后端返回的数据结构为 { data: { def2: number } }
    const def2Value = response.data?.def2;

    if (typeof def2Value === 'number') {
      return def2Value; // 返回 def2 的值
    } else {
      console.error("无效的 def2 数据:", response);
      throw new Error("无法获取有效的项目设计占比");
    }
  } catch (error) {
    console.error("获取项目设计占比失败:", error);
    throw error; // 抛出错误以便上层处理
  }
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
    profession: null,
    proLeader: null,
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
  proxy.resetForm("DesignRatioRef");
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
  title.value = "添加设计组比例分配";
  //自动填充项目ID和项目名称
  const prjId = route.params.prjId;
  console.log(prjId)
  const currentProject = projectList.value.find(project => project.id == prjId);
  console.log(currentProject)
  form.value.prjId = prjId;
  form.value.projectName = currentProject.projectName;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  console.log(form.value)
  reset();
  const _id = row.id || ids.value;
  getDesignRatio(_id).then(response => {
    form.value = response.data;
    // 调用 getProjectDesignPct 获取 projectDesignPct
    getProjectDesignPct(form.value.prjId).then(projectDesignPct => {
      form.value.projectDesignPct = projectDesignPct;
      open.value = true;
      title.value = "修改设计组比例分配";
    }).catch(error => {
      console.error("获取项目设计占比失败:", error);
      // 处理错误，例如显示错误信息给用户
    });
  });
}
import { ElMessageBox } from 'element-plus';
/** 提交按钮 */
function submitForm() {
  proxy.$refs["DesignRatioRef"].validate(valid => {
    if (!valid) {
      proxy.$modal.msgError("数据验证不通过");
      return; // 如果表单校验失败，直接返回
    }

    // 全局校验
    if (!validateInternalPctSum()) {
      // 弹出确认框，提供两个按钮选项
      ElMessageBox.confirm(
          "设计组内部比例的和不等于 100%，是否忽略检查？",
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
            // 用户选择“重新修改当前值”，中断提交流程
            return;
          });

      return;
    }

    // 提交后台数据
    handleContinueSubmit();
  });

}

// 继续提交逻辑
function handleContinueSubmit() {
  if (form.value.id != null) {
    updateDesignRatio(form.value,form.value.projectDesignPct).then(response => {


      proxy.$modal.msgSuccess("修改成功");
      open.value = false;
      getList();
    });
  } else {
    addDesignRatio(form.value,form.value.projectDesignPct).then(response => {
      proxy.$modal.msgSuccess("新增成功");
      open.value = false;
      getList();
    });
  }
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除设计组比例分配编号为"' + _ids + '"的数据项？').then(function() {
    return delDesignRatio(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/DesignRatio/export', {
    ...queryParams.value
  }, `DesignRatio_${new Date().getTime()}.xlsx`)
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
</script>
