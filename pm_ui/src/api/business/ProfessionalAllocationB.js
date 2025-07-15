import request from '@/utils/request'

// 查询专业负责人策划子表列表
  export function listProfessionalAllocationB(query) {
    return request({
      url: '/business/ProfessionalAllocationB/list',
      method: 'get',
      params: query
    })
  }

  // 查询专业负责人策划子表详细
  export function getProfessionalAllocationB(id) {
    return request({
      url: '/business/ProfessionalAllocationB/' + id,
      method: 'get'
    })
  }

  // 新增专业负责人策划子表
  export function addProfessionalAllocationB(data) {
    return request({
      url: '/business/ProfessionalAllocationB',
      method: 'post',
      data: data
    })
  }


  // 修改专业负责人策划子表
  export function updateProfessionalAllocationB(data) {
    return request({
      url: '/business/ProfessionalAllocationB',
      method: 'put',
      data: data
    })
  }

  // 删除专业负责人策划子表
  export function delProfessionalAllocationB(id) {
    return request({
      url: '/business/ProfessionalAllocationB/' + id,
      method: 'delete'
    })
  }


  export function getCommonNameList(query) {
    return request({
      url: "/business/ProfessionalAllocationB/proRespNames",
      method: "get",
      params: query,
    })
  }


  //从项目子项跳转专业策划时根据项目ID获取项目子项
export function getProjectItems(queryParams) {
  return request({
    url: '/business/ProfessionalAllocationB/getProjectItems',
    method: 'get',
    params: {
      prjId: queryParams.prjId,
      itemId: queryParams.itemId
    }
  })
}
