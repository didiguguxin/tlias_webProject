<template>
  <div class="report-emp">
    <el-row :gutter="20">
      <!--员工性别饼图-->
      <el-col :span="12">
        <el-card header="员工性别统计">
          <div ref="genderChartRef" style="height:400px;"></div>
        </el-card>
      </el-col>
      <!--员工职位柱状图-->
      <el-col :span="12">
        <el-card header="员工职位人数统计">
          <div ref="jobChartRef" style="height:400px;"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import * as echarts from 'echarts'
import { getEmpGenderDataApi, getEmpJobDataApi } from '@/api/report'

const genderChartRef = ref(null)
const jobChartRef = ref(null)
let genderChart = null
let jobChart = null

//渲染性别饼图
const renderGenderChart = async () => {
  const res = await getEmpGenderDataApi()
  genderChart = echarts.init(genderChartRef.value)
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
        name: '性别',
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
  genderChart.setOption(option)
}

//渲染职位柱状图
const renderJobChart = async () => {
  const res = await getEmpJobDataApi()
  jobChart = echarts.init(jobChartRef.value)
  const option = {
    xAxis: {
      type: 'category',
      data: res.data.jobList
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
  jobChart.setOption(option)
}

onMounted(() => {
  renderGenderChart()
  renderJobChart()
  window.addEventListener('resize', () => {
    genderChart?.resize()
    jobChart?.resize()
  })
})

onUnmounted(() => {
  genderChart?.dispose()
  jobChart?.dispose()
})
</script>

<style scoped>
.report-emp {
  padding: 10px;
}
</style>
