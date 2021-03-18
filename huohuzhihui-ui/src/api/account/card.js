import request from '@/utils/request'

// 查询账户下的卡列表
export function listCard(query) {
  return request({
    url: '/account/card/list',
    method: 'get',
    params: query
  })
}

// 查询账户下的卡详细
export function getCard(id) {
  return request({
    url: '/account/card/' + id,
    method: 'get'
  })
}

// 新增账户下的卡
export function addCard(data) {
  return request({
    url: '/account/card',
    method: 'post',
    data: data
  })
}

// 修改账户下的卡
export function updateCard(data) {
  return request({
    url: '/account/card',
    method: 'put',
    data: data
  })
}
// 补卡
export function replaceCard(data) {
  return request({
    url: '/account/card/replace',
    method: 'post',
    data: data
  })
}

// 删除账户下的卡
export function delCard(id) {
  return request({
    url: '/account/card/' + id,
    method: 'delete'
  })
}

// 导出账户下的卡
export function exportCard(query) {
  return request({
    url: '/account/card/export',
    method: 'get',
    params: query
  })
}
//挂失卡
export function lossCard(ids) {
  var url =  '/account/card/loss?';
  for(var i = 0 ; i <ids.length;i++ ){
    var id = ids[i];
    url = url+ "ids="+id + "&";
  }
  return request({
    url: url,
    method: 'post'
  })
}

//解挂卡
export function unlossCard(ids) {
  var url =  '/account/card/unloss?';
  for(var i = 0 ; i <ids.length;i++ ){
    var id = ids[i];
    url = url+ "ids="+id + "&";
  }
  return request({
    url: url,
    method: 'post'
  })
}
//注销卡
export function cancelCard(ids) {
  var url =  '/account/card/cancel?';
  for(var i = 0 ; i <ids.length;i++ ){
    var id = ids[i];
    url = url+ "ids="+id + "&";
  }
  return request({
    url: url,
    method: 'post'
  })
}
