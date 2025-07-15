import request from '@/utils/request'
import { defineStore } from 'pinia';

// 查询专业负责人策划列表
export function listProfessionalAllocation(query) {
  return request({
    url: '/business/ProfessionalAllocation/list',
    method: 'get',
    params: query
  })
}

// 查询专业负责人策划详细
export function getProfessionalAllocation(id) {
  return request({
    url: '/business/ProfessionalAllocation/' + id,
    method: 'get'
  })
}

// 新增专业负责人策划
export function addProfessionalAllocation(data) {
  return request({
    url: '/business/ProfessionalAllocation',
    method: 'post',
    data: data
  })
}

// 修改专业负责人策划
export function updateProfessionalAllocation(data) {
  return request({
    url: '/business/ProfessionalAllocation',
    method: 'put',
    data: data
  })
}

// 删除专业负责人策划
export function delProfessionalAllocation(id) {
  return request({
    url: '/business/ProfessionalAllocation/' + id,
    method: 'delete'
  })
}

// 用于储存主表的id，方便点击链接时将该Id传给子表
export const useProjectStore = defineStore('project', {
  state: () => ({
    prjId: null,
    headId: null,
  }),
  actions: {
    setPrjId(id) {
      this.prjId = id;
    },
    setHeadId(id) {
      this.headId = id;
    }
  }
});



// export function selectProfessionalAllocationB(query) {
//   return request({
//     url: '/business/ProfessionalAllocation/edit/ProfessionalAllocationB/',
//     method: 'get',
//     params: query
//   })
// }




  // 查询已分配设计比例的项目
  export function allocatedDesignList(query) {
    return request({
      url: '/business/ProfessionalAllocation/design/allocatedList',
      method: 'get',
      params: query
    })
  }


// 获取项目设计占比 (def2)
export function getProjectDesignPctFromBackEnd(prjId) {
  return request({
    url: '/business/ProfessionalAllocation/design/designRatioInAProject',
    method: 'get',
    params: { prjId }
  });

}

export function selectProfessionalAllocationB(queryParams) {
  return request({
    url: '/business/ProfessionalAllocationB/listByProject',
    method: 'get',
    params: {
      prjId: queryParams.prjId,
      profession: queryParams.profession, // 新增 headId 查询条件

    }
  })
}


/**
 * 根据 prjId 更新子表 tb_professional_allocation_b 的 headId
 * @param {string} prjId - 项目ID
 * @param {string} headId - 主表ID
 */
export function updateSubTableHeadIdByPrjId(prjId, headId) {
  return request({
    url: '/business/ProfessionalAllocation/updateSubHeadId',
    method: 'put',
    params: { prjId, headId }
  });
}


// 新增专业负责人策划
export function batchAddProfessionalAllocation(data) {
  return request({
    url: '/business/ProfessionalAllocation/batchAdd',
    method: 'post',
    data: data
  })
}

// 异步查询专业字段
export function getProfessionalByHeadId(headId) {
  return request({
    url: `/business/ProfessionalAllocation/getProfessionalByHeadId/${headId}`,
    method: 'get'
  });
}

// 更新专业字段
export function updateProfession(headId, profession) {
  return request({
    url: '/business/ProfessionalAllocation/updateProfession',
    method: 'put',
    params: {
      headId,
      profession
    }
  });
}
