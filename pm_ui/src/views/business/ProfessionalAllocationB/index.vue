<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
          v-hasPermi="['business:ProfessionalAllocationB:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:ProfessionalAllocationB:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:ProfessionalAllocationB:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:ProfessionalAllocationB:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProfessionalAllocationBList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
      <el-table-column label="子项编号" align="center" prop="itemNo" />
      <el-table-column label="子项名称" align="center" prop="itemName" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="子项权重" align="center" prop="itemWeight" />
      <el-table-column label="专业负责人" align="center" prop="proResp" />
      <el-table-column label="EIM" align="center" prop="eim" />
      <el-table-column label="总体方案" align="center" prop="overallPlan" />
      <el-table-column label="校核" align="center" prop="checker" />
      <el-table-column label="审核" align="center" prop="reviewer" />
      <el-table-column label="压力管道审核" align="center" prop="pipeReview" />
      <el-table-column label="审定" align="center" prop="approval" />
      <el-table-column label="设计指导" align="center" prop="designGuidance" />
      <el-table-column label="设计1" align="center" prop="design1" />
      <el-table-column label="设计2" align="center" prop="design2" />
      <el-table-column label="设计3" align="center" prop="design3" />
      <el-table-column label="设计4" align="center" prop="design4" />
      <el-table-column label="设计5" align="center" prop="design5" />
      <el-table-column label="设计6" align="center" prop="design6" />
      <el-table-column label="设计7" align="center" prop="design7" />
      <el-table-column label="设计8" align="center" prop="design8" />
      <el-table-column label="设计9" align="center" prop="design9" />
      <el-table-column label="设计10" align="center" prop="design10" />
      <el-table-column label="设计11" align="center" prop="design11" />
      <el-table-column label="设计12" align="center" prop="design12" />
      <el-table-column label="设计13" align="center" prop="design13" />
      <el-table-column label="设计14" align="center" prop="design14" />
      <el-table-column label="设计15" align="center" prop="design15" />
      <el-table-column label="设计16" align="center" prop="design16" />
      <el-table-column label="设计17" align="center" prop="design17" />
      <el-table-column label="设计18" align="center" prop="design18" />
      <el-table-column label="设计19" align="center" prop="design19" />
      <el-table-column label="设计20" align="center" prop="design20" />
      <el-table-column label="采购支持" align="center" prop="procurementSupport" />
      <el-table-column label="顾问" align="center" prop="consultant" />
      <el-table-column label="专业负责人" align="center" prop="proRespPct" />
      <el-table-column label="EIM" align="center" prop="eimPct" />
      <el-table-column label="总体方案" align="center" prop="overallPlanPct" />
      <el-table-column label="校核" align="center" prop="checkerPct" />
      <el-table-column label="审核" align="center" prop="reviewerPct" />
      <el-table-column label="压力管道审核" align="center" prop="pipeReviewPct" />
      <el-table-column label="审定" align="center" prop="approvalPct" />
      <el-table-column label="设计指导" align="center" prop="designGuidancePct" />
      <el-table-column label="设计1比例" align="center" prop="design1Pct" />
      <el-table-column label="设计2比例" align="center" prop="design2Pct" />
      <el-table-column label="设计3比例" align="center" prop="design3Pct" />
      <el-table-column label="设计4比例" align="center" prop="design4Pct" />
      <el-table-column label="设计5比例" align="center" prop="design5Pct" />
      <el-table-column label="设计6比例" align="center" prop="design6Pct" />
      <el-table-column label="设计7比例" align="center" prop="design7Pct" />
      <el-table-column label="设计8比例" align="center" prop="design8Pct" />
      <el-table-column label="设计9比例" align="center" prop="design9Pct" />
      <el-table-column label="设计10比例" align="center" prop="design10Pct" />
      <el-table-column label="设计11比例" align="center" prop="design11Pct" />
      <el-table-column label="设计12比例" align="center" prop="design12Pct" />
      <el-table-column label="设计13比例" align="center" prop="design13Pct" />
      <el-table-column label="设计14比例" align="center" prop="design14Pct" />
      <el-table-column label="设计15比例" align="center" prop="design15Pct" />
      <el-table-column label="设计16比例" align="center" prop="design16Pct" />
      <el-table-column label="设计17比例" align="center" prop="design17Pct" />
      <el-table-column label="设计18比例" align="center" prop="design18Pct" />
      <el-table-column label="设计19比例" align="center" prop="design19Pct" />
      <el-table-column label="设计20比例" align="center" prop="design20Pct" />
      <el-table-column label="采购支持" align="center" prop="procurementSupportPct" />
      <el-table-column label="顾问" align="center" prop="consultantPct" />
      <el-table-column label="设计1最终比例" align="center" prop="final1Pct" />
      <el-table-column label="设计2最终比例" align="center" prop="final2Pct" />
      <el-table-column label="设计3最终比例" align="center" prop="final3Pct" />
      <el-table-column label="设计4最终比例" align="center" prop="final4Pct" />
      <el-table-column label="设计5最终比例" align="center" prop="final5Pct" />
      <el-table-column label="设计6最终比例" align="center" prop="final6Pct" />
      <el-table-column label="设计7最终比例" align="center" prop="final7Pct" />
      <el-table-column label="设计8最终比例" align="center" prop="final8Pct" />
      <el-table-column label="设计9最终比例" align="center" prop="final9Pct" />
      <el-table-column label="设计10最终比例" align="center" prop="final10Pct" />
      <el-table-column label="设计11最终比例" align="center" prop="final11Pct" />
      <el-table-column label="设计12最终比例" align="center" prop="final12Pct" />
      <el-table-column label="设计13最终比例" align="center" prop="final13Pct" />
      <el-table-column label="设计14最终比例" align="center" prop="final14Pct" />
      <el-table-column label="设计15最终比例" align="center" prop="final15Pct" />
      <el-table-column label="设计16最终比例" align="center" prop="final16Pct" />
      <el-table-column label="设计17最终比例" align="center" prop="final17Pct" />
      <el-table-column label="设计18最终比例" align="center" prop="final18Pct" />
      <el-table-column label="设计19最终比例" align="center" prop="final19Pct" />
      <el-table-column label="设计20最终比例" align="center" prop="final20Pct" />
      <el-table-column label="设计1进度" align="center" prop="progress1" />
      <el-table-column label="设计2进度" align="center" prop="progress2" />
      <el-table-column label="设计3进度" align="center" prop="progress3" />
      <el-table-column label="设计4进度" align="center" prop="progress4" />
      <el-table-column label="设计5进度" align="center" prop="progress5" />
      <el-table-column label="设计6进度" align="center" prop="progress6" />
      <el-table-column label="设计7进度" align="center" prop="progress7" />
      <el-table-column label="设计8进度" align="center" prop="progress8" />
      <el-table-column label="设计9进度" align="center" prop="progress9" />
      <el-table-column label="设计10进度" align="center" prop="progress10" />
      <el-table-column label="设计11进度" align="center" prop="progress11" />
      <el-table-column label="设计12进度" align="center" prop="progress12" />
      <el-table-column label="设计13进度" align="center" prop="progress13" />
      <el-table-column label="设计14进度" align="center" prop="progress14" />
      <el-table-column label="设计15进度" align="center" prop="progress15" />
      <el-table-column label="设计16进度" align="center" prop="progress16" />
      <el-table-column label="设计17进度" align="center" prop="progress17" />
      <el-table-column label="设计18进度" align="center" prop="progress18" />
      <el-table-column label="设计19进度" align="center" prop="progress19" />
      <el-table-column label="设计20进度" align="center" prop="progress20" />
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200" fixed="right">
        <template #default="scope">
          <el-button link type="primary"  @click="handleUpdate(scope.row)" v-hasPermi="['business:ProfessionalAllocationB:edit']">修改</el-button>
          <el-button link type="primary"  @click="handleDelete(scope.row)" v-hasPermi="['business:ProfessionalAllocationB:remove']">删除</el-button>
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

    <!-- 添加或修改专业负责人策划子表对话框 -->
    <el-dialog :title="title" v-model="open" width="1200px" append-to-body>
      <el-form ref="ProfessionalAllocationBRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专业负责人策划主表ID" prop="headId">
          <el-input v-model="form.headId" placeholder="请输入专业负责人策划主表ID" />
        </el-form-item>
        <el-form-item label="项目ID" prop="prjId">
          <el-input v-model="form.prjId" placeholder="请输入项目ID" />
        </el-form-item>
        <el-form-item label="项目子项ID" prop="itemId">
          <el-input v-model="form.itemId" placeholder="请输入项目子项ID" />
        </el-form-item>
        <el-form-item label="子项编号" prop="itemNo">
          <el-input v-model="form.itemNo" placeholder="请输入子项编号" />
        </el-form-item>
        <el-form-item label="子项名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入子项名称" />
        </el-form-item>
        <el-form-item label="子项权重" prop="itemWeight">
          <el-input v-model="form.itemWeight" placeholder="请输入子项权重" />
        </el-form-item>
        <el-form-item label="专业负责人" prop="proResp">
          <el-input v-model="form.proResp" placeholder="请输入专业负责人" />
        </el-form-item>
        <el-form-item label="EIM" prop="eim">
          <el-input v-model="form.eim" placeholder="请输入EIM" />
        </el-form-item>
        <el-form-item label="总体方案" prop="overallPlan">
          <el-input v-model="form.overallPlan" placeholder="请输入总体方案" />
        </el-form-item>
        <el-form-item label="校核" prop="checker">
          <el-input v-model="form.checker" placeholder="请输入校核" />
        </el-form-item>
        <el-form-item label="审核" prop="reviewer">
          <el-input v-model="form.reviewer" placeholder="请输入审核" />
        </el-form-item>
        <el-form-item label="压力管道审核" prop="pipeReview">
          <el-input v-model="form.pipeReview" placeholder="请输入压力管道审核" />
        </el-form-item>
        <el-form-item label="审定" prop="approval">
          <el-input v-model="form.approval" placeholder="请输入审定" />
        </el-form-item>
        <el-form-item label="设计指导" prop="designGuidance">
          <el-input v-model="form.designGuidance" placeholder="请输入设计指导" />
        </el-form-item>
        <el-form-item label="设计1" prop="design1">
          <el-input v-model="form.design1" placeholder="请输入设计1" />
        </el-form-item>
        <el-form-item label="设计2" prop="design2">
          <el-input v-model="form.design2" placeholder="请输入设计2" />
        </el-form-item>
        <el-form-item label="设计3" prop="design3">
          <el-input v-model="form.design3" placeholder="请输入设计3" />
        </el-form-item>
        <el-form-item label="设计4" prop="design4">
          <el-input v-model="form.design4" placeholder="请输入设计4" />
        </el-form-item>
        <el-form-item label="设计5" prop="design5">
          <el-input v-model="form.design5" placeholder="请输入设计5" />
        </el-form-item>
        <el-form-item label="设计6" prop="design6">
          <el-input v-model="form.design6" placeholder="请输入设计6" />
        </el-form-item>
        <el-form-item label="设计7" prop="design7">
          <el-input v-model="form.design7" placeholder="请输入设计7" />
        </el-form-item>
        <el-form-item label="设计8" prop="design8">
          <el-input v-model="form.design8" placeholder="请输入设计8" />
        </el-form-item>
        <el-form-item label="设计9" prop="design9">
          <el-input v-model="form.design9" placeholder="请输入设计9" />
        </el-form-item>
        <el-form-item label="设计10" prop="design10">
          <el-input v-model="form.design10" placeholder="请输入设计10" />
        </el-form-item>
        <el-form-item label="设计11" prop="design11">
          <el-input v-model="form.design11" placeholder="请输入设计11" />
        </el-form-item>
        <el-form-item label="设计12" prop="design12">
          <el-input v-model="form.design12" placeholder="请输入设计12" />
        </el-form-item>
        <el-form-item label="设计13" prop="design13">
          <el-input v-model="form.design13" placeholder="请输入设计13" />
        </el-form-item>
        <el-form-item label="设计14" prop="design14">
          <el-input v-model="form.design14" placeholder="请输入设计14" />
        </el-form-item>
        <el-form-item label="设计15" prop="design15">
          <el-input v-model="form.design15" placeholder="请输入设计15" />
        </el-form-item>
        <el-form-item label="设计16" prop="design16">
          <el-input v-model="form.design16" placeholder="请输入设计16" />
        </el-form-item>
        <el-form-item label="设计17" prop="design17">
          <el-input v-model="form.design17" placeholder="请输入设计17" />
        </el-form-item>
        <el-form-item label="设计18" prop="design18">
          <el-input v-model="form.design18" placeholder="请输入设计18" />
        </el-form-item>
        <el-form-item label="设计19" prop="design19">
          <el-input v-model="form.design19" placeholder="请输入设计19" />
        </el-form-item>
        <el-form-item label="设计20" prop="design20">
          <el-input v-model="form.design20" placeholder="请输入设计20" />
        </el-form-item>
        <el-form-item label="采购支持" prop="procurementSupport">
          <el-input v-model="form.procurementSupport" placeholder="请输入采购支持" />
        </el-form-item>
        <el-form-item label="顾问" prop="consultant">
          <el-input v-model="form.consultant" placeholder="请输入顾问" />
        </el-form-item>
        <el-form-item label="专业负责人" prop="proRespPct">
          <el-input v-model="form.proRespPct" placeholder="请输入专业负责人" />
        </el-form-item>
        <el-form-item label="EIM" prop="eimPct">
          <el-input v-model="form.eimPct" placeholder="请输入EIM" />
        </el-form-item>
        <el-form-item label="总体方案" prop="overallPlanPct">
          <el-input v-model="form.overallPlanPct" placeholder="请输入总体方案" />
        </el-form-item>
        <el-form-item label="校核" prop="checkerPct">
          <el-input v-model="form.checkerPct" placeholder="请输入校核" />
        </el-form-item>
        <el-form-item label="审核" prop="reviewerPct">
          <el-input v-model="form.reviewerPct" placeholder="请输入审核" />
        </el-form-item>
        <el-form-item label="压力管道审核" prop="pipeReviewPct">
          <el-input v-model="form.pipeReviewPct" placeholder="请输入压力管道审核" />
        </el-form-item>
        <el-form-item label="审定" prop="approvalPct">
          <el-input v-model="form.approvalPct" placeholder="请输入审定" />
        </el-form-item>
        <el-form-item label="设计指导" prop="designGuidancePct">
          <el-input v-model="form.designGuidancePct" placeholder="请输入设计指导" />
        </el-form-item>
        <el-form-item label="设计1比例" prop="design1Pct">
          <el-input v-model="form.design1Pct" placeholder="请输入设计1比例" />
        </el-form-item>
        <el-form-item label="设计2比例" prop="design2Pct">
          <el-input v-model="form.design2Pct" placeholder="请输入设计2比例" />
        </el-form-item>
        <el-form-item label="设计3比例" prop="design3Pct">
          <el-input v-model="form.design3Pct" placeholder="请输入设计3比例" />
        </el-form-item>
        <el-form-item label="设计4比例" prop="design4Pct">
          <el-input v-model="form.design4Pct" placeholder="请输入设计4比例" />
        </el-form-item>
        <el-form-item label="设计5比例" prop="design5Pct">
          <el-input v-model="form.design5Pct" placeholder="请输入设计5比例" />
        </el-form-item>
        <el-form-item label="设计6比例" prop="design6Pct">
          <el-input v-model="form.design6Pct" placeholder="请输入设计6比例" />
        </el-form-item>
        <el-form-item label="设计7比例" prop="design7Pct">
          <el-input v-model="form.design7Pct" placeholder="请输入设计7比例" />
        </el-form-item>
        <el-form-item label="设计8比例" prop="design8Pct">
          <el-input v-model="form.design8Pct" placeholder="请输入设计8比例" />
        </el-form-item>
        <el-form-item label="设计9比例" prop="design9Pct">
          <el-input v-model="form.design9Pct" placeholder="请输入设计9比例" />
        </el-form-item>
        <el-form-item label="设计10比例" prop="design10Pct">
          <el-input v-model="form.design10Pct" placeholder="请输入设计10比例" />
        </el-form-item>
        <el-form-item label="设计11比例" prop="design11Pct">
          <el-input v-model="form.design11Pct" placeholder="请输入设计11比例" />
        </el-form-item>
        <el-form-item label="设计12比例" prop="design12Pct">
          <el-input v-model="form.design12Pct" placeholder="请输入设计12比例" />
        </el-form-item>
        <el-form-item label="设计13比例" prop="design13Pct">
          <el-input v-model="form.design13Pct" placeholder="请输入设计13比例" />
        </el-form-item>
        <el-form-item label="设计14比例" prop="design14Pct">
          <el-input v-model="form.design14Pct" placeholder="请输入设计14比例" />
        </el-form-item>
        <el-form-item label="设计15比例" prop="design15Pct">
          <el-input v-model="form.design15Pct" placeholder="请输入设计15比例" />
        </el-form-item>
        <el-form-item label="设计16比例" prop="design16Pct">
          <el-input v-model="form.design16Pct" placeholder="请输入设计16比例" />
        </el-form-item>
        <el-form-item label="设计17比例" prop="design17Pct">
          <el-input v-model="form.design17Pct" placeholder="请输入设计17比例" />
        </el-form-item>
        <el-form-item label="设计18比例" prop="design18Pct">
          <el-input v-model="form.design18Pct" placeholder="请输入设计18比例" />
        </el-form-item>
        <el-form-item label="设计19比例" prop="design19Pct">
          <el-input v-model="form.design19Pct" placeholder="请输入设计19比例" />
        </el-form-item>
        <el-form-item label="设计20比例" prop="design20Pct">
          <el-input v-model="form.design20Pct" placeholder="请输入设计20比例" />
        </el-form-item>
        <el-form-item label="采购支持" prop="procurementSupportPct">
          <el-input v-model="form.procurementSupportPct" placeholder="请输入采购支持" />
        </el-form-item>
        <el-form-item label="顾问" prop="consultantPct">
          <el-input v-model="form.consultantPct" placeholder="请输入顾问" />
        </el-form-item>
        <el-form-item label="设计1最终比例" prop="final1Pct">
          <el-input v-model="form.final1Pct" placeholder="请输入设计1最终比例" />
        </el-form-item>
        <el-form-item label="设计2最终比例" prop="final2Pct">
          <el-input v-model="form.final2Pct" placeholder="请输入设计2最终比例" />
        </el-form-item>
        <el-form-item label="设计3最终比例" prop="final3Pct">
          <el-input v-model="form.final3Pct" placeholder="请输入设计3最终比例" />
        </el-form-item>
        <el-form-item label="设计4最终比例" prop="final4Pct">
          <el-input v-model="form.final4Pct" placeholder="请输入设计4最终比例" />
        </el-form-item>
        <el-form-item label="设计5最终比例" prop="final5Pct">
          <el-input v-model="form.final5Pct" placeholder="请输入设计5最终比例" />
        </el-form-item>
        <el-form-item label="设计6最终比例" prop="final6Pct">
          <el-input v-model="form.final6Pct" placeholder="请输入设计6最终比例" />
        </el-form-item>
        <el-form-item label="设计7最终比例" prop="final7Pct">
          <el-input v-model="form.final7Pct" placeholder="请输入设计7最终比例" />
        </el-form-item>
        <el-form-item label="设计8最终比例" prop="final8Pct">
          <el-input v-model="form.final8Pct" placeholder="请输入设计8最终比例" />
        </el-form-item>
        <el-form-item label="设计9最终比例" prop="final9Pct">
          <el-input v-model="form.final9Pct" placeholder="请输入设计9最终比例" />
        </el-form-item>
        <el-form-item label="设计10最终比例" prop="final10Pct">
          <el-input v-model="form.final10Pct" placeholder="请输入设计10最终比例" />
        </el-form-item>
        <el-form-item label="设计11最终比例" prop="final11Pct">
          <el-input v-model="form.final11Pct" placeholder="请输入设计11最终比例" />
        </el-form-item>
        <el-form-item label="设计12最终比例" prop="final12Pct">
          <el-input v-model="form.final12Pct" placeholder="请输入设计12最终比例" />
        </el-form-item>
        <el-form-item label="设计13最终比例" prop="final13Pct">
          <el-input v-model="form.final13Pct" placeholder="请输入设计13最终比例" />
        </el-form-item>
        <el-form-item label="设计14最终比例" prop="final14Pct">
          <el-input v-model="form.final14Pct" placeholder="请输入设计14最终比例" />
        </el-form-item>
        <el-form-item label="设计15最终比例" prop="final15Pct">
          <el-input v-model="form.final15Pct" placeholder="请输入设计15最终比例" />
        </el-form-item>
        <el-form-item label="设计16最终比例" prop="final16Pct">
          <el-input v-model="form.final16Pct" placeholder="请输入设计16最终比例" />
        </el-form-item>
        <el-form-item label="设计17最终比例" prop="final17Pct">
          <el-input v-model="form.final17Pct" placeholder="请输入设计17最终比例" />
        </el-form-item>
        <el-form-item label="设计18最终比例" prop="final18Pct">
          <el-input v-model="form.final18Pct" placeholder="请输入设计18最终比例" />
        </el-form-item>
        <el-form-item label="设计19最终比例" prop="final19Pct">
          <el-input v-model="form.final19Pct" placeholder="请输入设计19最终比例" />
        </el-form-item>
        <el-form-item label="设计20最终比例" prop="final20Pct">
          <el-input v-model="form.final20Pct" placeholder="请输入设计20最终比例" />
        </el-form-item>
        <el-form-item label="设计1进度" prop="progress1">
          <el-input v-model="form.progress1" placeholder="请输入设计1进度" />
        </el-form-item>
        <el-form-item label="设计2进度" prop="progress2">
          <el-input v-model="form.progress2" placeholder="请输入设计2进度" />
        </el-form-item>
        <el-form-item label="设计3进度" prop="progress3">
          <el-input v-model="form.progress3" placeholder="请输入设计3进度" />
        </el-form-item>
        <el-form-item label="设计4进度" prop="progress4">
          <el-input v-model="form.progress4" placeholder="请输入设计4进度" />
        </el-form-item>
        <el-form-item label="设计5进度" prop="progress5">
          <el-input v-model="form.progress5" placeholder="请输入设计5进度" />
        </el-form-item>
        <el-form-item label="设计6进度" prop="progress6">
          <el-input v-model="form.progress6" placeholder="请输入设计6进度" />
        </el-form-item>
        <el-form-item label="设计7进度" prop="progress7">
          <el-input v-model="form.progress7" placeholder="请输入设计7进度" />
        </el-form-item>
        <el-form-item label="设计8进度" prop="progress8">
          <el-input v-model="form.progress8" placeholder="请输入设计8进度" />
        </el-form-item>
        <el-form-item label="设计9进度" prop="progress9">
          <el-input v-model="form.progress9" placeholder="请输入设计9进度" />
        </el-form-item>
        <el-form-item label="设计10进度" prop="progress10">
          <el-input v-model="form.progress10" placeholder="请输入设计10进度" />
        </el-form-item>
        <el-form-item label="设计11进度" prop="progress11">
          <el-input v-model="form.progress11" placeholder="请输入设计11进度" />
        </el-form-item>
        <el-form-item label="设计12进度" prop="progress12">
          <el-input v-model="form.progress12" placeholder="请输入设计12进度" />
        </el-form-item>
        <el-form-item label="设计13进度" prop="progress13">
          <el-input v-model="form.progress13" placeholder="请输入设计13进度" />
        </el-form-item>
        <el-form-item label="设计14进度" prop="progress14">
          <el-input v-model="form.progress14" placeholder="请输入设计14进度" />
        </el-form-item>
        <el-form-item label="设计15进度" prop="progress15">
          <el-input v-model="form.progress15" placeholder="请输入设计15进度" />
        </el-form-item>
        <el-form-item label="设计16进度" prop="progress16">
          <el-input v-model="form.progress16" placeholder="请输入设计16进度" />
        </el-form-item>
        <el-form-item label="设计17进度" prop="progress17">
          <el-input v-model="form.progress17" placeholder="请输入设计17进度" />
        </el-form-item>
        <el-form-item label="设计18进度" prop="progress18">
          <el-input v-model="form.progress18" placeholder="请输入设计18进度" />
        </el-form-item>
        <el-form-item label="设计19进度" prop="progress19">
          <el-input v-model="form.progress19" placeholder="请输入设计19进度" />
        </el-form-item>
        <el-form-item label="设计20进度" prop="progress20">
          <el-input v-model="form.progress20" placeholder="请输入设计20进度" />
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

<script setup name="ProfessionalAllocationB">
import { listProfessionalAllocationB, getProfessionalAllocationB, delProfessionalAllocationB, addProfessionalAllocationB, updateProfessionalAllocationB } from "@/api/business/ProfessionalAllocationB";
import { selectProfessionalAllocationB } from "@/api/business/ProfessionalAllocation";
import { useRoute } from "vue-router";

const route = useRoute();

const { proxy } = getCurrentInstance();

const ProfessionalAllocationBList = ref([]);
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
    // itemNo: null,
    // itemName: null,
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

const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  // itemNo: null,
  // itemName: null,
  prjId: route.params.prjId // 项目ID，由路由传递
});

const {  form, rules } = toRefs(data);

/** 查询专业负责人策划子表列表 */
function getList() {
  loading.value = true;
  selectProfessionalAllocationB(queryParams.value).then(response => {
    ProfessionalAllocationBList.value = response.rows;
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
    itemId: null,
    itemNo: null,
    itemName: null,
    itemWeight: null,
    proResp: null,
    eim: null,
    overallPlan: null,
    checker: null,
    reviewer: null,
    pipeReview: null,
    approval: null,
    designGuidance: null,
    design1: null,
    design2: null,
    design3: null,
    design4: null,
    design5: null,
    design6: null,
    design7: null,
    design8: null,
    design9: null,
    design10: null,
    design11: null,
    design12: null,
    design13: null,
    design14: null,
    design15: null,
    design16: null,
    design17: null,
    design18: null,
    design19: null,
    design20: null,
    procurementSupport: null,
    consultant: null,
    proRespPct: null,
    eimPct: null,
    overallPlanPct: null,
    checkerPct: null,
    reviewerPct: null,
    pipeReviewPct: null,
    approvalPct: null,
    designGuidancePct: null,
    design1Pct: null,
    design2Pct: null,
    design3Pct: null,
    design4Pct: null,
    design5Pct: null,
    design6Pct: null,
    design7Pct: null,
    design8Pct: null,
    design9Pct: null,
    design10Pct: null,
    design11Pct: null,
    design12Pct: null,
    design13Pct: null,
    design14Pct: null,
    design15Pct: null,
    design16Pct: null,
    design17Pct: null,
    design18Pct: null,
    design19Pct: null,
    design20Pct: null,
    procurementSupportPct: null,
    consultantPct: null,
    final1Pct: null,
    final2Pct: null,
    final3Pct: null,
    final4Pct: null,
    final5Pct: null,
    final6Pct: null,
    final7Pct: null,
    final8Pct: null,
    final9Pct: null,
    final10Pct: null,
    final11Pct: null,
    final12Pct: null,
    final13Pct: null,
    final14Pct: null,
    final15Pct: null,
    final16Pct: null,
    final17Pct: null,
    final18Pct: null,
    final19Pct: null,
    final20Pct: null,
    progress1: null,
    progress2: null,
    progress3: null,
    progress4: null,
    progress5: null,
    progress6: null,
    progress7: null,
    progress8: null,
    progress9: null,
    progress10: null,
    progress11: null,
    progress12: null,
    progress13: null,
    progress14: null,
    progress15: null,
    progress16: null,
    progress17: null,
    progress18: null,
    progress19: null,
    progress20: null,
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
  title.value = "添加专业负责人策划子表";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProfessionalAllocationB(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改专业负责人策划子表";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["ProfessionalAllocationBRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProfessionalAllocationB(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProfessionalAllocationB(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除专业负责人策划子表编号为"' + _ids + '"的数据项？').then(function() {
    return delProfessionalAllocationB(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/ProfessionalAllocationB/export', {
    ...queryParams.value
  }, `ProfessionalAllocationB_${new Date().getTime()}.xlsx`)
}

getList();
</script>
