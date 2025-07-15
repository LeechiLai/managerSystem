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
          v-hasPermi="['business:receipts:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:receipts:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:receipts:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:receipts:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="receiptsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
<!--      <el-table-column label="项目ID" align="center" prop="prjId" />-->
      <el-table-column label="项目名称" align="center" prop="projectName" width="300" :show-overflow-tooltip="true" />
      <el-table-column label="收款序号" align="center" prop="receiptSequence" />
      <el-table-column label="金额" align="center" prop="receiptAmount" />
      <el-table-column label="分包金额" align="center" prop="subcAmtIncTax" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="记录时间" align="center" prop="ts" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.ts, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" />
<!--      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['business:receipts:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['business:receipts:remove']">删除</el-button>
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

    <!-- 添加或修改项目收款记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="receiptsRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目id" prop="prjId" style="display: none" >
          <el-input v-model="form.prjId" type="hidden" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" disabled  />
        </el-form-item>
        <el-form-item label="收款序号" prop="receiptSequence">
          <el-select v-model="form.receiptSequence" placeholder="请选择收款序号">
            <el-option v-for="num in 15" :key="num" :label="num" :value="num" />
          </el-select>
        </el-form-item>
        <el-form-item label="金额" prop="receiptAmount">
          <el-input v-model="form.receiptAmount" placeholder="请输入金额" />
        </el-form-item>
        <el-form-item label="分包金额" prop="subcAmtIncTax">
          <el-input v-model="form.subcAmtIncTax" placeholder="请输入分包金额" />
        </el-form-item>
        <el-form-item label="记录时间" prop="ts">
          <el-date-picker v-model="form.ts" type="datetime"  placeholder="选择记录时间" />
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

<script setup name="Receipts">
import { listReceipts, getReceipts,getReceiptsByPrjId, delReceipts, addReceipts, updateReceipts } from "@/api/business/receipts";
import {reactive} from "vue";
import { listProject } from "@/api/business/project";
import { useRoute } from "vue-router";
import {ListReceipted} from "../../../../api/business/project.js";

const route = useRoute();

const { proxy } = getCurrentInstance();

const receiptsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {
    ts: new Date(), // 初始化默认值为当前系统时间
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    projectName: null,
  },
  rules: {
    projectName: [
      { required: true, message: "项目名称不能为空", trigger: "blur" }
    ],
    receiptSequence: [
      { required: true, message: "收款序号不能为空", trigger: "blur" }
    ],
    receiptAmount: [
      { required: true, message: "金额不能为空", trigger: "blur" }
    ],
  }
});

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  projectName: null,
  prjId: route.params.id // 项目ID，由路由传递
});

const { form, rules } = toRefs(data);

/** 查询项目收款记录列表 */
function getList() {
  loading.value = true;
  ListReceipted(queryParams).then(response => {
    receiptsList.value = response.rows;
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
    receiptSequence: null,
    receiptAmount: null,
    subcAmtIncTax: null,
    ts: new Date(), // 重置时设置默认值为当前系统时间
    delFlag: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null,
    deptId: null,
    userId: null
  };
  proxy.resetForm("receiptsRef");
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
async function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加项目收款记录";
  // 自动填充项目id和名称
  const prjId = route.params.id;
  const currentProject = projectList.value.find(project => project.id == prjId);
  form.value.prjId = prjId;
  form.value.projectName = currentProject.projectName;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getReceipts(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目收款记录";
  });
}
function formatDate(date) {
  const isoString = date.toISOString();
  return isoString.slice(0, 19).replace('T', ' ');
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["receiptsRef"].validate(valid => {
    if (valid) {
      form.value.ts = formatDate(form.value.ts);
      if (form.value.id != null) {
        updateReceipts(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addReceipts(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除项目收款记录编号为"' + _ids + '"的数据项？').then(function() {
    return delReceipts(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/receipts/export', {
    ...queryParams.value
  }, `receipts_${new Date().getTime()}.xlsx`)
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

async function getSequence(prjId) {
  if (prjId) {
    console.log("请求项目ID:", prjId); // 添加调试日志
    try {
      const response = await getReceiptsByPrjId(prjId); // 直接传递 prjId
      console.log("记录数",response);
      const existingSequences = response.rows.map(row => row.receiptSequence);
      const nextSequence = existingSequences.length > 0 ? Math.max(...existingSequences) + 1 : 1;
      form.value.receiptSequence = nextSequence;
    } catch (error) {
      console.error("获取项目收款记录失败", error);
    }
  } else {
    console.warn("项目ID不存在或无效");
  }
}


// 处理选择变化的方法
const handleProjectChange = (value) => {
  const selectProject = projectList.value.find(item => item.id == value);
  if (selectProject) {
    console.log("找到的项目:", selectProject); // 添加调试日志
    console.log("项目ID:", selectProject.id); // 添加调试日志
    form.value.prjId = selectProject.id;
    form.value.projectName = selectProject.projectName;
    console.log("选择的项目ID:", selectProject.id); // 添加调试日志
    console.log("类型:", typeof selectProject.id);
    const prjId = Number(selectProject.id); // 确保 prjId 是数字
    //getSequence(prjId);
  } else {
    console.warn("未找到对应的项目"); // 添加调试日志
    form.value.projectName = '';
    form.value.receiptSequence = null; // 清空收款序号
  }
};

getProjectList();

getList();
</script>
