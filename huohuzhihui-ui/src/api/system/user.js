import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getUser(userId) {
  return request({
    url: '/system/user/' + praseStrEmpty(userId),
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/user/' + userId,
    method: 'delete'
  })
}

// 导出用户
export function exportUser(query) {
  return request({
    url: '/system/user/export',
    method: 'get',
    params: query
  })
}

// 用户密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/system/user/importTemplate',
    method: 'get'
  })
}
// 查询账户详细
export function getAccount(userId) {
  return request({
    url: '/account/account/' + userId,
    method: 'get'
  })
}

//冻结账户
export function freezeAccount(ids) {
  var url =  '/account/account/freeze?';
  for(var i = 0 ; i <ids.length;i++ ){
    var id = ids[i];
    url = url+ "ids="+id + "&";
  }
  return request({
    url: url,
    method: 'post'
  })
}

//解冻账户
export function unfreezeAccount(ids) {
  var url =  '/account/account/unfreeze?';
  for(var i = 0 ; i <ids.length;i++ ){
    var id = ids[i];
    url = url+ "ids="+id + "&";
  }
  return request({
    url: url,
    method: 'post'
  })
}

//注销账户
export function cancelAccount(ids) {
  var url =  '/account/account/cancel?';
  for(var i = 0 ; i <ids.length;i++ ){
    var id = ids[i];
    url = url+ "ids="+id + "&";
  }
  return request({
    url: url,
    method: 'post'
  })
}
// 充值
export function recharge(id,amount,channelCode) {
  return request({
    url: '/account/recharge?userId=' + id+'&amount='+amount+'&payChannel='+channelCode,
    method: 'post'
  })
}

