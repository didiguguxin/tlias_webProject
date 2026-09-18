import request from '@/utils/request'

//学员分页条件查询
export const queryStudentPageApi = (params) => request.get('/students', { params })
//新增学员
export const addStudentApi = (student) => request.post('/students', student)
//根据id查询学员
export const queryStudentInfoApi = (id) => request.get(`/students/${id}`)
//修改学员
export const updateStudentApi = (student) => request.put('/students', student)
//批量删除学员 路径参数 /students/{ids}
export const deleteStudentApi = (ids) => request.delete(`/students/${ids}`)
//获取全部班级（下拉选择班级）
export const queryAllClazzApi = () => request.get('/clazzs/list')
