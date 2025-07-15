import request from '@/utils/request'

// 查询管理组比例分配列表
export function listManagementRatio(query) {
  return request({
    url: '/business/ManagementRatio/list',
    method: 'get',
    params: query
  })
}

// 查询管理组比例分配详细
export function getManagementRatio(id) {
  return request({
    url: '/business/ManagementRatio/' + id,
    method: 'get'
  })
}

// 新增管理组比例分配
export function addManagementRatio(data) {
  return request({
    url: '/business/ManagementRatio',
    method: 'post',
    data: data
  })
}

// 修改管理组比例分配
export function updateManagementRatio(data) {
  return request({
    url: '/business/ManagementRatio',
    method: 'put',
    data: data
  })
}

// 删除管理组比例分配
export function delManagementRatio(id) {
  return request({
    url: '/business/ManagementRatio/' + id,
    method: 'delete'
  })
}
