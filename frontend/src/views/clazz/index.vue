<template>
  <div>
    <h1>班级管理</h1>

    <!--搜索栏-->
    <el-form :model="queryParam" inline>
      <el-form-item label="班级名称">
        <el-input v-model="queryParam.name" placeholder="请输入班级名称"></el-input>
      </el-form-item>
      <el-form-item label="开课时间">
        <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" value-format="YYYY-MM-DD"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadClazzPage">查询</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-button type="primary" @click="add()" style="float:right;">+新增班级</el-button>
    <br><br>

    <!--表格+分页-->
    <el-table :data="clazzList" border style="width:100%">
      <el-table-column type="index" label="序号" width="80" align="center"/>
      <el-table-column prop="name" label="班级名称" align="center"/>
      <el-table-column prop="room" label="教室" align="center"/>
      <el-table-column prop="beginDate" label="开课日期" align="center"/>
      <el-table-column prop="endDate" label="结课日期" align="center"/>
      <el-table-column prop="masterName" label="班主任" align="center"/>
      <el-table-column prop="status" label="班级状态" align="center"/>
      <el-table-column prop="updateTime" label="更新时间" align="center"/>
      <el-table-column fixed="right" label="操作" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row.id)">修改</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        v-model:current-page="queryParam.page"
        v-model:page-size="queryParam.pageSize"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="loadClazzPage"
        @current-change="loadClazzPage"
    />

    <!--新增编辑弹窗-->
    <el-dialog v-model="showDialog" :title="dialogTitle" width="40%" @close="resetForm">
      <el-form :model="clazzForm" :rules="clazzRules" ref="clazzFormRef">
        <el-form-item label="班级名称" prop="name" label-width="100px">
          <el-input v-model="clazzForm.name"></el-input>
        </el-form-item>
        <el-form-item label="教室" prop="room" label-width="100px">
          <el-input v-model="clazzForm.room"></el-input>
        </el-form-item>
        <el-form-item label="开课日期" prop="beginDate" label-width="100px">
          <el-date-picker v-model="clazzForm.beginDate" type="date" value-format="YYYY-MM-DD"></el-date-picker>
        </el-form-item>
        <el-form-item label="结课日期" prop="endDate" label-width="100px">
          <el-date-picker v-model="clazzForm.endDate" type="date" value-format="YYYY-MM-DD"></el-date-picker>
        </el-form-item>
        <el-form-item label="班主任" prop="masterId" label-width="100px">
          <el-select v-model="clazzForm.masterId" placeholder="请选择班主任">
            <el-option v-for="item in masterList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <!--新增学科subject下拉，接口样例subject为数字 1代表Java方向-->
        <el-form-item label="学科" prop="subject" label-width="100px">
          <el-select v-model="clazzForm.subject" placeholder="请选择学科">
            <el-option label="Java" :value="1"></el-option>
            <el-option label="前端" :value="2"></el-option>
            <el-option label="大数据" :value="3"></el-option>
            <el-option label="Python" :value="4"></el-option>
            <el-option label="Go" :value="5"></el-option>
            <el-option label="嵌入式" :value="6"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showDialog=false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref,onMounted} from 'vue'
import {ElMessage,ElMessageBox} from 'element-plus'
import {queryClazzPageApi,addClazzApi,queryClazzInfoApi,updateClazzApi,deleteClazzApi,queryAllMasterApi} from '@/api/clazz'

// 查询条件对象
const queryParam = ref({
  name:'',
  begin:'',
  end:'',
  page:1,
  pageSize:5
})
const dateRange = ref([])
const clazzList = ref([])
const total = ref(0)
// 弹窗
const showDialog = ref(false)
const dialogTitle = ref('')
//表单 增加subject
const clazzForm = ref({
  id:null,
  name:'',
  room:'',
  beginDate:'',
  endDate:'',
  masterId:null,
  subject:null
})
//班主任下拉数据源
const masterList = ref([])
//表单引用和校验，新增subject校验
const clazzFormRef = ref(null)
const clazzRules = ref({
  name:[{required:true,message:'请输入班级名称',trigger:'blur'}],
  room:[{required:true,message:'请输入教室',trigger:'blur'}],
  beginDate:[{required:true,message:'请选择开课日期',trigger:'blur'}],
  endDate:[{required:true,message:'请选择结课日期',trigger:'blur'}],
  masterId:[{required:true,message:'请选择班主任',trigger:'change'}],
  subject:[{required:true,message:'请选择学科',trigger:'change'}]
})

//加载班级分页列表
const loadClazzPage = async ()=>{
  if(dateRange.value && dateRange.value.length===2){
    queryParam.value.begin = dateRange.value[0]
    queryParam.value.end = dateRange.value[1]
  }else{
    queryParam.value.begin=''
    queryParam.value.end=''
  }
  const res = await queryClazzPageApi(queryParam.value)
  if(res.code===1){
    clazzList.value = res.data.rows
    total.value = res.data.total
  }
}

//加载全部班主任（员工列表，弹窗下拉）
const loadMasterList = async ()=>{
  const res = await queryAllMasterApi()
  if(res.code===1){
    masterList.value = res.data
  }
}

//重置搜索
const resetQuery = ()=>{
  queryParam.value={name:'',begin:'',end:'',page:1,pageSize:5}
  dateRange.value=[]
  loadClazzPage()
}

//新增：重置对象必须带上subject:null
const add = ()=>{
  dialogTitle.value='新增班级'
  showDialog.value=true
  clazzForm.value={id:null,name:'',room:'',beginDate:'',endDate:'',masterId:null,subject:null}
}

//编辑回显
const handleEdit = async (id)=>{
  dialogTitle.value='修改班级'
  showDialog.value=true
  const res = await queryClazzInfoApi(id)
  if(res.code===1){
    clazzForm.value = res.data
  }
}

//删除
const handleDelete = (id)=>{
  ElMessageBox.confirm('确定删除该班级？','提示',{type:'warning'})
      .then(async ()=>{
        const res = await deleteClazzApi(id)
        if(res.code===1){
          ElMessage.success('删除成功')
          loadClazzPage()
        }else{
          ElMessage.error(res.msg||'删除失败')
        }
      })
}

//重置表单
const resetForm = ()=>{
  clazzFormRef.value?.resetFields()
}

//保存：新增/修改
const save = async ()=>{
  await clazzFormRef.value.validate(async valid=>{
    if(!valid) return
    let res
    if(clazzForm.value.id){
      res = await updateClazzApi(clazzForm.value)
    }else{
      res = await addClazzApi(clazzForm.value)
    }
    if(res.code===1){
      ElMessage.success('操作成功')
      showDialog.value=false
      resetForm()
      loadClazzPage()
    }else{
      ElMessage.error(res.msg||'操作失败')
    }
  })
}

onMounted(()=>{
  loadClazzPage()
  loadMasterList()
})
</script>

<style scoped>
</style>
