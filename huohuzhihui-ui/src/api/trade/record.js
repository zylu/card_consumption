import request from '@/utils/request'

// 查询交易记录列表
export function listRecord(query) {
  return request({
    url: '/trade/record/rechargeList',
    method: 'get',
    params: query
  })
}

// 查询交易记录详细
export function getRecord(id) {
  return request({
    url: '/trade/record/' + id,
    method: 'get'
  })
}

// 新增交易记录
export function addRecord(data) {
  return request({
    url: '/trade/record',
    method: 'post',
    data: data
  })
}

// 修改交易记录
export function updateRecord(data) {
  return request({
    url: '/trade/record',
    method: 'put',
    data: data
  })
}

// 删除交易记录
export function delRecord(id) {
  return request({
    url: '/trade/record/' + id,
    method: 'delete'
  })
}

// 导出交易记录
export function exportRecord(query) {
  return request({
    url: '/trade/record/export',
    method: 'get',
    params: query
  })
}
