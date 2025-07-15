import request from '@/utils/request'

// 查询项目收款记录列表
export function listReceipts(query) {
  return request({
    url: '/business/receipts/list',
    method: 'get',
    params: query
  })
}

// 查询项目收款记录详细
export function getReceipts(id) {
  return request({
    url: '/business/receipts/' + id,
    method: 'get'
  })
}

// 根据项目id查询项目收款记录详细
export function getReceiptsByPrjId(prj_id) {
  return request({
    url: '/business/receipts/getSequence/' + prj_id,
    method: 'get'
  })
}

// 新增项目收款记录
export function addReceipts(data) {
  return request({
    url: '/business/receipts',
    method: 'post',
    data: data
  })
}

// 修改项目收款记录
export function updateReceipts(data) {
  return request({
    url: '/business/receipts',
    method: 'put',
    data: data
  })
}

// 删除项目收款记录
export function delReceipts(id) {
  return request({
    url: '/business/receipts/' + id,
    method: 'delete'
  })
}

//获取每个项目的最新收款数据
export function getLatestReceipts(projectIds) {
  return request({
    url: '/business/receipts/latest',
    method: 'post',
    data: projectIds
  });
}
