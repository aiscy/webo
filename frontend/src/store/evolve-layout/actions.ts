import {ActionTree} from 'vuex'
import {Store} from '../index'
import {EvolveLayoutState} from './state'

const actions: ActionTree<EvolveLayoutState, Store> = {
  toggleLeftDrawer({ commit }) {
    commit('toggleLeftDrawer')
  }
}

export default actions
