import { post } from '@/utils/request'

// 题目页面列表数据、编辑、根据id查询、删除题目
export default {
  pageList: query => post('/api/admin/question/page', query),
  edit: query => post('/api/admin/question/edit', query),
  select: id => post('/api/admin/question/select/' + id),
  deleteQuestion: id => post('/api/admin/question/delete/' + id)
}
