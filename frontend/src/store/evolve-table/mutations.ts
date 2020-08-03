import {MutationTree} from 'vuex'
import {EvolveTableState} from './state'
import {EvolveLinesModel} from 'components/models'
import {InitializePayload} from 'src/store/evolve-table/payloads'

const mutation: MutationTree<EvolveTableState> = {
  initialize(state, payload: InitializePayload) {
    state.characterName = payload.characterName
    state.pagination.rowsNumber = payload.rowsNumber
  },
  terminate(state) {
    state.pagination.page = 1
    state.pagination.rowsNumber = 0
    state.characterName = ''
    state.tableData = []
    state.isFetchingData = false
  },
  fillTable(state, payload: Array<EvolveLinesModel>) {
    state.tableData.splice(0, state.tableData.length, ...payload)
  },
  fetching(state, payload) {
    state.isFetchingData = true
    if (payload) {
      state.pagination.page = payload
    }
  },
  fetched(state, payload) {
    state.isFetchingData = false
    if (payload) {
      state.pagination.page = payload
    }
  }
}

export default mutation
