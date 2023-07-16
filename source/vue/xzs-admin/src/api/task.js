import { post } from '@/utils/request'

// 查询页面数据、编辑、根据id查询、删除任务
export default {
  pageList: query => post('/api/admin/task/page', query),
  edit: query => post('/api/admin/task/edit', query),
  select: id => post('/api/admin/task/select/' + id),
  deleteTask: id => post('/api/admin/task/delete/' + id)
}
