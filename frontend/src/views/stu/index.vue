<template>
  <h1>学员管理</h1>

  <!--搜索栏-->
  <div class="container">
    <el-form :inline="true" :model="searchStudent">
      <el-form-item label="学员姓名">
        <el-input v-model="searchStudent.name" placeholder="请输入学员姓名"></el-input>
      </el-form-item>
      <el-form-item label="学历">
        <el-select v-model="searchStudent.degree" placeholder="请选择学历">
          <el-option v-for="item in degreeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select v-model="searchStudent.clazzId" placeholder="请选择班级">
          <el-option v-for="c in clazzList" :key="c.id" :label="c.name" :value="c.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清空</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!--功能按钮-->
  <div class="container">
    <el-button type="primary" @click="addStudent">+新增学员</el-button>
    <el-button type="danger" @click="batchDelete">-批量删除</el-button>
  </div>

  <!--表格-->
  <div class="container">
    <el-table :data="studentList" border style="width:100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column prop="name" label="姓名" width="100" align="center"/>
      <el-table-column prop="no" label="学号" width="140" align="center"/>
      <el-table-column label="性别" width="80" align="center">
        <template #default="scope">
          {{ scope.row.gender===1?'男':'女' }}
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="130" align="center"/>
      <el-table-column label="学历" width="100" align="center">
        <template #default="scope">
          {{ getDegreeText(scope.row.degree) }}
        </template>
      </el-table-column>
      <el-table-column prop="clazzName" label="所属班级" width="140" align="center"/>
      <el-table-column prop="address" label="地址" align="center"/>
      <el-table-column prop="graduationDate" label="毕业时间" width="120" align="center"/>
      <el-table-column label="违纪信息" width="130" align="center">
        <template #default="scope">
          次数:{{scope.row.violationCount}}｜扣分:{{scope.row.violationScore}}
        </template>
      </el-table-column>
      <el-table-column prop="updateTime" label="更新时间" width="180" align="center"/>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="deleteById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!--分页-->
  <div class="container">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[5,10,20,30,50,75,100]"
        :background
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        @size-change="search"
        @current-change="search"
    />
  </div>

  <!--新增编辑弹窗-->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="55%">
    <el-form :model="student" :rules="rules" ref="stuFormRef" label-width="100px">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="学员姓名" prop="name">
            <el-input v-model="student.name" placeholder="请输入学员姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学号" prop="no">
            <el-input v-model="student.no" placeholder="请输入学号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="student.gender" placeholder="请选择性别" style="width:100%">
              <el-option label="男" :value="1"></el-option>
              <el-option label="女" :value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="student.phone" placeholder="请输入手机号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="身份证号" prop="idCard">
            <el-input v-model="student.idCard" placeholder="请输入身份证"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="学历" prop="degree">
            <el-select v-model="student.degree" placeholder="请选择学历" style="width:100%">
              <el-option v-for="item in degreeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="是否大专" prop="isCollege">
            <el-select v-model="student.isCollege" placeholder="请选择" style="width:100%">
              <el-option label="否" :value="0"></el-option>
              <el-option label="是" :value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属班级" prop="clazzId">
            <el-select v-model="student.clazzId" placeholder="请选择班级" style="width:100%">
              <el-option v-for="c in clazzList" :key="c.id" :label="c.name" :value="c.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="家庭地址" prop="address">
            <el-input v-model="student.address" placeholder="请输入地址"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="毕业日期" prop="graduationDate">
            <el-date-picker v-model="student.graduationDate" type="date" value-format="YYYY-MM-DD" style="width:100%"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible=false">取消</el-button>
      <el-button type="primary" @click="save">保存</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import {ref,onMounted} from 'vue'
import {ElMessage,ElMessageBox} from 'element-plus'
import {queryStudentPageApi,addStudentApi,queryStudentInfoApi,updateStudentApi,deleteStudentApi,queryAllClazzApi} from '@/api/student'

//学历字典，接口文档：1初中 2高中 3大专 4本科 5硕士
const degreeList = ref([
  {label:'初中',value:1},
  {label:'高中',value:2},
  {label:'大专',value:3},
  {label:'本科',value:4},
  {label:'硕士',value:5},
])
//学历数字转中文
const getDegreeText = (val)=>{
  const o = degreeList.value.find(i=>i.value===val)
  return o ? o.label : '-'
}

//搜索条件
const searchStudent = ref({
  name:'',
  degree:'',
  clazzId:'',
  page:1,
  pageSize:10
})

//学员表格数据
const studentList = ref([])
const total = ref(0)
//分页
const currentPage = ref(1)
const pageSize = ref(10)

//弹窗
const dialogVisible = ref(false)
const dialogTitle = ref('新增学员')
//表单数据
const student = ref({
  id:null,
  name:'',
  no:'',
  gender:'',
  phone:'',
  idCard:'',
  degree:'',
  isCollege:'',
  address:'',
  graduationDate:'',
  clazzId:null,
  violationCount:0,
  violationScore:0
})
//全部班级下拉数据源
const clazzList = ref([])

//表单引用、校验规则
const stuFormRef = ref(null)
const rules = ref({
  name:[{required:true,message:'请输入学员姓名',trigger:'blur'}],
  no:[{required:true,message:'请输入学号',trigger:'blur'}],
  gender:[{required:true,message:'请选择性别',trigger:'change'}],
  phone:[
    {required:true,message:'请输入手机号',trigger:'blur'},
    {pattern:/^1[3-9]\d{9}$/,message:'手机号格式错误',trigger:'blur'}
  ],
  idCard:[{required:true,message:'请输入身份证号',trigger:'blur'}],
  degree:[{required:true,message:'请选择学历',trigger:'change'}],
  isCollege:[{required:true,message:'请选择是否大专',trigger:'change'}],
  clazzId:[{required:true,message:'请选择所属班级',trigger:'change'}],
  address:[{required:true,message:'请输入家庭地址',trigger:'blur'}],
  graduationDate:[{required:true,message:'请选择毕业日期',trigger:'change'}]
})

//选中id数组（批量删除）
const selectedIds = ref([])
const handleSelectionChange = (selection)=>{
  selectedIds.value = selection.map(item=>item.id)
}

//加载班级下拉
const loadClazz = async ()=>{
  const res = await queryAllClazzApi()
  if(res.code===1){
    clazzList.value = res.data
  }
}

//查询学员列表
const search = async ()=>{
  searchStudent.value.page = currentPage.value
  searchStudent.value.pageSize = pageSize.value
  const res = await queryStudentPageApi(searchStudent.value)
  if(res.code===1){
    studentList.value = res.data.rows
    total.value = res.data.total
  }
}

//清空搜索条件
const clear = ()=>{
  searchStudent.value={name:'',degree:'',clazzId:'',page:1,pageSize:10}
  currentPage.value=1
  search()
}

//新增学员
const addStudent = ()=>{
  dialogTitle.value='新增学员'
  dialogVisible.value=true
  student.value={
    id:null,
    name:'',
    no:'',
    gender:'',
    phone:'',
    idCard:'',
    degree:'',
    isCollege:'',
    address:'',
    graduationDate:'',
    clazzId:null,
    violationCount:0,
    violationScore:0
  }
  stuFormRef.value?.resetFields()
}

//编辑回显
const edit = async (id)=>{
  dialogTitle.value='修改学员'
  dialogVisible.value=true
  const res = await queryStudentInfoApi(id)
  if(res.code===1){
    student.value = res.data
  }
}

//单条删除
const deleteById = (id)=>{
  ElMessageBox.confirm('确认删除该学员？','提示',{type:'warning'})
      .then(async ()=>{
        const res = await deleteStudentApi(id)
        if(res.code===1){
          ElMessage.success('删除成功')
          search()
        }else{
          ElMessage.error(res.msg||'删除失败')
        }
      })
}

//批量删除
const batchDelete = ()=>{
  if(!selectedIds.value.length){
    ElMessage.warning("请勾选要删除学员")
    return
  }
  ElMessageBox.confirm('确认批量删除选中学员？','提示',{type:'warning'})
      .then(async ()=>{
        const idsStr = selectedIds.value.join(',')
        const res = await deleteStudentApi(idsStr)
        if(res.code===1){
          ElMessage.success('批量删除成功')
          search()
        }else{
          ElMessage.error(res.msg||'删除失败')
        }
      })
}

//保存新增/修改
const save = async ()=>{
  await stuFormRef.value.validate(async valid=>{
    if(!valid) return
    let res
    if(student.value.id){
      res = await updateStudentApi(student.value)
    }else{
      res = await addStudentApi(student.value)
    }
    if(res.code===1){
      ElMessage.success('保存成功')
      dialogVisible.value=false
      search()
    }else{
      ElMessage.error(res.msg||'操作失败')
    }
  })
}

onMounted(()=>{
  search()
  loadClazz()
})
</script>

<style scoped>
.container{
  margin:10px 0;
}
</style>
