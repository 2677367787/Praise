<template>
  <div class="custom-tree-container">
    <div class="block">
      <div>
        <el-button size='mini'
                   type='text'
                   @click="showEditPanel({}, 'addParent')">新增系统</el-button>
      </div>
      <el-tree :data="treeData"
               ref="tree"
               node-key="id"
               default-expand-all
               :check-on-click-node="true"
               :expand-on-click-node="false"
               @current-change="currentChange"
               @check-change="checkChange"
               @node-click="nodeClick"
               :render-content="renderContent">
      </el-tree>
    </div>
    <div class="add-table-panel">
      <el-button size='mini'
                 type='text'
                 @click="showTablePanel('add',{})">新增表格</el-button>
    </div>
    <div class="tab-panel">
      <div class="block"
           v-for="table in tableStruct"
           :key="table.tableId">
        <el-button size='mini'
                   type='text'
                   @click="showTablePanel('edit', table)">编辑表格</el-button>
        <el-button size='mini'
                   type='text'
                   @click="deleteTable(table)">删除表格</el-button>
        <el-button size='mini'
                   type='text'
                   @click="showRowPanel('add',{},table.tableId)">新增列</el-button>
        <el-table :data="table.tableStructList"
                  border
                  style="width: 100%">
          <el-table-column :label="table.tableName">
            <el-table-column prop="headId"
                             label="ID"
                             width="60">
            </el-table-column>
            <el-table-column prop="field"
                             label="字段名称">
            </el-table-column>
            <el-table-column prop="cellName"
                             label="字段描述">
            </el-table-column>
            <el-table-column prop="cellType"
                             label="字段类型">
            </el-table-column>
            <el-table-column fixed="right"
                             label="操作"
                             width="100">
              <template slot-scope="scope">
                <el-button @click="showRowPanel('edit',scope.row)"
                           type="text"
                           size="small">编辑</el-button>
                <el-button @click="rowRemove(scope.row)"
                           type="text"
                           size="small">删除</el-button>
              </template>
            </el-table-column>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <el-dialog :title="panelTips"
               :visible.sync="dialogVisible"
               width="30%">
      <el-form ref="form"
               :model="form"
               label-width="100px">
        <el-form-item label="名称"
                      v-if="form.level !== 2">
          <el-input v-model="form.label"
                    placeholder="请输入名称"
                    @keyup.enter.native="onSave(form.type)"></el-input>
        </el-form-item>
        <el-form-item label="表格名称"
                      v-else>
          <el-select v-model="form.tableId"
                     placeholder="请选择">
            <el-option v-for="item in tableList"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="onSave(form.type)">
          {{ panelTips }}
        </el-button>
      </span>
    </el-dialog>
    <el-dialog :title="panelTips"
               :visible.sync="cellDialogVisible"
               width="30%">
      <el-form ref="row"
               :model="row"
               label-width="100px">
        <el-form-item label="字段名称">
          <el-input v-model="row.field"
                    placeholder="请输入字段名称"
                    @keyup.enter.native="rowSave"></el-input>
        </el-form-item>
        <el-form-item label="字段描述">
          <el-input v-model="row.cellName"
                    placeholder="请输入字段描述"
                    @keyup.enter.native="rowSave"></el-input>
        </el-form-item>
        <el-form-item label="字段类型">
          <el-radio-group v-model="row.cellType">
            <el-radio label="0">普通</el-radio>
            <el-radio label="1">超链接</el-radio>
            <el-radio label="2">气泡</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="cellDialogVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="rowSave()">
          {{ panelTips }}
        </el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

const levelMapName = { 0: 'system', 1: 'tag', 2: 'table', 3: 'cell' }
const levelMapDesc = { 0: '系统', 1: '环境', 2: '表格', 3: 'cell' }

export default {
  data() {
    return {
      treeData: [],
      tableStruct: [],
      dialogVisible: false,
      cellDialogVisible: false,
      tableDialogVisible: false,
      tableList: [],
      panelTips: '新增',
      form: { id: '', parentId: '', type: '', label: '', cellType: '' },
      row: { id: '', tableId: '', field: '', cellName: '', cellType: '' },
      table: {}
    }
  },
  created() {
    this.loadFrame();
  },
  methods: {
    loadFrame() {
      this.$ajax.get(this.$apiUrl.envMapFrame).then(result => {
        this.treeData = result.data
      })
      this.$ajax.get(this.$apiUrl.envMapTableFrame).then(result => {
        console.log(result.data)
        this.tableStruct = result.data
        this.tableList = result.data.map(m => {
          const { tableId: value, tableName: label } = m;
          return { label, value }
        })
      })
    },
    onSave(type) {
      if (type === 'table') {
        this.$ajax.post(this.$apiUrl.envMapTableSave, this.form).then(result => {
          this.$message.success('保存成功!');
          this.loadFrame();
          this.dialogVisible = false;
        });
      } else {
        if (this.form.level === 2) {
          console.log(this.form)
          this.form.panelId = this.form.parentId;
          this.$ajax.post(this.$apiUrl.frameJoinTableSave, this.form).then(result => {
            this.$message.success('保存成功!');
            this.loadFrame();
            this.dialogVisible = false;
          });
        } else {
          this.$ajax.post(this.$apiUrl.envMapFrameSave, this.form).then(result => {
            this.$message.success('保存成功!');
            this.loadFrame();
            this.dialogVisible = false;
          });
        }
      }
    },
    showEditPanel(data, operate) {
      this.dialogVisible = true;
      this.form = {};
      if (operate === 'addParent') {
        this.form.parentId = 0;
        this.form.level = 0;
        this.form.levelName = levelMapName[0] || '';
        this.form.cellType = 0;
        this.panelTips = '新增系统';
      } else if (operate === 'add') {
        this.form.parentId = data.id;
        this.form.level = data.level + 1;
        this.form.levelName = levelMapName[data.level] || '';
        this.form.cellType = 0;

        this.panelTips = '新增' + levelMapDesc[(data.level + 1)];
      } else {
        this.form = data;
        this.panelTips = '编辑' + levelMapDesc[data.level];
      }
    },
    deleteTable(table) {
      this.$confirm('此操作将永久删除该表格,以及表格数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        table.id = table.tableId;
        this.$ajax.post(this.$apiUrl.envMapTableDelete, table).then(result => {
          this.$message.success('删除成功!');
          this.loadFrame();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    showTablePanel(operate, table) {
      this.dialogVisible = true;
      this.row = {};
      if (operate === 'add') {
        this.form = { id: '', label: '' }
        this.panelTips = '新增';
      } else {
        this.form = table;
        this.form.label = table.tableName;
        this.form.id = table.tableId;
        this.panelTips = '编辑';
      }
      this.form.type = 'table'
    },
    showRowPanel(operate, row, tableId) {
      console.log(tableId)
      this.cellDialogVisible = true;
      this.row = {};
      if (operate === 'add') {
        this.row = { id: '', tableId, field: '', cellName: '', cellType: '' }
        this.panelTips = '新增';
      } else {
        this.row = row;
        this.row.id = row.headId;
        this.panelTips = '编辑';
      }
    },
    rowSave() {
      this.$ajax.post(this.$apiUrl.envMapTableFrameSave, this.row).then(result => {
        this.$message.success('保存成功!');
        this.cellDialogVisible = false;
        this.loadFrame();
      });
    },
    rowRemove(row) {
      this.$confirm('此操作将永久删除该列,以及维护在该列的数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$ajax.post(this.$apiUrl.envMapTableFrameDelete, row).then(result => {
          this.$message.success('删除成功!');
          this.loadFrame();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    currentChange(data, node) {
      console.log(data, node)
    },
    getCurrentNode(data) {
      console.log(data)
    },
    remove(node, data) {
      this.$confirm('此操作将永久删除该节点,以及其所有子节点, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const url = data.level === 2 ? this.$apiUrl.frameJoinTableDelete : this.$apiUrl.envMapFrameDelete;
        this.$ajax.post(url, data).then(result => {
          this.$message.success('删除成功!');
          this.loadFrame();
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    checkChange(item, node, self) {
      if (node) { // 点击未选中复选框
        this.editCheckId = item.id;
        this.$refs.tree.setCheckedKeys([item.id]);
      } else {
        if (this.editCheckId === item.id) { // 点击已选中复选框，保证至少一个选中
          this.$refs.tree.setCheckedKeys([item.id]);
        }
      }
    },
    nodeClick(item, node, self) {
      this.editCheckId = item.id;
      this.$refs.tree.setCheckedKeys([item.id]);
    },
    renderContent(h, { node, data, store }) {
      return (
        <span class='custom-tree-node'>
          <span>{data.label}</span>
          <span>
            {data.level === 2 ? '' : <el-button size='mini' type='text' on-click={() => this.showEditPanel(data, 'add')}>新增</el-button>}
            {data.level === 2 ? '' : <el-button size='mini' type='text' on-click={() => this.showEditPanel(data, 'edit')}>编辑</el-button>}
            <el-button size='mini' type='text' on-click={() => this.remove(node, data)}>删除</el-button>
          </span>
        </span>);
    }
  }
};
</script>

<style>
.custom-tree-container {
  padding: 30px;
}
.add-table-panel {
  padding: 20px 0px 5px 0px;
}
.tab-panel {
  flex: 1;
  display: flex;
  margin-right: 100px;
  flex-wrap: wrap;
}
.block {
  width: 450px;
  margin-right: 100px;
}
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>