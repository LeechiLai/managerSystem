import request from '@/utils/request'

// 查询设计组比例分配列表
export function listDesignRatio(query) {
  return request({
    url: '/business/DesignRatio/list',
    method: 'get',
    params: query
  })
}

// 查询设计组比例分配详细
export function getDesignRatio(id) {
  return request({
    url: '/business/DesignRatio/' + id,
    method: 'get'
  })
}

// 新增设计组比例分配
export function addDesignRatio(data,projectDesignPct) {
  return request({
    url: '/business/DesignRatio',
    method: 'post',
    data: data,
    params: { projectDesignPct } // 查询参数
  })
}

// 修改设计组比例分配
export function updateDesignRatio(data,projectDesignPct) {
  return request({
    url: '/business/DesignRatio',
    method: 'put',
    data: data,
    params: { projectDesignPct } // 查询参数
  })
}

// 删除设计组比例分配
  export function delDesignRatio(id) {
    return request({
      url: '/business/DesignRatio/' + id,
      method: 'delete'
    })
  }
  // src/api/business/DesignRatio.js


  /**
   * 根据条件查询设计比例
   */
  export function getProjPct(prjId, profession) {
    return request({
      url: "/business/DesignRatio/projPct",
      method: "get",
      params: { prjId, profession}
    });
  }

