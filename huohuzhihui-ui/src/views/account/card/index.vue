<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="卡类型" prop="cardTypeId">
        <el-select v-model="queryParams.cardTypeId" placeholder="请选择卡类型" clearable size="small">
          <el-option
            v-for="cardtype in cardTypeIdOptions"
            :key="cardtype.id"
            :label="cardtype.name"
            :value="cardtype.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="卡号" prop="cardNo">
        <el-input
          v-model="queryParams.cardNo"
          placeholder="请输入卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账户" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入账户"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卡状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择卡状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
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
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['account:card:add']"
        >开卡</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
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

    <el-table v-loading="loading" :data="cardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="卡编号" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="userName" />
      <el-table-column label="卡号" align="center" prop="cardNo" />
      <el-table-column label="卡类型" align="center" prop="cardTypeId" :formatter="cardTypeFormat" />
      <el-table-column label="卡状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="开卡时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <!--<template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['account:card:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['account:card:remove']"
          >删除</el-button>
        </template>-->
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改账户下的卡对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="accountId">
          <el-input v-model="userName" readonly/>
          <el-input v-model="form.accountId" v-if="false"/>
        </el-form-item>
        <el-form-item label="卡类型" prop="cardTypeId">
          <el-select v-model="form.cardTypeId" placeholder="请选择卡类型" @change="changeCardType">
            <el-option
              v-for="cardtype in cardTypeIdOptions"
              :key="cardtype.id"
              :label="cardtype.name"
              :value="cardtype.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="押金" prop="deposit">
          <el-input v-model="form.deposit" readonly/>
        </el-form-item>
        <el-form-item label="制卡费" prop="cost">
          <el-input v-model="form.cost" readonly/>
        </el-form-item>
        <el-form-item label="卡号" prop="cardNo">
          <el-input v-model="form.cardNo" placeholder="请输入卡号" />
        </el-form-item>
        <el-form-item label="卡状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择卡状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="parseInt(dict.dictValue)"
            ></el-option>
          </el-select>
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
import { listCard, getCard, delCard, addCard, exportCard ,lossCard,unlossCard,cancelCard} from "@/api/account/card";
import { getAccount } from "@/api/account/account";
import { listCardtype } from "@/api/account/cardtype";
export default {
  name: "Card",
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
      // 账户下的卡表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 卡类型字典
      cardTypeIdOptions: [],
      // 卡状态：0，正常，1挂失，2注销字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cardTypeId: null,
        cardNo: null,
        accountId: null,
        userName:null,
        status: null,
        remarks: null,
        createTime: null,
      },
      // 表单参数
      form: {},
      //账户ID
      accountId:"",
      //人员姓名
      userName:"",
      // 表单校验
      rules: {
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
      }
    };
  },
  created() {
    // 取到路由带过来的参数

    if(this.$route.query.accountId!=null && this.$route.query.accountId!=undefined && this.$route.query.accountId!="" ){
      this.accountId = this.$route.query.accountId;
      this.queryParams.accountId = this.accountId;
      //查询 账户
      getAccount(this.accountId).then(response => {
        this.userName = response.data.userName;
        this.queryParams.userName = this.userName;
      });
    }


    this.getList();
    listCardtype().then(response => {
      this.cardTypeIdOptions = response.rows;
    });
    this.getDicts("card_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询账户下的卡列表 */
    getList() {
      this.loading = true;
      listCard(this.queryParams).then(response => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 卡状态：0，正常，1挂失，2注销字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 卡类型翻译
    cardTypeFormat(row, column) {
        for(var i = 0; i < this.cardTypeIdOptions.length; i++){
            if(this.cardTypeIdOptions[i].id==row.cardTypeId){
                return this.cardTypeIdOptions[i].name;
            }
        }
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
        cardTypeId: null,
        cardNo: null,
        accountId: null,
        status: null,
        remarks: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        createTime: null,
        delFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.queryParams.accountId = null;
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
      this.form.accountId = this.accountId;
        //默认选择一种卡状态
        this.form.status = 0;
      this.open = true;
      this.title = "开卡";
    },
    /** 换卡按钮操作 */
    handleReplace(row) {
      this.reset();
      this.form.accountId = this.accountId;
        //默认选择一种卡状态
        this.form.status = 0;
      this.open = true;
      this.title = "补卡";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            replaceCard(this.form).then(response => {
              this.msgSuccess("补卡成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCard(this.form).then(response => {
              this.msgSuccess("开卡成功");
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
      this.$confirm('是否确认删除账户下的卡编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCard(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有账户下的卡数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCard(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
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
              this.msgSuccess("解挂成功");
          });
      },
      //选择一种卡类型，那么对应的费用也要提示出来
    changeCardType(cardTypeId){
        for(var i = 0; i < this.cardTypeIdOptions.length; i++){
            var cardType = this.cardTypeIdOptions[i];
            if(cardType.id==cardTypeId){
                this.form.deposit = cardType.deposit;
                //判断是不是首次开卡
                if(this.cardList==null ||this.cardList.length==0){
                    this.form.cost = cardType.firstcost;
                }else{
                    this.form.cost = cardType.addcost;
                }
                break;
            }
        }
    }
  }
};
</script>
