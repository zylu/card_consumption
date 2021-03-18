import request from '@/utils/request'

// 查询商户设备在线离线列表
export function listOnline(query) {
  return request({
    url: '/merchant/online/list',
    method: 'get',
    params: query
  })
}

// 查询商户设备在线离线详细
export function getOnline(id) {
  return request({
    url: '/merchant/online/' + id,
    method: 'get'
  })
}

// 新增商户设备在线离线
export function addOnline(data) {
  return request({
    url: '/merchant/online',
    method: 'post',
    data: data
  })
}

// 修改商户设备在线离线
export function updateOnline(data) {
  return request({
    url: '/merchant/online',
    method: 'put',
    data: data
  })
}

// 删除商户设备在线离线
export function delOnline(id) {
  return request({
    url: '/merchant/online/' + id,
    method: 'delete'
  })
}

// 导出商户设备在线离线
export function exportOnline(query) {
  return request({
    url: '/merchant/online/export',
    method: 'get',
    params: query
  })
}