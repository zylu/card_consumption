<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型：" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择交易类型：" clearable size="small">
          <el-option
            v-for="dict in tradeTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态：" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择订单状态：" clearable size="small">
          <el-option
            v-for="dict in orderStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="用户名称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="mobile">
        <el-input
          v-model="queryParams.mobile"
          placeholder="请输入手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.createTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.endTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="设备" prop="deviceId">
        <el-select v-model="queryParams.deviceId" placeholder="请选择设备：" clearable size="small">
          <el-option
            v-for="dict in deviceOptions"
            :key="dict.id"
            :label="dict.no"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="来源：" prop="source">
        <el-select v-model="queryParams.source" placeholder="请选择订单状态：" clearable size="small">
        <el-option
          v-for="dict in orderSourceOptions"
          :key="dict.dictValue"
          :label="dict.dictLabel"
          :value="dict.dictValue"
        />
        </el-select>
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
          v-hasPermi="['trade:order:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="交易类型" align="center" prop="type" :formatter="tradeTypeFormat"/>
      <el-table-column label="姓名" align="center" prop="userName" />
      <el-table-column label="金额" align="center" prop="amount" />
      <el-table-column label="订单状态" align="center" prop="status" :formatter="orderStatusFormat"/>
      <el-table-column label="创建时间" align="center" prop="createTime" />
      <el-table-column label="完成时间" align="center" prop="updateTime" />
      <el-table-column label="订单来源" align="center" prop="source" :formatter="orderSourceFormat"/>
      <!--<el-table-column label="设备ID" align="center" prop="deviceId" />-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="交易类型：" prop="type">
          <el-select v-model="form.type" placeholder="请选择交易类型：">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入账户ID" />
        </el-form-item>
        <el-form-item label="金额" prop="amount">
          <el-input v-model="form.amount" placeholder="请输入金额" />
        </el-form-item>
        <el-form-item label="订单状态：">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="订单来源：" prop="source">
          <el-input v-model="form.source" placeholder="请输入订单来源" />
        </el-form-item>
        <el-form-item label="设备ID" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入设备ID" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/trade/order";
import { listDevice } from "@/api/merchant/device";
export default {
  name: "Order",
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
      // 订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //交易类型
      tradeTypeOptions:[],
      //订单状态
      orderStatusOptions:[],
      //订单来源
      orderSourceOptions:[],
      //设备
      deviceOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        userName: null,
        mobile:null,
        amount: null,
        status: null,
        source: null,
        deviceId: null,
        beginTime: null,
        endTime: null,
        remarks: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "交易类型不能为空", trigger: "change" }
        ],
        accountId: [
          { required: true, message: "账户ID不能为空", trigger: "blur" }
        ],
        amount: [
          { required: true, message: "金额不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "订单状态不能为空", trigger: "blur" }
        ],
        source: [
          { required: true, message: "订单来源不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("acc_trade_type").then(response => {
      this.tradeTypeOptions = response.data;
    });
    this.getDicts("merchant_goods_order_status").then(response => {
      this.orderStatusOptions = response.data;
    });
    this.getDicts("trade_order_source").then(response => {
      this.orderSourceOptions = response.data;
    });
    //设备列表
    listDevice().then(response => {
      this.deviceOptions = response.rows;
    });
  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 设备类型字典翻译
    tradeTypeFormat(row, column) {
      return this.selectDictLabel(this.tradeTypeOptions, row.type);
    },
    // 设备类型字典翻译
    orderStatusFormat(row, column) {
      return this.selectDictLabel(this.orderStatusOptions, row.status);
    },
    // 设备类型字典翻译
    orderSourceFormat(row, column) {
      return this.selectDictLabel(this.orderSourceOptions, row.source);
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
        type: null,
        accountId: null,
        amount: null,
        status: 0,
        source: null,
        deviceId: null,
        remarks: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
