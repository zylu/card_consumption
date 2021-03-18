<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!--<el-form-item label="卡类型名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入卡类型名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['account:cardtype:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['account:cardtype:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['account:cardtype:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:cardtype:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cardtypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="卡类型编号" align="center" prop="id" />
      <el-table-column label="卡类型名称" align="center" prop="name" />
      <el-table-column label="押金（元）" align="center" prop="deposit" :formatter="depositFormat" />
      <el-table-column label="管理费（元）" align="center" prop="addfare" :formatter="addfareFormat" />
      <el-table-column label="新发卡成本费（元）" align="center" prop="firstcost" :formatter="firstcostFormat" />
      <el-table-column label="补卡成本费（元）" align="center" prop="addcost" :formatter="addcostFormat" />
      <el-table-column label="有效期" align="center" prop="avdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.avdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易次数限制" align="center" prop="timelimit" />
      <el-table-column label="消费折扣" align="center" prop="discount" :formatter="discountFormat" />
      <el-table-column label="备注信息" align="center" prop="remarks" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['account:cardtype:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['account:cardtype:remove']"
          >删除</el-button>
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

    <!-- 添加或修改卡类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="170px">
        <el-form-item label="卡类型名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入卡类型名称" />
        </el-form-item>
        <el-form-item label="押金" prop="deposit">
          <el-select v-model="form.deposit" placeholder="请选择押金">
            <el-option
              v-for="dict in depositOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="管理费" prop="addfare">
          <el-select v-model="form.addfare" placeholder="请选择管理费">
            <el-option
              v-for="dict in addfareOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="新发卡成本费(元)" prop="firstcost">
          <el-input v-model="form.firstcost" placeholder="请输入新发卡成本费(单位为元)"/>
        </el-form-item>
        <el-form-item label="补卡成本费(元)" prop="addcost">
          <el-input v-model="form.addcost" placeholder="请输入补卡成本费(单位为元)" />
        </el-form-item>
        <el-form-item label="有效期" prop="avdate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.avdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择有效期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易次数限制" prop="timelimit">
          <el-input v-model="form.timelimit" placeholder="请输入交易次数限制" />
        </el-form-item>
        <el-form-item label="消费折扣" prop="discount">
          <el-select v-model="form.discount" placeholder="请选择消费折扣">
            <el-option
              v-for="dict in discountOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注信息" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注信息" type="textarea"/>
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
import { listCardtype, getCardtype, delCardtype, addCardtype, updateCardtype, exportCardtype } from "@/api/account/cardtype";

export default {
  name: "Cardtype",
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
      // 卡类型表格数据
      cardtypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 押金字典
      depositOptions: [],
      // 管理费字典
      addfareOptions: [],
      // 新发卡成本费(单位为元)字典
      firstcostOptions: [],
      // 补卡成本费(单位为元)字典
      addcostOptions: [],
      // 有效期字典
      avdateOptions: [],
      // 消费折扣字典
      discountOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("acc_account_deposit").then(response => {
      this.depositOptions = response.data;
    });
    this.getDicts("acc_account_addfare").then(response => {
      this.addfareOptions = response.data;
    });
    this.getDicts("money_amount").then(response => {
      this.firstcostOptions = response.data;
    });
    this.getDicts("money_amount").then(response => {
      this.addcostOptions = response.data;
    });
    this.getDicts("acc_account_avdate").then(response => {
      this.avdateOptions = response.data;
    });
    this.getDicts("consume_discount").then(response => {
      this.discountOptions = response.data;
    });
  },
  methods: {
    /** 查询卡类型列表 */
    getList() {
      this.loading = true;
      listCardtype(this.queryParams).then(response => {
        this.cardtypeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 押金字典翻译
    depositFormat(row, column) {
      return this.selectDictLabel(this.depositOptions, row.deposit);
    },
    // 管理费字典翻译
    addfareFormat(row, column) {
      return this.selectDictLabel(this.addfareOptions, row.addfare);
    },
    // 新发卡成本费(单位为元)字典翻译
    firstcostFormat(row, column) {
      return this.selectDictLabel(this.firstcostOptions, row.firstcost);
    },
    // 补卡成本费(单位为元)字典翻译
    addcostFormat(row, column) {
      return this.selectDictLabel(this.addcostOptions, row.addcost);
    },
    // 有效期字典翻译
    avdateFormat(row, column) {
      return this.selectDictLabel(this.avdateOptions, row.avdate);
    },
    // 消费折扣字典翻译
    discountFormat(row, column) {
      return this.selectDictLabel(this.discountOptions, row.discount);
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
        name: null,
        deposit: null,
        addfare: null,
        firstcost: null,
        addcost: null,
        avdate: null,
        timelimit: null,
        discount: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remarks: null,
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
      this.title = "添加卡类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCardtype(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改卡类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCardtype(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCardtype(this.form).then(response => {
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
      this.$confirm('是否确认删除卡类型编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCardtype(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有卡类型数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCardtype(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
