import request from '@/utils/request'

// 查询专业负责人策划列表
export function listSubitemsPlanning(query) {
  return request({
    url: '/business/SubitemsPlanning/list',
    method: 'get',
    params: query
  })
}

// 查询专业负责人策划详细
export function getSubitemsPlanning(id) {
  return request({
    url: '/business/SubitemsPlanning/' + id,
    method: 'get'
  })
}

// 新增专业负责人策划
export function addSubitemsPlanning(data) {
  return request({
    url: '/business/SubitemsPlanning',
    method: 'post',
    data: data
  })
}

// 修改专业负责人策划
export function updateSubitemsPlanning(data) {
  return request({
    url: '/business/SubitemsPlanning',
    method: 'put',
    data: data
  })
}

// 删除专业负责人策划
export function delSubitemsPlanning(id) {
  return request({
    url: '/business/SubitemsPlanning/' + id,
    method: 'delete'
  })
}

//查询已分配的设计比例
export function allocatedDesignList(query) {
  return request({
    //url: '/business/SubitemsPlanning/design/allocatedList/',
    url: '/business/SubitemsPlanning/index/',
    method: 'get',
    params: query
  })

}
