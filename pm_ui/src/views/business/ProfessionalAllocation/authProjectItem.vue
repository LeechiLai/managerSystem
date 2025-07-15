<template>
  <div class="app-container">
    <h4>未分配的项目子项</h4>

    <!-- 批量操作工具栏 -->
    <el-row :gutter="20" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            icon="Plus"
            @click="batchAssign"
            v-hasPermi="['business:ProfessionalAllocationB:edit']"
        >批量分配</el-button>
      </el-col>
    </el-row>

    <el-table :data="assignedItems" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" :index="(index) => index + 1" />
      <el-table-column label="项目名称" prop="projectName" width="600" :show-overflow-tooltip="true" />
      <el-table-column label="子项编号" prop="itemNo" />
      <el-table-column label="子项名称" prop="itemName" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button link type="primary" @click="assign(scope.row)" v-hasPermi="['business:ProfessionalAllocationB:edit']">分配</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup name="AuthProjectItem">
import { getAssignedProjectItems,assignProjectItems, assignProjectItemsBatch} from "@/api/business/projectItem";
import { useRoute } from "vue-router";

const { proxy } = getCurrentInstance();
const route = useRoute();
const assignedItems = ref([]);
const headId = route.query.id; // 来自 query 参数

const selectedItems = ref([]); // 存储选中的行数据
// 多选事件
function handleSelectionChange(selection) {
  selectedItems.value = selection;
}

// 加载状态
const loading = ref(true);
// 查询参数
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  prjId: Number(route.params.prjId) // 来自路径参数
})

/** 查询方法 */
function getList() {
  loading.value = true;
  getAssignedProjectItems(queryParams.value.prjId).then(res => {
    assignedItems.value = res.rows;
    loading.value = false;
  }).catch(() => {
    loading.value = false;
  });
}


function assign(row) {
  proxy.$modal.confirm('是否确认分配该项目子项？').then(() => {
    const headId = route.query.id;
    const prjId = route.params.prjId;
    const id =  row.id;
    // 调用接口：将子表中 prjId 相同且 headId 为空的数据更新 headId
    if (prjId && headId) {
      assignProjectItems(id,prjId, headId).then(() => {
        getList();
        proxy.$modal.msgSuccess("分配成功");
      }).catch(error => {
        proxy.$modal.msgError("子表更新失败");
        console.error("更新子表失败:", error);
        getList();
      });
    }
  });
}

// 批量分配方法
function batchAssign() {
  if (selectedItems.value.length === 0) {
    proxy.$modal.msgError("请至少选择一条数据");
    return;
  }

  const headId = route.query.id;
  const prjId = route.params.prjId;


  if (!prjId || !headId) {
    proxy.$modal.msgError("缺少必要参数");
    return;
  }

  proxy.$modal.confirm('是否确认批量分配所选项目子项？').then(() => {
    const ids = selectedItems.value.map(row => row.id);
    // 调用接口进行批量更新
    assignProjectItemsBatch(ids, prjId, headId).then(() => {
      proxy.$modal.msgSuccess("批量分配成功");
      getList(); // 刷新列表
    }).catch(error => {
      proxy.$modal.msgError("批量分配失败");
      console.error("批量分配失败:", error);
      getList();
    });
  });
}

getList();
</script>
