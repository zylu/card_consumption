import request from '@/utils/request'

// 查询云存储列表
export function listStorage(query) {
  return request({
    url: '/system/storage/list',
    method: 'get',
    params: query
  })
}

// 查询云存储详细
export function getStorage(id) {
  return request({
    url: '/system/storage/' + id,
    method: 'get'
  })
}

// 新增云存储
export function addStorage(data) {
  return request({
    url: '/system/storage',
    method: 'post',
    data: data
  })
}

// 修改云存储
export function updateStorage(data) {
  return request({
    url: '/system/storage',
    method: 'put',
    data: data
  })
}

// 删除云存储
export function delStorage(id) {
  return request({
    url: '/system/storage/' + id,
    method: 'delete'
  })
}

// 导出云存储
export function exportStorage(query) {
  return request({
    url: '/system/storage/export',
    method: 'get',
    params: query
  })
}