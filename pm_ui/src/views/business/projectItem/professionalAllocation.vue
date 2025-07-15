<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目号" prop="projectNo">
        <el-input
          v-model="queryParams.projectNo"
          placeholder="请输入项目号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阶段号" prop="phaseNo">
        <el-input
          v-model="queryParams.phaseNo"
          placeholder="请输入阶段号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8 operation-bar">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['business:ProfessionalAllocation:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:ProfessionalAllocation:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:ProfessionalAllocation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:ProfessionalAllocation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProfessionalAllocationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
      <el-table-column label="项目ID" align="center" prop="prjId" ></el-table-column>
      <el-table-column label="专业进度" align="center">
        <template #default="scope">
            {{ scope.row.majorProgress }}
        </template>
      </el-table-column>
      <el-table-column label="专业" align="center" prop="profession">
        <template #default="scope">
          <dict-tag :options="user_pro" :value="scope.row.profession"/>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true" />
      <el-table-column label="子项名称" align="center" prop="itemName" width="300" :show-overflow-tooltip="true" >
        <template #default="scope">
          {{ route.query.itemName }}
        </template>
      </el-table-column>>
      <el-table-column label="项目号" align="center" prop="projectNo" width="150"/>
      <el-table-column label="阶段号" align="center" prop="phaseNo" />
<!--      <el-table-column label="自定义字段1" align="center" prop="def1" />
      <el-table-column label="自定义字段2" align="center" prop="def2" />
      <el-table-column label="自定义字段3" align="center" prop="def3" />
      <el-table-column label="自定义字段4" align="center" prop="def4" />
      <el-table-column label="自定义字段5" align="center" prop="def5" />
      <el-table-column label="自定义字段6" align="center" prop="def6" />
      <el-table-column label="自定义字段7" align="center" prop="def7" />
      <el-table-column label="自定义字段8" align="center" prop="def8" />
      <el-table-column label="自定义字段9" align="center" prop="def9" />
      <el-table-column label="自定义字段10" align="center" prop="def10" />-->
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="280" fixed="right">
        <template #default="scope">
<!--          <el-button link type="primary"  @click="handleDesignRatio(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">分项策划</el-button>-->
<!--          <el-button link type="primary"  @click="handleAuthProjectItem(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">分配子项</el-button>-->
          <el-button link type="primary"  @click="editBody(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">策划</el-button>
<!--          <el-button link type="primary"  @click="jumpToBody(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">编辑与查看子项</el-button>-->
          <el-button link type="primary"  @click="handleUpdate(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">修改</el-button>
          <el-button link type="primary"  @click="handleDelete(scope.row)" v-hasPermi="['business:ProfessionalAllocation:remove']">删除</el-button>
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

    <!-- 添加或修改专业负责人策划对话框 -->
    <el-dialog :title="title" v-model="open" width="1200px" append-to-body>
      <el-form ref="ProfessionalAllocationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专业" prop="profession">
          <el-select v-model="form.profession" placeholder="请选择专业">
            <el-option
              v-for="dict in user_pro"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目ID:" prop="prjId">
          <template #default="scope">
            {{ route.query.prjId }}
          </template>
        </el-form-item>
        <el-form-item label="项目名称:" prop="prjId">
          <template #default="scope">
            {{ route.query.projectName }}
          </template>
        </el-form-item>
        <el-form-item label="子项名称:" prop="projectName">
          <template #default="scope">
            {{ route.query.itemName }}
          </template>
        </el-form-item>
        <el-form-item label="项目号" prop="projectNo">
          <el-input v-model="form.projectNo" placeholder="请输入项目号" />
        </el-form-item>
        <el-form-item label="阶段号" prop="phaseNo">
          <el-input v-model="form.phaseNo" placeholder="请输入阶段号" />
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

<script setup name="ProfessionalAllocation">
import { listProfessionalAllocation, getProfessionalAllocation, delProfessionalAllocation, addProfessionalAllocation, updateProfessionalAllocation } from "@/api/business/ProfessionalAllocation";
import { listProject } from "@/api/business/project";
import { reactive  } from "vue";
import { calculateSubItemProgress }  from "@/utils";
import { onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
const route = useRoute();
const router = useRouter();
import { selectProfessionalAllocationB } from '@/api/business/ProfessionalAllocation';

// 安全获取 projectName
function getProjectNameFromQuery() {
  return route.query?.projectName || null;
}

// // 在组件挂载时尝试初始化查询
// onMounted(() => {
//   const projectName = getProjectNameFromQuery();
//   if (projectName) {
//     queryParams.value.projectName = projectName;
//     handleQuery(); // 自动触发搜索
//   }
// });
onMounted(() => {
  const projectName = getProjectNameFromQuery();
  if (projectName) {
    queryParams.value.projectName = projectName;
    handleQuery();
  } else {
    console.warn('未找到 projectName 参数，跳过自动搜索');
  }
});
// 监听路由变化，确保参数更新后能重新查询
watch(
    () => route.query,
    (newQuery) => {
      const projectName = newQuery?.projectName || null;
      if (projectName) {
        queryParams.value.projectName = projectName;
        handleQuery();
      }
    }
);
const { proxy } = getCurrentInstance();
const { user_pro } = proxy.useDict('user_pro');
import { useProjectStore,updateSubTableHeadIdByPrjId } from "@/api/business/ProfessionalAllocation";

const projectStore = useProjectStore();
const ProfessionalAllocationList = ref([]);
const professionalAllocationBList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedProfessionalAllocationB = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    profession: null,
    projectName: null,
    projectNo: null,
    phaseNo: null,
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询专业负责人策划列表 */
function getList() {
  loading.value = true;
  listProfessionalAllocation(queryParams.value).then(response => {
    // ✅ 初始化每行的 majorProgress
    ProfessionalAllocationList.value = response.rows.map(row => ({
      ...row,
      majorProgress: '0.00%'
    }));
    total.value = response.total;



    // ✅ 异步加载每一行的专业进度
    ProfessionalAllocationList.value.forEach(row => {
      calculateMajorProgress(row);
    });
  }).finally(() => {
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
    profession: null,
    projectName: null,
    projectNo: null,
    phaseNo: null,
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
    remark: null,
    deptId: null,
    userId: null
  };
  professionalAllocationBList.value = [];
  proxy.resetForm("ProfessionalAllocationRef");
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
  title.value = "添加专业负责人策划";

  // 从路由参数赋值给 form（用于提交）
  form.value.prjId = route.query.prjId;
  form.value.projectName = route.query.projectName;
  form.value.itemName = route.query.itemName;

  // 存入 store
  projectStore.setPrjId(form.value.prjId);
  projectStore.setHeadId(form.value.id);
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProfessionalAllocation(_id).then(response => {
    form.value = response.data;
    professionalAllocationBList.value = response.data.professionalAllocationBList;
    open.value = true;
    title.value = "修改专业负责人策划";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProfessionalAllocationRef"].validate(valid => {
    if (valid) {
      form.value.professionalAllocationBList = professionalAllocationBList.value;
      if (form.value.id != null) {
        updateProfessionalAllocation(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProfessionalAllocation(form.value).then(response => {
          console.log(response)
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
  proxy.$modal.confirm('是否确认删除专业负责人策划编号为"' + _ids + '"的数据项？').then(function() {
    return delProfessionalAllocation(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 专业负责人策划子序号 */
function rowProfessionalAllocationBIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 专业负责人策划子删除按钮操作 */
function handleDeleteProfessionalAllocationB() {
  if (checkedProfessionalAllocationB.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的专业负责人策划子数据");
  } else {
    const professionalAllocationBs = professionalAllocationBList.value;
    const checkedProfessionalAllocationBs = checkedProfessionalAllocationB.value;
    professionalAllocationBList.value = professionalAllocationBs.filter(function(item) {
      return checkedProfessionalAllocationBs.indexOf(item.index) == -1
    });
  }
}
/** 复选框选中数据 */
function handleProfessionalAllocationBSelectionChange(selection) {
  checkedProfessionalAllocationB.value = selection.map(item => item.index)
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/ProfessionalAllocation/export', {
    ...queryParams.value
  }, `ProfessionalAllocation_${new Date().getTime()}.xlsx`)
}

function jumpToBody(row) {
  projectStore.prjId = row.prjId;
  projectStore.headId = row.id;
  proxy.$router.push({
    path: '/business/ProfessionalAllocationB/index',
    query: {
      prjId: row.prjId,
      profession: row.profession,
      headId: row.id // 从主表获取 headId（即 tb_professional_allocation.id）
    }
  });
}

/** 设计组策划 */
function handleDesignRatio(row) {
  const prjId = row.prjId;
  router.push("/business/design-allocated/index/" + prjId);
}


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

// 添加子项时，自动填充项目ID和名称
function handleAddProfessionalAllocationB() {
  if ( form.value.projectName ) {
    const obj = {
      prjId: form.value.prjId,
      projectName:  form.value.projectName,
      def10: form.value.projectName,
      headId : "",
      itemId : "",
      itemNo : "",itemName : "",itemWeight : "",proResp : "",eim : "",overallPlan : "",
      checker : "",reviewer : "",pipeReview : "",approval : "",designGuidance : "",design1 : "",design2 : "",design3 : "",
      design4 : "",design5 : "",design6 : "",design7 : "",design8 : "",design9 : "",design10 : "",design11 : "",
      design12 : "",design13 : "",design14 : "",design15 : "",design16 : "",design17 : "",design18 : "",design19 : "",
      design20 : "",procurementSupport : "",consultant : "",proRespPct : "",eimPct : "",overallPlanPct : "",checkerPct : "",
      reviewerPct : "",pipeReviewPct : "",approvalPct : "",designGuidancePct : "",design1Pct : "",design2Pct : "",design3Pct : "",
      design4Pct : "",design5Pct : "",design6Pct : "", design7Pct : "",design8Pct : "",design9Pct : "",
      design10Pct : "",design11Pct : "",design12Pct : "",design13Pct : "",design14Pct : "",design15Pct : "",design16Pct : "",
      design17Pct : "",design18Pct : "",design19Pct : "",design20Pct : "",procurementSupportPct : "",consultantPct : "",
      final1Pct : "",final2Pct : "",final3Pct : "",final4Pct : "",final5Pct : "",final6Pct : "",final7Pct : "",final8Pct : "",
      final9Pct : "",final10Pct : "",final11Pct : "",final12Pct : "",final13Pct : "",final14Pct : "",final15Pct : "",final16Pct : "",
      final17Pct : "",final18Pct : "",final19Pct : "",final20Pct : "",progress1 : "",progress2 : "",progress3 : "",
      progress4 : "",progress5 : "",progress6 : "",progress7 : "",progress8 : "",progress9 : "",progress10 : "",progress11 : "",
      progress12 : "",progress13 : "",progress14 : "",progress15 : "",progress16 : "",progress17 : "",progress18 : "",progress19 : "",
      progress20 : "",createBy : "",
      createTime : "",updateBy : "",
      updateTime : "",remark : "",
    };
    professionalAllocationBList.value.push(obj);
  } else {
    // 可以在这里添加提示信息，告知用户需要先选择项目
    alert('请先选择项目！');
  }
}
// 处理选择变化的方法
const handleProjectChange = (value) => {
  const selectProject = projectList.value.find(item => item.id == value);
  if (selectProject) {
    form.value.prjId = selectProject.id;
    form.value.projectName = selectProject.projectName;
    form.value.projectNo = selectProject.projectCode;
    professionalAllocationBList.value.forEach(subitem => {
      subitem.prjId = selectProject.id;
    })
  } else {
    form.value.prj = '';
    form.value.projectName = '';
  }
};
function parseWeight(weight) {
  if (typeof weight === 'string') {
    return parseFloat(weight.replace('%', '')) / 100;
  }
  return weight || 0;
}
// 在 ProfessionalAllocation/index.vue 中复用 calculateSubItemProgress


async function calculateMajorProgress(row) {
  // ...原有代码保持不变
  for (const item of relatedItems) {
    const progress = parseFloat(calcSubItemProgress(item)) || 0;
    const weight = parseWeight(item.itemWeight) || 0;
    total += (progress * weight) / 100;
  }
}

//项目子项跳转专业负责人策划功能 start
watch(
    () => route.query.projectName,
    (newVal) => {
      queryParams.value.projectName = newVal || null
      handleQuery()
    },
    { immediate: true }
)

//项目子项跳转专业负责人策划功能 end

function handleAuthProjectItem(row) {
  router.push({
    path: `/business/ProfessionalAllocation/auth-project-item/index/${Number(row.prjId)}`,
    query: {
      id: row.id // 不参与后端查询
    }
  });
}

function editBody(row) {
  projectStore.prjId = row.prjId;
  projectStore.headId = row.id;
  proxy.$router.push({
    path: '/business/ProfessionalAllocationB/index',
    query: {
      prjId: row.prjId,
      headId: row.id,
      profession: row.profession,
      openDialog: true
    }
  });
}

getProjectList();
getList();
</script>
