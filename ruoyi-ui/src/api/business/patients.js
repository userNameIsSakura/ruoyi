import request from '@/utils/request'

// 查询病人列表列表
export function listPatients(query) {
  return request({
    url: '/business/patients/list',
    method: 'get',
    params: query
  })
}

// 查询病人列表详细
export function getPatients(patientId) {
  return request({
    url: '/business/patients/' + patientId,
    method: 'get'
  })
}

// 新增病人列表
export function addPatients(data) {
  return request({
    url: '/business/patients',
    method: 'post',
    data: data
  })
}

// 修改病人列表
export function updatePatients(data) {
  return request({
    url: '/business/patients',
    method: 'put',
    data: data
  })
}

// 删除病人列表
export function delPatients(patientId) {
  return request({
    url: '/business/patients/' + patientId,
    method: 'delete'
  })
}


//根据病人id获得网关id
export function gateWayId(patientId) {
  // alert(patientId);
  return request({
    url:'/business/message/gateWayId/'+patientId,
    method: 'get'
  })
}
