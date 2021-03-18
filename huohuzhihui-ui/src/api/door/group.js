import request from '@/utils/request'

// 查询门分组列表
export function listGroup(query) {
  return request({
    url: '/door/group/list',
    method: 'get',
    params: query
  })
}

// 查询门分组详细
export function getGroup(id) {
  return request({
    url: '/door/group/' + id,
    method: 'get'
  })
}

// 新增门分组
export function addGroup(data) {
  return request({
    url: '/door/group',
    method: 'post',
    data: data
  })
}

// 修改门分组
export function updateGroup(data) {
  return request({
    url: '/door/group',
    method: 'put',
    data: data
  })
}

// 删除门分组
export function delGroup(id) {
  return request({
    url: '/door/group/' + id,
    method: 'delete'
  })
}

// 导出门分组
export function exportGroup(query) {
  return request({
    url: '/door/group/export',
    method: 'get',
    params: query
  })
}