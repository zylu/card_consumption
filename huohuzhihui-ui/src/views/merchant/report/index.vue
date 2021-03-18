<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商户" prop="timeId">
        <el-select v-model="queryParams.merchantId" placeholder="请选择商户" clearable size="small">
          <el-option
            v-for="merchant in merchantOptions"
            :key="merchant.id"
            :label="merchant.name"
            :value="merchant.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="开始日期" prop="beginDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.beginDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束日期" prop="endDate">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="queryParams.endDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择结束日期">
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
          v-hasPermi="['merchant:order:export']"
        >导出</el-button>
      </el-col>
	    <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商户" align="center" prop="merchantName" />
      <el-table-column label="金额（元）" align="center" prop="sum" />
      <el-table-column label="笔数" align="center" prop="count" >
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listOrder, exportOrder } from "@/api/merchant/report";
import { listMerchant } from "@/api/merchant/merchant";
export default {
  name: "Report",
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
      // 商户商品订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
        //商户选项
      merchantOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        beginDate: null,
        endDate: null,
        merchantId: null,
      },

    };
  },
  created() {
    this.getList();
    listMerchant().then(response => {
      this.merchantOptions = response.rows;
      window.console.log(this.merchantOptions);
    });
  },
  methods: {
    /** 查询商户商品订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有数据项?', "警告", {
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
