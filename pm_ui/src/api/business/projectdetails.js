import request from '@/utils/request'

// 查询项目负责人策划列表
export function listProjectdetails(query) {
  return request({
    url: '/business/projectdetails/list',
    method: 'get',
    params: query
  })
}

// 查询项目负责人策划详细
export function getProjectdetails(id) {
  return request({
    url: '/business/projectdetails/' + id,
    method: 'get'
  })
}

// 新增项目负责人策划
export function addProjectdetails(data) {
  return request({
    url: '/business/projectdetails',
    method: 'post',
    data: data
  })
}

// 修改项目负责人策划
export function updateProjectdetails(data) {
  return request({
    url: '/business/projectdetails',
    method: 'put',
    data: data
  })
}

// 删除项目负责人策划
export function delProjectdetails(id) {
  return request({
    url: '/business/projectdetails/' + id,
    method: 'delete'
  })
}

// 删除项目子项
export function delProjectSubitems(id) {
  return request({
    url: '/business/projectdetails/sub/' + id,
    method: 'delete'
  })
}

// 查询已分配管理比例的项目
export function allocatedManageList(query) {
  return request({
    url: '/business/projectdetails/manage/allocatedList/',
    method: 'get',
    params: query
  })
}

