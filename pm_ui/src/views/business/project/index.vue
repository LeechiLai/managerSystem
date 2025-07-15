<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="计入年月" prop="yearmonth">
        <el-date-picker clearable
          v-model="queryParams.yearmonth"
          type="month"
          value-format="YYYY-MM"
          placeholder="请选择计入年月">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="业务类型" prop="businessType">
        <el-select v-model="queryParams.businessType" placeholder="请选择业务类型" clearable>
          <el-option
            v-for="dict in business_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="合同号" prop="contractNumber">
        <el-input
          v-model="queryParams.contractNumber"
          placeholder="请输入合同号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人" prop="projectManager" label-width="100">
        <el-select v-model="queryParams.projectManager" placeholder="请选择项目负责人" clearable>
          <el-option
            v-for="dict in project_manager"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['business:project:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:project:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['business:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" type="index" width="50" align="center" prop="id" />
      <el-table-column label="计入年月" align="center" prop="yearmonth" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.yearmonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目名称" align="left" prop="projectName" width="600" :show-overflow-tooltip="true" />
      <el-table-column label="业务类型" align="center" prop="businessType">
        <template #default="scope">
          <dict-tag :options="business_type" :value="scope.row.businessType"/>
        </template>
      </el-table-column>
      <el-table-column label="行业大类" align="center" prop="industryCategory">
        <template #default="scope">
          <dict-tag :options="industry_category" :value="scope.row.industryCategory"/>
        </template>
      </el-table-column>
      <el-table-column label="行业细分" align="center" prop="industrySubcategory">
        <template #default="scope">
          <dict-tag :options="industry_subcategory" :value="scope.row.industrySubcategory"/>
        </template>
      </el-table-column>
      <el-table-column label="地域" align="center" prop="region">
        <template #default="scope">
          <dict-tag :options="region" :value="scope.row.region"/>
        </template>
      </el-table-column>
      <el-table-column label="项目规模" align="center" prop="projectScale">
        <template #default="scope">
          <dict-tag :options="project_scale" :value="scope.row.projectScale"/>
        </template>
      </el-table-column>
      <el-table-column label="生效时间" align="center" prop="contractSxsj" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.contractSxsj, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合同状态" align="center" prop="contractStatus">
        <template #default="scope">
          <dict-tag :options="contract_status" :value="scope.row.contractStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="执行状态" align="center" prop="prjexecStatus">
        <template #default="scope">
          <dict-tag :options="prjexec_status" :value="scope.row.prjexecStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="所属部门" align="center" prop="department" width="120" clearable>
        <template #default="scope">
          <dict-tag :options="department" :value="scope.row.department"/>
        </template>
      </el-table-column>
      <el-table-column label="结转原部门" align="center" prop="originalDepartment" width="120" />
      <el-table-column label="工程号" align="center" prop="projectCode" width="120" />
      <el-table-column label="项目阶段" align="center" prop="projectPhase" width="120" >
        <template #default="scope">
          <dict-tag :options="project_phase" :value="scope.row.projectPhase"/>
        </template>
<!--        <template #default="scope">
          <dict-tag :options="project_phase" :value="scope.row.projectPhase ? scope.row.projectPhase.split(',') : []"/>
        </template>-->
      </el-table-column>
      <el-table-column label="合同号" align="center" prop="contractNumber" width="300" :show-overflow-tooltip="true" />
      <el-table-column label="建设单位" align="center" prop="constructionUnit" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="项目内容" align="center" prop="projectScope" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="总投资" align="center" prop="totalInvestment" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="合同金额" align="center" prop="amtIncTax" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="合同金额" align="center" prop="amtExcTax" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="阶段合同额" align="center" prop="stageAmtIncTa" width="120" :show-overflow-tooltip="true" />
      <el-table-column label="项目负责人" align="center" prop="projectManager" width="120" :show-overflow-tooltip="true">
        <template #default="scope">
          <dict-tag :options="project_manager" :value="scope.row.projectManager"/>
        </template>
      </el-table-column>
      <el-table-column label="合同洽谈负责人" align="center" prop="negResp" width="120" :show-overflow-tooltip="true"/>
      <el-table-column label="行业总监" align="center" prop="industryDirector">
        <template #default="scope">
          <dict-tag :options="industry_director" :value="scope.row.industryDirector"/>
        </template>
      </el-table-column>
      <el-table-column label="执行负责人" align="center" prop="execResp"  width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="持证负责人" align="center" prop="certResp" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="EIM负责人" align="center" prop="eimResp" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="分包金额" align="center" prop="subcAmtIncTax" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="项目已收款" align="center" prop="recvdAmt" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="项目未收款" align="center" prop="unrecdAmt" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="已收款比例 " align="center" prop="recvRatio" width="100" :show-overflow-tooltip="true">
        <template #default="scope">
          {{ formatPercentage(scope.row.recvRatio) }}
        </template>
      </el-table-column>
      <el-table-column label="之前年度收款" align="center" prop="prevYrRecv" width="120" :show-overflow-tooltip="true"/>
      <el-table-column label="本年度收款" align="center" prop="currYrRecv" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="最新收款" align="center" prop="receipt_amount" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="本次奖金发放额度" align="center" prop="recvreMarks" width="100" :show-overflow-tooltip="true"/>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200" fixed="right">
        <template #default="scope">
          <!--<el-button link type="primary" @click="handleReceipts(scope.row.id)" v-hasPermi="['business:project:edit']">收款记录</el-button>-->
          <el-button link type="primary" @click="handleUpdate(scope.row)" v-hasPermi="['business:project:edit']">修改</el-button>
          <el-button link type="primary" @click="handleDelete(scope.row)" v-hasPermi="['business:project:remove']">删除</el-button>
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

    <!-- 添加或修改项目信息对话框 -->
    <el-dialog :title="title" v-model="open" width="900px" append-to-body>
      <el-form ref="projectRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="计入年月" prop="yearmonth" >
              <el-date-picker clearable
                              v-model="form.yearmonth"
                              type="month"
                              value-format="YYYY-MM"
                              placeholder="请选择计入年月">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="业务类型" prop="businessType">
              <el-select v-model="form.businessType" placeholder="请选择业务类型">
                <el-option
                    v-for="dict in business_type"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="行业大类" prop="industryCategory">
              <el-select v-model="form.industryCategory" placeholder="请选择所属行业大类">
                <el-option
                    v-for="dict in industry_category"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行业细分" prop="industrySubcategory">
              <el-select v-model="form.industrySubcategory" placeholder="请选择所属行业细分" clearable>
                <el-option
                    v-for="dict in industry_subcategory"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="地域" prop="region" >
              <el-select v-model="form.region" placeholder="请选择地域">
                <el-option
                    v-for="dict in region"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目规模" prop="projectScale">
              <el-select v-model="form.projectScale" placeholder="请选择项目规模大小">
                <el-option
                    v-for="dict in project_scale"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="合同时间" prop="contractSxsj">
              <el-date-picker clearable
                              v-model="form.contractSxsj"
                              type="date"
                              value-format="YYYY-MM-DD"
                              placeholder="请选择合同生效时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="合同状态" prop="contractStatus">
              <el-select v-model="form.contractStatus" placeholder="请选择合同状态">
                <el-option
                    v-for="dict in contract_status"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="执行状态" prop="prjexecStatus" clearable>
              <el-select v-model="form.prjexecStatus"  placeholder="请选择项目执行状态">
                <el-option
                    v-for="dict in prjexec_status"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属部门" prop="department" label-position="left" clearable>
              <el-select v-model="form.department" style = "width: 60%" placeholder="请选择项目所属部门">
                <el-option
                    v-for="dict in department"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="结转部门" prop="originalDepartment">
              <el-input v-model="form.originalDepartment" placeholder="请输入结转原部门" style="width: 215px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工程号" prop="projectCode">
              <el-input v-model="form.projectCode" placeholder="请输入工程号" style="width: 215px;"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="项目阶段" prop="projectPhase">
              <el-select v-model="form.projectPhase" placeholder="请选择项目阶段">
                <el-option
                    v-for="dict in project_phase"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                ></el-option>
              </el-select>
<!--              <el-checkbox-group v-model="form.projectPhase">
                <el-checkbox
                    v-for="dict in project_phase"
                    :key="dict.value"
                    :label="dict.value">
                  {{dict.label}}
                </el-checkbox>
              </el-checkbox-group>-->
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="合同号" prop="contractNumber">
              <el-input v-model="form.contractNumber" placeholder="请输入合同号" style="width: 215px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="项目名称" prop="projectName">
              <el-input v-model="form.projectName" placeholder="请输入项目名称" style="width: 215px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="建设单位" prop="constructionUnit">
              <el-input v-model="form.constructionUnit" placeholder="请输入建设单位(委拖方)" style="width: 215px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="项目内容" prop="projectScope">
              <el-input v-model="form.projectScope" placeholder="请输入项目规模及内容" style="width: 215px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总投资" prop="totalInvestment">
              <el-input v-model="form.totalInvestment" placeholder="请输入项目总投资(万元)" style="width: 215px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="合同金额" prop="amtIncTax">
              <el-input v-model="form.amtIncTax" placeholder="请输入合同金额（含税）" style="width: 215px;"/>
            </el-form-item>
          </el-col>
<!--          <el-col :span="12">
            <el-form-item label="合同金额" prop="amtExcTax">
              <el-input v-model="form.amtExcTax" placeholder="请输入合同金额（不含税）"/>
            </el-form-item>
          </el-col>-->


          <el-col :span="12">
            <el-form-item label="阶段金额" prop="stageAmtIncTa">
              <el-input v-model="form.stageAmtIncTa" placeholder="请输入阶段合同额" style="width: 215px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="负责人" prop="projectManager">
              <el-select v-model="form.projectManager" placeholder="请选择项目负责人" clearable>
                <el-option
                  v-for="dict in project_manager"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="洽谈人" prop="negResp">
              <el-select v-model="form.negResp" placeholder="请输入合同洽谈负责人" clearable
                  prefix-icon="Search" filterable remote :remote-method="searchUsers"
                  :loading="userLoading" style="width: 215px;"
              >
                <el-option v-for="user in userList" :key="user.userId" :label="user.nickName" :value="user.nickName" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="行业总监" prop="industryDirector">
              <el-select v-model="form.industryDirector" placeholder="请选择行业总监">
                <el-option
                  v-for="dict in industry_director"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="执行人" prop="execResp">
              <el-select v-model="form.execResp" placeholder="请输入执行负责人" clearable
                         prefix-icon="Search" filterable remote :remote-method="searchUsers"
                         :loading="userLoading" style="width: 215px;"
              >
                <el-option v-for="user in userList" :key="user.userId" :label="user.nickName" :value="user.nickName" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="持证人" prop="certResp">
              <el-select v-model="form.certResp" placeholder="请输入持证负责人" clearable
                         prefix-icon="Search" filterable remote :remote-method="searchUsers"
                         :loading="userLoading" style="width: 215px;"
              >
                <el-option v-for="user in userList" :key="user.userId" :label="user.nickName" :value="user.nickName" />
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="EIM负责人" prop="eimResp">
              <el-select v-model="form.eimResp" placeholder="请输入EIM负责人" clearable
                         prefix-icon="Search" filterable remote :remote-method="searchUsers"
                         :loading="userLoading" style="width: 215px;"
              >
                <el-option v-for="user in userList" :key="user.userId" :label="user.nickName" :value="user.nickName" />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <!-- 分割线 -->
        <el-divider content-position="center">
          <el-button type="primary" plain v-if="selectedId != null"  @click="goToProjectReceipts" v-hasPermi="['business:project:edit']">收款记录</el-button>
        </el-divider>
        <el-row>
          <el-col :span="12">
            <el-form-item label="之前年度收款" prop="prevYrRecv" label-width="100">
              <el-input v-model="form.prevYrRecv" placeholder="请输入之前年度收款" />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="本年度收款" prop="currYrRecv" label-width="90">
              <el-input v-model="form.currYrRecv" placeholder="请输入本年度收款" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="项目未收款" prop="unrecdAmt" label-width="100">
              <!--<el-input v-model="form.unrecdAmt" placeholder="请输入项目未收款" />-->
              <el-input v-model="form.unrecdAmt" placeholder="自动计算" disabled />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目已收款" prop="recvdAmt" label-width="90">
              <!--<el-input v-model="form.recvdAmt" placeholder="请输入项目已收款" />-->
              <el-input v-model="form.recvdAmt" placeholder="自动计算" disabled />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="分包金额" prop="subcAmtIncTax" label-width="100">
              <el-input v-model="form.subcAmtIncTax" placeholder="请输入分包金额" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="已收款比例 " prop="recvRatio" label-width="90">
              <el-input v-model="form.recvRatio" placeholder="请输入已收款比例 " />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="本次奖金发放额度" prop="recvreMarks" label-width="130">
              <el-input v-model="form.recvreMarks" placeholder="请输入本次奖金发放额度" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="最新收款" prop="receipt_amount" label-width="90">
              <el-input v-model="form.receipt_amount" placeholder="自动计算" disabled />
            </el-form-item>
          </el-col>
        </el-row>
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

<script setup name="Project">
import { listProject, getProject, delProject, addProject, updateProject } from "@/api/business/project";
const router = useRouter();
import { watch } from 'vue';

const { proxy } = getCurrentInstance();
const { industry_director, project_scale, sys_common_status, department, industry_subcategory, region, project_manager, prjexec_status, industry_category, business_type, contract_status, project_phase } = proxy.useDict('industry_director', 'project_scale', 'sys_common_status', 'department', 'industry_subcategory', 'region', 'project_manager', 'prjexec_status', 'industry_category', 'business_type', 'contract_status', 'project_phase');

const projectList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const selectedId = ref(null);

// 设置百分比显示
const formatPercentage = (value) => {
  if (!value) return null;
  return `${(value * 1).toFixed(2)}%`;
};

const data = reactive({
  form: {
    id: null,
    yearmonth: null,
    businessType: null,
    industryCategory: null,
    industrySubcategory: null,
    region: null,
    projectScale: null,
    contractSxsj: null,
    contractStatus: null,
    prjexecStatus: null,
    department: null,
    originalDepartment: null,
    projectCode: null,
    projectPhase: null,
    contractNumber: null,
    projectName: null,
    constructionUnit: null,
    projectScope: null,
    totalInvestment: null,
    amtIncTax: null,
    amtExcTax: null,
    stageAmtIncTa: null,
    projectManager: null,
    negResp: null,
    industryDirector: null,
    execResp: null,
    certResp: null,
    eimResp: null,
    subcAmtIncTax: null,
    recvdAmt: null,
    unrecdAmt: null,
    recvRatio: null,
    prevYrRecv: null,
    currYrRecv: null,
    recvreMarks: null,
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
  },
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    yearmonth: null,
    businessType: null,
    contractNumber: null,
    projectName: null,
    projectManager: null,
  },
  rules: {
    yearmonth: [
      { required: true, message: "计入年月不能为空", trigger: "blur" }
    ],
    businessType: [
      { required: true, message: "业务类型不能为空", trigger: "change" }
    ],
    industryCategory: [
      { required: true, message: "行业大类不能为空", trigger: "change" }
    ],
    projectCode: [
      { required: true, message: "工程号不能为空", trigger: "blur" }
    ],
    projectPhase: [
      { required: true, message: "项目阶段不能为空", trigger: "change" }
    ],
    contractNumber: [
      { required: true, message: "合同号不能为空", trigger: "blur" }
    ],
    projectName: [
      { required: true, message: "项目名称不能为空", trigger: "blur" }
    ],
    projectManager: [
      { required: true, message: "项目负责人不能为空", trigger: "change" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询项目信息列表 */
import {  getLatestReceipts } from "@/api/business/receipts";
function getList() {
  loading.value = true;
  listProject(queryParams.value).then(response => {
    projectList.value = response.rows;
    total.value = response.total;
    // 获取每个项目的最新收款数据
    const projectIds = projectList.value.map(p => p.id);
    getLatestReceipts(projectIds).then(receiptData => {
      //console.log("【调试】后端返回的收款数据:", receiptData);
      //console.log("原始JSON:", JSON.stringify(receiptData));
      const receipts = receiptData || [];
      projectList.value.forEach(project => {
        const receipt = receiptData.find(r => r.prjId === project.id);
        if (receipt && receipt.receiptAmount !== undefined) {
          // 显示金额，保留两位小数
          project.receipt_amount = Number(receipt.receiptAmount).toFixed(2);
        } else {
          project.receipt_amount = '0.00'; // 默认值
        }
      });
    });

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
    yearmonth: null,
    businessType: null,
    industryCategory: null,
    industrySubcategory: null,
    region: null,
    projectScale: null,
    contractSxsj: null,
    contractStatus: null,
    prjexecStatus: null,
    department: null,
    originalDepartment: null,
    projectCode: null,
    projectPhase: null,
    contractNumber: null,
    projectName: null,
    constructionUnit: null,
    projectScope: null,
    totalInvestment: null,
    amtIncTax: null,
    amtExcTax: null,
    stageAmtIncTa: null,
    projectManager: null,
    negResp: null,
    industryDirector: null,
    execResp: null,
    certResp: null,
    eimResp: null,
    subcAmtIncTax: null,
    recvdAmt: null,
    unrecdAmt: null,
    recvRatio: null,
    prevYrRecv: null,
    currYrRecv: null,
    recvreMarks: null,
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
  proxy.resetForm("projectRef");
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
  selectedId.value = null;
  open.value = true;
  title.value = "添加项目信息";
}
/** 修改按钮操作 */
function handleUpdate(row) {
  selectedId.value = row.id;
  reset();
  const _id = row.id || ids.value
  getProject(_id).then(response => {
    form.value = response.data;
    //form.value.projectPhase = form.value.projectPhase.split(",");//不用复选可删
    // 手动赋值 receipt_amount 字段
    //console.log("修改的ID",response.data.id)
    if (row.receipt_amount) {
      form.value.receipt_amount = row.receipt_amount;
    }
    open.value = true;
    title.value = "修改项目信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["projectRef"].validate(valid => {
    if (valid) {
      //form.value.projectPhase = form.value.projectPhase.join(",");//不用复选可删
      if (form.value.id != null) {
        updateProject(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProject(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除项目信息编号为"' + _ids + '"的数据项？').then(function() {
    return delProject(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('business/project/export', {
    ...queryParams.value
  }, `project_${new Date().getTime()}.xlsx`)
}


function handleReceipts(id) {
  console.log("【调试】传入的 id 是：", id);
  if (!id) {
    console.warn("⚠️ id 为空");
  }
  router.push("/business/receipted/index/" + id);
}

function handleReceiptsInUpdate() {
  if (!form.value.id) {
    console.warn("⚠️ 当前表单没有有效的项目 ID");
    return;
  }
  router.push("/business/receipted/index/" + form.value.id);
}


// 自动计算已收款和未收款：项目已收款 = 之前年度收款 + 本年度收款，项目未收款 = 合同金额（含税） - 项目已收款
watch(
    () => [form.value.prevYrRecv, form.value.currYrRecv],
    ([prevYrRecv, currYrRecv]) => {
      const prev = parseFloat(prevYrRecv) || 0;
      const curr = parseFloat(currYrRecv) || 0;
      form.value.recvdAmt = (prev + curr).toFixed(2);

      // 触发未收款更新
      const total = parseFloat(form.value.amtIncTax) || 0;
      form.value.unrecdAmt = (total - (prev + curr)).toFixed(2);
    },
    { deep: true }
);

watch(
    () => form.value.amtIncTax,
    (amtIncTax) => {
      const total = parseFloat(amtIncTax) || 0;
      const recv = parseFloat(form.value.recvdAmt) || 0;
      form.value.unrecdAmt = (total - recv).toFixed(2);
    },
    { deep: true }
);

function goToProjectReceipts() {
  if (selectedId) {
    handleReceipts(selectedId.value);
    open.value  = false;
  }
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

getList();
</script>
