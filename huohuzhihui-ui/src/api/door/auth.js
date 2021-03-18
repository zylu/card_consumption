import request from '@/utils/request'

// 查询门禁授权列表
export function listAuth(query) {
  return request({
    url: '/door/auth/list',
    method: 'get',
    params: query
  })
}

// 查询门禁授权详细
export function getAuth(id) {
  return request({
    url: '/door/auth/' + id,
    method: 'get'
  })
}

// 新增门禁授权
export function addAuth(data) {
  return request({
    url: '/door/auth',
    method: 'post',
    data: data
  })
}

// 修改门禁授权
export function updateAuth(data) {
  return request({
    url: '/door/auth',
    method: 'put',
    data: data
  })
}

// 删除门禁授权
export function delAuth(id) {
  return request({
    url: '/door/auth/' + id,
    method: 'delete'
  })
}

// 导出门禁授权
export function exportAuth(query) {
  return request({
    url: '/door/auth/export',
    method: 'get',
    params: query
  })
}