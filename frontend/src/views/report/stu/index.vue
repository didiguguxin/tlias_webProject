<template>
  <div class="report-student">
    <el-row :gutter="20">
      <!--学员学历饼图-->
      <el-col :span="12">
        <el-card header="学员学历统计">
          <div ref="degreeChartRef" style="height:400px;"></div>
        </el-card>
      </el-col>
      <!--班级人数柱状图-->
      <el-col :span="12">
        <el-card header="班级人数统计">
          <div ref="clazzChartRef" style="height:400px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import * as echarts from 'echarts'
import { getStudentDegreeDataApi, getStudentCountDataApi } from '@/api/report'

const degreeChartRef = ref(null)
const clazzChartRef = ref(null)
let degreeChart = null
let clazzChart = null

//学历饼图
const renderDegreeChart = async () => {
  const res = await getStudentDegreeDataApi()
  degreeChart = echarts.init(degreeChartRef.value)
  const option = {
    tooltip: {
      trigger: 'item'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        name: '学历',
        type: 'pie',
        radius: '50%',
        data: res.data,
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  }
  degreeChart.setOption(option)
}

//班级人数柱状图
const renderClazzChart = async () => {
  const res = await getStudentCountDataApi()
  clazzChart = echarts.init(clazzChartRef.value)
  const option = {
    xAxis: {
      type: 'category',
      data: res.data.clazzList
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: res.data.dataList,
        type: 'bar'
      }
    ]
  }
  clazzChart.setOption(option)
}

onMounted(() => {
  renderDegreeChart()
  renderClazzChart()
  window.addEventListener('resize', () => {
    degreeChart?.resize()
    clazzChart?.resize()
  })
})

onUnmounted(() => {
  degreeChart?.dispose()
  clazzChart?.dispose()
})
</script>

<style scoped>
.report-student {
  padding:10px;
}
</style>
