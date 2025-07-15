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
        <el-button type="primary" plain @click="handleAdd" v-hasPermi="['business:ProfessionalAllocationB:add']">
        新增专业
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleOpenUpdate" v-hasPermi="['business:ProfessionalAllocationB:edit']">
          修改专业
        </el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['business:ProfessionalAllocationB:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['business:ProfessionalAllocationB:export']"
        >导出
        </el-button>
      </el-col>-->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ProfessionalAllocationBList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" type="index" width="50" align="center" prop="id"/>
      <el-table-column label="子项编号" align="center" prop="itemNo"/>
      <el-table-column label="各子项进度" align="center" width="100"/>
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true">
        <template #default="scope">
          {{ route.query.projectName }}
        </template>
      </el-table-column>
      <el-table-column label="专业" align="center" prop="profession">
        <template #default="scope">
          {{ scope.row.profession || '-' }}
        </template>
      </el-table-column>
      <el-table-column label="子项名称" align="center" prop="itemName" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="子项权重" align="center" prop="itemWeight"/>
      <el-table-column label="专业负责人" align="center" prop="proResp"/>
      <el-table-column label="EIM" align="center" prop="eim"/>
      <el-table-column label="总体方案" align="center" prop="overallPlan"/>
      <el-table-column label="校核" align="center" prop="checker"/>
      <el-table-column label="审核" align="center" prop="reviewer"/>
      <el-table-column label="压力管道审核" align="center" prop="pipeReview"/>
      <el-table-column label="审定" align="center" prop="approval"/>
      <el-table-column label="设计指导" align="center" prop="designGuidance"/>
      <el-table-column label="子项名称" align="center" prop="itemName" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="子项权重" align="center" prop="itemWeight"/>
      <el-table-column label="专业负责人" align="center" prop="proResp" width="100"/>
      <el-table-column label="EIM" align="center" prop="eim"/>
      <el-table-column label="总体方案" align="center" prop="overallPlan"/>
      <el-table-column label="校核" align="center" prop="checker"/>
      <el-table-column label="审核" align="center" prop="reviewer"/>
      <el-table-column label="压力管道审核" align="center" prop="pipeReview" width="110"/>
      <el-table-column label="审定" align="center" prop="approval"/>
      <el-table-column label="设计指导" align="center" prop="designGuidance"/>
      <el-table-column label="设计1" align="center" prop="design1"/>
      <el-table-column label="设计2" align="center" prop="design2"/>
      <el-table-column label="设计3" align="center" prop="design3"/>
      <el-table-column label="设计4" align="center" prop="design4"/>
      <el-table-column label="设计5" align="center" prop="design5"/>
      <el-table-column label="设计6" align="center" prop="design6"/>
      <el-table-column label="设计7" align="center" prop="design7"/>
      <el-table-column label="设计8" align="center" prop="design8"/>
      <el-table-column label="设计9" align="center" prop="design9"/>
      <el-table-column label="设计10" align="center" prop="design10"/>
      <el-table-column label="设计11" align="center" prop="design11"/>
      <el-table-column label="设计12" align="center" prop="design12"/>
      <el-table-column label="设计13" align="center" prop="design13"/>
      <el-table-column label="设计14" align="center" prop="design14"/>
      <el-table-column label="设计15" align="center" prop="design15"/>
      <el-table-column label="设计16" align="center" prop="design16"/>
      <el-table-column label="设计17" align="center" prop="design17"/>
      <el-table-column label="设计18" align="center" prop="design18"/>
      <el-table-column label="设计19" align="center" prop="design19"/>
      <el-table-column label="设计20" align="center" prop="design20"/>
      <el-table-column label="采购支持" align="center" prop="procurementSupport"/>
      <el-table-column label="顾问" align="center" prop="consultant"/>
      <el-table-column label="专业负责人" align="center" prop="proRespPct"/>
      <el-table-column label="EIM" align="center" prop="eimPct"/>
      <el-table-column label="总体方案" align="center" prop="overallPlanPct"/>
      <el-table-column label="校核" align="center" prop="checkerPct"/>
      <el-table-column label="审核" align="center" prop="reviewerPct"/>
      <el-table-column label="压力管道审核" align="center" prop="pipeReviewPct"/>
      <el-table-column label="审定" align="center" prop="approvalPct"/>
      <el-table-column label="设计指导" align="center" prop="designGuidancePct"/>
      <el-table-column label="设计1比例" align="center" prop="design1Pct"/>
      <el-table-column label="设计2比例" align="center" prop="design2Pct"/>
      <el-table-column label="设计3比例" align="center" prop="design3Pct"/>
      <el-table-column label="设计4比例" align="center" prop="design4Pct"/>
      <el-table-column label="设计5比例" align="center" prop="design5Pct"/>
      <el-table-column label="设计6比例" align="center" prop="design6Pct"/>
      <el-table-column label="设计7比例" align="center" prop="design7Pct"/>
      <el-table-column label="设计8比例" align="center" prop="design8Pct"/>
      <el-table-column label="设计9比例" align="center" prop="design9Pct"/>
      <el-table-column label="设计10比例" align="center" prop="design10Pct"/>
      <el-table-column label="设计11比例" align="center" prop="design11Pct"/>
      <el-table-column label="设计12比例" align="center" prop="design12Pct"/>
      <el-table-column label="设计13比例" align="center" prop="design13Pct"/>
      <el-table-column label="设计14比例" align="center" prop="design14Pct"/>
      <el-table-column label="设计15比例" align="center" prop="design15Pct"/>
      <el-table-column label="设计16比例" align="center" prop="design16Pct"/>
      <el-table-column label="设计17比例" align="center" prop="design17Pct"/>
      <el-table-column label="设计18比例" align="center" prop="design18Pct"/>
      <el-table-column label="设计19比例" align="center" prop="design19Pct"/>
      <el-table-column label="设计20比例" align="center" prop="design20Pct"/>
      <el-table-column label="采购支持" align="center" prop="procurementSupportPct"/>
      <el-table-column label="顾问" align="center" prop="consultantPct"/>
      <el-table-column label="设计1最终比例" align="center" prop="final1Pct"/>
      <el-table-column label="设计2最终比例" align="center" prop="final2Pct"/>
      <el-table-column label="设计3最终比例" align="center" prop="final3Pct"/>
      <el-table-column label="设计4最终比例" align="center" prop="final4Pct"/>
      <el-table-column label="设计5最终比例" align="center" prop="final5Pct"/>
      <el-table-column label="设计6最终比例" align="center" prop="final6Pct"/>
      <el-table-column label="设计7最终比例" align="center" prop="final7Pct"/>
      <el-table-column label="设计8最终比例" align="center" prop="final8Pct"/>
      <el-table-column label="设计9最终比例" align="center" prop="final9Pct"/>
      <el-table-column label="设计10最终比例" align="center" prop="final10Pct"/>
      <el-table-column label="设计11最终比例" align="center" prop="final11Pct"/>
      <el-table-column label="设计12最终比例" align="center" prop="final12Pct"/>
      <el-table-column label="设计13最终比例" align="center" prop="final13Pct"/>
      <el-table-column label="设计14最终比例" align="center" prop="final14Pct"/>
      <el-table-column label="设计15最终比例" align="center" prop="final15Pct"/>
      <el-table-column label="设计16最终比例" align="center" prop="final16Pct"/>
      <el-table-column label="设计17最终比例" align="center" prop="final17Pct"/>
      <el-table-column label="设计18最终比例" align="center" prop="final18Pct"/>
      <el-table-column label="设计19最终比例" align="center" prop="final19Pct"/>
      <el-table-column label="设计20最终比例" align="center" prop="final20Pct"/>
      <el-table-column label="设计1进度" align="center" prop="progress1"/>
      <el-table-column label="设计2进度" align="center" prop="progress2"/>
      <el-table-column label="设计3进度" align="center" prop="progress3"/>
      <el-table-column label="设计4进度" align="center" prop="progress4"/>
      <el-table-column label="设计5进度" align="center" prop="progress5"/>
      <el-table-column label="设计6进度" align="center" prop="progress6"/>
      <el-table-column label="设计7进度" align="center" prop="progress7"/>
      <el-table-column label="设计8进度" align="center" prop="progress8"/>
      <el-table-column label="设计9进度" align="center" prop="progress9"/>
      <el-table-column label="设计10进度" align="center" prop="progress10"/>
      <el-table-column label="设计11进度" align="center" prop="progress11"/>
      <el-table-column label="设计12进度" align="center" prop="progress12"/>
      <el-table-column label="设计13进度" align="center" prop="progress13"/>
      <el-table-column label="设计14进度" align="center" prop="progress14"/>
      <el-table-column label="设计15进度" align="center" prop="progress15"/>
      <el-table-column label="设计16进度" align="center" prop="progress16"/>
      <el-table-column label="设计17进度" align="center" prop="progress17"/>
      <el-table-column label="设计18进度" align="center" prop="progress18"/>
      <el-table-column label="设计19进度" align="center" prop="progress19"/>
      <el-table-column label="设计20进度" align="center" prop="progress20"/>
      <el-table-column label="创建者" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy"/>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="handleUpdate(scope.row)"v-hasPermi="['business:ProfessionalAllocationB:edit']">比例策划</el-button>
          <el-button link type="primary" @click="handleDelete(scope.row)"v-hasPermi="['business:ProfessionalAllocationB:remove']">删除</el-button>
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

    <!--  新增专业对话框  -->
    <el-dialog :title="title" v-model="openAdd" width="1200px" append-to-body>
      <el-form ref="ProfessionalAllocationRef" :model="mainForm" :rules="rules" label-width="80px">
        <el-form-item label="专业" prop="profession">
          <el-select v-model="mainForm.profession" placeholder="请选择专业">
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
          <el-input v-model="mainForm.projectNo" placeholder="请输入项目号" />
        </el-form-item>
<!--        <el-form-item label="阶段号" prop="phaseNo">
          <el-input v-model="mainForm.phaseNo" placeholder="请输入阶段号" />
        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="mainForm.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitMainForm">确 定</el-button>
          <el-button @click="cancelAdd">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!--  修改专业对话框  -->
    <el-dialog :title="title" v-model="openUpdate" width="1200px" append-to-body>
      <el-form ref="ProfessionalAllocationRef" :model="mainForm" :rules="rules" label-width="80px">
        <el-form-item label="专业" prop="profession">
          <el-select v-model="mainForm.profession" placeholder="请选择专业">
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
          <el-input v-model="mainForm.projectNo" placeholder="请输入项目号" />
        </el-form-item>
<!--        <el-form-item label="阶段号" prop="phaseNo">
          <el-input v-model="mainForm.phaseNo" placeholder="请输入阶段号" />
        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="mainForm.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitUpdate">确 定</el-button>
          <el-button @click="cancelUpdate">取 消</el-button>
        </div>
      </template>
      <el-button link type="primary" @click="openSubTableDialog"v-hasPermi="['business:ProfessionalAllocationB:edit']">比例策划详情</el-button>
    </el-dialog>

    <!-- 修改专业负责人策划子表对话框 -->
    <el-dialog :title="title" v-model="open" width="950px" append-to-body>
      <el-form ref="ProfessionalAllocationBRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-form-item label="专业负责人策划主表ID" prop="headId" label-width="160">
            <el-input v-model="form.headId" placeholder="请输入专业负责人策划主表ID" style="width: 120px;"/>
          </el-form-item>
          <el-form-item label="项目ID" prop="prjId" label-width="160">
            <el-input v-model="form.prjId" placeholder="请输入项目ID" style="width: 120px;"/>
          </el-form-item>
          <el-form-item label="项目子项ID" prop="itemId" label-width="160">
            <el-input v-model="form.itemId" placeholder="请输入项目子项ID" style="width: 120px;"/>
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
        </el-row>
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
          <el-input v-model="data.form.remark" type="textarea" placeholder="请输入内容"/>
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
import {
  getProjectItems,
  getProfessionalAllocationB,
  delProfessionalAllocationB,
  addProfessionalAllocationB,
  updateProfessionalAllocationB
} from "@/api/business/ProfessionalAllocationB";
import {batchAddProfessionalAllocation,getProfessionalByHeadId,updateProfession} from "@/api/business/ProfessionalAllocation";
import {useRoute} from "vue-router";
import {watch} from "vue";

const route = useRoute();

const {proxy} = getCurrentInstance();
const { user_pro } = proxy.useDict('user_pro');

const ProfessionalAllocationBList = ref([]);
const open = ref(false);
const openAdd = ref(false);
const openUpdate = ref(false);
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
    itemNo: null,
    itemName: null,
    prjId: route.query.prjId,
    itemId:  route.query.itemId
  },
  rules: {
    itemNo: [
      {required: true, message: "子项编号不能为空", trigger: "blur"}
    ],
    itemName: [
      {required: true, message: "子项名称不能为空", trigger: "blur"}
    ],
  }
});


const {queryParams, form, rules} = toRefs(data);

/** 查询专业负责人策划子表列表 */
function getList() {
  loading.value = true;
  getProjectItems(queryParams.value).then(async response => {
    const list = response.rows;
    // 如果有 headId，异步加载 profession 并更新每一行
    if (list.length > 0 && list[0].headId) {
      try {
        const res = await getProfessionalByHeadId(list[0].headId);
        const profession = res.data || '-';
        ProfessionalAllocationBList.value = list.map(item => ({
          ...item,
          profession
        }));
      } catch (error) {
        console.error('获取专业失败:', error);
        ProfessionalAllocationBList.value = list;
      }
    } else {
      ProfessionalAllocationBList.value = list;
    }

    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}
// 新增对话框取消按钮
function cancelAdd() {
  openAdd.value = false;
  resetMainForm();
}
// 修改对话框取消按钮
function cancelUpdate() {
  openUpdate.value = false;
  resetMainForm();
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

function resetMainForm() {
  mainForm.id = null;
  mainForm.prjId = route.query.prjId || null;
  mainForm.itemId = route.query.itemId || null;
  mainForm.itemName = route.query.itemName || null;
  mainForm.itemWeight = null;
  mainForm.remark = null;
  mainForm.deptId = null;
  mainForm.userId = null;

  proxy.resetForm("ProfessionalAllocationRef");
}

/** 新增专业按钮操作 */
function handleAdd() {
  reset();
  resetMainForm();
  openAdd.value = true;
  title.value = "添加专业";

  // 发起请求获取 profession
  if (route.query.itemId) {
    getProfessionalByHeadId(route.query.itemId).then(res => {
      const profession = res.data || '';
      console.log('profession:', profession)
      mainForm.profession = profession;  // 如果有值就赋值，否则留空
    }).catch(err => {
      console.error('获取专业失败:', err);
      mainForm.profession = ''; // 出错也清空
    });
  } else {
    mainForm.profession = ''; // itemId 不存在时也清空
  }

  mainForm.projectName = route.query.projectName;
}
const selectedRow = ref(null);
/** 修改专业按钮操作 */
function handleOpenUpdate() {
  resetMainForm();
  title.value = "修改专业";
  openUpdate.value = true;

  selectedRow.value = ProfessionalAllocationBList.value.find(item => item.id === ids.value[0]);
  if (selectedRow.value && selectedRow.value.headId) {
    getProfessionalByHeadId(selectedRow.value.headId).then(res => {
      mainForm.profession = res.data || '';
    }).catch(err => {
      console.error('获取专业失败:', err);
      mainForm.profession = '';
    });
  } else {
    mainForm.profession = '';
  }
  mainForm.id = selectedRow.value.headId;
  mainForm.projectName = route.query.projectName;
}

function openSubTableDialog() {
  if (selectedRow.value) {
    handleUpdate(selectedRow.value);
  }
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
  proxy.$modal.confirm('是否确认删除专业负责人策划子表编号为"' + _ids + '"的数据项？').then(function () {
    return delProfessionalAllocationB(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/ProfessionalAllocationB/export', {
    ...queryParams.value
  }, `ProfessionalAllocationB_${new Date().getTime()}.xlsx`)
}

/** 实现输入用户名称时实时搜索，laizhiying的方式 start*/
import {listUser} from "@/api/system/user";

const userLoading = ref(false);
const userList = ref([]);

function searchUsers(query) {
  if (query !== '') {
    userLoading.value = true;
    // 使用已封装的 listUser 接口
    listUser({nickName: query}).then(response => {
      userList.value = response.rows;
      userLoading.value = false;
    });
  } else {
    userList.value = [];
  }
}

/** 实现输入用户名称时实时搜索 end*/

//项目子项跳转专业负责人策划功能 start
watch(
    () => route.query.itemName,
    (newVal) => {
      queryParams.value.itemName = newVal || null
      handleQuery()
    },
    {immediate: true}
)

//项目子项跳转专业负责人策划功能 end

const mainForm = reactive({
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
});

/** 提交主表 并将主表id保存到子表*/
function submitMainForm() {
  proxy.$refs["ProfessionalAllocationRef"].validate(valid => {
    if (valid) {
      const params = {
        professionalAllocation: { ...mainForm },
        itemId: route.query.itemId
      };
      batchAddProfessionalAllocation(params).then(response => {
        proxy.$modal.msgSuccess("新增成功");
        openAdd.value = false;
        getList();
      }).catch(err => {
        proxy.$message.error("新增失败：" + err.message);
      });
    }
  });
}

/*修改专业提交*/
function submitUpdate() {
  proxy.$refs["ProfessionalAllocationRef"].validate(valid => {
    console.log("提交数据：", mainForm)
    console.log("id：", mainForm.id)
    if (valid) {
      if (!mainForm.id) {
        proxy.$modal.msgError("还未分配专业，无需修改专业");
        return;
      }
      // 调用更新接口
      updateProfession(mainForm.id, mainForm.profession).then(response => {
        proxy.$modal.msgSuccess("修改成功");
        openUpdate.value = false; // 关闭对话框
        getList(); // 刷新列表
      }).catch(err => {
        proxy.$message.error("修改失败：" + err.message);
      });
    }
  });
}

getList();
</script>
