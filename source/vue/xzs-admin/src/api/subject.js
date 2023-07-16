import { post } from '@/utils/request'

// 列表查询、页面列表数据、编辑、查询、删除学科
export default {
  list: query => post('/api/admin/education/subject/list'),
  pageList: query => post('/api/admin/education/subject/page', query),
  edit: query => post('/api/admin/education/subject/edit', query),
  select: id => post('/api/admin/education/subject/select/' + id),
  deleteSubject: id => post('/api/admin/education/subject/delete/' + id)
}
