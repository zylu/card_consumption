import request from '@/utils/request'

// 查询交易渠道列表
export function listChannel(query) {
  return request({
    url: '/trade/channel/list',
    method: 'get',
    params: query
  })
}

// 查询交易渠道详细
export function getChannel(id) {
  return request({
    url: '/trade/channel/' + id,
    method: 'get'
  })
}

// 新增交易渠道
export function addChannel(data) {
  return request({
    url: '/trade/channel',
    method: 'post',
    data: data
  })
}

// 修改交易渠道
export function updateChannel(data) {
  return request({
    url: '/trade/channel',
    method: 'put',
    data: data
  })
}

// 删除交易渠道
export function delChannel(id) {
  return request({
    url: '/trade/channel/' + id,
    method: 'delete'
  })
}

// 导出交易渠道
export function exportChannel(query) {
  return request({
    url: '/trade/channel/export',
    method: 'get',
    params: query
  })
}