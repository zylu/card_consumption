import request from '@/utils/request'

// 查询商户商品订单列表
export function listOrder(query) {
  return request({
    url: '/merchant/order/list',
    method: 'get',
    params: query
  })
}

// 查询商户商品订单详细
export function getOrder(id) {
  return request({
    url: '/merchant/order/' + id,
    method: 'get'
  })
}

// 新增商户商品订单
export function addOrder(data) {
  return request({
    url: '/merchant/order',
    method: 'post',
    data: data
  })
}

// 修改商户商品订单
export function updateOrder(data) {
  return request({
    url: '/merchant/order',
    method: 'put',
    data: data
  })
}

// 删除商户商品订单
export function delOrder(id) {
  return request({
    url: '/merchant/order/' + id,
    method: 'delete'
  })
}

// 导出商户商品订单
export function exportOrder(query) {
  return request({
    url: '/merchant/order/export',
    method: 'get',
    params: query
  })
}