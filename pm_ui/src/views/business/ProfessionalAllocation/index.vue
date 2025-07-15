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
      <el-table-column label="项目ID" align="center" prop="prjId" />
      <el-table-column label="专业进度" align="center">
        <template #default="scope">
          <router-link
              :to="{
        path: '/business/ProfessionalAllocationB/index',
        query: {
          prjId: scope.row.prjId,
          profession: scope.row.profession,
          headId: scope.row.id
        }
      }"      style="text-decoration: underline; color: #1890FF;"
          >
            {{ scope.row.majorProgress }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="专业" align="center" prop="profession">
        <template #default="scope">
          <dict-tag :options="user_pro" :value="scope.row.profession"/>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true"/>
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="240" fixed="right">
        <template #default="scope">
<!--          <el-button link type="primary"  @click="handleDesignRatio(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">分项策划</el-button>-->
          <el-button link type="primary"  @click="jumpToBody(scope.row)" v-hasPermi="['business:ProfessionalAllocation:edit']">编辑与查看子项</el-button>
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
<!--        <el-form-item label="项目ID" prop="prjId">
          <el-input v-model="form.prjId" placeholder="请输入项目ID" />
        </el-form-item>-->
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
          <el-input v-model="form.projectName" disabled />
        </el-form-item>
        <el-form-item label="项目号" prop="projectNo">
          <el-input v-model="form.projectNo" placeholder="请输入项目号" />
        </el-form-item>
        <el-form-item label="阶段号" prop="phaseNo">
          <el-input v-model="form.phaseNo" placeholder="请输入阶段号" />
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
        <el-divider content-position="center">专业负责人策划子信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddProfessionalAllocationB">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteProfessionalAllocationB">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="professionalAllocationBList" :row-class-name="rowProfessionalAllocationBIndex" @selection-change="handleProfessionalAllocationBSelectionChange" ref="professionalAllocationB">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="项目id" prop="prjId" width="150" >
            <template #default="scope">
              <el-input v-model="scope.row.prjId" placeholder="请输入项目id" />
            </template>
          </el-table-column>
          <el-table-column label="子项编号" prop="itemNo" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.itemNo" placeholder="请输入子项编号" />
            </template>
          </el-table-column>
          <el-table-column label="子项名称" prop="itemName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.itemName" placeholder="请输入子项名称" />
            </template>
          </el-table-column>
          <el-table-column label="子项权重" prop="itemWeight" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.itemWeight" placeholder="请输入子项权重" />
            </template>
          </el-table-column>
          <el-table-column label="专业负责人" prop="proResp" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.proResp" placeholder="请输入专业负责人" />
            </template>
          </el-table-column>
          <el-table-column label="EIM" prop="eim" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.eim" placeholder="请输入EIM" />
            </template>
          </el-table-column>
          <el-table-column label="总体方案" prop="overallPlan" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.overallPlan" placeholder="请输入总体方案" />
            </template>
          </el-table-column>
          <el-table-column label="校核" prop="checker" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.checker" placeholder="请输入校核" />
            </template>
          </el-table-column>
          <el-table-column label="审核" prop="reviewer" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.reviewer" placeholder="请输入审核" />
            </template>
          </el-table-column>
          <el-table-column label="压力管道审核" prop="pipeReview" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.pipeReview" placeholder="请输入压力管道审核" />
            </template>
          </el-table-column>
          <el-table-column label="审定" prop="approval" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.approval" placeholder="请输入审定" />
            </template>
          </el-table-column>
          <el-table-column label="设计指导" prop="designGuidance" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.designGuidance" placeholder="请输入设计指导" />
            </template>
          </el-table-column>
          <el-table-column label="设计1" prop="design1" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design1" placeholder="请输入设计1" />
            </template>
          </el-table-column>
          <el-table-column label="设计2" prop="design2" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design2" placeholder="请输入设计2" />
            </template>
          </el-table-column>
          <el-table-column label="设计3" prop="design3" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design3" placeholder="请输入设计3" />
            </template>
          </el-table-column>
          <el-table-column label="设计4" prop="design4" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design4" placeholder="请输入设计4" />
            </template>
          </el-table-column>
          <el-table-column label="设计5" prop="design5" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design5" placeholder="请输入设计5" />
            </template>
          </el-table-column>
          <el-table-column label="设计6" prop="design6" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design6" placeholder="请输入设计6" />
            </template>
          </el-table-column>
          <el-table-column label="设计7" prop="design7" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design7" placeholder="请输入设计7" />
            </template>
          </el-table-column>
          <el-table-column label="设计8" prop="design8" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design8" placeholder="请输入设计8" />
            </template>
          </el-table-column>
          <el-table-column label="设计9" prop="design9" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design9" placeholder="请输入设计9" />
            </template>
          </el-table-column>
          <el-table-column label="设计10" prop="design10" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design10" placeholder="请输入设计10" />
            </template>
          </el-table-column>
          <el-table-column label="设计11" prop="design11" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design11" placeholder="请输入设计11" />
            </template>
          </el-table-column>
          <el-table-column label="设计12" prop="design12" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design12" placeholder="请输入设计12" />
            </template>
          </el-table-column>
          <el-table-column label="设计13" prop="design13" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design13" placeholder="请输入设计13" />
            </template>
          </el-table-column>
          <el-table-column label="设计14" prop="design14" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design14" placeholder="请输入设计14" />
            </template>
          </el-table-column>
          <el-table-column label="设计15" prop="design15" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design15" placeholder="请输入设计15" />
            </template>
          </el-table-column>
          <el-table-column label="设计16" prop="design16" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design16" placeholder="请输入设计16" />
            </template>
          </el-table-column>
          <el-table-column label="设计17" prop="design17" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design17" placeholder="请输入设计17" />
            </template>
          </el-table-column>
          <el-table-column label="设计18" prop="design18" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design18" placeholder="请输入设计18" />
            </template>
          </el-table-column>
          <el-table-column label="设计19" prop="design19" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design19" placeholder="请输入设计19" />
            </template>
          </el-table-column>
          <el-table-column label="设计20" prop="design20" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design20" placeholder="请输入设计20" />
            </template>
          </el-table-column>
          <el-table-column label="采购支持" prop="procurementSupport" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.procurementSupport" placeholder="请输入采购支持" />
            </template>
          </el-table-column>
          <el-table-column label="顾问" prop="consultant" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.consultant" placeholder="请输入顾问" />
            </template>
          </el-table-column>
          <el-table-column label="专业负责人" prop="proRespPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.proRespPct" placeholder="请输入专业负责人" />
            </template>
          </el-table-column>
          <el-table-column label="EIM" prop="eimPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.eimPct" placeholder="请输入EIM" />
            </template>
          </el-table-column>
          <el-table-column label="总体方案" prop="overallPlanPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.overallPlanPct" placeholder="请输入总体方案" />
            </template>
          </el-table-column>
          <el-table-column label="校核" prop="checkerPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.checkerPct" placeholder="请输入校核" />
            </template>
          </el-table-column>
          <el-table-column label="审核" prop="reviewerPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.reviewerPct" placeholder="请输入审核" />
            </template>
          </el-table-column>
          <el-table-column label="压力管道审核" prop="pipeReviewPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.pipeReviewPct" placeholder="请输入压力管道审核" />
            </template>
          </el-table-column>
          <el-table-column label="审定" prop="approvalPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.approvalPct" placeholder="请输入审定" />
            </template>
          </el-table-column>
          <el-table-column label="设计指导" prop="designGuidancePct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.designGuidancePct" placeholder="请输入设计指导" />
            </template>
          </el-table-column>
          <el-table-column label="设计1比例" prop="design1Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design1Pct" placeholder="请输入设计1比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计2比例" prop="design2Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design2Pct" placeholder="请输入设计2比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计3比例" prop="design3Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design3Pct" placeholder="请输入设计3比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计4比例" prop="design4Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design4Pct" placeholder="请输入设计4比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计5比例" prop="design5Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design5Pct" placeholder="请输入设计5比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计6比例" prop="design6Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design6Pct" placeholder="请输入设计6比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计7比例" prop="design7Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design7Pct" placeholder="请输入设计7比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计8比例" prop="design8Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design8Pct" placeholder="请输入设计8比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计9比例" prop="design9Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design9Pct" placeholder="请输入设计9比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计10比例" prop="design10Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design10Pct" placeholder="请输入设计10比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计11比例" prop="design11Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design11Pct" placeholder="请输入设计11比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计12比例" prop="design12Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design12Pct" placeholder="请输入设计12比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计13比例" prop="design13Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design13Pct" placeholder="请输入设计13比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计14比例" prop="design14Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design14Pct" placeholder="请输入设计14比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计15比例" prop="design15Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design15Pct" placeholder="请输入设计15比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计16比例" prop="design16Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design16Pct" placeholder="请输入设计16比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计17比例" prop="design17Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design17Pct" placeholder="请输入设计17比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计18比例" prop="design18Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design18Pct" placeholder="请输入设计18比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计19比例" prop="design19Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design19Pct" placeholder="请输入设计19比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计20比例" prop="design20Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.design20Pct" placeholder="请输入设计20比例" />
            </template>
          </el-table-column>
          <el-table-column label="采购支持" prop="procurementSupportPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.procurementSupportPct" placeholder="请输入采购支持" />
            </template>
          </el-table-column>
          <el-table-column label="顾问" prop="consultantPct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.consultantPct" placeholder="请输入顾问" />
            </template>
          </el-table-column>
          <el-table-column label="设计1最终比例" prop="final1Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final1Pct" placeholder="请输入设计1最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计2最终比例" prop="final2Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final2Pct" placeholder="请输入设计2最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计3最终比例" prop="final3Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final3Pct" placeholder="请输入设计3最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计4最终比例" prop="final4Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final4Pct" placeholder="请输入设计4最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计5最终比例" prop="final5Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final5Pct" placeholder="请输入设计5最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计6最终比例" prop="final6Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final6Pct" placeholder="请输入设计6最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计7最终比例" prop="final7Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final7Pct" placeholder="请输入设计7最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计8最终比例" prop="final8Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final8Pct" placeholder="请输入设计8最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计9最终比例" prop="final9Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final9Pct" placeholder="请输入设计9最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计10最终比例" prop="final10Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final10Pct" placeholder="请输入设计10最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计11最终比例" prop="final11Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final11Pct" placeholder="请输入设计11最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计12最终比例" prop="final12Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final12Pct" placeholder="请输入设计12最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计13最终比例" prop="final13Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final13Pct" placeholder="请输入设计13最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计14最终比例" prop="final14Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final14Pct" placeholder="请输入设计14最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计15最终比例" prop="final15Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final15Pct" placeholder="请输入设计15最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计16最终比例" prop="final16Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final16Pct" placeholder="请输入设计16最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计17最终比例" prop="final17Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final17Pct" placeholder="请输入设计17最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计18最终比例" prop="final18Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final18Pct" placeholder="请输入设计18最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计19最终比例" prop="final19Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final19Pct" placeholder="请输入设计19最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计20最终比例" prop="final20Pct" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.final20Pct" placeholder="请输入设计20最终比例" />
            </template>
          </el-table-column>
          <el-table-column label="设计1进度" prop="progress1" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress1" placeholder="请输入设计1进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计2进度" prop="progress2" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress2" placeholder="请输入设计2进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计3进度" prop="progress3" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress3" placeholder="请输入设计3进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计4进度" prop="progress4" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress4" placeholder="请输入设计4进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计5进度" prop="progress5" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress5" placeholder="请输入设计5进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计6进度" prop="progress6" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress6" placeholder="请输入设计6进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计7进度" prop="progress7" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress7" placeholder="请输入设计7进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计8进度" prop="progress8" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress8" placeholder="请输入设计8进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计9进度" prop="progress9" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress9" placeholder="请输入设计9进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计10进度" prop="progress10" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress10" placeholder="请输入设计10进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计11进度" prop="progress11" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress11" placeholder="请输入设计11进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计12进度" prop="progress12" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress12" placeholder="请输入设计12进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计13进度" prop="progress13" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress13" placeholder="请输入设计13进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计14进度" prop="progress14" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress14" placeholder="请输入设计14进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计15进度" prop="progress15" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress15" placeholder="请输入设计15进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计16进度" prop="progress16" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress16" placeholder="请输入设计16进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计17进度" prop="progress17" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress17" placeholder="请输入设计17进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计18进度" prop="progress18" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress18" placeholder="请输入设计18进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计19进度" prop="progress19" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress19" placeholder="请输入设计19进度" />
            </template>
          </el-table-column>
          <el-table-column label="设计20进度" prop="progress20" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.progress20" placeholder="请输入设计20进度" />
            </template>
          </el-table-column>
          <el-table-column label="创建者" prop="createBy" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.createBy" placeholder="请输入创建者" />
            </template>
          </el-table-column>
          <el-table-column label="创建时间" prop="createTime" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.createTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择创建时间">
              </el-date-picker>
            </template>
          </el-table-column>
          <el-table-column label="更新者" prop="updateBy" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.updateBy" placeholder="请输入更新者" />
            </template>
          </el-table-column>
          <el-table-column label="更新时间" prop="updateTime" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.updateTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择更新时间">
              </el-date-picker>
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

<script setup name="ProfessionalAllocation">
import { listProfessionalAllocation, getProfessionalAllocation, delProfessionalAllocation, addProfessionalAllocation, updateProfessionalAllocation } from "@/api/business/ProfessionalAllocation";
import { listProject } from "@/api/business/project";
import { reactive  } from "vue";
import { calculateSubItemProgress } from '@/utils';
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
// 主页面 ProfessionalAllocation/index.vue

import { emitter } from '@/utils/mitt';
onMounted(() => {
  emitter.on('refresh-professional-allocation', handleQuery);
  const projectName = getProjectNameFromQuery();
  if (projectName) {
    queryParams.value.projectName = projectName;
    handleQuery();
  } else {
    console.warn('未找到 projectName 参数，跳过自动搜索');
  }
});

// 记得在 unmounted 中移除监听
import { onUnmounted } from 'vue';
onUnmounted(() => {
  emitter.off('refresh-professional-allocation', handleQuery);
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
import { onActivated } from 'vue';
onActivated(() => {
  handleQuery();
});
async function handleQuery() {
  queryParams.value.pageNum = 1;
  await getList(); // ✅ 获取主表数据

  // ✅ 使用 Promise.all 确保所有行都完成计算
  await Promise.all(
      ProfessionalAllocationList.value.map(row => calculateMajorProgress(row))
  );
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

          const headId = response.data.id; // 新增主表的 id
          const prjId = form.value.prjId;   // 主表的 prjId
          // 调用接口：将子表中 prjId 相同且 headId 为空的数据更新 headId
          if (prjId && headId) {
            updateSubTableHeadIdByPrjId(prjId, headId).then(() => {
              proxy.$modal.msgSuccess("子表 headId 更新完成");
            }).catch(error => {
              proxy.$modal.msgError("子表更新失败");
              console.error("更新子表失败:", error);
            });
          }

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

// 获取项目子项列表 start
import { getItemListByPrjId } from "@/api/business/projectItem";
const projectItemList  = ref([]);
console.log(projectItemList)

function getProjectItemList(prjId){
  const params = reactive({
    pageNum: 1,
    pageSize: 100000,
    prjId : prjId
  })
  getItemListByPrjId(params).then(response => {
    console.log(params)
    projectItemList.value = response.rows;
  });
};
//end

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

  //getProjectItemList(selectProject.id);
};
function parseWeight(weight) {
  if (typeof weight === 'string') {
    return parseFloat(weight.replace('%', '')) / 100;
  }
  return weight || 0;
}
// 定义一个响应式的变量来存储专业进度
const majorProgress = ref('0.00%');
async function calculateMajorProgress(row) {
  if (!row || !row.profession || !row.prjId) {
    row.majorProgress = '0.00%';
    return;
  }

  try {
    const subResponse = await selectProfessionalAllocationB({
      profession: row.profession,
      prjId: row.prjId
    });

    if (!subResponse.rows || !subResponse.rows.length) {
      row.majorProgress = '0.00%';
      return;
    }

    const relatedItems = subResponse.rows.filter(item => item.prjId === row.prjId);
    let total = 0;

    for (const item of relatedItems) {
      const progress = parseFloat(calculateSubItemProgress(item)) || 0;
      const weight = parseWeight(item.itemWeight) || 0;
      total += (progress * weight) / 100;
    }

    row.majorProgress = `${total.toFixed(2)}%`; // ✅ 更新当前行的进度字段
  } catch (error) {
    console.error('获取专业进度失败:', error);
    row.majorProgress = '0.00%';
  }
}

//项目子项跳转专业负责人策划功能 start
watch(
    () => [route.query.prjId, route.query.profession, route.query.projectName],
    async ([newPrjId, newProfession, newProjectName]) => {
      if (newPrjId || newProfession || newProjectName) {
        await handleQuery();
      }
    },
    { immediate: true, deep: true }
)

//项目子项跳转专业负责人策划功能 end

getProjectList();
getList();
</script>
