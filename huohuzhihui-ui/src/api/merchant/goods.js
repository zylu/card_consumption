import request from '@/utils/request'

// 查询商户商品列表
export function listGoods(query) {
  return request({
    url: '/merchant/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商户商品详细
export function getGoods(id) {
  return request({
    url: '/merchant/goods/' + id,
    method: 'get'
  })
}

// 新增商户商品
export function addGoods(data) {
  return request({
    url: '/merchant/goods',
    method: 'post',
    data: data
  })
}

// 修改商户商品
export function updateGoods(data) {
  return request({
    url: '/merchant/goods',
    method: 'put',
    data: data
  })
}

// 删除商户商品
export function delGoods(id) {
  return request({
    url: '/merchant/goods/' + id,
    method: 'delete'
  })
}

// 导出商户商品
export function exportGoods(query) {
  return request({
    url: '/merchant/goods/export',
    method: 'get',
    params: query
  })
}