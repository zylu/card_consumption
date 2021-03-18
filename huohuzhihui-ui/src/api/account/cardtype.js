import request from '@/utils/request'

// 查询卡类型列表
export function listCardtype(query) {
  return request({
    url: '/account/cardtype/list',
    method: 'get',
    params: query
  })
}

// 查询卡类型详细
export function getCardtype(id) {
  return request({
    url: '/account/cardtype/' + id,
    method: 'get'
  })
}

// 新增卡类型
export function addCardtype(data) {
  return request({
    url: '/account/cardtype',
    method: 'post',
    data: data
  })
}

// 修改卡类型
export function updateCardtype(data) {
  return request({
    url: '/account/cardtype',
    method: 'put',
    data: data
  })
}

// 删除卡类型
export function delCardtype(id) {
  return request({
    url: '/account/cardtype/' + id,
    method: 'delete'
  })
}

// 导出卡类型
export function exportCardtype(query) {
  return request({
    url: '/account/cardtype/export',
    method: 'get',
    params: query
  })
}
