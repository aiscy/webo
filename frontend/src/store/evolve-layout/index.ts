import {Module} from 'vuex'
import {Store} from '../index'
import state, {EvolveLayoutState} from './state'
import actions from './actions'
import getters from './getters'
import mutations from './mutations'

const module: Module<EvolveLayoutState, Store> = {
  namespaced: true,
  actions,
  getters,
  mutations,
  state
}

export default module
