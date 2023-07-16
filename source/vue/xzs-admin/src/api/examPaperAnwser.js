import { post } from '@/utils/request'

// 试卷答案
export default {
  page: query => post('/api/admin/examPaperAnswer/page', query)
}
