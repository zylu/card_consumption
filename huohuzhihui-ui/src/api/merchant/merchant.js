import request from '@/utils/request'

// 查询商户列表
export function listMerchant(query) {
  return request({
    url: '/merchant/merchant/list',
    method: 'get',
    params: query
  })
}

// 查询商户详细
export function getMerchant(id) {
  return request({
    url: '/merchant/merchant/' + id,
    method: 'get'
  })
}

// 新增商户
export function addMerchant(data) {
  return request({
    url: '/merchant/merchant',
    method: 'post',
    data: data
  })
}

// 修改商户
export function updateMerchant(data) {
  return request({
    url: '/merchant/merchant',
    method: 'put',
    data: data
  })
}

// 删除商户
export function delMerchant(id) {
  return request({
    url: '/merchant/merchant/' + id,
    method: 'delete'
  })
}

// 导出商户
export function exportMerchant(query) {
  return request({
    url: '/merchant/merchant/export',
    method: 'get',
    params: query
  })
}