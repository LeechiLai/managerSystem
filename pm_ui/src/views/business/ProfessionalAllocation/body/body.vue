<template>
  <div class="app-container">
<!--    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
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
    </el-form>-->

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

    <el-table v-loading="loading" v-if="getListSignal" :data="ProfessionalAllocationBList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="子项编号" align="center" prop="itemNo" />
      <el-table-column label="各子项进度" align="center" width="100">
        <template #default="scope">
          {{ calculateSubItemProgress(scope.row) }}
        </template>
      </el-table-column>
      <el-table-column label="子项名称" align="center" prop="itemName" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="子项权重" align="center" prop="itemWeight" />
      <el-table-column label="专业负责人" align="center" prop="proResp" width="100"/>
      <el-table-column label="EIM" align="center" prop="eim" />
      <el-table-column label="总体方案" align="center" prop="overallPlan" />
      <el-table-column label="校核" align="center" prop="checker" />
      <el-table-column label="审核" align="center" prop="reviewer" />
      <el-table-column label="压力管道审核" align="center" prop="pipeReview" width="110"/>
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
    <el-dialog :title="title" v-model="open" width="950px" append-to-body>

        <el-form ref="ProfessionalAllocationBRef" :model="form" :rules="rules" label-width="80px">
          <el-row>
            <el-form-item label="专业负责人策划主表ID" prop="headId" label-width="160">
              <el-input v-model="form.headId" placeholder="请输入专业负责人策划主表ID" style="width: 120px;" />
            </el-form-item>
            <el-form-item label="项目ID" prop="prjId" label-width="160">
              <el-input v-model="form.prjId" placeholder="请输入项目ID" style="width: 120px;" />
            </el-form-item>
            <el-form-item label="项目子项ID" prop="itemId" label-width="160">
              <el-input v-model="form.itemId" placeholder="请输入项目子项ID" style="width: 120px;" />
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

          <el-divider content-position="center" style="margin-left: -20px;">管理咨询区</el-divider>
<!--A-->
<!--          <el-row>
            <el-form-item label="专业负责人" prop="proResp" label-width="160">
              <el-select
                  v-model="form.proResp"
                  placeholder="请选择专业负责人"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="proRespPct" label-width="100">
              <el-input
                  v-model.number="form.proRespPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="proRespProgress" label-width="100">
              <el-input
                  v-model.number="form.proRespProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="proRespFinalPct" label-width="160">
              <el-input
                  v-model="data.form.proRespFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly

              />
            </el-form-item>
&lt;!&ndash;            <el-form-item label="本次来款按比例发放金额" prop="proRespIssuedAmount" label-width="200">
              <el-input
                  v-model="data.form.proRespIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>&ndash;&gt;
          </el-row>-->

          <el-row>
            <el-form-item label="专业负责人" prop="proResp" label-width="160">
              <el-select
                  v-model="form.proResp"
                  placeholder="请选择专业负责人"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="proRespPct" label-width="100">
              <el-input
                  v-model.number="form.proRespPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="proRespProgress" label-width="100">
              <el-input
                  v-model.number="form.proRespProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="proRespFinalPct" label-width="160">
              <el-input
                  v-model="data.form.proRespFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="proRespIssuedAmount" label-width="200">
              <el-input
                  v-model="data.form.proRespIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <el-row>
            <el-form-item label="专业EIM" prop="eim" label-width="160">
              <el-select
                  v-model="data.form.eim"
                  placeholder="请选择专业EIM"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="eimPct" label-width="100">
              <el-input
                  v-model.number="data.form.eimPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="eimProgress" label-width="100">
              <el-input
                  v-model.number="data.form.eimProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="eimFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.eimFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="eimIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.eimIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 总体方案 -->
          <el-row>
            <el-form-item label="总体方案" prop="overallPlan" label-width="160">
              <el-select
                  v-model="data.form.overallPlan"
                  placeholder="请选择总体方案"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="overallPlanPct" label-width="100">
              <el-input
                  v-model.number="data.form.overallPlanPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="overallPlanProgress" label-width="100">
              <el-input
                  v-model.number="data.form.overallPlanProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="overallPlanFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.overallPlanFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="overallPlanIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.overallPlanIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 校核 -->
          <el-row>
            <el-form-item label="校核" prop="checker" label-width="160">
              <el-select
                  v-model="data.form.checker"
                  placeholder="请选择校核"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="checkerPct" label-width="100">
              <el-input
                  v-model.number="data.form.checkerPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="checkerProgress" label-width="100">
              <el-input
                  v-model.number="data.form.checkerProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="checkerFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.checkerFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="checkerIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.checkerIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 审核 -->
          <el-row>
            <el-form-item label="审核" prop="reviewer" label-width="160">
              <el-select
                  v-model="data.form.reviewer"
                  placeholder="请选择审核"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="reviewerPct" label-width="100">
              <el-input
                  v-model.number="data.form.reviewerPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="reviewerProgress" label-width="100">
              <el-input
                  v-model.number="data.form.reviewerProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="reviewerFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.reviewerFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="reviewerIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.reviewerIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 压力管道审核 -->
          <el-row>
            <el-form-item label="压力管道审核" prop="pipeReview" label-width="160">
              <el-select
                  v-model="data.form.pipeReview"
                  placeholder="请选择压力管道审核"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="pipeReviewPct" label-width="100">
              <el-input
                  v-model.number="data.form.pipeReviewPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="pipeReviewProgress" label-width="100">
              <el-input
                  v-model.number="data.form.pipeReviewProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="pipeReviewFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.pipeReviewFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="pipeReviewIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.pipeReviewIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 审定 -->
          <el-row>
            <el-form-item label="审定" prop="approval" label-width="160">
              <el-select
                  v-model="data.form.approval"
                  placeholder="请选择审定"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="approvalPct" label-width="100">
              <el-input
                  v-model.number="data.form.approvalPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="approvalProgress" label-width="100">
              <el-input
                  v-model.number="data.form.approvalProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="approvalFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.approvalFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="approvalIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.approvalIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计指导 -->
          <el-row>
            <el-form-item label="设计指导" prop="designGuidance" label-width="160">
              <el-select
                  v-model="data.form.designGuidance"
                  placeholder="请选择设计指导"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="designGuidancePct" label-width="100">
              <el-input
                  v-model.number="data.form.designGuidancePct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="designGuidanceProgress" label-width="100">
              <el-input
                  v-model.number="data.form.designGuidanceProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="designGuidanceFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.designGuidanceFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="designGuidanceIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.designGuidanceIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 顾问 -->
          <el-row>
            <el-form-item label="顾问" prop="consultant" label-width="160">
              <el-select
                  v-model="data.form.consultant"
                  placeholder="请选择顾问"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="consultantPct" label-width="100">
              <el-input
                  v-model.number="data.form.consultantPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="consultantProgress" label-width="100">
              <el-input
                  v-model.number="data.form.consultantProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="consultantFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.consultantFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="consultantIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.consultantIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 采购支持 -->
          <el-row>
            <el-form-item label="采购支持" prop="procurementSupport" label-width="160">
              <el-select
                  v-model="data.form.procurementSupport"
                  placeholder="请选择采购支持"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="procurementSupportPct" label-width="100">
              <el-input
                  v-model.number="data.form.procurementSupportPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="procurementSupportProgress" label-width="100">
              <el-input
                  v-model.number="data.form.procurementSupportProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="procurementSupportFinalPct" label-width="160">
              <el-input
                  v-model.number="data.form.procurementSupportFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="procurementSupportIssuedAmount" label-width="200">
              <el-input
                  :value="data.form.procurementSupportIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>
          <!-- 设计1 -->
          <el-row>
            <el-form-item label="设计1" prop="design1" label-width="160">
              <el-select
                  v-model="data.form.design1"
                  placeholder="请选择设计1"
                  clearable
                  prefix-icon="Search"
                  filterable
                  remote
                  :remote-method="searchUsers"
                  :loading="userLoading" style="width: 100px;"
              >
                <el-option
                    v-for="user in userList"
                    :key="user.userId"
                    :label="user.nickName"
                    :value="user.nickName"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design1Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design1Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress1" label-width="100">
              <el-input
                  v-model.number="data.form.progress1"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final1Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final1Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design1IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design1IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计2 -->
          <el-row>
            <el-form-item label="设计2" prop="design2" label-width="160">
              <el-select
                  v-model="data.form.design2"
                  placeholder="请选择设计2"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design2Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design2Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress2" label-width="100">
              <el-input
                  v-model.number="data.form.progress2"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final2Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final2Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design2IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design2IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计3 -->
          <el-row>
            <el-form-item label="设计3" prop="design3" label-width="160">
              <el-select
                  v-model="data.form.design3"
                  placeholder="请选择设计3"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design3Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design3Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress3" label-width="100">
              <el-input
                  v-model.number="data.form.progress3"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final3Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final3Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design3IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design3IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计4 -->
          <el-row>
            <el-form-item label="设计4" prop="design4" label-width="160">
              <el-select
                  v-model="data.form.design4"
                  placeholder="请选择设计4"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design4Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design4Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress4" label-width="100">
              <el-input
                  v-model.number="data.form.progress4"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final4Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final4Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design4IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design4IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计5 -->
          <el-row>
            <el-form-item label="设计5" prop="design5" label-width="160">
              <el-select
                  v-model="data.form.design5"
                  placeholder="请选择设计5"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design5Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design5Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress5" label-width="100">
              <el-input
                  v-model.number="data.form.progress5"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final5Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final5Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design5IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design5IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计6 -->
          <el-row>
            <el-form-item label="设计6" prop="design6" label-width="160">
              <el-select
                  v-model="data.form.design6"
                  placeholder="请选择设计6"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design6Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design6Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress6" label-width="100">
              <el-input
                  v-model.number="data.form.progress6"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final6Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final6Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design6IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design6IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计7 -->
          <el-row>
            <el-form-item label="设计7" prop="design7" label-width="160">
              <el-select
                  v-model="data.form.design7"
                  placeholder="请选择设计7"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design7Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design7Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress7" label-width="100">
              <el-input
                  v-model.number="data.form.progress7"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final7Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final7Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design7IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design7IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计8 -->
          <el-row>
            <el-form-item label="设计8" prop="design8" label-width="160">
              <el-select
                  v-model="data.form.design8"
                  placeholder="请选择设计8"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design8Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design8Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress8" label-width="100">
              <el-input
                  v-model.number="data.form.progress8"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final8Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final8Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design8IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design8IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>

          <!-- 设计9 -->
          <el-row>
            <el-form-item label="设计9" prop="design9" label-width="160">
              <el-select
                  v-model="data.form.design9"
                  placeholder="请选择设计9"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design9Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design9Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress9" label-width="100">
              <el-input
                  v-model.number="data.form.progress9"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final9Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final9Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <!-- 注释掉的部分 -->
            <!-- <el-form-item label="本次来款按比例发放金额" prop="design9IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design9IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item> -->
          </el-row>



<!--           设计15
          <el-row>
            <el-form-item label="设计15" prop="design15" label-width="160">
              <el-select
                  v-model="data.form.design15"
                  placeholder="请选择设计15"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design15Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design15Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress15" label-width="100">
              <el-input
                  v-model.number="data.form.progress15"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final15Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final15Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
             注释掉的部分
             <el-form-item label="本次来款按比例发放金额" prop="design15IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design15IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
          </el-row>

           设计16
          <el-row>
            <el-form-item label="设计16" prop="design16" label-width="160">
              <el-select
                  v-model="data.form.design16"
                  placeholder="请选择设计16"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design16Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design16Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress16" label-width="100">
              <el-input
                  v-model.number="data.form.progress16"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final16Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final16Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
             注释掉的部分
             <el-form-item label="本次来款按比例发放金额" prop="design16IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design16IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
          </el-row>

           设计17
          <el-row>
            <el-form-item label="设计17" prop="design17" label-width="160">
              <el-select
                  v-model="data.form.design17"
                  placeholder="请选择设计17"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design17Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design17Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress17" label-width="100">
              <el-input
                  v-model.number="data.form.progress17"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final17Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final17Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
             注释掉的部分
             <el-form-item label="本次来款按比例发放金额" prop="design17IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design17IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
          </el-row>


           设计18
          <el-row>
            <el-form-item label="设计18" prop="design18" label-width="160">
              <el-select
                  v-model="data.form.design18"
                  placeholder="请选择设计18"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design18Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design18Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress18" label-width="100">
              <el-input
                  v-model.number="data.form.progress18"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final18Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final18Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
             注释掉的部分
             <el-form-item label="本次来款按比例发放金额" prop="design18IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design18IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
          </el-row>

           设计19
          <el-row>
            <el-form-item label="设计19" prop="design19" label-width="160">
              <el-select
                  v-model="data.form.design19"
                  placeholder="请选择设计19"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design19Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design19Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress19" label-width="100">
              <el-input
                  v-model.number="data.form.progress19"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final19Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final19Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
             注释掉的部分
             <el-form-item label="本次来款按比例发放金额" prop="design19IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design19IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
          </el-row>

           设计20
          <el-row>
            <el-form-item label="设计20" prop="design20" label-width="160">
              <el-select
                  v-model="data.form.design20"
                  placeholder="请选择设计20"
                  clearable
                prefix-icon="Search"
                filterable
                remote
                :remote-method="searchUsers"
                :loading="userLoading" style="width: 100px;"
            >
              <el-option
                  v-for="user in userList"
                  :key="user.userId"
                  :label="user.nickName"
                  :value="user.nickName"
              />
            </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="design20Pct" label-width="100">
              <el-input
                  v-model.number="data.form.design20Pct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="progress20" label-width="100">
              <el-input
                  v-model.number="data.form.progress20"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final20Pct" label-width="160">
              <el-input
                  v-model.number="data.form.final20Pct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
             注释掉的部分
             <el-form-item label="本次来款按比例发放金额" prop="design20IssuedAmount" label-width="200">
              <el-input
                  :value="data.form.design20IssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
          </el-row>-->




          <!--B-->
<!--

          <el-row>
            <el-form-item label="专业负责人" prop="proResp" label-width="100">
              <el-select
                  v-model="data.form.proResp"
                  placeholder="请选择专业负责人"
                  filterable
                  remote
                  :remote-method="filterNameList"
                  :loading="data.loading"  style="width: 100px;"
              >

                <el-option
                    v-for="name in data.filteredNames"
                    :key="name"
                    :label="name"
                    :value="name"
                />
              </el-select>

            </el-form-item>
            <el-form-item label="比例" prop="proRespPct" label-width="50">
              <el-input
                  v-model.number="data.form.proRespPct"
                  placeholder="%"
                  style="width: 50px;"

              />
            </el-form-item>
            <el-form-item label="进度" prop="proRespProgress" label-width="50">
              <el-input v-model.number="data.form.proRespProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="proRespFinalPct" label-width="120">
              <el-input
                  v-model.number="data.form.proRespFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="proRespMaxProgressAmount" label-width="140">
                          <el-input
                              :value="data.form.proRespMaxProgressAmount"
                              placeholder="元"
                              style="width: 70px;"
                              readonly
                          />
                        </el-form-item>
            <el-form-item label="本次来款按比例发放金额" prop="proRespIssuedAmount" label-width="100">
              <el-input
                  :value="data.form.proRespIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <el-form-item label="本次实发" prop="proRespActualAmount" label-width="100">
                          <el-input v-model.number="data.form.proRespActualAmount" placeholder="元" style="width: 70px;" />
                        </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="专业EIM" prop="eim" label-width="100">
              <el-select
                  v-model="data.form.eim"
                  placeholder="请选择专业EIM"
                  filterable
                  remote
                  :remote-method="filterNameList"
                  :loading="data.loading"  style="width: 100px;"
              >
                <el-option
                    v-for="name in data.filteredNames"
                    :key="name"
                    :label="name"
                    :value="name"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="比例" prop="eimPct" label-width="50">
              <el-input
                  v-model.number="data.form.eimPct"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度" prop="eimProgress" label-width="50">
              <el-input
                  v-model.number="data.form.eimProgress"
                  placeholder="%"
                  style="width: 50px;"
              />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="eimFinalPct" label-width="120">
              <el-input
                  v-model.number="data.form.eimFinalPct"
                  placeholder="%"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
            <el-form-item label="进度可发最大额" prop="eimMaxProgressAmount" label-width="140">
                          <el-input
                              :value="data.form.eimMaxProgressAmount"
                              placeholder="元"
                              style="width: 70px;"
                              readonly
                          />
                        </el-form-item>
            <el-form-item label="本次来款按比例发放金额" prop="eimIssuedAmount" label-width="100">
              <el-input
                  :value="data.form.eimIssuedAmount"
                  placeholder="元"
                  style="width: 70px;"
                  readonly
              />
            </el-form-item>
                        <el-form-item label="本次实发" prop="eimActualAmount" label-width="100">
                          <el-input
                              v-model.number="data.form.eimActualAmount"
                              placeholder="元"
                              style="width: 70px;"
                          />
                        </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="总体方案" prop="overallPlan" label-width="100">
              <el-input v-model="data.form.overallPlan" placeholder="请输入总体方案" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="overallPlanPct" label-width="50">
              <el-input v-model="data.form.overallPlanPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="overallPlanProgress" label-width="50">
              <el-input v-model="data.form.overallPlanProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="overallPlanFinalPct" label-width="120">
              <el-input v-model="data.form.overallPlanFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="overallPlanMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.overallPlanMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="overallPlanIssuedAmount" label-width="100">
              <el-input v-model="data.form.overallPlanIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="overallPlanActualAmount" label-width="100">
              <el-input v-model="data.form.overallPlanActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="校核" prop="checker" label-width="100">
              <el-input v-model="data.form.checker" placeholder="请输入校核" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="checkerPct" label-width="50">
              <el-input v-model="data.form.checkerPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="checkerProgress" label-width="50">
              <el-input v-model="data.form.checkerProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="checkerFinalPct" label-width="120">
              <el-input v-model="data.form.checkerFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="checkerMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.checkerMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="checkerIssuedAmount" label-width="100">
              <el-input v-model="data.form.checkerIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="checkerActualAmount" label-width="100">
              <el-input v-model="data.form.checkerActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="审核" prop="reviewer" label-width="100">
              <el-input v-model="data.form.reviewer" placeholder="请输入审核" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="reviewerPct" label-width="50">
              <el-input v-model="data.form.reviewerPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="reviewerProgress" label-width="50">
              <el-input v-model="data.form.reviewerProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="reviewerFinalPct" label-width="120">
              <el-input v-model="data.form.reviewerFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="reviewerMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.reviewerMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="reviewerIssuedAmount" label-width="100">
              <el-input v-model="data.form.reviewerIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="reviewerActualAmount" label-width="100">
              <el-input v-model="data.form.reviewerActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="压力管道审核" prop="pipeReview" label-width="100">
              <el-input v-model="data.form.pipeReview" placeholder="请输入压力管道审核" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="pipeReviewPct" label-width="50">
              <el-input v-model="data.form.pipeReviewPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="pipeReviewProgress" label-width="50">
              <el-input v-model="data.form.pipeReviewProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="pipeReviewFinalPct" label-width="120">
              <el-input v-model="data.form.pipeReviewFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="pipeReviewMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.pipeReviewMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="reviewerIssuedAmount" label-width="100">
              <el-input v-model="data.form.reviewerIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="pipeReviewActualAmount" label-width="100">
              <el-input v-model="data.form.pipeReviewActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="审定" prop="approval" label-width="100">
              <el-input v-model="data.form.approval" placeholder="请输入审定" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="approvalPct" label-width="50">
              <el-input v-model="data.form.approvalPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="approvalProgress" label-width="50">
              <el-input v-model="data.form.approvalProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="approvalFinalPct" label-width="120">
              <el-input v-model="data.form.approvalFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="approvalMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.approvalMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="approvalIssuedAmount" label-width="100">
              <el-input v-model="data.form.approvalIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="approvalActualAmount" label-width="100">
              <el-input v-model="data.form.approvalActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计指导" prop="designGuidance" label-width="100">
              <el-input v-model="data.form.designGuidance" placeholder="请输入设计指导" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="designGuidancePct" label-width="50">
              <el-input v-model="data.form.designGuidancePct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="designGuidanceProgress" label-width="50">
              <el-input v-model="data.form.designGuidanceProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="designGuidanceFinalPct" label-width="120">
              <el-input v-model="data.form.designGuidanceFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="designGuidanceMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.designGuidanceMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="designGuidanceIssuedAmount" label-width="100">
              <el-input v-model="data.form.designGuidanceIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="designGuidanceActualAmount" label-width="100">
              <el-input v-model="data.form.designGuidanceActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="顾问" prop="consultant" label-width="100">
              <el-input v-model="data.form.consultant" placeholder="请输入顾问" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="consultantPct" label-width="50">
              <el-input v-model="data.form.consultantPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="consultantProgress" label-width="50">
              <el-input v-model="data.form.consultantProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="consultantFinalPct" label-width="120">
              <el-input v-model="data.form.consultantFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="consultantMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.consultantMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="consultantIssuedAmount" label-width="100">
              <el-input v-model="data.form.consultantIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="consultantActualAmount" label-width="100">
              <el-input v-model="data.form.consultantActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="采购支持" prop="procurementSupport" label-width="100">
              <el-input v-model="data.form.procurementSupport" placeholder="请输入采购支持" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="procurementSupportPct" label-width="50">
              <el-input v-model="data.form.procurementSupportPct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="procurementSupportProgress" label-width="50">
              <el-input v-model="data.form.procurementSupportProgress" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="procurementSupportFinalPct" label-width="120">
              <el-input v-model="data.form.procurementSupportFinalPct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="procurementSupportMaxProgressAmount" label-width="140">
              <el-input v-model="data.form.procurementSupportMaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="procurementSupportIssuedAmount" label-width="100">
              <el-input v-model="data.form.procurementSupportIssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="procurementSupportActualAmount" label-width="100">
              <el-input v-model="data.form.procurementSupportActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-divider content-position="center" style="margin-left: -20px;">设计咨询区</el-divider>

          <el-row>
            <el-form-item label="设计1" prop="design1" label-width="100">
              <el-input v-model="data.form.design1" placeholder="请输入设计1" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design1Pct" label-width="50">
              <el-input v-model="data.form.design1Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress1" label-width="50">
              <el-input v-model="data.form.progress1" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final1Pct" label-width="120">
              <el-input v-model="data.form.final1Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design1MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design1MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design1IssuedAmount" label-width="100">
              <el-input v-model="data.form.design1IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design1ActualAmount" label-width="100">
              <el-input v-model="data.form.design1ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计2" prop="design2" label-width="100">
              <el-input v-model="data.form.design2" placeholder="请输入设计2" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design2Pct" label-width="50">
              <el-input v-model="data.form.design2Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress2" label-width="50">
              <el-input v-model="data.form.progress2" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final2Pct" label-width="120">
              <el-input v-model="data.form.final2Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design2MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design2MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design2IssuedAmount" label-width="100">
              <el-input v-model="data.form.design2IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design2ActualAmount" label-width="100">
              <el-input v-model="data.form.design2ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计3" prop="design3" label-width="100">
              <el-input v-model="data.form.design3" placeholder="请输入设计3" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design3Pct" label-width="50">
              <el-input v-model="data.form.design3Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress3" label-width="50">
              <el-input v-model="data.form.progress3" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final3Pct" label-width="120">
              <el-input v-model="data.form.final3Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design3MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design3MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design3IssuedAmount" label-width="100">
              <el-input v-model="data.form.design3IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design3ActualAmount" label-width="100">
              <el-input v-model="data.form.design3ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计4" prop="design4" label-width="100">
              <el-input v-model="data.form.design4" placeholder="请输入设计4" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design4Pct" label-width="50">
              <el-input v-model="data.form.design4Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress4" label-width="50">
              <el-input v-model="data.form.progress4" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final4Pct" label-width="120">
              <el-input v-model="data.form.final4Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design4MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design4MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design4IssuedAmount" label-width="100">
              <el-input v-model="data.form.design4IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design4ActualAmount" label-width="100">
              <el-input v-model="data.form.design4ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计5" prop="design5" label-width="100">
              <el-input v-model="data.form.design5" placeholder="请输入设计5" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design5Pct" label-width="50">
              <el-input v-model="data.form.design5Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress5" label-width="50">
              <el-input v-model="data.form.progress5" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final5Pct" label-width="120">
              <el-input v-model="data.form.final5Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design5MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design5MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design5IssuedAmount" label-width="100">
              <el-input v-model="data.form.design5IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design5ActualAmount" label-width="100">
              <el-input v-model="data.form.design5ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计6" prop="design6" label-width="100">
              <el-input v-model="data.form.design6" placeholder="请输入设计6" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design6Pct" label-width="50">
              <el-input v-model="data.form.design6Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress6" label-width="50">
              <el-input v-model="data.form.progress6" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final6Pct" label-width="120">
              <el-input v-model="data.form.final6Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design6MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design6MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design6IssuedAmount" label-width="100">
              <el-input v-model="data.form.design6IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design6ActualAmount" label-width="100">
              <el-input v-model="data.form.design6ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计7" prop="design7" label-width="100">
              <el-input v-model="data.form.design7" placeholder="请输入设计7" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design7Pct" label-width="50">
              <el-input v-model="data.form.design7Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress7" label-width="50">
              <el-input v-model="data.form.progress7" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final7Pct" label-width="120">
              <el-input v-model="data.form.final7Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design7MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design7MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design7IssuedAmount" label-width="100">
              <el-input v-model="data.form.design7IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design7ActualAmount" label-width="100">
              <el-input v-model="data.form.design7ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计8" prop="design8" label-width="100">
              <el-input v-model="data.form.design8" placeholder="请输入设计8" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design8Pct" label-width="50">
              <el-input v-model="data.form.design8Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress8" label-width="50">
              <el-input v-model="data.form.progress8" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final8Pct" label-width="120">
              <el-input v-model="data.form.final8Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design8MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design8MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design8IssuedAmount" label-width="100">
              <el-input v-model="data.form.design8IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design8ActualAmount" label-width="100">
              <el-input v-model="data.form.design8ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计9" prop="design9" label-width="100">
              <el-input v-model="data.form.design9" placeholder="请输入设计9" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design9Pct" label-width="50">
              <el-input v-model="data.form.design9Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress9" label-width="50">
              <el-input v-model="data.form.progress9" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final9Pct" label-width="120">
              <el-input v-model="data.form.final9Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design9MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design9MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design9IssuedAmount" label-width="100">
              <el-input v-model="data.form.design9IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design9ActualAmount" label-width="100">
              <el-input v-model="data.form.design9ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计10" prop="design10" label-width="100">
              <el-input v-model="data.form.design10" placeholder="请输入设计10" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design10Pct" label-width="50">
              <el-input v-model="data.form.design10Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress10" label-width="50">
              <el-input v-model="data.form.progress10" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final10Pct" label-width="120">
              <el-input v-model="data.form.final10Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design10MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design10MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design10IssuedAmount" label-width="100">
              <el-input v-model="data.form.design10IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design10ActualAmount" label-width="100">
              <el-input v-model="data.form.design10ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计11" prop="design11" label-width="100">
              <el-input v-model="data.form.design11" placeholder="请输入设计11" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design11Pct" label-width="50">
              <el-input v-model="data.form.design11Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress11" label-width="50">
              <el-input v-model="data.form.progress11" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final11Pct" label-width="120">
              <el-input v-model="data.form.final11Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design11MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design11MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design11IssuedAmount" label-width="100">
              <el-input v-model="data.form.design11IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design11ActualAmount" label-width="100">
              <el-input v-model="data.form.design11ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计12" prop="design12" label-width="100">
              <el-input v-model="data.form.design12" placeholder="请输入设计12" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design12Pct" label-width="50">
              <el-input v-model="data.form.design12Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress12" label-width="50">
              <el-input v-model="data.form.progress12" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final12Pct" label-width="120">
              <el-input v-model="data.form.final12Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design12MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design12MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design12IssuedAmount" label-width="100">
              <el-input v-model="data.form.design12IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design12ActualAmount" label-width="100">
              <el-input v-model="data.form.design12ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计13" prop="design13" label-width="100">
              <el-input v-model="data.form.design13" placeholder="请输入设计13" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design13Pct" label-width="50">
              <el-input v-model="data.form.design13Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress13" label-width="50">
              <el-input v-model="data.form.progress13" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final13Pct" label-width="120">
              <el-input v-model="data.form.final13Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design13MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design13MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design13IssuedAmount" label-width="100">
              <el-input v-model="data.form.design13IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design13ActualAmount" label-width="100">
              <el-input v-model="data.form.design13ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计14" prop="design14" label-width="100">
              <el-input v-model="data.form.design14" placeholder="请输入设计14" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design14Pct" label-width="50">
              <el-input v-model="data.form.design14Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress14" label-width="50">
              <el-input v-model="data.form.progress14" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final14Pct" label-width="120">
              <el-input v-model="data.form.final14Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design14MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design14MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design14IssuedAmount" label-width="100">
              <el-input v-model="data.form.design14IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design14ActualAmount" label-width="100">
              <el-input v-model="data.form.design14ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计15" prop="design15" label-width="100">
              <el-input v-model="data.form.design15" placeholder="请输入设计15" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design15Pct" label-width="50">
              <el-input v-model="data.form.design15Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress15" label-width="50">
              <el-input v-model="data.form.progress15" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final15Pct" label-width="120">
              <el-input v-model="data.form.final15Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design15MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design15MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design15IssuedAmount" label-width="100">
              <el-input v-model="data.form.design15IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design15ActualAmount" label-width="100">
              <el-input v-model="data.form.design15ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="设计16" prop="design16" label-width="100">
              <el-input v-model="data.form.design16" placeholder="请输入设计16" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design16Pct" label-width="50">
              <el-input v-model="data.form.design16Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress16" label-width="50">
              <el-input v-model="data.form.progress16" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final16Pct" label-width="120">
              <el-input v-model="data.form.final16Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design16MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design16MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design16IssuedAmount" label-width="100">
              <el-input v-model="data.form.design16IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design16ActualAmount" label-width="100">
              <el-input v-model="data.form.design16ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计17" prop="design17" label-width="100">
              <el-input v-model="data.form.design17" placeholder="请输入设计17" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design17Pct" label-width="50">
              <el-input v-model="data.form.design17Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress17" label-width="50">
              <el-input v-model="data.form.progress17" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final17Pct" label-width="120">
              <el-input v-model="data.form.final17Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design17MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design17MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design17IssuedAmount" label-width="100">
              <el-input v-model="data.form.design17IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design17ActualAmount" label-width="100">
              <el-input v-model="data.form.design17ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计18" prop="design18" label-width="100">
              <el-input v-model="data.form.design18" placeholder="请输入设计18" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design18Pct" label-width="50">
              <el-input v-model="data.form.design18Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress18" label-width="50">
              <el-input v-model="data.form.progress18" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final18Pct" label-width="120">
              <el-input v-model="data.form.final18Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design18MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design18MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design18IssuedAmount" label-width="100">
              <el-input v-model="data.form.design18IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design18ActualAmount" label-width="100">
              <el-input v-model="data.form.design18ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计19" prop="design19" label-width="100">
              <el-input v-model="data.form.design19" placeholder="请输入设计19" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design19Pct" label-width="50">
              <el-input v-model="data.form.design19Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress19" label-width="50">
              <el-input v-model="data.form.progress19" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final19Pct" label-width="120">
              <el-input v-model="data.form.final19Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design19MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design19MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design19IssuedAmount" label-width="100">
              <el-input v-model="data.form.design19IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design19ActualAmount" label-width="100">
              <el-input v-model="data.form.design19ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

          <el-row>
            <el-form-item label="设计20" prop="design20" label-width="100">
              <el-input v-model="data.form.design20" placeholder="请输入设计20" style="width: 100px;" />
            </el-form-item>
            <el-form-item label="比例" prop="design20Pct" label-width="50">
              <el-input v-model="data.form.design20Pct" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度" prop="progress20" label-width="50">
              <el-input v-model="data.form.progress20" placeholder="%" style="width: 50px;" />
            </el-form-item>
            <el-form-item label="进度最终比例" prop="final20Pct" label-width="120">
              <el-input v-model="data.form.final20Pct" placeholder="%" style="width: 70px;" />
            </el-form-item>

            <el-form-item label="进度可发最大额" prop="design20MaxProgressAmount" label-width="140">
              <el-input v-model="data.form.design20MaxProgressAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="已发放金额" prop="design20IssuedAmount" label-width="100">
              <el-input v-model="data.form.design20IssuedAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
            <el-form-item label="本次实发" prop="design20ActualAmount" label-width="100">
              <el-input v-model="data.form.design20ActualAmount" placeholder="元" style="width: 70px;" />
            </el-form-item>
          </el-row>

-->
          <el-collapse v-model="activeNames">
            <el-collapse-item name="design10To20" title="设计10-设计20">
              <!-- 设计10 -->
              <el-row>
                <el-form-item label="设计10" prop="design10" label-width="160">
                  <el-select v-model="data.form.design10" placeholder="请选择设计10" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design10Pct" label-width="100">
                  <el-input v-model.number="data.form.design10Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress10" label-width="100">
                  <el-input v-model.number="data.form.progress10" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final10Pct" label-width="160">
                  <el-input v-model.number="data.form.final10Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计11 -->
              <el-row>
                <el-form-item label="设计11" prop="design11" label-width="160">
                  <el-select v-model="data.form.design11" placeholder="请选择设计11" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design11Pct" label-width="100">
                  <el-input v-model.number="data.form.design11Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress11" label-width="100">
                  <el-input v-model.number="data.form.progress11" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final11Pct" label-width="160">
                  <el-input v-model.number="data.form.final11Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计12 -->
              <el-row>
                <el-form-item label="设计12" prop="design12" label-width="160">
                  <el-select v-model="data.form.design12" placeholder="请选择设计12" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design12Pct" label-width="100">
                  <el-input v-model.number="data.form.design12Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress12" label-width="100">
                  <el-input v-model.number="data.form.progress12" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final12Pct" label-width="160">
                  <el-input v-model.number="data.form.final12Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计13 -->
              <el-row>
                <el-form-item label="设计13" prop="design13" label-width="160">
                  <el-select v-model="data.form.design13" placeholder="请选择设计13" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design13Pct" label-width="100">
                  <el-input v-model.number="data.form.design13Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress13" label-width="100">
                  <el-input v-model.number="data.form.progress13" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final13Pct" label-width="160">
                  <el-input v-model.number="data.form.final13Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计14 -->
              <el-row>
                <el-form-item label="设计14" prop="design14" label-width="160">
                  <el-select v-model="data.form.design14" placeholder="请选择设计14" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design14Pct" label-width="100">
                  <el-input v-model.number="data.form.design14Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress14" label-width="100">
                  <el-input v-model.number="data.form.progress14" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final14Pct" label-width="160">
                  <el-input v-model.number="data.form.final14Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计15 -->
              <el-row>
                <el-form-item label="设计15" prop="design15" label-width="160">
                  <el-select v-model="data.form.design15" placeholder="请选择设计15" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design15Pct" label-width="100">
                  <el-input v-model.number="data.form.design15Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress15" label-width="100">
                  <el-input v-model.number="data.form.progress15" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final15Pct" label-width="160">
                  <el-input v-model.number="data.form.final15Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计16 -->
              <el-row>
                <el-form-item label="设计16" prop="design16" label-width="160">
                  <el-select v-model="data.form.design16" placeholder="请选择设计16" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design16Pct" label-width="100">
                  <el-input v-model.number="data.form.design16Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress16" label-width="100">
                  <el-input v-model.number="data.form.progress16" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final16Pct" label-width="160">
                  <el-input v-model.number="data.form.final16Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计17 -->
              <el-row>
                <el-form-item label="设计17" prop="design17" label-width="160">
                  <el-select v-model="data.form.design17" placeholder="请选择设计17" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design17Pct" label-width="100">
                  <el-input v-model.number="data.form.design17Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress17" label-width="100">
                  <el-input v-model.number="data.form.progress17" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final17Pct" label-width="160">
                  <el-input v-model.number="data.form.final17Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计18 -->
              <el-row>
                <el-form-item label="设计18" prop="design18" label-width="160">
                  <el-select v-model="data.form.design18" placeholder="请选择设计18" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design18Pct" label-width="100">
                  <el-input v-model.number="data.form.design18Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress18" label-width="100">
                  <el-input v-model.number="data.form.progress18" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final18Pct" label-width="160">
                  <el-input v-model.number="data.form.final18Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计19 -->
              <el-row>
                <el-form-item label="设计19" prop="design19" label-width="160">
                  <el-select v-model="data.form.design19" placeholder="请选择设计19" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design19Pct" label-width="100">
                  <el-input v-model.number="data.form.design19Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress19" label-width="100">
                  <el-input v-model.number="data.form.progress19" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final19Pct" label-width="160">
                  <el-input v-model.number="data.form.final19Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>

              <!-- 设计20 -->
              <el-row>
                <el-form-item label="设计20" prop="design20" label-width="160">
                  <el-select v-model="data.form.design20" placeholder="请选择设计20" clearable
                             prefix-icon="Search"
                             filterable
                             remote
                             :remote-method="searchUsers"
                             :loading="userLoading" style="width: 100px;"
                  >
                    <el-option
                        v-for="user in userList"
                        :key="user.userId"
                        :label="user.nickName"
                        :value="user.nickName"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item label="比例" prop="design20Pct" label-width="100">
                  <el-input v-model.number="data.form.design20Pct" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度" prop="progress20" label-width="100">
                  <el-input v-model.number="data.form.progress20" placeholder="%" style="width: 50px;"/>
                </el-form-item>
                <el-form-item label="进度最终比例" prop="final20Pct" label-width="160">
                  <el-input v-model.number="data.form.final20Pct" placeholder="%" style="width: 70px;" readonly/>
                </el-form-item>
              </el-row>
            </el-collapse-item>
          </el-collapse>






          <el-form-item label="备注" prop="remark">
          <el-input v-model="data.form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button><!--对话框的确定按钮-->

          <el-button @click="cancel">取 消</el-button><!--对话框的取消按钮-->
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="ProfessionalAllocationB">
import { ref, reactive, toRefs } from 'vue';
import {getCommonNameList} from "@/api/business/ProfessionalAllocationB";
import {getProfessionalAllocation} from "@/api/business/ProfessionalAllocation";
import { listProfessionalAllocationB, getProfessionalAllocationB, delProfessionalAllocationB, addProfessionalAllocationB, updateProfessionalAllocationB } from "@/api/business/ProfessionalAllocationB";
import { selectProfessionalAllocationB,useProjectStore } from "@/api/business/ProfessionalAllocation";
import { useRoute } from "vue-router";
import { ElDivider } from 'element-plus';

import { ElMessage, ElMessageBox } from 'element-plus';

const route = useRoute();


const { proxy } = getCurrentInstance() || {};
if (!proxy) {
  console.error("getCurrentInstance 返回 null，请检查组件是否正确初始化");
}

const ProfessionalAllocationBList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
import { watch } from 'vue'


function processFormFields() {
  const form = data.form

  for (const key in form) {
    // 判断字段是否是“比例”或“进度”，但不是“最终比例”
    if ((key.endsWith('Pct') || key.endsWith('Progress')) && !key.endsWith('FinalPct')) {
      // 如果值为 null，则设为 0
      if (form[key] === null) {
        form[key] = 0
      }
    }
  }
}
  function filterNameList(query) {
    if (!query || query.trim() === "") {
      data.filteredNames = data.commonNameList;
    } else {
      data.filteredNames = data.commonNameList.filter(name =>
          String(name).toLowerCase().includes(query.trim().toLowerCase())
      );
    }
  }




function validatePercentage() {
  // 获取所有比例字段
  const percentageFields = [
    form.value.proRespPct,
    form.value.eimPct,
    form.value.overallPlanPct,
    form.value.checkerPct,
    form.value.reviewerPct,
    form.value.pipeReviewPct,
    form.value.approvalPct,
    form.value.designGuidancePct,
    form.value.consultantPct,
    form.value.procurementSupportPct,
    form.value.design1Pct,
    form.value.design2Pct,
    form.value.design3Pct,
    form.value.design4Pct,
    form.value.design5Pct,
    form.value.design6Pct,
    form.value.design7Pct,
    form.value.design8Pct,
    form.value.design9Pct,
    form.value.design10Pct,
    form.value.design11Pct,
    form.value.design12Pct,
    form.value.design13Pct,
    form.value.design14Pct,
    form.value.design15Pct,
    form.value.design16Pct,
    form.value.design17Pct,
    form.value.design18Pct,
    form.value.design19Pct,
    form.value.design20Pct
  ];

  // 过滤掉未填写的比例（null、undefined、空字符串）
  const allPercentages = percentageFields.filter(p => p !== null && p !== undefined && p !== '');

  // 检查单个比例是否在 0-100 范围内
  for (const pct of allPercentages) {
    if (pct < 0 || pct > 100) {
      ElMessageBox.alert(`有的比例超出 0-100 范围`, "校验错误", {
        confirmButtonText: "确定",
        type: "error"
      });
      return false;
    }
  }

  // 计算所有比例的总和
  const total = allPercentages.reduce((sum, pct) => sum + Number(pct), 0);

  // 检查总和是否在 100 ± 0.1 之间
  if (Math.abs(total - 100) > 0.1) {
    ElMessageBox.alert(`所有比例总和需等于100，当前总和为 ${total.toFixed(2)}`, "校验错误", {
      confirmButtonText: "确定",
      type: "error"
    });
    return false;
  }

  return true; // 校验通过
}




function validateProgress(rules, value, callback) {


  const fieldName = rules.field;

  // 检查是否为空
  if (value === null || value === undefined || value === '') {
    callback(new Error('进度不能为空'));
    return;
  }

  // 检查是否为有效数字
  if (typeof value !== 'number') {
    callback(new Error('请输入有效的数字'));
    return;
  }

/*  // 检查范围是否在 0-100 之间
  if (value < 0 || value > 100) {
    callback(new Error('进度需在0-100之间'));
    return;
  }*/

  // 获取初始值
  const initialValue = data.initialProgress[fieldName];


  // // 如果初始值存在且当前值小于初始值，则报错
  // if (initialValue !== null && initialValue !== undefined && value < initialValue) {
  //   callback(new Error('进度不能减少'));
  //   return;
  // }

  // 验证通过后，更新初始值
  data.initialProgress[fieldName] = value;

  callback();
}




async function loadData() {
  if (data.isDataLoaded) return;

  data.loading = true;
  try {
    const response = await getCommonNameList(queryParams);

    if (Array.isArray(response.data)) {
      data.commonNameList = response.data; // 假设API返回对象需提取name字段
    }
    data.isDataLoaded = true;
  } catch (error) {
    console.error("加载名称列表失败：", error);
  } finally {
    data.loading = false;
  }
}


const data = reactive({

  form:reactive( {

  }),
  initialProgress: {}, // 存储初始表单进度数据，存旧的数据，防止新数据小于旧数据的时候，有个阻止
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    itemNo: null,
    itemName: null,
    prjId: route.query.prjId || null,
    profession: route.query.profession || null,
    headId: route.query.headId || null // 新增 headId 参数
  },
  filteredNames: [], // 用于存储过滤后的名称列表
  commonNameList: [], // 通用名称列表
  isDataLoaded: false, // 数据是否已加载
  loading: false, // 全局加载状态

  queryParams2: {
    prjId: route.params.prjId, // 项目ID，由路由传递
    profession: null,
    proLeader: null
  },
  projPct: null, // 项目设计占比

  rules: {
      itemNo: [
        { required: true, message: "子项编号不能为空", trigger: "blur" }
      ],
      itemName: [
        { required: true, message: "子项名称不能为空", trigger: "blur" }
      ],


    proRespPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    eimPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    overallPlanPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    checkerPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    reviewerPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    pipeReviewPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    approvalPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    designGuidancePct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    consultantPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    procurementSupportPct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design1Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design2Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design3Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design4Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design5Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design6Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design7Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design8Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design9Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design10Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design11Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design12Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design13Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design14Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design15Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design16Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design17Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design18Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design19Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],
    design20Pct: [
      { required: true, message: '请输入比例', trigger: 'change' },
      { type: 'number', min: 0, max: 100, message: '比例需在0-100之间', trigger: 'change' }
    ],



      proRespProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      eimProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      overallPlanProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      checkerProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      reviewerProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      pipeReviewProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      approvalProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      designGuidanceProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      consultantProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      procurementSupportProgress: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      // 设计1-20进度
      progress1: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress2: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress3: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress4: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress5: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress6: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress7: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress8: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress9: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress10: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress11: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress12: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress13: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress14: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress15: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress16: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress17: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress18: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress19: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ],
      progress20: [
        { required: true, message: '请输入进度', trigger: 'blur' },
        { type: 'number', min: 0, max: 100, message: '进度需在0-100之间', trigger: 'blur' },
        { validator: validateProgress, trigger: 'blur' }
      ]


  }
});



const {  form, rules,initialProgress } = toRefs(data);
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  prjId: route.query.prjId, // 项目ID，由路由传递

  profession: route.query.profession,// 项目ID，由路由传递
  headId:route.query.headId // 从主表获取 headId（即 tb_professional_allocation.id）
});




import { getProjPct } from "@/api/business/DesignRatio";




// 方法定义
// 计算单个字段的最终比例
async function calculateSingleFinalPct(field) {
  if(data.queryParams2.profession == null && data.queryParams2.prjId  === undefined ) {
    data.queryParams2.profession = route.query.profession || '';
    data.queryParams2.prjId = route.query.prjId || '';
  }
    const { pct, progress, final } = field;
    if(data.projPct == null )
    {
      try {
        const projPct = await getProjPct(data.queryParams2.prjId, data.queryParams2.profession);//宁总的代码
        data.projPct = projPct.data;

        // 初始化所有字段的最终比例
        fields.forEach(field => calculateSingleFinalPct(field));
      } catch (error) {
        console.error("Failed to fetch project percentage:", error);
      }
    }
    // 确保所需字段存在且有效
    if (
        form.value[pct] !== undefined &&
        form.value[progress] !== undefined &&
        data.projPct !== null &&
        form.value.itemWeight !== undefined
    ){
      // 计算公式
      const finalPctValue = (
          (data.projPct * form.value.itemWeight * form.value[pct] )/
          1000000
      ).toFixed(5) * 100;

      // 更新最终比例
      data.form[final] = finalPctValue;
    }

}
   /* async function calculateFinalPct(){
      //不要触发的三计算
      try {
        const projPct = await getProjPct(this.form.prjId, this.form.profession);
        this.data.projPct = projPct.data;
      } catch (error) {
        console.error("Failed to fetch project percentage:", error);
      }

        // 计算专业负责人的最终比例
        this.data.form.proRespFinalPct = (this.data.projPct * this.form.itemWeight * this.form.proRespPct * this.form.proRespProgress / 1000000).toFixed(2) * 100;

        // 计算EIM的最终比例
        this.data.form.eimFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.eimPct * this.data.form.eimProgress / 1000000).toFixed(2) * 100;

        // 计算总体方案的最终比例
        this.data.form.overallPlanFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.overallPlanPct * this.data.form.overallPlanProgress / 1000000).toFixed(2) * 100;

        // 计算校核的最终比例
        this.data.form.checkerFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.checkerPct * this.data.form.checkerProgress / 1000000).toFixed(2) * 100;

        // 计算审核的最终比例
        this.data.form.reviewerFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.reviewerPct * this.data.form.reviewerProgress / 1000000).toFixed(2) * 100;

        // 计算压力管道审核的最终比例
        this.data.form.pipeReviewFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.pipeReviewPct * this.data.form.pipeReviewProgress / 1000000).toFixed(2) * 100;

        // 计算审定的最终比例
        this.data.form.approvalFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.approvalPct * this.data.form.approvalProgress / 1000000).toFixed(2) * 100;

        // 计算设计指导的最终比例
        this.data.form.designGuidanceFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.designGuidancePct * this.data.form.designGuidanceProgress / 1000000).toFixed(2) * 100;

        // 计算顾问的最终比例
        this.data.form.consultantFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.consultantPct * this.data.form.consultantProgress / 1000000).toFixed(2) * 100;

        // 计算采购支持的最终比例
        this.data.form.procurementSupportFinalPct = (this.data.projPct * this.form.itemWeight * this.data.form.procurementSupportPct * this.data.form.procurementSupportProgress / 1000000).toFixed(2) * 100;

        // 计算设计1到设计20的最终比例
        this.data.form.final1Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design1Pct * this.data.form.progress1 / 1000000).toFixed(2) * 100;
        this.data.form.final2Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design2Pct * this.data.form.progress2 / 1000000).toFixed(2) * 100;
        this.data.form.final3Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design3Pct * this.data.form.progress3 / 1000000).toFixed(2) * 100;
        this.data.form.final4Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design4Pct * this.data.form.progress4 / 1000000).toFixed(2) * 100;
        this.data.form.final5Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design5Pct * this.data.form.progress5 / 1000000).toFixed(2) * 100;
        this.data.form.final6Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design6Pct * this.data.form.progress6 / 1000000).toFixed(2) * 100;
        this.data.form.final7Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design7Pct * this.data.form.progress7 / 1000000).toFixed(2) * 100;
        this.data.form.final8Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design8Pct * this.data.form.progress8 / 1000000).toFixed(2) * 100;
        this.data.form.final9Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design9Pct * this.data.form.progress9 / 1000000).toFixed(2) * 100;
        this.data.form.final10Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design10Pct * this.data.form.progress10 / 1000000).toFixed(2) * 100;
        this.data.form.final11Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design11Pct * this.data.form.progress11 / 1000000).toFixed(2) * 100;
        this.data.form.final12Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design12Pct * this.data.form.progress12 / 1000000).toFixed(2) * 100;
        this.data.form.final13Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design13Pct * this.data.form.progress13 / 1000000).toFixed(2) * 100;
        this.data.form.final14Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design14Pct * this.data.form.progress14 / 1000000).toFixed(2) * 100;
        this.data.form.final15Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design15Pct * this.data.form.progress15 / 1000000).toFixed(2) * 100;
        this.data.form.final16Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design16Pct * this.data.form.progress16 / 1000000).toFixed(2) * 100;
        this.data.form.final17Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design17Pct * this.data.form.progress17 / 1000000).toFixed(2) * 100;
        this.data.form.final18Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design18Pct * this.data.form.progress18 / 1000000).toFixed(2) * 100;
        this.data.form.final19Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design19Pct * this.data.form.progress19 / 1000000).toFixed(2) * 100;
        this.data.form.final20Pct = (this.data.projPct * this.form.itemWeight * this.data.form.design20Pct * this.data.form.progress20 / 1000000).toFixed(2) * 100;


      }
*/


// 监听器配置
// 动态生成 watch 监听器
const fields = [
  { key: 'proResp', pct: 'proRespPct', progress: 'proRespProgress', final: 'proRespFinalPct' },
  { key: 'eim', pct: 'eimPct', progress: 'eimProgress', final: 'eimFinalPct' },
  { key: 'overallPlan', pct: 'overallPlanPct', progress: 'overallPlanProgress', final: 'overallPlanFinalPct' },
  { key: 'checker', pct: 'checkerPct', progress: 'checkerProgress', final: 'checkerFinalPct' },
  { key: 'reviewer', pct: 'reviewerPct', progress: 'reviewerProgress', final: 'reviewerFinalPct' },
  { key: 'pipeReview', pct: 'pipeReviewPct', progress: 'pipeReviewProgress', final: 'pipeReviewFinalPct' },
  { key: 'approval', pct: 'approvalPct', progress: 'approvalProgress', final: 'approvalFinalPct' },
  { key: 'designGuidance', pct: 'designGuidancePct', progress: 'designGuidanceProgress', final: 'designGuidanceFinalPct' },
  { key: 'consultant', pct: 'consultantPct', progress: 'consultantProgress', final: 'consultantFinalPct' },
  { key: 'procurementSupport', pct: 'procurementSupportPct', progress: 'procurementSupportProgress', final: 'procurementSupportFinalPct' }
];

// 动态添加设计1-20的字段
for (let i = 1; i <= 20; i++) {
  fields.push({
    key: `design${i}`,
    pct: `design${i}Pct`,
    progress: `progress${i}`,
    final: `final${i}Pct`
  });
}

// 创建监听器
fields.forEach(field => {
  // 监听比例变化
  watch(
      () => form.value[field.pct],
      () => calculateSingleFinalPct(field)
  );

  //修改宁总代码，在新增子项时不需判断比例和进度是否为空
  /*watch(
      () => form.value[field.pct],
      (newVal) => {
        if (newVal !== null && newVal !== undefined) {
          calculateSingleFinalPct(field);
        }
      }
  );*/

  // 监听进度变化
  watch(
      () => form.value[field.progress],
      () => calculateSingleFinalPct(field)
  );

  //修改宁总代码,在新增子项时不需判断比例和进度是否为空
  /*watch(
      () => form.value[field.progress],
      (newVal) => {
        if (newVal !== null && newVal !== undefined) {
          calculateSingleFinalPct(field);
        }
      }
  )*/
});

/*
watch(
    () => form.value.proRespPct,
    () => calculateFinalPct(),
    { immediate: false }
);

watch(
    () => form.value.proRespProgress,
    () => calculateFinalPct(),
    { immediate: false }
);
*/

let getListSignal=false;

/** 查询专业负责人策划子表列表 */
function getList() {
  loading.value = true;
  selectProfessionalAllocationB(queryParams).then(response => {
    ProfessionalAllocationBList.value = response.rows;

    total.value = response.total;
    loading.value = false;
    getListSignal=true;
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
  // 存储初始进度数据
  initialProgress.value = {
    proRespProgress: form.value.proRespProgress,
    eimProgress: form.value.eimProgress,
    overallPlanProgress: form.value.overallPlanProgress,
    checkerProgress: form.value.checkerProgress,
    reviewerProgress: form.value.reviewerProgress,
    pipeReviewProgress: form.value.pipeReviewProgress,
    approvalProgress: form.value.approvalProgress,
    designGuidanceProgress: form.value.designGuidanceProgress,
    consultantProgress: form.value.consultantProgress,
    procurementSupportProgress: form.value.procurementSupportProgress,
    progress1: form.value.progress1,
    progress2: form.value.progress2,
    progress3: form.value.progress3,
    progress4: form.value.progress4,
    progress5: form.value.progress5,
    progress6: form.value.progress6,
    progress7: form.value.progress7,
    progress8: form.value.progress8,
    progress9: form.value.progress9,
    progress10: form.value.progress10,
    progress11: form.value.progress11,
    progress12: form.value.progress12,
    progress13: form.value.progress13,
    progress14: form.value.progress14,
    progress15: form.value.progress15,
    progress16: form.value.progress16,
    progress17: form.value.progress17,
    progress18: form.value.progress18,
    progress19: form.value.progress19,
    progress20: form.value.progress20
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

const projectStore = useProjectStore();
onMounted(() => {
  loadData();
});

/** 新增按钮操作 */
function handleAdd() {
  reset();
  loadData(); // 加载名称列表
  open.value = true;
  title.value = "添加专业负责人策划子表";
  //自动填充项目ID和主表id
  form.value.prjId = route.query.prjId ;
  form.value.headId = projectStore.headId;
}

  /** 修改按钮操作 */
 async function handleUpdate(row) {


        // 从 row 中获取 headId 参数
        const headId = row.headId;
        // 调用 API 获取专业名和专业负责人
       await getProfessionalAllocation(headId).then(async response => {
          // 将获取到的数据赋值给 professionGet 和 proLeaderGet
          data.professionGet = response.data.profession;


          // 将数据赋值给 queryParams2
          /*data.queryParams2 = { 宁总的代码
            prjId: row.prjId, // 项目ID
            profession: data.professionGet, // 专业
          };*/
          data.queryParams2 = {
            prjId: '', // 项目ID
            profession: null, // 专业
          };



        }).catch(error => {
          console.error("获取专业负责人和专业名失败：", error);
          proxy.$modal.msgError("获取专业负责人和专业名失败，请重试");
        });

        loadData(); // 加载名称列表


        const _id = row.id || ids.value


        //后台取数，存进度初始值
        getProfessionalAllocationB(_id).then(response => {
          form.value = response.data;
          // 同步更新 initialProgress.value
          initialProgress.value = {
            proRespProgress: form.value.proRespProgress,
            eimProgress: form.value.eimProgress,
            overallPlanProgress: form.value.overallPlanProgress,
            checkerProgress: form.value.checkerProgress,
            reviewerProgress: form.value.reviewerProgress,
            pipeReviewProgress: form.value.pipeReviewProgress,
            approvalProgress: form.value.approvalProgress,
            designGuidanceProgress: form.value.designGuidanceProgress,
            consultantProgress: form.value.consultantProgress,
            procurementSupportProgress: form.value.procurementSupportProgress,
            progress1: form.value.progress1,
            progress2: form.value.progress2,
            progress3: form.value.progress3,
            progress4: form.value.progress4,
            progress5: form.value.progress5,
            progress6: form.value.progress6,
            progress7: form.value.progress7,
            progress8: form.value.progress8,
            progress9: form.value.progress9,
            progress10: form.value.progress10,
            progress11: form.value.progress11,
            progress12: form.value.progress12,
            progress13: form.value.progress13,
            progress14: form.value.progress14,
            progress15: form.value.progress15,
            progress16: form.value.progress16,
            progress17: form.value.progress17,
            progress18: form.value.progress18,
            progress19: form.value.progress19,
            progress20: form.value.progress20
          };


          open.value = true;
          processFormFields(); // 数据加载完成后执行处理
          title.value = "修改专业负责人策划子表";
        });
  }

/** 提交按钮 */
function submitForm() {
  if (!validatePercentage()) {
    return; // 校验失败，阻止提交
  }


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

// 计算各子项进度
function calculateSubItemProgress(row) {
  if (!row) return '0.00';

  let total = 0;

  // 所有需要计算的字段对
  const fieldPairs = [
    // 专业负责人相关
    { pct: 'proRespPct', progress: 'proRespProgress' },
    { pct: 'eimPct', progress: 'eimProgress' },
    { pct: 'overallPlanPct', progress: 'overallPlanProgress' },
    { pct: 'checkerPct', progress: 'checkerProgress' },
    { pct: 'reviewerPct', progress: 'reviewerProgress' },
    { pct: 'pipeReviewPct', progress: 'pipeReviewProgress' },
    { pct: 'approvalPct', progress: 'approvalProgress' },
    { pct: 'designGuidancePct', progress: 'designGuidanceProgress' },
    { pct: 'consultantPct', progress: 'consultantProgress' },
    { pct: 'procurementSupportPct', progress: 'procurementSupportProgress' },

    // 设计1~设计20
    ...Array.from({ length: 20 }, (_, i) => ({
      pct: `design${i + 1}Pct`,
      progress: `progress${i + 1}`
    }))
  ];

  // 遍历所有字段对
  for (const pair of fieldPairs) {
    const pct = parseFloat(row[pair.pct]) || 0;
    const progress = parseFloat(row[pair.progress]) || 0;

    total += pct * progress/100;
  }

  return total.toFixed(2) + '%';
}

/** 实现输入用户名称时实时搜索，laizhiying的方式 start*/
import { listUser } from "@/api/system/user";
//import {reactive} from "vue";
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

// 监听路由参数变化
watch(
    () => route.query,
    (newQuery) => {
      if (newQuery.openDialog) {
        if (newQuery.id) {
          // 如果有 id，执行修改操作
          handleUpdate({ id: newQuery.id });
        } else {
          // 否则执行新增操作
          handleAdd();
        }
      }
    },
    { immediate: true, deep: true }
);

getList();
</script>
