import request from '@/utils/request'

// 查询门禁列表
export function listDoor(query) {
  return request({
    url: '/door/door/list',
    method: 'get',
    params: query
  })
}

// 查询门禁详细
export function getDoor(id) {
  return request({
    url: '/door/door/' + id,
    method: 'get'
  })
}

// 新增门禁
export function addDoor(data) {
  return request({
    url: '/door/door',
    method: 'post',
    data: data
  })
}

// 修改门禁
export function updateDoor(data) {
  return request({
    url: '/door/door',
    method: 'put',
    data: data
  })
}

// 删除门禁
export function delDoor(id) {
  return request({
    url: '/door/door/' + id,
    method: 'delete'
  })
}

// 导出门禁
export function exportDoor(query) {
  return request({
    url: '/door/door/export',
    method: 'get',
    params: query
  })
}