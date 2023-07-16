import { post } from '@/utils/request'

export default {
  // 发送post请求
  index: () => post('/api/admin/dashboard/index')
}
