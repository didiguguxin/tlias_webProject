import request from '@/utils/request'
//分页查询操作日志
export const getLogListApi = (params) => {
    return request.get('/log/list', { params })
}
