import {GetterTree} from 'vuex'
import {Store} from '../index'
import {EvolveTableState} from './state'

const getters: GetterTree<EvolveTableState, Store> = {
  hasData(state): boolean {
    return state.tableData.length > 0
  }
}

export default getters
