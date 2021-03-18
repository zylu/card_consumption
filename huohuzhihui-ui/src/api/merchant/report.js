import request from '@/utils/request'

// 查询商户商品订单报表
export function listOrder(query) {
  return request({
    url: '/merchant/order/report',
    method: 'get',
    params: query
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
