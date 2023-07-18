import Cookies from 'js-cookie'

const state = {
  // 对象用于管理侧边栏的状态
  sidebar: {
    opened: Cookies.get('sidebarStatus') ? !!+Cookies.get('sidebarStatus') : true,
    withoutAnimation: false
  },
  device: 'desktop',
  size: Cookies.get('size') || 'medium'
}

const mutations = {
  // 切换用于管理侧边栏的状态
  TOGGLE_SIDEBAR: state => {
    state.sidebar.opened = !state.sidebar.opened
    state.sidebar.withoutAnimation = false
    if (state.sidebar.opened) {
      Cookies.set('sidebarStatus', 1)
    } else {
      Cookies.set('sidebarStatus', 0)
    }
  },
  // CLOSE_SIDEBAR：关闭侧边栏，将 opened 设为 false，并根据 withoutAnimation 设置 cookies。
  CLOSE_SIDEBAR: (state, withoutAnimation) => {
    Cookies.set('sidebarStatus', 0)
    state.sidebar.opened = false
    state.sidebar.withoutAnimation = withoutAnimation
  },
  // 切换设备类型，将 device 更新为传入的设备类型。SET_SIZE：设置应用程序的尺寸，将 size 更新为传入的尺寸，并设置 cookies。
  TOGGLE_DEVICE: (state, device) => {
    state.device = device
  },
  SET_SIZE: (state, size) => {
    state.size = size
    Cookies.set('size', size)
  }
}

const actions = {
  toggleSideBar ({ commit }) {
    commit('TOGGLE_SIDEBAR')
  },
  // 触发 CLOSE_SIDEBAR mutation，同时传入是否使用动画的参数。
  closeSideBar ({ commit }, { withoutAnimation }) {
    commit('CLOSE_SIDEBAR', withoutAnimation)
  },
  // 触发 TOGGLE_DEVICE mutation，传入设备类型参数。
  toggleDevice ({ commit }, device) {
    commit('TOGGLE_DEVICE', device)
  },
  // 设备大小
  setSize ({ commit }, size) {
    commit('SET_SIZE', size)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
