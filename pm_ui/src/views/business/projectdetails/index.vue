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
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['business:projectdetails:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:projectdetails:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:projectdetails:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:projectdetails:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectdetailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" width="55" type="index" align="center" prop="id" />
      <el-table-column type="expand" ><!--下拉扩展用以显示子表信息-->
        <template #default="scope">
          <el-table :data="scope.row.projectSubitemsList" size="mini" style="width: 92%;margin-left: 5%;color: grey;">
            <el-table-column label="序号" width="55" type="index" align="center"  />
            <el-table-column label="子项编号" prop="itemNo" align="center" width="150" :show-overflow-tooltip="true"/>
            <el-table-column label="子项名称" prop="itemName" width="150" :show-overflow-tooltip="true"/>
            <el-table-column label="备注" prop="remark" width="400" :show-overflow-tooltip="true"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120" fixed="right">
              <template #default="scope">
                <!--<el-button link type="primary"  @click="handleDeleteProjectSubitems(scope.row.projectSubitemsList)" v-hasPermi="['business:projectdetails:edit']">修改</el-button>-->
                <el-button link type="primary"  @click="DeleteProjectSubitems(scope.row)" v-hasPermi="['business:projectdetails:remove']">删除</el-button>
              </template>
            </el-table-column>
          </el-table>>
        </template>
      </el-table-column>>
      <el-table-column label="更新日期" align="center" prop="updateDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目综合完成度" align="center" prop="def1" width="150">
        <template #default="scope">
          {{ formatPercentage(scope.row.def1) }}
        </template>
      </el-table-column>

      <el-table-column label="项目名称" align="center" prop="project.projectName" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="工程号" align="center" prop="project.projectCode" width="150" :show-overflow-tooltip="true"/>

<!--      <el-table-column label="项目阶段" align="center" prop="project.projectPhase" width="150" :show-overflow-tooltip="true">
        <template #default="scope">
          <dict-tag :options="project_phase" :value="scope.row.projectPhase"/>
        </template>
      </el-table-column>-->
      <el-table-column label="合同号" align="center" prop="project.contractNumber" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="建设单位" align="center" prop="project.constructionUnit" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="行业总监" align="center" prop="project.industryDirector" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="项目负责人" align="center" prop="project.projectManager" width="120">
        <template #default="scope">
          <dict-tag :options="project_manager" :value="scope.row.project?.projectManager" />
        </template>
      </el-table-column>
      <el-table-column label="执行负责人" align="center" prop="project.execResp" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="持证负责人" align="center" prop="project.certResp" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="EIM负责人" align="center" prop="project.eimResp" width="150" :show-overflow-tooltip="true"/>
      <el-table-column label="项目执行状态" align="center" prop="project.prjexecStatus" width="150" :show-overflow-tooltip="true"/>

<!--      <el-table-column label="项目执行状态" align="center" prop="project.prjexecStatus" clearable>
        <template #default="scope">
          <dict-tag :options="prjexec_status" :value="scope.row.prjexecStatus"/>
        </template>
      </el-table-column>-->
      <el-table-column label="设计成果归档" align="center" prop="designRes">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.designRes"/>
        </template>
      </el-table-column>
      <el-table-column label="立项批复归档" align="center" prop="estabAppr">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.estabAppr"/>
        </template>
      </el-table-column>
      <el-table-column label="可研批复归档" align="center" prop="feasAppr">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.feasAppr"/>
        </template>
      </el-table-column>
      <el-table-column label="初设批复归档" align="center" prop="prelimAppr">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.prelimAppr"/>
        </template>
      </el-table-column>
      <el-table-column label="审图通过归档" align="center" prop="reviewPass">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.reviewPass"/>
        </template>
      </el-table-column>
      <el-table-column label="主体验收归档" align="center" prop="subjAccept">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.subjAccept"/>
        </template>
      </el-table-column>
      <el-table-column label="消防验收归档" align="center" prop="fireAccept">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.fireAccept"/>
        </template>
      </el-table-column>
      <el-table-column label="竣工验收归档" align="center" prop="finAccept">
        <template #default="scope">
          <dict-tag :options="yes_or_no" :value="scope.row.finAccept"/>
        </template>
      </el-table-column>

<!--
      <el-table-column label="自定义字段2" align="center" prop="def2" />
      <el-table-column label="自定义字段3" align="center" prop="def3" />
      <el-table-column label="自定义字段4" align="center" prop="def4" />
      <el-table-column label="自定义字段5" align="center" prop="def5" />
      <el-table-column label="自定义字段6" align="center" prop="def6" />
      <el-table-column label="自定义字段7" align="center" prop="def7" />
      <el-table-column label="自定义字段8" align="center" prop="def8" />
      <el-table-column label="自定义字段9" align="center" prop="def9" />
      <el-table-column label="自定义字段10" align="center" prop="def10" />-->
      <el-table-column label="备注" align="center" prop="remark" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新日期" align="center" prop="updateDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="280" fixed="right">
        <template #default="scope">
          <el-button link type="primary"  @click="handleManagementRatio(scope.row)" v-hasPermi="['business:projectdetails:edit']">管理组策划</el-button>
          <el-button link type="primary"  @click="handleDesignRatio(scope.row)" v-hasPermi="['business:projectdetails:edit']">分项组策划</el-button>
          <el-button link type="primary"  @click="handleUpdate(scope.row)" v-hasPermi="['business:projectdetails:edit']">修改</el-button>
          <el-button link type="primary"  @click="handleDelete(scope.row)" v-hasPermi="['business:projectdetails:remove']">删除</el-button>
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

    <!-- 添加或修改项目负责人策划对话框 -->
    <el-dialog :title="title" v-model="open" width="1200px" append-to-body>
      <el-form ref="projectdetailsRef" :model="form" :rules="rules" label-width="80px">
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
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" disabled  />
        </el-form-item>
        <el-form-item label="更新日期" prop="updateDate">
          <el-date-picker clearable
            v-model="form.updateDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择更新日期">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="项目执行状态" prop="prjexecStatus" label-width="100">
          <el-select v-model="form.prjexecStatus" placeholder="请选择项目执行状态">
            <el-option
              v-for="dict in prjexec_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>-->
<!--        <el-form-item label="项目执行状态" prop="prjexecStatus" label-width="100">
          <el-select v-model="form.prjexecStatus" placeholder="请选择项目执行状态">
            <el-option
                v-for="item in projectList"
                :key="item.id"
                :label="item.prjexecStatus"
                :value="item.prjexecStatus"
            ></el-option>
          </el-select>
        </el-form-item>-->
        <el-row :gutter="20">
          <!-- 第一行 -->
          <el-col :span="6" v-for="(item, index) in formItems" :key="index" v-show="index < 4">
            <el-form-item :label="item.label" :prop="item.prop" label-width="100px">
              <el-radio-group v-model="form[item.prop]">
                <el-radio v-for="dict in yes_or_no" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
              </el-radio-group>
              <el-button type="text" @click="resetField(item.prop)" class="reset-btn">取消选择</el-button>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20" style="margin-top: 10px;">
          <!-- 第二行 -->
          <el-col :span="6" v-for="(item, index) in formItems" :key="index" v-show="index >= 4">
            <el-form-item :label="item.label" :prop="item.prop" label-width="100px">
              <el-radio-group v-model="form[item.prop]">
                <el-radio v-for="dict in yes_or_no" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
              </el-radio-group>
              <el-button type="text" @click="resetField(item.prop)" class="reset-btn">取消选择</el-button>
            </el-form-item>
          </el-col>
        </el-row>


        <el-form-item label="项目综合完成度" prop="def1" label-width="150">
          <template #default="scope">
            {{ formatPercentage(form.def1) }}
          </template>
        </el-form-item>
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
        </el-form-item>
        <el-form-item label="自定义字段6" prop="def6">
          <el-input v-model="form.def6" placeholder="请输入自定义字段6" />
        </el-form-item>
        <el-form-item label="自定义字段7" prop="def7">
          <el-input v-model="form.def7" placeholder="请输入自定义字段7" />
        </el-form-item>
        <el-form-item label="自定义字段8" prop="def8">
          <el-input v-model="form.def8" placeholder="请输入自定义字段8" />
        </el-form-item>
        <el-form-item label="自定义字段9" prop="def9">
          <el-input v-model="form.def9" placeholder="请输入自定义字段9" />
        </el-form-item>
        <el-form-item label="自定义字段10" prop="def10">
          <el-input v-model="form.def10" placeholder="请输入自定义字段10" />
        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">项目子项信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddProjectSubitems">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteProjectSubitems">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="projectSubitemsList" :row-class-name="rowProjectSubitemsIndex" @selection-change="handleProjectSubitemsSelectionChange" ref="projectSubitems">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" type="index" width="50" align="center"  />
<!--          <el-table-column label="项目id" prop="prjId" width="150" >
            <template #default="scope">
              <el-input v-model="scope.row.prjId" placeholder="请输入项目id" />
            </template>
          </el-table-column>-->
          <el-table-column label="项目名称" prop="projectName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.projectName" placeholder="请输入项目名称" />
            </template>
          </el-table-column>
          <el-table-column label="子项编号" prop="itemNo" width="150" :show-overflow-tooltip="true">
            <template #default="scope">
              <el-input v-model="scope.row.itemNo" placeholder="请输入子项编号" />
            </template>
          </el-table-column>
          <el-table-column label="子项名称" prop="itemName" width="400" :show-overflow-tooltip="true">
            <template #default="scope">
              <el-input v-model="scope.row.itemName" placeholder="请输入子项名称" />
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark" width="250" :show-overflow-tooltip="true">
            <template #default="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入内容" />
            </template>
          </el-table-column>
        </el-table>
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

<script setup name="Projectdetails">
import { listProjectdetails, getProjectdetails, delProjectdetails, addProjectdetails, updateProjectdetails, delProjectSubitems } from "@/api/business/projectdetails";
import { listProject } from "@/api/business/project";
import { reactive, computed  } from "vue";

const { proxy } = getCurrentInstance();
const { yes_or_no, prjexec_status, project_phase, project_manager } = proxy.useDict('yes_or_no', 'prjexec_status', 'project_phase', 'project_manager');

const router = useRouter();
const projectdetailsList = ref([]);
const projectSubitemsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedProjectSubitems = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const formItems = [
  { label: '设计成果归档', prop: 'designRes' },
  { label: '立项批复归档', prop: 'estabAppr' },
  { label: '可研批复归档', prop: 'feasAppr' },
  { label: '初设批复归档', prop: 'prelimAppr' },
  { label: '审图通过归档', prop: 'reviewPass' },
  { label: '主体验收归档', prop: 'subjAccept' },
  { label: '消防验收归档', prop: 'fireAccept' },
  { label: '竣工验收归档', prop: 'finAccept' }
];

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    projectName: null,
  },
  rules: {
  }
});

const { queryParams,
  form=reactive(
      {
        prjId: '',
        projectName: ''
  }),
  rules } = toRefs(data);

/** 查询项目负责人策划列表 */
function getList() {
  loading.value = true;
  listProjectdetails(queryParams.value).then(response => {
    projectdetailsList.value = response.rows;
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
    updateDate: null,
    prjexecStatus: null,
    designRes: null,
    estabAppr: null,
    feasAppr: null,
    prelimAppr: null,
    reviewPass: null,
    subjAccept: null,
    fireAccept: null,
    finAccept: null,
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
  projectSubitemsList.value = [];
  proxy.resetForm("projectdetailsRef");
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
  title.value = "添加项目负责人策划";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProjectdetails(_id).then(response => {
    form.value = response.data;
    projectSubitemsList.value = response.data.projectSubitemsList;
    open.value = true;
    title.value = "修改项目负责人策划";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["projectdetailsRef"].validate(valid => {
    if (valid) {
      form.value.projectSubitemsList = projectSubitemsList.value;
      if (form.value.id != null) {
        updateProjectdetails(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProjectdetails(form.value).then(response => {
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
  console.log(row.projectName);
  proxy.$modal.confirm('是否确认删除项目项目名称为【"' + row.projectName + '"】的数据项？').then(function() {
    return delProjectdetails(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 项目子项序号 */
function rowProjectSubitemsIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 项目子项添加按钮操作 */
/*function handleAddProjectSubitems(value) {
  let obj = {};
  obj.prjId = "";
  obj.projectName = "";
  obj.itemNo = "";
  obj.itemName = "";
  obj.remark = "";
  projectSubitemsList.value.push(obj);
}*/

// 添加子项时，自动填充项目ID和名称
function handleAddProjectSubitems() {
  if ( form.value.projectName ) {
    const newSubitem = {
      prjId: form.value.prjId,
      projectName: form.value.projectName,
      itemNo: '', // 子项编号需要手动输入
      itemName: '', // 子项名称需要手动输入
      remark: '' // 备注需要手动输入
    };
    projectSubitemsList.value.push(newSubitem);
  } else {
    // 可以在这里添加提示信息，告知用户需要先选择项目
    alert('请先选择项目！');
  }
}

/** 项目子项删除按钮操作 */
function handleDeleteProjectSubitems() {
  if (checkedProjectSubitems.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的项目子项数据");
  } else {
    const projectSubitemss = projectSubitemsList.value;
    const checkedProjectSubitemss = checkedProjectSubitems.value;
    projectSubitemsList.value = projectSubitemss.filter(function(item) {
      return checkedProjectSubitemss.indexOf(item.index) == -1
    });
  }
}

/** 下拉扩展项目子项删除按钮操作 */
function DeleteProjectSubitems(row){
  const _ids = row.id || ids.value;
  const itemNo = row.itemNo;
  proxy.$modal.confirm('是否确认删除[子项编号]为"' + itemNo + '"的数据项？').then(function() {
    return delProjectSubitems(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}


/** 复选框选中数据 */
function handleProjectSubitemsSelectionChange(selection) {
  checkedProjectSubitems.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/projectdetails/export', {
    ...queryParams.value
  }, `projectdetails_${new Date().getTime()}.xlsx`)
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

// 处理选择变化的方法
const handleProjectChange = (value) => {
  const selectProject = projectList.value.find(item => item.id == value);
  if (selectProject) {
    form.value.prjId = selectProject.id;
    form.value.projectName = selectProject.projectName;
    projectSubitemsList.value.forEach(subitem => {
      subitem.prjId = selectProject.id;
      subitem.projectName = selectProject.projectName;
    })
  } else {
    form.value.projectName = '';
  }
  form.value.updateDate = getToday();//设置当前日期为今天
};

// 获取今天的日期并格式化为 YYYY-MM-DD
const getToday = () => {
  const today = new Date();
  const year = today.getFullYear();
  let month = today.getMonth() + 1; // 月份以零为基础
  let day = today.getDate();

  if (month < 10) month = '0' + month;
  if (day < 10) day = '0' + day;

  return `${year}-${month}-${day}`;
};

const getProjectNames = (value) => {
  const projectNames = projectList.value.find(item => item.id == value);
  console.log(projectNames);
  return projectNames;
};



// 重置单选按钮
function resetField(fieldName) {
  form.value[fieldName] = null;
}
/*function resetDesignRes() {
  form.value.designRes = null;
}*/

/** 管理组策划 */
function handleManagementRatio(row) {
  const prjId = row.prjId;
  router.push("/business/manage-allocated/index/" + prjId);
}
/** 分项策划跳转 */
function handleDesignRatio(row) {
  const prjId = row.prjId; // 获取当前行的项目ID
  if (!prjId) {
    proxy.$modal.msgError("项目ID不存在，请检查数据！");
    return;
  }
  // router.push("/business/design-allocated"+prjId);
  router.push("/business/design-allocated/index/"+prjId);

}
// 设置百分比显示
const formatPercentage = (value) => {
  return `${(value * 1).toFixed(2)}%`;
};

getProjectList();
import { watch } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

watch(
    () => route.params,
    () => {
      // 当路由参数变化时，重新获取数据
      getList();
    },
    { immediate: true } // 立即执行一次
);
getList();
</script>

<style scoped>
.reset-btn {
  margin-left: 10px;
  font-size: 12px;
  color: #999;
  cursor: pointer;
}

.reset-btn:hover {
  color: #409EFF; /* Element Plus 主色调 */
}

/* 让 radio 水平排列 */
.el-radio-group {
  display: flex;
  align-items: center;
}

/* 控制 radio 之间的间距 */
.el-radio {
  margin-right: 10px !important;
}

/* 表单项紧凑排版 */
.el-form-item__content {
  display: flex;
  align-items: center;
}
</style>


