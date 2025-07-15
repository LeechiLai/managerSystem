import request from '@/utils/request'

// 查询个人奖金列表
export function listBonus(query) {
  return request({
    url: '/business/bonus/list',
    method: 'get',
    params: query
  })
}

// 查询个人奖金详细
export function getBonus(id) {
  return request({
    url: '/business/bonus/' + id,
    method: 'get'
  })
}

// 新增个人奖金
export function addBonus(data) {
  return request({
    url: '/business/bonus',
    method: 'post',
    data: data
  })
}

// 修改个人奖金
export function updateBonus(data) {
  return request({
    url: '/business/bonus',
    method: 'put',
    data: data
  })
}

// 删除个人奖金
export function delBonus(id) {
  return request({
    url: '/business/bonus/' + id,
    method: 'delete'
  })




}


export function getBonusListByName(name) {
  return request({
    url: '/business/bonus/listByName',
    method: 'get',
    params: { name }
  })
}