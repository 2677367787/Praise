<template>
  <div class="map-panel">
    <el-tabs tab-position="left"
             v-model="activeSystem"
             @tab-click="setActiveSystem">
      <el-tab-pane v-for="tag in frameData"
                   :key="tag.id"
                   :name="tag.label"
                   :label="tag.label">
        <el-tabs type="border-card"
                 v-model="activePanel"
                 @tab-click="setActivePanel">
          <el-button type="text"
                     @click="isEdit=!isEdit"
                     size="mini">编辑模式</el-button>
          <el-tab-pane v-for="panel in tag.children"
                       :key="panel.id"
                       :name="panel.label"
                       :label="panel.label">
            <div class="tab-panel"
                 v-for="table in panel.tables">
              <div class="btn-panel"
                   v-if="isEdit">
                <el-button type="primary"
                           plain
                           @click="handleEdit('add',{},table.tableStruct,panel.id)"
                           size="mini">新 增</el-button>
              </div>
              <el-table :data="table.tableData"
                        border
                        :key="table.id"
                        :header-cell-style="{ background: '#475669', color: '#fff' }">
                <el-table-column :label="head.cellName"
                                 v-for="head in table.tableStruct"
                                 :key="head.headId"
                                 :min-width="head.weight"
                                 :property="head.field">
                  <template slot-scope="scope">
                    <span v-if="head.cellType === '0'">
                      {{ cellValue(scope.row,scope.column.property) }}
                    </span>
                    <span v-else-if="head.cellType === '1'">
                      <a target="_blank"
                         :href="scope.row[scope.column.property]['tips'] || ''">
                        <el-button type="text">{{ cellValue(scope.row,scope.column.property) }}</el-button>
                      </a>
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
                                 width="100"
                                 v-if="isEdit">
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
import Cookies from 'js-cookie'
export default {
  components: {
    tag
  },
  data() {
    return {
      frameData: [],
      tableStruct: [],
      rowData: [],
      isEdit: false,
      panelName: '',
      panelTips: '',
      dialogVisible: false,
      form: {},
      activeSystem: '',
      activePanel: ''
    };
  },
  methods: {
    handleEdit(operate, row, struct, panelId) {
      console.log(row)
      console.log(struct)
      struct.forEach(cell => {
        if (!row[cell.field]) {
          row[cell.field] = {}
          if (!row[cell.field][cell.field]) {
            row[cell.field][cell.field] = ''
            row[cell.field].tips = ''
            row[cell.field].headId = cell.headId
            row[cell.field].panelId = panelId
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
      console.log(this.form)
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
    setActiveSystem(tab, event) {
      if (tab.name) {
        Cookies.set('activeSystem', tab.name, { expires: 365 });
      }
    },
    setActivePanel(tab, event) {
      if (tab.name) {
        Cookies.set('activePanel', tab.name, { expires: 365 });
      }
    },
    loadData() {
      this.$ajax.get(ApiUrl.envMapUrl).then(result => {
        this.frameData = result.data;
        this.activeSystem = Cookies.get('activeSystem') || '';
        this.activePanel = Cookies.get('activePanel') || '';
      });
    },
    openNewTab(row, field) {
      console.log(row)
      window.location.href = row[field] && row[field]['tips'] || '';
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
