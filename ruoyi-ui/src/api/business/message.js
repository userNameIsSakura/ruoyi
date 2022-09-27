import request from '@/utils/request'

// 查询业务数据列表
export function listMessage(query) {
  return request({
    url: '/business/message/list',
    method: 'get',
    params: query
  })
}

// 查询业务数据详细
export function getMessage(messageId) {
  return request({
    url: '/business/message/' + messageId,
    method: 'get'
  })
}

// 新增业务数据
export function addMessage(data) {
  return request({
    url: '/business/message',
    method: 'post',
    data: data
  })
}


// 修改业务数据
export function updateMessage(data) {
  return request({
    url: '/business/message',
    method: 'put',
    data: data
  })
}

// 删除业务数据
export function delMessage(messageId) {
  return request({
    url: '/business/message/' + messageId,
    method: 'delete'
  })
}
