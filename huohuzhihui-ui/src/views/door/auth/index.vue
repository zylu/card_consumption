<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="门的id" prop="doorId">
        <el-input
          v-model="queryParams.doorId"
          placeholder="请输入门的id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一卡通账户id" prop="accId">
        <el-input
          v-model="queryParams.accId"
          placeholder="请输入一卡通账户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授权起始日期" prop="startTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择授权起始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="授权终止日期" prop="endTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择授权终止日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="门禁卡号" prop="cardNo">
        <el-input
          v-model="queryParams.cardNo"
          placeholder="请输入门禁卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门禁卡号" prop="stuCode">
        <el-input
          v-model="queryParams.stuCode"
          placeholder="请输入门禁卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否写入" prop="isWrited">
        <el-select v-model="queryParams.isWrited" placeholder="请选择是否已写入门禁机" clearable size="small">
          <el-option
            v-for="dict in isWritedOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="宝石卡号" prop="bsCardNo">
        <el-input
          v-model="queryParams.bsCardNo"
          placeholder="请输入宝石卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="扩展权限" prop="authExtend">
        <el-input
          v-model="queryParams.authExtend"
          placeholder="请输入扩展权限"
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
          v-hasPermi="['door:auth:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['door:auth:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['door:auth:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['door:auth:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="authList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="扩展权限" align="center" prop="id" />
      <el-table-column label="门的id" align="center" prop="doorId" />
      <el-table-column label="一卡通账户id" align="center" prop="accId" />
      <el-table-column label="授权起始日期" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="授权终止日期" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="门禁卡号" align="center" prop="cardNo" />
      <el-table-column label="门禁卡号" align="center" prop="stuCode" />
      <el-table-column label="是否写入" align="center" prop="isWrited" :formatter="isWritedFormat" />
      <el-table-column label="宝石卡号" align="center" prop="bsCardNo" />
      <el-table-column label="扩展权限" align="center" prop="authExtend" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['door:auth:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['door:auth:remove']"
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

    <!-- 添加或修改门禁授权对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="门的id" prop="doorId">
          <el-input v-model="form.doorId" placeholder="请输入门的id" />
        </el-form-item>
        <el-form-item label="一卡通账户id" prop="accId">
          <el-input v-model="form.accId" placeholder="请输入一卡通账户id" />
        </el-form-item>
        <el-form-item label="授权起始日期" prop="startTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择授权起始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="授权终止日期" prop="endTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择授权终止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="删除标记：0未删除，1删除" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标记：0未删除，1删除" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="门禁卡号" prop="cardNo">
          <el-input v-model="form.cardNo" placeholder="请输入门禁卡号" />
        </el-form-item>
        <el-form-item label="门禁卡号：" prop="stuCode">
          <el-input v-model="form.stuCode" placeholder="请输入门禁卡号" />
        </el-form-item>
        <el-form-item label="是否写入门禁机" prop="isWrited">
          <el-select v-model="form.isWrited" placeholder="请选择是否已写入门禁机">
            <el-option
              v-for="dict in isWritedOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="宝石卡号" prop="bsCardNo">
          <el-input v-model="form.bsCardNo" placeholder="请输入宝石卡号" />
        </el-form-item>
        <el-form-item label="扩展权限" prop="authExtend">
          <el-input v-model="form.authExtend" placeholder="请输入扩展权限" />
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
import { listAuth, getAuth, delAuth, addAuth, updateAuth, exportAuth } from "@/api/door/auth";

export default {
  name: "Auth",
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
      // 门禁授权表格数据
      authList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否已写入门禁机：0，未写入，1已写入字典
      isWritedOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        doorId: null,
        accId: null,
        startTime: null,
        endTime: null,
        cardNo: null,
        stuCode: null,
        isWrited: null,
        bsCardNo: null,
        authExtend: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        doorId: [
          { required: true, message: "门的id不能为空", trigger: "blur" }
        ],
        startTime: [
          { required: true, message: "授权起始日期不能为空", trigger: "blur" }
        ],
        endTime: [
          { required: true, message: "授权终止日期不能为空", trigger: "blur" }
        ],
        stuCode: [
          { required: true, message: "授权终止日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("acc_common_status").then(response => {
      this.isWritedOptions = response.data;
    });
  },
  methods: {
    /** 查询门禁授权列表 */
    getList() {
      this.loading = true;
      listAuth(this.queryParams).then(response => {
        this.authList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否已写入门禁机：0，未写入，1已写入字典翻译
    isWritedFormat(row, column) {
      return this.selectDictLabel(this.isWritedOptions, row.isWrited);
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
        doorId: null,
        accId: null,
        startTime: null,
        endTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        cardNo: null,
        stuCode: null,
        isWrited: null,
        bsCardNo: null,
        authExtend: null
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
      this.title = "添加门禁授权";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAuth(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门禁授权";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAuth(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAuth(this.form).then(response => {
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
      this.$confirm('是否确认删除门禁授权编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAuth(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门禁授权数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAuth(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
