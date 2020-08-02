import {Module} from 'vuex'
import {Store} from '../index'
import state, {EvolveTableState} from './state'
import actions from './actions'
import getters from './getters'
import mutations from './mutations'

const module: Module<EvolveTableState, Store> = {
  namespaced: true,
  actions,
  getters,
  mutations,
  state
}

export default module
