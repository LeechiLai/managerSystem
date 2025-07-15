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
            v-hasPermi="['business:SubitemsPlanning:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['business:SubitemsPlanning:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['business:SubitemsPlanning:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['business:SubitemsPlanning:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SubitemsPlanningList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" type="index" width="50" align="center" prop="id"/>
      <!--      <el-table-column label="项目ID" align="center" prop="prjId" />
            <el-table-column label="项目子项ID" align="center" prop="itemId" />-->
      <el-table-column label="专业" align="center" prop="profession">
        <template #default="scope">
          <dict-tag :options="user_pro" :value="scope.row.profession"/>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="center" prop="projectName" width="300" :show-overflow-tooltip="true"/>
      <el-table-column label="项目号" align="center" prop="projectNo"/>
      <el-table-column label="阶段号" align="center" prop="phaseNo"/>
      <el-table-column label="子项编号" align="center" prop="itemNo"/>
      <el-table-column label="子项名称" align="center" prop="itemName" width="300" :show-overflow-tooltip="true"/>
      <el-table-column label="子项权重" align="center" prop="itemWeight">
        <template #default="scope">{{ scope.row.itemWeight }}%</template>
      </el-table-column>
      >
      <el-table-column label="专业负责人" align="center" prop="proResp"/>
      <el-table-column label="EIM" align="center" prop="eim"/>
      <el-table-column label="总体方案" align="center" prop="overallPlan"/>
      <el-table-column label="校核" align="center" prop="checker"/>
      <el-table-column label="审核" align="center" prop="reviewer"/>
      <el-table-column label="压力管道审核" align="center" prop="pipeReview"/>
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
      <el-table-column label="专业负责人" align="center" prop="proRespPct">
        <template #default="scope">{{ scope.row.proRespPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="EIM" align="center" prop="eimPct">
        <template #default="scope">{{ scope.row.eimPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="总体方案" align="center" prop="overallPlanPct">
        <template #default="scope">{{ scope.row.overallPlanPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="校核比例" align="center" prop="checkerPct">
        <template #default="scope">{{ scope.row.checkerPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="审核" align="center" prop="reviewerPct">
        <template #default="scope">{{ scope.row.reviewerPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="审核比例" align="center" prop="reviewerPct">
        <template #default="scope">{{ scope.row.reviewerPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="压力管道审核" align="center" prop="pipeReviewPct">
        <template #default="scope">{{ scope.row.pipeReviewPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="压力管道审核比例" align="center" prop="pipeReviewPct">
        <template #default="scope">{{ scope.row.pipeReviewPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="审定" align="center" prop="approvalPct">
        <template #default="scope">{{ scope.row.approvalPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="审定比例" align="center" prop="approvalPct">
        <template #default="scope">{{ scope.row.approvalPct }}%</template>
      </el-table-column>
      >
      <el-table-column label="设计指导" align="center" prop="designGuidancePct"/>
      <el-table-column label="设计1比例" align="center" prop="design1Pct">
        <template #default="scope">{{ scope.row.design1Pct }}%</template>
      </el-table-column>
      >
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
      <el-table-column label="进度1" align="center" prop="progress1"/>
      <el-table-column label="进度2" align="center" prop="progress2"/>
      <el-table-column label="进度3" align="center" prop="progress3"/>
      <el-table-column label="进度4" align="center" prop="progress4"/>
      <el-table-column label="进度5" align="center" prop="progress5"/>
      <el-table-column label="进度6" align="center" prop="progress6"/>
      <el-table-column label="进度7" align="center" prop="progress7"/>
      <el-table-column label="进度8" align="center" prop="progress8"/>
      <el-table-column label="进度9" align="center" prop="progress9"/>
      <el-table-column label="进度10" align="center" prop="progress10"/>
      <el-table-column label="进度11" align="center" prop="progress11"/>
      <el-table-column label="进度12" align="center" prop="progress12"/>
      <el-table-column label="进度13" align="center" prop="progress13"/>
      <el-table-column label="进度14" align="center" prop="progress14"/>
      <el-table-column label="进度15" align="center" prop="progress15"/>
      <el-table-column label="进度16" align="center" prop="progress16"/>
      <el-table-column label="进度17" align="center" prop="progress17"/>
      <el-table-column label="进度18" align="center" prop="progress18"/>
      <el-table-column label="进度19" align="center" prop="progress19"/>
      <el-table-column label="进度20" align="center" prop="progress20"/>

      <!--
         <el-table-column label="项目管理占比" align="center" prop="def1"/>
      <el-table-column label="项目设计占比" align="center" prop="def2"/>
         <el-table-column label="自定义字段3" align="center" prop="def3"/>
            <el-table-column label="自定义字段4" align="center" prop="def4"/>
            <el-table-column label="自定义字段5" align="center" prop="def5"/>
            <el-table-column label="自定义字段6" align="center" prop="def6"/>
            <el-table-column label="自定义字段7" align="center" prop="def7"/>
            <el-table-column label="自定义字段8" align="center" prop="def8"/>
            <el-table-column label="自定义字段9" align="center" prop="def9"/>
            <el-table-column label="自定义字段10" align="center" prop="def10"/>-->
      <el-table-column label="备注" align="center" prop="remark" width="200" :show-overflow-tooltip="true"/>
      <el-table-column label="创建者" align="center" prop="createBy"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy"/>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200" fixed="right">
        <template #default="scope">
          <el-button link type="primary" @click="handleDesignRatio(scope.row)" v-hasPermi="['business:SubitemsPlanning:edit']">分项策划</el-button>
          <el-button link type="primary" @click="handleUpdate(scope.row)" v-hasPermi="['business:SubitemsPlanning:edit']">修改</el-button>
          <el-button link type="primary" @click="handleDelete(scope.row)"v-hasPermi="['business:SubitemsPlanning:remove']">删除</el-button>
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
    <el-dialog :title="title" v-model="open" width="1250px" append-to-body>
      <el-form ref="SubitemsPlanningRef" :model="form" :rules="rules" label-width="80px">
        <!--        <el-row>
                  <el-form-item label="项目ID" prop="prjId">
                    <el-input v-model="form.prjId" placeholder="请输入项目ID" />
                  </el-form-item>
                  <el-form-item label="子项ID" prop="itemId">
                    <el-input v-model="form.itemId" placeholder="请输入项目子项ID" />
                  </el-form-item>
                </el-row>-->
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
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-row>
          <el-form-item label="项目号" prop="projectNo">
            <el-input v-model="form.projectNo" placeholder="请输入项目号"/>
          </el-form-item>
          <el-form-item label="阶段号" prop="phaseNo">
            <el-input v-model="form.phaseNo" placeholder="请输入阶段号"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="子项编号" prop="itemNo">
            <el-input v-model="form.itemNo" placeholder="请输入子项编号"/>
          </el-form-item>
          <el-form-item label="子项名称" prop="itemName">
            <el-input v-model="form.itemName" placeholder="请输入子项名称"/>
          </el-form-item>
        </el-row>
        <el-row>

          <el-form-item label="子项权重" prop="itemWeight">
            <el-input v-model="form.itemWeight" placeholder="请输入子项权重"/>
          </el-form-item>
          <el-form-item label="专业负责人" prop="proResp" label-width="100">
            <el-input v-model="form.proResp" placeholder="请输入专业负责人"/>
          </el-form-item>
        </el-row>
        <el-row>

          <el-form-item label="EIM" prop="eim">
            <el-input v-model="form.eim" placeholder="请输入EIM"/>
          </el-form-item>
          <el-form-item label="总体方案" prop="overallPlan">
            <el-input v-model="form.overallPlan" placeholder="请输入总体方案"/>
          </el-form-item>
        </el-row>
        <el-row>

          <el-form-item label="校核" prop="checker">
            <el-input v-model="form.checker" placeholder="请输入校核"/>
          </el-form-item>
          <el-form-item label="审核" prop="reviewer">
            <el-input v-model="form.reviewer" placeholder="请输入审核"/>
          </el-form-item>
        </el-row>
        <el-row>

          <el-form-item label="压力管道审核" prop="pipeReview" label-width="100">
            <el-input v-model="form.pipeReview" placeholder="请输入压力管道审核"/>
          </el-form-item>
          <el-form-item label="审定" prop="approval">
            <el-input v-model="form.approval" placeholder="请输入审定"/>
          </el-form-item>
        </el-row>
        <el-row>

        </el-row>
        <el-form-item label="设计指导" prop="designGuidance">
          <el-input v-model="form.designGuidance" placeholder="请输入设计指导"/>
        </el-form-item>
        <el-row>
          <el-form-item label="设计1" prop="design1">
            <el-input v-model="form.design1" placeholder="请输入设计1"/>
          </el-form-item>
          <el-form-item label="设计1比例" prop="design1Pct">
            <el-input v-model="form.design1Pct" placeholder="请输入设计1比例"/>
          </el-form-item>
          <el-form-item label="进度1" prop="progress1">
            <el-input v-model="form.progress1" placeholder="请输入进度1"/>
          </el-form-item>
          <el-form-item label="设计1最终比例（%）" prop="final1Pct" label-width="160">
            <el-input v-model="form.final1Pct" placeholder="请输入设计1最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计2" prop="design2">
            <el-input v-model="form.design2" placeholder="请输入设计2"/>
          </el-form-item>
          <el-form-item label="设计2比例" prop="design2Pct">
            <el-input v-model="form.design2Pct" placeholder="请输入设计2比例"/>
          </el-form-item>
          <el-form-item label="进度2" prop="progress2">
            <el-input v-model="form.progress2" placeholder="请输入进度2"/>
          </el-form-item>
          <el-form-item label="设计2最终比例（%）" prop="final2Pct" label-width="160">
            <el-input v-model="form.final2Pct" placeholder="请输入设计2最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计3" prop="design3">
            <el-input v-model="form.design3" placeholder="请输入设计3"/>
          </el-form-item>
          <el-form-item label="设计3比例" prop="design3Pct">
            <el-input v-model="form.design3Pct" placeholder="请输入设计3比例"/>
          </el-form-item>
          <el-form-item label="进度3" prop="progress3">
            <el-input v-model="form.progress3" placeholder="请输入进度3"/>
          </el-form-item>
          <el-form-item label="设计3最终比例（%）" prop="final3Pct" label-width="160">
            <el-input v-model="form.final3Pct" placeholder="请输入设计3最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计4" prop="design4">
            <el-input v-model="form.design4" placeholder="请输入设计4"/>
          </el-form-item>
          <el-form-item label="设计4比例" prop="design4Pct">
            <el-input v-model="form.design4Pct" placeholder="请输入设计4比例"/>
          </el-form-item>
          <el-form-item label="进度4" prop="progress4">
            <el-input v-model="form.progress4" placeholder="请输入进度4"/>
          </el-form-item>
          <el-form-item label="设计4最终比例（%）" prop="final4Pct" label-width="160">
            <el-input v-model="form.final4Pct" placeholder="请输入设计4最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计5" prop="design5">
            <el-input v-model="form.design5" placeholder="请输入设计5"/>
          </el-form-item>
          <el-form-item label="设计5比例" prop="design5Pct">
            <el-input v-model="form.design5Pct" placeholder="请输入设计5比例"/>
          </el-form-item>
          <el-form-item label="进度5" prop="progress5">
            <el-input v-model="form.progress5" placeholder="请输入进度5"/>
          </el-form-item>
          <el-form-item label="设计5最终比例（%）" prop="final5Pct" label-width="160">
            <el-input v-model="form.final5Pct" placeholder="请输入设计5最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计6" prop="design6">
            <el-input v-model="form.design6" placeholder="请输入设计6"/>
          </el-form-item>
          <el-form-item label="设计6比例" prop="design6Pct">
            <el-input v-model="form.design6Pct" placeholder="请输入设计6比例"/>
          </el-form-item>
          <el-form-item label="进度6" prop="progress6">
            <el-input v-model="form.progress6" placeholder="请输入进度6"/>
          </el-form-item>
          <el-form-item label="设计6最终比例（%）" prop="final6Pct" label-width="160">
            <el-input v-model="form.final6Pct" placeholder="请输入设计6最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计7" prop="design7">
            <el-input v-model="form.design7" placeholder="请输入设计7"/>
          </el-form-item>
          <el-form-item label="设计7比例" prop="design7Pct">
            <el-input v-model="form.design7Pct" placeholder="请输入设计7比例"/>
          </el-form-item>
          <el-form-item label="进度7" prop="progress7">
            <el-input v-model="form.progress7" placeholder="请输入进度7"/>
          </el-form-item>
          <el-form-item label="设计7最终比例（%）" prop="final7Pct" label-width="160">
            <el-input v-model="form.final7Pct" placeholder="请输入设计7最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计8" prop="design8">
            <el-input v-model="form.design8" placeholder="请输入设计8"/>
          </el-form-item>
          <el-form-item label="设计8比例" prop="design8Pct">
            <el-input v-model="form.design8Pct" placeholder="请输入设计8比例"/>
          </el-form-item>
          <el-form-item label="进度8" prop="progress8">
            <el-input v-model="form.progress8" placeholder="请输入进度8"/>
          </el-form-item>
          <el-form-item label="设计8最终比例（%）" prop="final8Pct" label-width="160">
            <el-input v-model="form.final8Pct" placeholder="请输入设计8最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计9" prop="design9">
            <el-input v-model="form.design9" placeholder="请输入设计9"/>
          </el-form-item>
          <el-form-item label="设计9比例" prop="design9Pct">
            <el-input v-model="form.design9Pct" placeholder="请输入设计9比例"/>
          </el-form-item>
          <el-form-item label="进度9" prop="progress9">
            <el-input v-model="form.progress9" placeholder="请输入进度9"/>
          </el-form-item>
          <el-form-item label="设计9最终比例（%）" prop="final9Pct" label-width="160">
            <el-input v-model="form.final9Pct" placeholder="请输入设计9最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计10" prop="design10">
            <el-input v-model="form.design10" placeholder="请输入设计10"/>
          </el-form-item>
          <el-form-item label="设计10比例" prop="design10Pct" label-width="90">
            <el-input v-model="form.design10Pct" placeholder="请输入设计10比例"/>
          </el-form-item>
          <el-form-item label="进度10" prop="progress10">
            <el-input v-model="form.progress10" placeholder="请输入进度10"/>
          </el-form-item>
          <el-form-item label="设计10最终比例（%）" prop="final10Pct" label-width="160">
            <el-input v-model="form.final10Pct" placeholder="请输入设计10最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计11" prop="design11">
            <el-input v-model="form.design11" placeholder="请输入设计11"/>
          </el-form-item>
          <el-form-item label="设计11比例" prop="design11Pct" label-width="90">
            <el-input v-model="form.design11Pct" placeholder="请输入设计11比例"/>
          </el-form-item>
          <el-form-item label="进度11" prop="progress11">
            <el-input v-model="form.progress11" placeholder="请输入进度11"/>
          </el-form-item>
          <el-form-item label="设计11最终比例（%）" prop="final11Pct" label-width="160">
            <el-input v-model="form.final11Pct" placeholder="请输入设计11最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计12" prop="design12" >
            <el-input v-model="form.design12" placeholder="请输入设计12"/>
          </el-form-item>
          <el-form-item label="设计12比例" prop="design12Pct"  label-width="90">
            <el-input v-model="form.design12Pct" placeholder="请输入设计12比例"/>
          </el-form-item>
          <el-form-item label="进度12" prop="progress12">
            <el-input v-model="form.progress12" placeholder="请输入进度12"/>
          </el-form-item>
          <el-form-item label="设计12最终比例（%）" prop="final12Pct" label-width="160">
            <el-input v-model="form.final12Pct" placeholder="请输入设计12最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计13" prop="design13">
            <el-input v-model="form.design13" placeholder="请输入设计13"/>
          </el-form-item>
          <el-form-item label="设计13比例" prop="design13Pct"  label-width="90">
            <el-input v-model="form.design13Pct" placeholder="请输入设计13比例"/>
          </el-form-item>
          <el-form-item label="进度13" prop="progress13">
            <el-input v-model="form.progress13" placeholder="请输入进度13"/>
          </el-form-item>
          <el-form-item label="设计13最终比例（%）" prop="final13Pct" label-width="160">
            <el-input v-model="form.final13Pct" placeholder="请输入设计13最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计14" prop="design14">
            <el-input v-model="form.design14" placeholder="请输入设计14"/>
          </el-form-item>
          <el-form-item label="设计14比例" prop="design14Pct" label-width="90">
            <el-input v-model="form.design14Pct" placeholder="请输入设计14比例"/>
          </el-form-item>
          <el-form-item label="进度14" prop="progress14">
            <el-input v-model="form.progress14" placeholder="请输入进度14"/>
          </el-form-item>
          <el-form-item label="设计14最终比例（%）" prop="final14Pct" label-width="160">
            <el-input v-model="form.final14Pct" placeholder="请输入设计14最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计15" prop="design15">
            <el-input v-model="form.design15" placeholder="请输入设计15"/>
          </el-form-item>
          <el-form-item label="设计15比例" prop="design15Pct" label-width="90">
            <el-input v-model="form.design15Pct" placeholder="请输入设计15比例"/>
          </el-form-item>
          <el-form-item label="进度15" prop="progress15">
            <el-input v-model="form.progress15" placeholder="请输入进度15"/>
          </el-form-item>
          <el-form-item label="设计15最终比例（%）" prop="final15Pct" label-width="160">
            <el-input v-model="form.final15Pct" placeholder="请输入设计15最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计16" prop="design16">
            <el-input v-model="form.design16" placeholder="请输入设计16"/>
          </el-form-item>
          <el-form-item label="设计16比例" prop="design16Pct" label-width="90">
            <el-input v-model="form.design16Pct" placeholder="请输入设计16比例"/>
          </el-form-item>
          <el-form-item label="进度16" prop="progress16">
            <el-input v-model="form.progress16" placeholder="请输入进度16"/>
          </el-form-item>
          <el-form-item label="设计16最终比例（%）" prop="final16Pct" label-width="160">
            <el-input v-model="form.final16Pct" placeholder="请输入设计16最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计17" prop="design17">
            <el-input v-model="form.design17" placeholder="请输入设计17"/>
          </el-form-item>
          <el-form-item label="设计17比例" prop="design17Pct" label-width="90">
            <el-input v-model="form.design17Pct" placeholder="请输入设计17比例"/>
          </el-form-item>
          <el-form-item label="进度17" prop="progress17">
            <el-input v-model="form.progress17" placeholder="请输入进度17"/>
          </el-form-item>
          <el-form-item label="设计17最终比例（%）" prop="final17Pct" label-width="160">
            <el-input v-model="form.final17Pct" placeholder="请输入设计17最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计18" prop="design18">
            <el-input v-model="form.design18" placeholder="请输入设计18"/>
          </el-form-item>
          <el-form-item label="设计18比例" prop="design18Pct" label-width="90">
            <el-input v-model="form.design18Pct" placeholder="请输入设计18比例"/>
          </el-form-item>
          <el-form-item label="进度18" prop="progress18">
            <el-input v-model="form.progress18" placeholder="请输入进度18"/>
          </el-form-item>
          <el-form-item label="设计18最终比例（%）" prop="final18Pct" label-width="160">
            <el-input v-model="form.final18Pct" placeholder="请输入设计18最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计19" prop="design19">
            <el-input v-model="form.design19" placeholder="请输入设计19"/>
          </el-form-item>
          <el-form-item label="设计19比例" prop="design19Pct" label-width="90">
            <el-input v-model="form.design19Pct" placeholder="请输入设计19比例"/>
          </el-form-item>
          <el-form-item label="进度19" prop="progress19">
            <el-input v-model="form.progress19" placeholder="请输入进度19"/>
          </el-form-item>
          <el-form-item label="设计19最终比例（%）" prop="final19Pct" label-width="160">
            <el-input v-model="form.final19Pct" placeholder="请输入设计19最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="设计20" prop="design20">
            <el-input v-model="form.design20" placeholder="请输入设计20"/>
          </el-form-item>
          <el-form-item label="设计20比例" prop="design20Pct" label-width="90">
            <el-input v-model="form.design20Pct" placeholder="请输入设计20比例"/>
          </el-form-item>
          <el-form-item label="进度20" prop="progress20">
            <el-input v-model="form.progress20" placeholder="请输入进度20"/>
          </el-form-item>
          <el-form-item label="设计20最终比例（%）" prop="final20Pct" label-width="160">
            <el-input v-model="form.final20Pct" placeholder="请输入设计20最终比例（%）"/>
          </el-form-item>
        </el-row>

        <el-form-item label="采购支持" prop="procurementSupport">
          <el-input v-model="form.procurementSupport" placeholder="请输入采购支持"/>
        </el-form-item>
        <el-form-item label="顾问" prop="consultant">
          <el-input v-model="form.consultant" placeholder="请输入顾问"/>
        </el-form-item>
        <el-form-item label="专业负责人" prop="proRespPct">
          <el-input v-model="form.proRespPct" placeholder="请输入专业负责人"/>
        </el-form-item>
        <el-form-item label="EIM" prop="eimPct">
          <el-input v-model="form.eimPct" placeholder="请输入EIM"/>
        </el-form-item>
        <el-form-item label="总体方案" prop="overallPlanPct">
          <el-input v-model="form.overallPlanPct" placeholder="请输入总体方案"/>
        </el-form-item>
        <el-form-item label="校核" prop="checkerPct">
          <el-input v-model="form.checkerPct" placeholder="请输入校核"/>
        </el-form-item>
        <el-form-item label="审核" prop="reviewerPct">
          <el-input v-model="form.reviewerPct" placeholder="请输入审核"/>
        </el-form-item>
        <el-form-item label="压力管道审核" prop="pipeReviewPct">
          <el-input v-model="form.pipeReviewPct" placeholder="请输入压力管道审核"/>
        </el-form-item>
        <el-form-item label="审定" prop="approvalPct">
          <el-input v-model="form.approvalPct" placeholder="请输入审定"/>
        </el-form-item>
        <el-form-item label="设计指导" prop="designGuidancePct">
          <el-input v-model="form.designGuidancePct" placeholder="请输入设计指导"/>
        </el-form-item>

        <el-form-item label="采购支持" prop="procurementSupportPct">
          <el-input v-model="form.procurementSupportPct" placeholder="请输入采购支持"/>
        </el-form-item>
        <el-form-item label="顾问" prop="consultantPct">
          <el-input v-model="form.consultantPct" placeholder="请输入顾问"/>
        </el-form-item>

<!--        <el-form-item label="自定义字段1" prop="def1">
          <el-input v-model="form.def1" placeholder="请输入自定义字段1"/>
        </el-form-item>
        <el-form-item label="自定义字段2" prop="def2">
          <el-input v-model="form.def2" placeholder="请输入自定义字段2"/>
        </el-form-item>
        <el-form-item label="自定义字段3" prop="def3">
          <el-input v-model="form.def3" placeholder="请输入自定义字段3"/>
        </el-form-item>
        <el-form-item label="自定义字段4" prop="def4">
          <el-input v-model="form.def4" placeholder="请输入自定义字段4"/>
        </el-form-item>
        <el-form-item label="自定义字段5" prop="def5">
          <el-input v-model="form.def5" placeholder="请输入自定义字段5"/>
        </el-form-item>
        <el-form-item label="自定义字段6" prop="def6">
          <el-input v-model="form.def6" placeholder="请输入自定义字段6"/>
        </el-form-item>
        <el-form-item label="自定义字段7" prop="def7">
          <el-input v-model="form.def7" placeholder="请输入自定义字段7"/>
        </el-form-item>
        <el-form-item label="自定义字段8" prop="def8">
          <el-input v-model="form.def8" placeholder="请输入自定义字段8"/>
        </el-form-item>
        <el-form-item label="自定义字段9" prop="def9">
          <el-input v-model="form.def9" placeholder="请输入自定义字段9"/>
        </el-form-item>
        <el-form-item label="自定义字段10" prop="def10">
          <el-input v-model="form.def10" placeholder="请输入自定义字段10"/>
        </el-form-item>-->
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
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

<script setup name="SubitemsPlanning">
import {
  listSubitemsPlanning,
  getSubitemsPlanning,
  delSubitemsPlanning,
  addSubitemsPlanning,
  updateSubitemsPlanning
} from "@/api/business/SubitemsPlanning";
const router = useRouter();

const {proxy} = getCurrentInstance();
const {user_pro} = proxy.useDict('user_pro');

const SubitemsPlanningList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const calculateMajorAmount = (row) => {
  if (!row.receivedAmount || !row.projPct) return 0;
  return (row.receivedAmount * row.projPct).toFixed(2);
};

// 计算个人分配金额:internalPct 管理者内部比例；projPct：管理角色占总项目比例
const calculatePersonAmount = (row) => {
  if (!row.receivedAmount || !row.projPct || !row.internalPct) return 0;
  return (row.receivedAmount * row.projPct * row.internalPct).toFixed(2);
};


const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    profession: null,
    projectName: null,
    projectNo: null,
    phaseNo: null,
    itemName: null,
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

/** 查询专业负责人策划列表 */
function getList() {
  loading.value = true;
  listSubitemsPlanning(queryParams.value).then(response => {
    console.log(queryParams),
        SubitemsPlanningList.value = response.rows;
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
    itemId: null,
    profession: null,
    projectName: null,
    projectNo: null,
    phaseNo: null,
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
    remark: null
  };
  proxy.resetForm("SubitemsPlanningRef");
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
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getSubitemsPlanning(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改专业负责人策划";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["SubitemsPlanningRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateSubitemsPlanning(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addSubitemsPlanning(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除专业负责人策划编号为"' + _ids + '"的数据项？').then(function () {
    return delSubitemsPlanning(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/SubitemsPlanning/export', {
    ...queryParams.value
  }, `SubitemsPlanning_${new Date().getTime()}.xlsx`)
}

/** 跳转到分项策划 */
/*function handleDesignRatio(row) {
  console.log(row)
  const prjId = row.prjId;
  router.push("/business/design-allocated/index/" + prjId);
}*/

function handleDesignRatio(row) {
  router.push("ratio/DesignRatio/");
}

getList();
</script>
