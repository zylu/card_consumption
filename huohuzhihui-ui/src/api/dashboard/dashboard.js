import request from '@/utils/request'

export function init(){
  return request({
    url: '/system/dashboard/init',
    method: 'get'
  })
}

export function getChart(){
  return request({
    url: '/system/dashboard/getChart',
    method: 'get'
  })
}


