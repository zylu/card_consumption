import request from '@/utils/request'

// 查询门禁记录列表
export function listRecord(query) {
  return request({
    url: '/door/record/list',
    method: 'get',
    params: query
  })
}

// 查询门禁记录详细
export function getRecord(id) {
  return request({
    url: '/door/record/' + id,
    method: 'get'
  })
}

// 新增门禁记录
export function addRecord(data) {
  return request({
    url: '/door/record',
    method: 'post',
    data: data
  })
}

// 修改门禁记录
export function updateRecord(data) {
  return request({
    url: '/door/record',
    method: 'put',
    data: data
  })
}

// 删除门禁记录
export function delRecord(id) {
  return request({
    url: '/door/record/' + id,
    method: 'delete'
  })
}

// 导出门禁记录
export function exportRecord(query) {
  return request({
    url: '/door/record/export',
    method: 'get',
    params: query
  })
}