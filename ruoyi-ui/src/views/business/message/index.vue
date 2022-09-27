<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="传感器id" prop="sensorId">
        <el-input
          v-model="queryParams.sensorId"
          placeholder="请输入传感器id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="起始时间" prop="messageStart">
        <el-date-picker clearable
          v-model="queryParams.messageStart"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择起始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="终止时间" prop="messageEnd">
        <el-date-picker clearable
          v-model="queryParams.messageEnd"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="请选择终止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="监测数据" prop="messageValue">
        <el-input
          v-model="queryParams.messageValue"
          placeholder="请输入监测数据"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据频率" prop="messageFrequency">
        <el-input
          v-model="queryParams.messageFrequency"
          placeholder="请输入数据频率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备id" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['business:message:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:message:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:message:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:message:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="messageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="信息id" align="center" prop="messageId" />
      <el-table-column label="网关id" align="center" prop="gatewayId" />
      <el-table-column label="传感器id" align="center" prop="sensorId" />
      <el-table-column label="起始时间" align="center" prop="messageStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.messageStart, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="终止时间" align="center" prop="messageEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.messageEnd, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="监测数据" align="center" prop="messageValue" />
      <el-table-column label="数据频率" align="center" prop="messageFrequency" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="设备id" align="center" prop="deviceId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:message:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:message:remove']"
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

    <!-- 添加或修改业务数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网关id" prop="gatewayId">
          <el-input v-model="form.gatewayId" placeholder="请输入网关id" />
        </el-form-item>
        <el-form-item label="传感器id" prop="sensorId">
          <el-input v-model="form.sensorId" placeholder="请输入传感器id" />
        </el-form-item>
        <el-form-item label="起始时间" prop="messageStart">
          <el-date-picker clearable
            v-model="form.messageStart"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择起始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="终止时间" prop="messageEnd">
          <el-date-picker clearable
            v-model="form.messageEnd"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择终止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="监测数据" prop="messageValue">
          <el-input v-model="form.messageValue" placeholder="请输入监测数据" />
        </el-form-item>
        <el-form-item label="数据频率" prop="messageFrequency">
          <el-input v-model="form.messageFrequency" placeholder="请输入数据频率" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="设备id" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入设备id" />
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
import { listMessage, getMessage, delMessage, addMessage, updateMessage, gateWayId } from '@/api/business/message'

export default {
  name: "Message",
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
      // 业务数据表格数据
      messageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        gatewayId: Number(this.$route.params.mes),
        // gatewayId: this.getGateWayId(),
        sensorId: null,
        messageStart: null,
        messageEnd: null,
        messageValue: null,
        messageFrequency: null,
        deviceId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        gatewayId: [
          { required: true, message: "网关id不能为空", trigger: "blur" }
        ],
        sensorId: [
          { required: true, message: "传感器id不能为空", trigger: "blur" }
        ],
        messageStart: [
          { required: true, message: "起始时间不能为空", trigger: "blur" }
        ],
        messageEnd: [
          { required: true, message: "终止时间不能为空", trigger: "blur" }
        ],
        messageValue: [
          { required: true, message: "监测数据不能为空", trigger: "blur" }
        ],
        messageFrequency: [
          { required: true, message: "数据频率不能为空", trigger: "blur" }
        ],
        deviceId: [
          { required: true, message: "设备id不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询业务数据列表 */
    getList() {
      this.loading = true;
      listMessage(this.queryParams).then(response => {
        this.messageList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        messageId: null,
        gatewayId: null,
        sensorId: null,
        messageStart: null,
        messageEnd: null,
        messageValue: null,
        messageFrequency: null,
        remark: null,
        deviceId: null
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
      this.ids = selection.map(item => item.messageId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加业务数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const messageId = row.messageId || this.ids
      getMessage(messageId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改业务数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.messageId != null) {
            updateMessage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMessage(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const messageIds = row.messageId || this.ids;
      this.$modal.confirm('是否确认删除业务数据编号为"' + messageIds + '"的数据项？').then(function() {
        return delMessage(messageIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('business/message/export', {
        ...this.queryParams
      }, `message_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
