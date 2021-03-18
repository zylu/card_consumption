import request from '@/utils/request'

// 查询商户消费列表
export function listCost(query) {
  return request({
    url: '/trade/cost/list',
    method: 'get',
    params: query
  })
}

// 查询商户消费详细
export function getCost(id) {
  return request({
    url: '/trade/cost/' + id,
    method: 'get'
  })
}

// 新增商户消费
export function addCost(data) {
  return request({
    url: '/trade/cost',
    method: 'post',
    data: data
  })
}

// 修改商户消费
export function updateCost(data) {
  return request({
    url: '/trade/cost',
    method: 'put',
    data: data
  })
}

// 删除商户消费
export function delCost(id) {
  return request({
    url: '/trade/cost/' + id,
    method: 'delete'
  })
}

// 导出商户消费
export function exportCost(query) {
  return request({
    url: '/trade/cost/export',
    method: 'get',
    params: query
  })
}