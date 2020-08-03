import {store} from 'quasar/wrappers'
import Vuex from 'vuex'

import evolveTable from './evolve-table'
import {EvolveTableState} from './evolve-table/state'

import evolveLayout from './evolve-layout'
import {EvolveLayoutState} from './evolve-layout/state'

export interface Store {
  evolveTable: EvolveTableState
  evolveLayout: EvolveLayoutState
}

export default store(function({ Vue }) {
  Vue.use(Vuex)

  return new Vuex.Store<Store>({
    modules: {
      evolveTable,
      evolveLayout
    },
    /* plugins: [
      ORMDatabase.install()
    ], */
    strict: !!process.env.DEV
  })
})
