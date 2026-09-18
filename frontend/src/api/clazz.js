import request from '@/utils/request'

// 分页条件查询班级列表
export const queryClazzPageApi = (params)=> request.get('/clazzs',{params})
// 新增班级
export const addClazzApi = (clazz)=> request.post('/clazzs',clazz)
// 根据id查询班级
export const queryClazzInfoApi = (id)=> request.get(`/clazzs/${id}`)
// 修改班级
export const updateClazzApi = (clazz)=> request.put('/clazzs',clazz)
// 删除班级 路径参数 /clazzs/{id}
export const deleteClazzApi = (id)=> request.delete(`/clazzs/${id}`)
// 查询全部班主任（全部员工，下拉选择班主任）
export const queryAllMasterApi = ()=> request.get('/emps/list')
