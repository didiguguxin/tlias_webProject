import request from '@/utils/request'

//员工性别统计
export const getEmpGenderDataApi = () => {
    return request.get('/report/empGenderData')
}

//员工职位统计
export const getEmpJobDataApi = () => {
    return request.get('/report/empJobData')
}

//学员学历统计
export const getStudentDegreeDataApi = () => {
    return request.get('/report/studentDegreeData')
}

//班级人数统计
export const getStudentCountDataApi = () => {
    return request.get('/report/studentCountData')
}
