import { post } from '@/utils/request'

// 获取用户的页面数据、
// 获取用户的事件页面数据、
// 创建用户、
// 查询用户、
// 获取现在的用户、
// 更新用户、
// 改变状态、
// 删除用户、
// 通过用户名称查询
export default {
  getUserPageList: query => post('/api/admin/user/page/list', query),
  getUserEventPageList: query => post('/api/admin/user/event/page/list', query),
  createUser: query => post('/api/admin/user/edit', query),
  selectUser: id => post('/api/admin/user/select/' + id),
  getCurrentUser: () => post('/api/admin/user/current'),
  updateUser: query => post('/api/admin/user/update', query),
  changeStatus: id => post('/api/admin/user/changeStatus/' + id),
  deleteUser: id => post('/api/admin/user/delete/' + id),
  selectByUserName: query => post('/api/admin/user/selectByUserName', query)
}
