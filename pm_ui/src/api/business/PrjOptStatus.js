import request from '@/utils/request'

// 查询项目运行情况列表
export function listPrjOptStatus(query) {
  return request({
    url: '/business/PrjOptStatus/list',
    method: 'get',
    params: query
  })
}

// 查询项目运行情况详细
export function getPrjOptStatus(id) {
  return request({
    url: '/business/PrjOptStatus/' + id,
    method: 'get'
  })
}

// 新增项目运行情况
export function addPrjOptStatus(data) {
  return request({
    url: '/business/PrjOptStatus',
    method: 'post',
    data: data
  })
}

// 修改项目运行情况
export function updatePrjOptStatus(data) {
  return request({
    url: '/business/PrjOptStatus',
    method: 'put',
    data: data
  })
}

// 删除项目运行情况
export function delPrjOptStatus(id) {
  return request({
    url: '/business/PrjOptStatus/' + id,
    method: 'delete'
  })
}
