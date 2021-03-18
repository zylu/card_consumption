<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="SN号" prop="sn">
        <el-input
          v-model="queryParams.sn"
          placeholder="请输入门禁机SN号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="锁号" prop="lockNo">
        <el-input
          v-model="queryParams.lockNo"
          placeholder="请输入门禁机对应的锁号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="进出状态" prop="isIn">
        <el-select v-model="queryParams.isIn" placeholder="请选择进出状态" clearable size="small">
          <el-option
            v-for="dict in isInOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否有效" prop="isValid">
        <el-select v-model="queryParams.isValid" placeholder="请选择是否有效" clearable size="small">
          <el-option
            v-for="dict in isValidOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="卡号" prop="cardNo">
        <el-input
          v-model="queryParams.cardNo"
          placeholder="请输入刷卡的卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结果描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入结果描述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择记录类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="记录类型" prop="openTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.openTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择记录类型">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="学工号" prop="stuCode">
        <el-input
          v-model="queryParams.stuCode"
          placeholder="请输入学工号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员姓名" prop="accCname">
        <el-input
          v-model="queryParams.accCname"
          placeholder="请输入人员姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="人员部门" prop="depCname">
        <el-input
          v-model="queryParams.depCname"
          placeholder="请输入人员部门"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['door:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['door:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['door:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['door:record:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="人员部门" align="center" prop="id" />
      <el-table-column label="门禁机SN号" align="center" prop="sn" />
      <el-table-column label="门禁机对应的锁号" align="center" prop="lockNo" />
      <el-table-column label="进出状态" align="center" prop="isIn" :formatter="isInFormat" />
      <el-table-column label="是否有效" align="center" prop="isValid" :formatter="isValidFormat" />
      <el-table-column label="刷卡的卡号" align="center" prop="cardNo" />
      <el-table-column label="结果描述" align="center" prop="description" />
      <el-table-column label="记录类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="开门时间" align="center" prop="openTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.openTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学工号" align="center" prop="stuCode" />
      <el-table-column label="人员姓名" align="center" prop="accCname" />
      <el-table-column label="人员部门" align="center" prop="depCname" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['door:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['door:record:remove']"
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

    <!-- 添加或修改门禁记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="门禁机SN号" prop="sn">
          <el-input v-model="form.sn" placeholder="请输入门禁机SN号" />
        </el-form-item>
        <el-form-item label="门禁机对应的锁号" prop="lockNo">
          <el-input v-model="form.lockNo" placeholder="请输入门禁机对应的锁号" />
        </el-form-item>
        <el-form-item label="进门还是出门：1进门；2出门" prop="isIn">
          <el-select v-model="form.isIn" placeholder="请选择进门还是出门：1进门；2出门">
            <el-option
              v-for="dict in isInOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有效：禁止/通行：0禁止，1通过" prop="isValid">
          <el-select v-model="form.isValid" placeholder="请选择是否有效：禁止/通行：0禁止，1通过">
            <el-option
              v-for="dict in isValidOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="刷卡的卡号" prop="cardNo">
          <el-input v-model="form.cardNo" placeholder="请输入刷卡的卡号" />
        </el-form-item>
        <el-form-item label="结果描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入结果描述" />
        </el-form-item>
        <el-form-item label="记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录" prop="type">
          <el-select v-model="form.type" placeholder="请选择记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录" prop="openTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.openTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="学工号" prop="stuCode">
          <el-input v-model="form.stuCode" placeholder="请输入学工号" />
        </el-form-item>
        <el-form-item label="人员姓名" prop="accCname">
          <el-input v-model="form.accCname" placeholder="请输入人员姓名" />
        </el-form-item>
        <el-form-item label="人员部门" prop="depCname">
          <el-input v-model="form.depCname" placeholder="请输入人员部门" />
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
import { listRecord, getRecord, delRecord, addRecord, updateRecord, exportRecord } from "@/api/door/record";

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
      // 门禁记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 进门还是出门：1进门；2出门字典
      isInOptions: [],
      // 是否有效：禁止/通行：0禁止，1通过字典
      isValidOptions: [],
      // 记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sn: null,
        lockNo: null,
        isIn: null,
        isValid: null,
        cardNo: null,
        description: null,
        type: null,
        openTime: null,
        stuCode: null,
        accCname: null,
        depCname: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sn: [
          { required: true, message: "门禁机SN号不能为空", trigger: "blur" }
        ],
        lockNo: [
          { required: true, message: "门禁机对应的锁号不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "刷卡时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("acc_common_status").then(response => {
      this.isInOptions = response.data;
    });
    this.getDicts("acc_common_status").then(response => {
      this.isValidOptions = response.data;
    });
    this.getDicts("sys_notice_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    /** 查询门禁记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 进门还是出门：1进门；2出门字典翻译
    isInFormat(row, column) {
      return this.selectDictLabel(this.isInOptions, row.isIn);
    },
    // 是否有效：禁止/通行：0禁止，1通过字典翻译
    isValidFormat(row, column) {
      return this.selectDictLabel(this.isValidOptions, row.isValid);
    },
    // 记录类型：1，刷卡；2，门磁，按钮，设备启动，远程；3，报警记录字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
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
        sn: null,
        lockNo: null,
        isIn: null,
        isValid: null,
        cardNo: null,
        createTime: null,
        description: null,
        type: null,
        openTime: null,
        stuCode: null,
        accCname: null,
        depCname: null
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
      this.title = "添加门禁记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门禁记录";
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
      this.$confirm('是否确认删除门禁记录编号为"' + ids + '"的数据项?', "警告", {
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
      this.$confirm('是否确认导出所有门禁记录数据项?', "警告", {
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
