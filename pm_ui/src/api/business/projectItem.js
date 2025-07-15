import request from '@/utils/request'

// 查询项目子项列表
export function listProjectItem(query) {
  return request({
    url: '/business/ProfessionalAllocationB/list',
    method: 'get',
    params: query
  })
}

// 查询项目子项详细
export function getProjectItem(id) {
  return request({
    url: '/business/ProfessionalAllocationB/' + id,
    method: 'get'
  })
}

// 新增项目子项
export function addProjectItem(data) {
  return request({
    url: '/business/ProfessionalAllocationB',
    method: 'post',
    data: data
  })
}

// 修改项目子项
export function updateProjectItem(data) {
  return request({
    url: '/business/ProfessionalAllocationB',
    method: 'put',
    data: data
  })
}

// 删除项目子项
export function delProjectItem(id) {
  return request({
    url: '/business/ProfessionalAllocationB/' + id,
    method: 'delete'
  })
}

// 根据prjId查询项目子项列表
export function getItemListByPrjId(query) {
  return request({
    url: '/business/ProfessionalAllocationB/getListByPrjId',
    method: 'get',
    params: query
  })
}


export function getUnallocatedProjectItems(params) {
  return request({
    url: '/projectItem/unallocated',
    method: 'get',
    params
  });
}

export function assignProjectItems(id,prjId, headId) {
  return request({
    url: '/projectItem/assign',
    method: 'put',
    params: { id, prjId, headId }
  });
}

export function getAssignedProjectItems(headId) {
  return request({
    url: `/projectItem/assigned/${headId}`,
    method: 'get'
  });
}

export function unassignProjectItem(id) {
  return request({
    url: `/projectItem/unassign/${id}`,
    method: 'delete'
  });
}

export function assignProjectItemsBatch(ids, prjId, headId) {
  return request({
    url: '/projectItem/assign/batch',
    method: 'put',
    params: { prjId, headId },
    data: ids // 发送数组作为 body 数据
  });
}
