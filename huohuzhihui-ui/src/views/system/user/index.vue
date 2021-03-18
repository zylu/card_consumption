<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="用户姓名" prop="userName">
            <el-input
              v-model="queryParams.userName"
              placeholder="请输入用户姓名"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="手机号码" prop="phonenumber">
            <el-input
              v-model="queryParams.phonenumber"
              placeholder="请输入手机号码"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="用户状态"
              clearable
              size="small"
              style="width: 240px"
            >
              <el-option
                v-for="dict in statusOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="卡号" prop="cardNo">
            <el-input
              v-model="queryParams.cardNo"
              placeholder="请刷卡"
              clearable
              size="small"
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="创建时间">
            <el-date-picker
              v-model="dateRange"
              size="small"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:user:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate"
              v-hasPermi="['system:user:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['system:user:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="info"
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
              v-hasPermi="['system:user:import']"
            >导入</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['system:user:export']"
            >导出</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-money"
              size="mini"
              :disabled="single"
              @click="handleRecharge"
              v-hasPermi="['account:account:recharge']"
            >充值
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="info"
              icon="el-icon-money"
              size="mini"
              :disabled="single"
              @click="handleSubsidy"
              v-hasPermi="['account:subsidy:add']"
            >补贴</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleFreeze"
              v-hasPermi="['account:account:remove']"
            >冻结
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleUnfreeze"
              v-hasPermi="['account:account:remove']"
            >解冻
            </el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleReplace"
              v-hasPermi="['account:card:replace']"
            >补卡</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="warning"
              icon="el-icon-loss"
              size="mini"
              :disabled="multiple"
              @click="handleLoss"
              v-hasPermi="['account:card:loss']"
            >挂失</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              icon="el-icon-unloss"
              size="mini"
              :disabled="multiple"
              @click="handleUnloss"
              v-hasPermi="['account:card:unloss']"
            >解挂</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-loss"
              size="mini"
              :disabled="multiple"
              @click="handleCancel"
              v-hasPermi="['account:card:cancel']"
            >注销</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="用户编号" align="center" prop="userId" />
          <el-table-column label="用户姓名" align="center" prop="userName" :show-overflow-tooltip="true" />
          <!--<el-table-column label="用户昵称" align="center" prop="nickName" :show-overflow-tooltip="true" />-->
          <el-table-column label="部门" align="center" prop="dept.deptName" :show-overflow-tooltip="true" />
          <el-table-column label="手机号码" align="center" prop="phonenumber" width="120" />
          <el-table-column label="账户余额" align="center" prop="balance" width="120">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.accountStatus == 0" type="success">{{scope.row.balance}}</el-tag>
              <el-tag v-if="scope.row.accountStatus == 1" type="warning">{{scope.row.balance}}</el-tag>
              <el-tag v-if="scope.row.accountStatus == 2" type="danger">{{scope.row.balance}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="卡号" align="center" prop="cardNo" width="120">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.cardStatus == 0" type="success">{{scope.row.cardNo}}</el-tag>
              <el-tag v-if="scope.row.cardStatus == 1" type="warning">{{scope.row.cardNo}}</el-tag>
              <el-tag v-if="scope.row.cardStatus == 2" type="danger">{{scope.row.cardNo}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="状态" align="center">
            <template slot-scope="scope">
              <el-switch
                v-model="scope.row.status"
                active-value="0"
                inactive-value="1"
                @change="handleStatusChange(scope.row)"
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" align="center" prop="createTime" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:user:edit']"
              >修改</el-button>
              <el-button
                v-if="scope.row.userId !== 1"
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:user:remove']"
              >删除</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-key"
                @click="handleResetPwd(scope.row)"
                v-hasPermi="['system:user:resetPwd']"
              >重置</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item v-if="form.userId == undefined" label="用户姓名" prop="userName">
              <el-input v-model="form.userName" placeholder="请输入用户姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">
              <el-input v-model="form.password" placeholder="请输入用户密码" type="password" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="归属部门" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" placeholder="请选择归属部门" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="手机号码" prop="phonenumber">
              <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="用户性别">
              <el-select v-model="form.sex" placeholder="请选择">
                <el-option
                  v-for="dict in sexOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="岗位">
              <el-select v-model="form.postIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in postOptions"
                  :key="item.postId"
                  :label="item.postName"
                  :value="item.postId"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="角色">
              <el-select v-model="form.roleIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in roleOptions"
                  :key="item.roleId"
                  :label="item.roleName"
                  :value="item.roleId"
                  :disabled="item.status == 1"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <div class="text-center">
              <userAvatar :user="user" />
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="卡号"  prop="cardNo">
              <el-input v-model="form.cardNo" placeholder="请刷卡" :readonly="readonly"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
              <el-button type="success" @click="generateCardNo" :disabled="disabled">生成一个</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="余额">
                <el-input v-model="form.balance" placeholder="请输入余额" :readonly="readonly" oninput ="value=value.replace(/[^0-9.]/g,'')"/>
                <template #append>元</template>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="24">
            <el-form-item label="备注">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的用户数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 充值对话框 -->
    <el-dialog :title="title" :visible.sync="rechargeOpen" width="500px" append-to-body>
      <el-form ref="rechargeform" :model="rechargeData" :rules="rules" label-width="80px">
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="rechargeData.userName" placeholder="请输入用户" readonly/>
        </el-form-item>
        <el-form-item label="当前余额" prop="balance">
          <el-input v-model="rechargeData.balance" readonly/>
        </el-form-item>
        <el-form-item label="充值金额（元）" prop="amount">
          <el-radio-group v-model="rechargeData.amount">
            <el-radio
              v-for="dict in addMoneyOptions" :key="dict.dictValue" :label="parseInt(dict.dictValue)">{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="支付渠道" prop="channelCode">
          <el-select v-model="rechargeData.channelCode">
            <el-option
              v-for="dict in payChannelOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitRechargeForm">确 定</el-button>
        <el-button @click="cancelRecharge">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改账户下的卡对话框 -->
    <el-dialog :title="title" :visible.sync="replaceCardOpen" width="500px" append-to-body>
      <el-form ref="cardForm" :model="cardForm" :rules="cardRules" label-width="80px">
        <el-form-item label="姓名" prop="userId">
          <el-input v-model="cardForm.userName" readonly/>
          <el-input v-model="cardForm.userId" v-if="false"/>
        </el-form-item>
        <el-form-item label="卡类型" prop="cardTypeId">
          <el-select v-model="cardForm.cardTypeId" placeholder="请选择卡类型" @change="changeCardType">
            <el-option
              v-for="cardtype in cardTypeIdOptions"
              :key="cardtype.id"
              :label="cardtype.name"
              :value="cardtype.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="押金" prop="deposit">
          <el-input v-model="cardForm.deposit" readonly/>
        </el-form-item>
        <el-form-item label="制卡费" prop="cost">
          <el-input v-model="cardForm.cost" readonly/>
        </el-form-item>
        <el-form-item label="卡号" prop="cardNo">
          <el-input v-model="cardForm.cardNo" placeholder="请输入卡号" />
        </el-form-item>
        <el-form-item label="卡状态" prop="status">
          <el-select v-model="cardForm.status" placeholder="请选择卡状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="cardForm.remarks" placeholder="请输入备注" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitCardForm">确 定</el-button>
        <el-button @click="cancelCardForm">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加补贴对话框 -->
    <el-dialog :title="title" :visible.sync="subsidyFormOpen" width="500px" append-to-body>
      <el-form ref="subsidyForm" :model="subsidyForm" :rules="subsidyRules" label-width="80px">
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="subsidyForm.userName" placeholder="请输入用户" readonly/>
        </el-form-item>
        <el-form-item label="当前余额" prop="balance">
          <el-input v-model="subsidyForm.balance" readonly/>
        </el-form-item>
        <el-form-item label="补贴金额（元）" prop="amount">
          <el-radio-group v-model="subsidyForm.amount">
            <el-radio
              v-for="dict in addMoneyOptions" :key="dict.dictValue" :label="parseInt(dict.dictValue)">{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSubsidyForm">确 定</el-button>
        <el-button @click="cancelSubsidyForm">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser, exportUser, resetUserPwd, changeUserStatus, importTemplate,freezeAccount,unfreezeAccount,getAccount,recharge,getUserProfile } from "@/api/system/user";
import { getToken } from "@/utils/auth";
import { treeselect } from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { lossCard,unlossCard,cancelCard,replaceCard } from "@/api/account/card";
import { listCardtype } from "@/api/account/cardtype";
import {addSubsidy } from "@/api/account/subsidy";
import userAvatar from "./userAvatar";
export default {
  name: "User",
  components: { Treeselect,userAvatar },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 状态数据字典
      statusOptions: [],
      // 性别状态字典
      sexOptions: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      readonly:false,
      disabled:false,
      //充值弹出框
      rechargeOpen: false,
      //充值参数
      rechargeData: {
        userName: "",
        id: "",
        balance: "",
        amount: "",
        channelCode: ""
      },
      //增款金额
      addMoneyOptions: [],
      //支付渠道
      parChannelOptions: [],
      //补卡业务开始
      //补卡弹出框
      replaceCardOpen: false,
      cardForm:{
        id: null,
        userId: null,
        userName:"",
        cardTypeId:"",
        deposit:0.00,
        cost:0.00,
        cardNo:null,
        status:0,
        remarks:""
      },
      // 卡类型字典
      cardTypeIdOptions: [],
      // 卡状态：0，正常，1挂失，2注销字典
      statusOptions: [],
      //补贴业务开始
      subsidyFormOpen:false,
      subsidyForm:{
        id: null,
        userId: null,
        amount:"",
        source:""
      },
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined,
        cardNo:undefined
      },
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户姓名不能为空", trigger: "blur" }
        ],
        /*nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],*/
        deptId: [
          { required: true, message: "归属部门不能为空", trigger: "change" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" }
        ],
        /*email: [
          { required: true, message: "邮箱地址不能为空", trigger: "blur" },
          {
            type: "email",
            message: "'请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],*/
        phonenumber: [
          { required: true, message: "手机号码不能为空", trigger: "blur" },
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ],
        cardNo: [
          { required: true, message: "卡号不能为空", trigger: "blur" }
        ]
      },
      // 补卡表单校验
      cardRules: {
        cardTypeId: [
          { required: true, message: "卡类型不能为空", trigger: "change" }
        ],
        cardNo: [
          { required: true, message: "卡号不能为空", trigger: "blur" }
        ],
        accountId: [
          { required: true, message: "账户不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "卡状态", trigger: "change" }
        ],
      },
      // 补贴表单校验
      subsidyRules: {
        userId: [
          { required: true, message: "用户不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ]
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    this.getTreeselect();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.sexOptions = response.data;
    });
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
    //增款金额字典
    this.getDicts("money_amount").then(response => {
      this.addMoneyOptions = response.data;
    });
    //支付渠道
    this.getDicts("pay_channel").then(response => {
      this.payChannelOptions = response.data;
      this.rechargeData.channelCode = response.data[0].id;
    });
    listCardtype().then(response => {
      this.cardTypeIdOptions = response.rows;
    });
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listUser(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    /** 查询部门下拉树结构 */
    getTreeselect() {
      treeselect().then(response => {
        this.deptOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList();
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
      this.$confirm('确认要"' + text + '""' + row.userName + '"用户吗?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return changeUserStatus(row.userId, row.status);
        }).then(() => {
          this.msgSuccess(text + "成功");
        }).catch(function() {
          row.status = row.status === "0" ? "1" : "0";
        });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        postIds: [],
        roleIds: [],
        balance: 0.00,
        cardNo:undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.page = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect();
      this.readonly =false;
      this.disabled =false;
      getUser().then(response => {
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.open = true;
        this.title = "添加用户";
        this.form.password = this.initPassword;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const userId = row.userId || this.ids;
      this.readonly =true;
      this.disabled =true;
      getUser(userId).then(response => {
        this.form = response.data;
        this.postOptions = response.posts;
        this.roleOptions = response.roles;
        this.form.postIds = response.postIds;
        this.form.roleIds = response.roleIds;
        this.open = true;
        this.title = "修改用户";
        this.form.password = "";
      });
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消"
      }).then(({ value }) => {
          resetUserPwd(row.userId, value).then(response => {
            this.msgSuccess("修改成功，新密码是：" + value);
          });
        }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != undefined) {
            updateUser(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUser(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$confirm('是否确认删除用户编号为"' + userIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delUser(userIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportUser(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      importTemplate().then(response => {
        this.download(response.msg);
      });
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    /** 冻结按钮操作 */
    handleFreeze(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认冻结（冻结后将不能使用）账户编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return freezeAccount(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("冻结成功");
      })
    },
    /** 解冻按钮操作 */
    handleUnfreeze(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认解冻账户编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return unfreezeAccount(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("解冻成功");
      })
    },
    /** 注销按钮操作 */
    handleCancel(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认注销（注销后将不能使用和恢复）账户编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return cancelAccount(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("注销成功");
      })
    },
    /** 充值按钮操作 */
    handleRecharge(row) {
      var that = this;
      const id = row.id || this.ids;
      getAccount(id).then(response => {
        that.rechargeData = {"id": id, "userName": response.data.userName, "balance": response.data.balance};
        that.rechargeOpen = true;
        that.title = "充值";
      });
    },
    // 取消充值按钮
    cancelRecharge() {
      this.rechargeOpen = false;
      this.resetRechargeForm();
    },
    // 充值表单重置
    resetRechargeForm() {
      this.rechargeform = {
        id: null,
        userName: null,
        balance: null
      };
      this.resetForm("rechargeForm");
    },
    //
    /** 提交充值按钮 */
    submitRechargeForm() {
      recharge(this.rechargeData.id, this.rechargeData.amount, this.rechargeData.channelCode).then(response => {
        this.msgSuccess("充值成功");
        this.rechargeOpen = false;
        this.getList();
      });
    },
    /** 换卡按钮操作 */
    handleReplace(row) {
      this.resetCardForm();
      var that = this;
      const id = row.id || that.ids;

      that.cardForm.userId = id[0];
      getUser(id).then(response => {
        that.cardForm.userName = response.data.userName;
      });
      //默认选择一种卡状态
      that.cardForm.status = 0;
      that.replaceCardOpen = true;
      that.title = "补卡";
    },
    /** 提交按钮 */
    submitCardForm() {
      this.$refs["cardForm"].validate(valid => {
        if (valid) {
            replaceCard(this.cardForm).then(response => {
              this.msgSuccess("补卡成功");
              this.replaceCardOpen = false;
              this.getList();
            });
        }
      });
    },
    // 取消按钮
    cancelCardForm() {
      this.replaceCardOpen = false;
      this.resetCardForm();
    },
    // 表单重置
    resetCardForm() {
      this.cardForm = {
        id: null,
        userId: null,
        userName:"",
        cardTypeId:"",
        deposit:0.00,
        cost:0.00,
        cardNo:null,
        status:0,
        remarks:""
      };
      this.resetForm("cardForm");
    },
    //选择一种卡类型，那么对应的费用也要提示出来
    changeCardType(cardTypeId){
      for(var i = 0; i < this.cardTypeIdOptions.length; i++){
        var cardType = this.cardTypeIdOptions[i];
        if(cardType.id==cardTypeId){
          this.cardForm.deposit = cardType.deposit;
          //判断是不是首次开卡
          this.cardForm.cost = cardType.addcost;
          break;
        }
      }
    },
    /** 挂失 */
    handleLoss(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认挂失账户下的卡编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return lossCard(ids);
      }).then(() => {
        this.getList();
        this.msgSuccess("挂失成功");
      })
    },
    /** 解挂 */
    handleUnloss(row) {
      const ids = row.id || this.ids;
      unlossCard(ids).then(response => {
        this.getList();
        this.msgSuccess("解挂成功");
      });
    },
    /** 注销 */
    handleCancel(row) {
      const ids = row.id || this.ids;
      cancelCard(ids).then(response => {
        this.getList();
        this.msgSuccess("注销成功");
      });
    },
    //补贴业务开始
    /** 补贴按钮操作 */
    handleSubsidy(row) {
      var that = this;
      that.subsidyReset();
      const id = row.id || this.ids;
      getAccount(id).then(response => {
        that.subsidyFormOpen = true;
        that.title = "补贴";
        that.subsidyForm.source = 4;
        that.subsidyForm.userId = id[0];
        that.subsidyForm.userName = response.data.userName;
        that.subsidyForm.balance = response.data.balance;
      });
    },
    subsidyReset() {
      this.subsidyForm = {
        id: null,
      };
      this.resetForm("subsidyForm");
    },
    /** 提交按钮 */
    submitSubsidyForm() {
      this.$refs["subsidyForm"].validate(valid => {
        if (valid) {
          addSubsidy(this.subsidyForm).then(response => {
            this.msgSuccess("补贴成功");
            this.subsidyFormOpen = false;
            this.getList();
          });

        }
      });
    },
    // 取消按钮
    cancelSubsidyForm() {
      this.subsidyFormOpen = false;
      this.subsidyReset();
    },
    //随机生成一个十位数卡号
    generateCardNo(){
      var random = Math.floor((Math.random()+Math.floor(Math.random()*9+1))*Math.pow(10,10-1));
      this.form.cardNo = random;
    }

  }
};
</script>
