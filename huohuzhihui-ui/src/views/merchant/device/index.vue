<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备编号" prop="no">
        <el-input
          v-model="queryParams.no"
          placeholder="请输入设备编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="序列号" prop="sn">
        <el-input
          v-model="queryParams.sn"
          placeholder="请输入设备序列号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ip地址" prop="ip">
        <el-input
          v-model="queryParams.ip"
          placeholder="请输入ip地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceTypeId">
        <el-select v-model="queryParams.deviceTypeId" placeholder="请选择设备类型" clearable size="small">
          <el-option
            v-for="dict in deviceTypeIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属商户" prop="merchantId">
        <el-select v-model="queryParams.merchantId" placeholder="请选择所属商户" clearable size="small">
          <el-option
            v-for="merchant in merchantOptions"
            :key="merchant.id"
            :label="merchant.name"
            :value="merchant.id"
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
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['merchant:device:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['merchant:device:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['merchant:device:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['merchant:device:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="设备编号" align="center" prop="no" />
      <el-table-column label="商户" align="center" prop="merchantName" />
      <el-table-column label="设备序列号" align="center" prop="sn" />
      <el-table-column label="ip地址" align="center" prop="ip" />
      <el-table-column label="端口号" align="center" prop="port" />
      <el-table-column label="设备类型" align="center" prop="deviceTypeId" :formatter="deviceTypeIdFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['merchant:device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['merchant:device:remove']"
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

    <!-- 添加或修改商户设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备类型" prop="deviceTypeId">
          <el-select v-model="form.deviceTypeId" placeholder="请选择设备类型">
            <el-option
              v-for="dict in deviceTypeIdOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所属商户" prop="merchantId">
          <el-select v-model="form.merchantId" placeholder="请选择所属商户">
            <el-option
              v-for="merchant in merchantOptions"
              :key="merchant.id"
              :label="merchant.name"
              :value="merchant.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备编号" prop="no">
          <el-input v-model="form.no" placeholder="请输入设备编号" />
        </el-form-item>

        <el-form-item label="序列号" prop="sn">
          <el-input v-model="form.sn" placeholder="请输入设备序列号" />
        </el-form-item>
        <el-form-item label="ip地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入ip地址" />
        </el-form-item>
        <el-form-item label="端口号" prop="port">
          <el-input v-model="form.port" placeholder="请输入端口号" />
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
import { listDevice, getDevice, delDevice, addDevice, updateDevice, exportDevice } from "@/api/merchant/device";
import { listMerchant } from "@/api/merchant/merchant";
export default {
  name: "Device",
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
      // 商户设备表格数据
      deviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 设备类型字典
      deviceTypeIdOptions: [],
      //商户选项
      merchantOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        no: null,
        sn: null,
        ip: null,
        deviceTypeId: null,
        merchantId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        no: [
          { required: true, message: "设备编号不能为空", trigger: "blur" }
        ],
        sn: [
          { required: true, message: "序列号不能为空", trigger: "blur" }
        ],
        deviceTypeId: [
          { required: true, message: "设备类型不能为空", trigger: "change" }
        ],
        merchantId: [
          { required: true, message: "所属商户不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("card_device_type").then(response => {
      this.deviceTypeIdOptions = response.data;
    });
    listMerchant().then(response => {
      this.merchantOptions = response.rows;
      window.console.log(this.merchantOptions);
    });
  },
  methods: {
    /** 查询商户设备列表 */
    getList() {
      this.loading = true;
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 设备类型字典翻译
    deviceTypeIdFormat(row, column) {
      return this.selectDictLabel(this.deviceTypeIdOptions, row.deviceTypeId);
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
        no: null,
        sn: null,
        ip: null,
        port: null,
        deviceTypeId: null,
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
      this.title = "添加商户设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDevice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商户设备";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDevice(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDevice(this.form).then(response => {
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
      this.$confirm('是否确认删除商户设备编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDevice(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有商户设备数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDevice(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
