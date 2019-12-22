<template>
  <div class="map-panel">
    <el-tabs :tab-position="'left'">
      <el-tab-pane v-for="tag in tagList"
                   :key="tag.id"
                   :label="tag.tagName">
        <span slot="label">
          <a href="javascript:void;">{{ tag.tagName }}</a>
        </span>
        <el-tabs type="border-card">
          <el-tab-pane v-for="panel in tag.panelList"
                       :key="panel.id"
                       :label="panel.panelName">
            <el-table v-for="table in panel.tableList"
                      :data="table.tableData"
                      border
                      :key="table.id"
                      :header-cell-style="{ background: '#475669', color: '#fff' }">
              <el-table-column :label="head.title"
                               v-for="head in table.tableTitleList"
                               :key="head.id"
                               :property="head.field"
                               :render-header="labelHead">
                <template slot-scope="scope">
                  <span v-if="head.cellType === '1'">
                    <el-button type="text"
                               size="small">
                      {{ scope.row[scope.column.property] }}
                    </el-button>
                  </span>
                  <el-tooltip placement="bottom"
                              v-else>
                    <div slot="content">{{ scope.row["tips"] }}</div>
                    <span>{{ scope.row[scope.column.property] }}</span>
                  </el-tooltip>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane v-if="isEdit">
            <span slot="label">
              <el-button type="primary"
                         size="mini"
                         plain
                         @click="showTagPanel('add', tag.id, 'panel')">新 增</el-button>
            </span>
          </el-tab-pane>
        </el-tabs>
      </el-tab-pane>
      <el-tab-pane v-if="isEdit">
        <span slot="label">
          <el-button type="primary"
                     size="mini"
                     plain
                     @click="showTagPanel('add', '', 'tag')">新 增</el-button>
        </span>
      </el-tab-pane>
    </el-tabs>

    <el-dialog :title="panelTips"
               :visible.sync="dialogVisible"
               width="30%">
      <el-form ref="form"
               :model="form"
               label-width="80px">
        <el-form-item label="标签名称">
          <el-input v-model="form.name"
                    placeholder="请输入名称"
                    @keyup.enter.native="onEnvSave"
                    @blur="onEnvSave"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="dialogVisible = false">{{
          panelTips
        }}</el-button>
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
      tagList: [],
      rowData: [],
      isEdit: true,
      panelName: '',
      panelTips: '',
      dialogVisible: false,
      form: { id: '', type: '', name: '' }
    };
  },
  methods: {
    showTagPanel(operate, id, type) {
      this.dialogVisible = true;
      this.panelTips = operate === 'add' ? '新 增' : '修 改';
      this.form.id = id;
      this.form.type = type;
    },
    onEnvSave() {
      this.$ajax.post(ApiUrl.envMapSave, this.form).then(result => {
        this.form.name = '';
        this.loadData();
      });
    },
    loadData() {
      this.$ajax.get(ApiUrl.envMapUrl).then(result => {
        result.data.forEach(tag => {
          tag.panelList.forEach(panel => {
            panel.tableList.forEach(t => {
              const tableData = [];
              t.tableData = tableData;
              t.tableTitleList.forEach(h => {
                let rowId = 0;
                h.tableBodyList.forEach(b => {
                  if (!tableData[rowId]) {
                    tableData[rowId] = {};
                  }
                  tableData[rowId][h.field] = b.content;
                  tableData[rowId]['tips'] = b.tips;
                  tableData[rowId]['cellType'] = h.cellType;
                  tableData[rowId]['id'] = h.id;
                  rowId++;
                });
              });
            });
          });
        });
        this.tagList = result.data;
        console.log(result.data);
      });
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
</style>
