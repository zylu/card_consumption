import request from '@/utils/request'

// 查询账户充值列表
export function listRecharge(query) {
  return request({
    url: '/account/recharge/list',
    method: 'get',
    params: query
  })
}

// 查询账户充值详细
export function getRecharge(id) {
  return request({
    url: '/account/recharge/' + id,
    method: 'get'
  })
}

// 新增账户充值
export function addRecharge(data) {
  return request({
    url: '/account/recharge',
    method: 'post',
    data: data
  })
}

// 修改账户充值
export function updateRecharge(data) {
  return request({
    url: '/account/recharge',
    method: 'put',
    data: data
  })
}

// 删除账户充值
export function delRecharge(id) {
  return request({
    url: '/account/recharge/' + id,
    method: 'delete'
  })
}

// 导出账户充值
export function exportRecharge(query) {
  return request({
    url: '/account/recharge/export',
    method: 'get',
    params: query
  })
}