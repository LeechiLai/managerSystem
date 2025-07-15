import request from '@/utils/request'

// 查询项目概况列表
export function listProjectOverview(query) {
  return request({
    url: '/business/project/list',
    method: 'get',
    params: query
  })
}

// 查询项目概况详细
export function getProjectOverview(id) {
  return request({
    url: '/business/ProjectOverview/' + id,
    method: 'get'
  })
}

// 新增项目概况
export function addProjectOverview(data) {
  return request({
    url: '/business/ProjectOverview',
    method: 'post',
    data: data
  })
}

// 修改项目概况
export function updateProjectOverview(data) {
  return request({
    url: '/business/ProjectOverview',
    method: 'put',
    data: data
  })
}

// 删除项目概况
export function delProjectOverview(id) {
  return request({
    url: '/business/ProjectOverview/' + id,
    method: 'delete'
  })
}
