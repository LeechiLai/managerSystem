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
      <el-form-item label="子项编号" prop="itemNo">
        <el-input
          v-model="queryParams.itemNo"
          placeholder="请输入子项编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="子项名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入子项名称"
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
          v-hasPermi="['business:projectItem:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:projectItem:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:projectItem:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:projectItem:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectItemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
<!--      <el-table-column label="prjId" align="center" prop="prjId" />-->
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true"/>
<!--      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true">
        <template #default="scope">
          <router-link
              :to="{
                    path: '/business/plan/ProfessionalAllocation',
                    query: { projectName: scope.row.projectName }
                  }" style="text-decoration: underline; color: #1890FF;"
          >
            {{ scope.row.projectName }}
          </router-link>
        </template>
      </el-table-column>-->
<!--      <el-table-column label="headId" align="center" prop="headId" />-->
      <el-table-column label="子项编号" align="center" prop="itemNo" />
      <el-table-column label="子项名称" align="center" prop="itemName" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="子项权重" align="center" prop="itemWeight" />
<!--      <el-table-column label="创建者" align="center" prop="createBy" />
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
      </el-table-column>-->
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="250" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="jumpToBody(scope.row)" v-hasPermi="['business:projectItem:edit']">策划</el-button>
<!--          <el-button link type="primary" @click="jumpToProfessionalAllocation(scope.row)" v-hasPermi="['business:projectItem:edit']">专业策划</el-button>-->
          <el-button link type="primary" @click="handleUpdate(scope.row)" v-hasPermi="['business:projectItem:edit']">修改</el-button>
          <el-button link type="primary" @click="handleDelete(scope.row)" v-hasPermi="['business:projectItem:remove']">删除</el-button>
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

    <!-- 添加或修改项目子项对话框 -->
    <el-dialog :title="title" v-model="open" width="950px" append-to-body>
      <el-form ref="ProfessionalAllocationBRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-form-item label="主表ID" prop="headId" label-width="160">
            <el-input v-model="form.headId" placeholder="请输入专业负责人策划主表ID" style="width: 120px;" />
          </el-form-item>
          <el-form-item label="项目ID" prop="prjId" label-width="160">
            <el-input v-model="form.prjId" placeholder="请输入项目ID" style="width: 120px;" />
          </el-form-item>
          <el-form-item label="选择项目" prop="prjId">
            <el-select v-model="form.prjId" placeholder="请选择项目" @change="handleProjectChange">
              <el-option
                  v-for="item in projectList"
                  :key="item.id"
                  :label="item.projectName"
                  :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="子项编号" prop="itemNo" label-width="160">
            <el-input v-model="form.itemNo" placeholder="请输入子项编号" style="width: 120px;"/>
          </el-form-item>
          <el-form-item label="子项名称" prop="itemName" label-width="160">
            <el-input v-model="form.itemName" placeholder="请输入子项名称" style="width: 120px;"/>
          </el-form-item>
          <el-form-item label="子项权重" prop="itemWeight" label-width="160">
            <el-input v-model="form.itemWeight" placeholder="请输入子项权重" style="width: 120px;"/>
          </el-form-item>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="data.form.remark" type="textarea" placeholder="请输入内容" />
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

<script setup name="ProjectItem">
import { listProjectItem, getProjectItem, delProjectItem, addProjectItem, updateProjectItem } from "@/api/business/projectItem";

const { proxy } = getCurrentInstance();

const projectItemList = ref([]);
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
    itemNo: null,
    itemName: null,
  },
  rules: {
    itemNo: [
      { required: true, message: "子项编号不能为空", trigger: "blur" }
    ],
    itemName: [
      { required: true, message: "子项名称不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目子项列表 */
function getList() {
  loading.value = true;
  listProjectItem(queryParams.value).then(response => {
    projectItemList.value = response.rows;
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
    headId: null,
    prjId: null,
    projectName: null,
    itemId: null,
    itemNo: null,
    itemName: null,
    itemWeight: null,
    deptId: null,
    userId: null
  };
  proxy.resetForm("ProfessionalAllocationBRef");
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
  title.value = "添加项目子项";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProjectItem(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改项目子项";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProfessionalAllocationBRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProjectItem(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProjectItem(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除项目子项编号为"' + _ids + '"的数据项？').then(function() {
    return delProjectItem(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/projectItem/export', {
    ...queryParams.value
  }, `projectItem_${new Date().getTime()}.xlsx`)
}


/** 实现输入用户名称时实时搜索，laizhiying的方式 start*/
import { listUser } from "@/api/system/user";
import {reactive} from "vue";
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
/** 实现输入用户名称时实时搜索 end*/



/*查询所有条件对象*/
import { listProject } from "@/api/business/project";
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
// 处理选择变化的方法
const handleProjectChange = (value) => {
  const selectProject = projectList.value.find(item => item.id == value);
  if (selectProject) {
    form.value.prjId = selectProject.id;
    form.value.projectName = selectProject.projectName;
  } else {
    form.value.prj = '';
    form.value.projectName = '';
  }
};

//跳转专业负责人策划页面
function jumpToProfessionalAllocation(row) {
  proxy.$router.push({
    path: `/business/projectItem/item-profession/index/${row.prjId}`,
    query: {
      prjId: row.prjId,
      id: row.id,//子项id
      itemName: row.itemName,
      projectName: row.projectName
    }
  });
}

//跳转专业负责人策划页面（相当于body页面）
function jumpToBody(row) {
  proxy.$router.push({
    path: '/business/cehua/index',
    query: {
      prjId: row.prjId,
      itemId: row.id,//子项id
      itemName: row.itemName,
      projectName: row.projectName
    }
  });
}

getProjectList();
getList();
</script>
