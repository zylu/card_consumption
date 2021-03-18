import request from '@/utils/request'

// 查询商户设备列表
export function listDevice(query) {
  return request({
    url: '/merchant/device/list',
    method: 'get',
    params: query
  })
}

// 查询商户设备详细
export function getDevice(id) {
  return request({
    url: '/merchant/device/' + id,
    method: 'get'
  })
}

// 新增商户设备
export function addDevice(data) {
  return request({
    url: '/merchant/device',
    method: 'post',
    data: data
  })
}

// 修改商户设备
export function updateDevice(data) {
  return request({
    url: '/merchant/device',
    method: 'put',
    data: data
  })
}

// 删除商户设备
export function delDevice(id) {
  return request({
    url: '/merchant/device/' + id,
    method: 'delete'
  })
}

// 导出商户设备
export function exportDevice(query) {
  return request({
    url: '/merchant/device/export',
    method: 'get',
    params: query
  })
}