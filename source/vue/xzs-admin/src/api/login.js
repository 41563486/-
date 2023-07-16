import { post, postWithLoadTip } from '@/utils/request'

//  登录或者登出
export default {
  login: query => postWithLoadTip(`/api/user/login`, query),
  logout: query => post(`/api/user/logout`, query)
}
