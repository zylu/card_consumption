<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">

      <el-form-item label="订单号" prop="tradeOrderId">
        <el-input
          v-model="queryParams.tradeOrderId"
          placeholder="请输入交易订单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="交易时间" prop="tradeTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.tradeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择交易时间">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['trade:record:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
    <!--  <el-table-column label="流水类型" align="center" prop="type" :formatter="typeFormat" />-->
      <el-table-column label="账户名称" align="center" prop="accountName" />
      <el-table-column label="金额" align="center" prop="amount" />
     <!-- <el-table-column label="交易订单号" align="center" prop="tradeOrderId" />
      <el-table-column label="交易流水号" align="center" prop="tradeNo" />-->
      <el-table-column label="支付渠道" align="center" prop="channelName" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="交易时间" align="center" prop="tradeTime" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.tradeTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作人" align="center" prop="createBy"  />
      <el-table-column label="备注" align="center" prop="remark" />
      <!--
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改交易记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入金额" />
        </el-form-item>
        <el-form-item label="流水类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择流水类型 ">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易流水号" prop="tradeNo">
          <el-input v-model="form.tradeNo" placeholder="请输入交易流水号" />
        </el-form-item>
        <el-form-item label="交易渠道编码" prop="channelCode">
          <el-input v-model="form.channelCode" placeholder="请输入交易渠道编码" />
        </el-form-item>
        <el-form-item label="交易渠道名称" prop="channelName">
          <el-input v-model="form.channelName" placeholder="请输入交易渠道名称" />
        </el-form-item>
        <el-form-item label="交易订单号" prop="tradeOrderId">
          <el-input v-model="form.tradeOrderId" placeholder="请输入交易订单号" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="账户名称" prop="accountName">
          <el-input v-model="form.accountName" placeholder="请输入账户名称" />
        </el-form-item>
        <el-form-item label="交易时间" prop="tradeTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.tradeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="账户id" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入账户id" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord, exportRecord } from "@/api/trade/record";

export default {
  name: "Record",
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
      // 交易记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 流水类型 1充值 2 消费字典
      typeOptions: [],
      // 状态  1 已入账 2未入账字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        amount: null,
        type: null,
        tradeNo: null,
        channelCode: null,
        channelName: null,
        tradeOrderId: null,
        status: null,
        accountName: null,
        tradeTime: null,
        accountId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        amount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "流水类型", trigger: "change" }
        ],
        tradeNo: [
          { required: true, message: "交易流水号不能为空", trigger: "blur" }
        ],
        channelCode: [
          { required: true, message: "交易渠道编码不能为空", trigger: "blur" }
        ],
        channelName: [
          { required: true, message: "交易渠道名称不能为空", trigger: "blur" }
        ],
        tradeOrderId: [
          { required: true, message: "交易订单号不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        accountName: [
          { required: true, message: "账户名称不能为空", trigger: "blur" }
        ],
        tradeTime: [
          { required: true, message: "交易时间不能为空", trigger: "blur" }
        ],
        accountId: [
          { required: true, message: "账户id不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("acc_trade_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("acc_trade_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询交易记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 流水类型 1充值 2 消费字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 状态  1 已入账 2未入账字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        amount: null,
        type: null,
        tradeNo: null,
        channelCode: null,
        channelName: null,
        tradeOrderId: null,
        status: 0,
        accountName: null,
        tradeTime: null,
        accountId: null,
        createTime: null,
        createBy: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加交易记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改交易记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateRecord(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除交易记录编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRecord(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有交易记录数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRecord(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
