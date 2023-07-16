import { post } from '@/utils/request'

// 消息页面列表，发出消息
export default {
  pageList: query => post('/api/admin/message/page', query),
  send: query => post('/api/admin/message/send', query)
}
