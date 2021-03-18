import request from '@/utils/request'

// 查询交易记录列表
export function listRecord(query) {
  return request({
    url: '/trade/record/consumeList',
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

// 导出交易记录
export function exportRecord(query) {
  return request({
    url: '/trade/record/export',
    method: 'get',
    params: query
  })
}
