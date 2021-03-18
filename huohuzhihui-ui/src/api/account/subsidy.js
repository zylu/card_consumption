import request from '@/utils/request'

// 查询补贴列表
export function listSubsidy(query) {
  return request({
    url: '/account/subsidy/list',
    method: 'get',
    params: query
  })
}

// 查询补贴详细
export function getSubsidy(id) {
  return request({
    url: '/account/subsidy/' + id,
    method: 'get'
  })
}

// 新增补贴
export function addSubsidy(data) {
  return request({
    url: '/account/subsidy',
    method: 'post',
    data: data
  })
}

// 修改补贴
export function updateSubsidy(data) {
  return request({
    url: '/account/subsidy',
    method: 'put',
    data: data
  })
}

// 删除补贴
export function delSubsidy(id) {
  return request({
    url: '/account/subsidy/' + id,
    method: 'delete'
  })
}

// 导出补贴
export function exportSubsidy(query) {
  return request({
    url: '/account/subsidy/export',
    method: 'get',
    params: query
  })
}