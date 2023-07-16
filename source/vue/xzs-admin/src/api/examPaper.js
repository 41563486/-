import { post } from '@/utils/request'

export default {
  // 页面列表
  pageList: query => post('/api/admin/exam/paper/page', query),
  // 任务试卷页面
  taskExamPage: query => post('/api/admin/exam/paper/taskExamPage', query),
  // 编辑
  edit: query => post('/api/admin/exam/paper/edit', query),
  select: id => post('/api/admin/exam/paper/select/' + id),
  deletePaper: id => post('/api/admin/exam/paper/delete/' + id)
}
