<template>
  <div class="log-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>操作日志管理</span>
        </div>
      </template>
      <!-- 日志表格 -->
      <el-table :data="tableData" border stripe>
        <el-table-column label="序号" type="index" width="80" />
        <el-table-column prop="operateUser" label="操作人" />
        <el-table-column prop="operateTime" label="操作时间" />
        <el-table-column prop="className" label="操作类名" />
        <el-table-column prop="methodName" label="操作方法" />
        <el-table-column prop="methodDesc" label="功能描述" />
        <el-table-column prop="returnValue" label="返回结果" />
      </el-table>
      <!-- 分页 -->
      <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
          @change="loadLogData"
          style="margin-top:15px"
      />
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getLogListApi } from '@/api/log'

// 分页参数
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const tableData = ref([])

// 请求日志数据
const loadLogData = async () => {
  const res = await getLogListApi({
    pageNum: pageNum.value,
    pageSize: pageSize.value
  })
  tableData.value = res.data.rows
  total.value = res.data.total
}

onMounted(() => {
  loadLogData()
})
</script>

<style scoped>
.log-container {
  padding: 10px;
}
</style>
