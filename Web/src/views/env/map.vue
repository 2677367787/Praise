<template>
  <div class="map-panel">
    <el-tabs :tab-position="'left'">
      <el-tab-pane v-for="tag in frameData"
                   :key="tag.id"
                   :label="tag.label">
        <span slot="label">
          <a href="javascript:void;">{{ tag.label }}</a>
        </span>
        <el-tabs type="border-card">
          <el-tab-pane v-for="panel in tag.children"
                       :key="panel.id"
                       :label="panel.label">
            <div class="tab-panel"
                 v-for="table in panel.tables">
              <div class="btn-panel">
                <el-button type="primary"
                           @click="handleEdit('add',{},table.tableStruct)"
                           size="mini">新 增</el-button>
              </div>
              <el-table :data="table.tableData"
                        border
                        :key="table.id"
                        :header-cell-style="{ background: '#475669', color: '#fff' }">
                <el-table-column :label="head.cellName"
                                 v-for="head in table.tableStruct"
                                 :key="head.headId"
                                 :property="head.field"
                                 :render-header="labelHead">
                  <template slot-scope="scope">
                    <span v-if="head.cellType === '0'">
                      {{ cellValue(scope.row,scope.column.property) }}
                    </span>
                    <span v-else-if="head.cellType === '1'">
                      <el-button type="text"
                                 size="small">
                        {{ cellValue(scope.row,scope.column.property) }}
                      </el-button>
                    </span>
                    <el-tooltip placement="bottom"
                                v-else>
                      <div slot="content">{{ tipsValue(scope.row,scope.column.property,'tips') }}</div>
                      <span>{{ cellValue(scope.row,scope.column.property) }}</span>
                    </el-tooltip>
                  </template>
                </el-table-column>
                <el-table-column fixed="right"
                                 label="操作"
                                 width="100">
                  <template slot-scope="scope">
                    <el-button @click="handleEdit('edit',scope.row,table.tableStruct)"
                               type="text"
                               size="small">编辑</el-button>
                    <el-button @click="handleDelete(scope.row,table.tableStruct)"
                               type="text"
                               size="small">删除</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-tab-pane>
    </el-tabs>
    <el-dialog :title="panelTips"
               :visible.sync="dialogVisible"
               width="30%">
      <el-form ref="form"
               :model="form"
               label-width="100px">
        <el-form-item v-for="(tab, index) in tableStruct"
                      :label="tab.cellName"
                      :key="tab.field">
          <el-input v-model="form[tab.field][tab.field]"></el-input>
          <el-input v-model="form[tab.field].tips"
                    v-if="tab.cellType !== '0'"
                    :placeholder="viewPlaceholder(tab.cellType)"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="onRowSave()">
          {{ panelTips }}
        </el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { ApiUrl } from '@/api/apiUrl';
import tag from './tag';
export default {
  components: {
    tag
  },
  data() {
    return {
      frameData: [],
      tableStruct: [],
      rowData: [],
      isEdit: true,
      panelName: '',
      panelTips: '',
      dialogVisible: false,
      form: {}
    };
  },
  methods: {
    handleEdit(operate, row, struct) {
      console.log(row)
      console.log(struct)
      struct.forEach(cell => {
        if (!row[cell.field]) {
          row[cell.field] = {}
          if (!row[cell.field][cell.field]) {
            row[cell.field][cell.field] = ''
            row[cell.field].tips = ''
            row[cell.field].headId = cell.headId
          }
        }
      })
      this.dialogVisible = true;
      this.tableStruct = struct
      this.form = row;
      this.panelTips = operate === 'add' ? '新 增' : '修 改';
    },
    handleDelete(row, struct) {
      this.$confirm('此操作将永久删除该行数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = [];
        Object.keys(row).forEach(field => {
          param.push(row[field]);
        })
        this.$ajax.post(ApiUrl.envMapDelete, param).then(result => {
          this.$message.success('删除成功!');
          this.loadData();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    onRowSave() {
      const param = [];
      Object.keys(this.form).forEach(field => {
        this.form[field]['content'] = this.form[field][field];
        param.push(this.form[field]);
      })
      console.log(param)
      this.$ajax.post(ApiUrl.envMapSave, param).then(result => {
        this.$message.success('操作成功!');
        this.loadData();
        this.dialogVisible = false
      });
    },
    loadData() {
      this.$ajax.get(ApiUrl.envMapUrl).then(result => {
        this.frameData = result.data;
        console.log(result.data);
      });
    },
    cellValue(row, field) {
      return row[field] && row[field][field] || '';
    },
    tipsValue(row, field, tips) {
      return row[field] && row[field][tips] || '';
    },
    viewPlaceholder(cellType) {
      return cellType === '1' ? '超链接' : '气泡';
    },
    labelHead(h, { column, index }) {
      const l = column.label.length;
      // 每个字大小，其实是每个字的比例值，大概会比字体大小差不多大一点，
      const f = 12;
      // 字大小乘个数即长度 ,注意不要加px像素，这里minWidth只是一个比例值，不是真正的长度
      column.minWidth = f * l;
      // 然后将列标题放在一个div块中，注意块的宽度一定要100%，否则表格显示不完全
      return h('span', { class: 'table-head', style: { width: '100%' }}, [
        column.label
      ]);
    }
  },
  created() {
    this.loadData();
  }
};
</script>
<style lang="scss">
.map-panel {
  width: 80%;
  color: #475669;
  margin: 0 auto;
}
.tab-panel {
  padding: 20px 0px;
}
.btn-panel {
  text-align: right;
}
</style>
