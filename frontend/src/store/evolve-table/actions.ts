import {ActionTree} from 'vuex'
import {Store} from '../index'
import {EvolveTableState} from './state'
import Axios, {AxiosResponse} from 'axios'
import {EvolveJsonLines, EvolveJsonRowsNumber, JsonResponse} from 'components/models'
import {FetchDataPayload} from 'src/store/evolve-table/payloads'
import {Notify} from 'quasar'

function showErrorMessage(): void {
  Notify.create({
    timeout: 10000,
    multiLine: false,
    type: 'negative',
    closeBtn: true,
    message: `An error occurred while trying to fetch the data from the server!\n
               Very not nice of the server! ):<`
  })
}

const actions: ActionTree<EvolveTableState, Store> = {
  async initialize({ commit, dispatch }, payload) { // TODO
    let response: AxiosResponse<JsonResponse<EvolveJsonRowsNumber>>
    try {
      response = await Axios.get('/api/evolve/rowsNumberOf/' + payload.name)
    } catch (error) {
      console.log(error)
      showErrorMessage()
      throw error
    }
    // @ts-ignore // TODO
    const rowsNumber = response.data.data.rowsNumber
    commit('initialize', {
      characterName: payload.name,
      rowsNumber
    })
    await dispatch('fetchData', { page: payload.page })
  },
  terminate({ commit }) {
    commit('terminate')
  },
  async fetchData({ commit, state }, payload: FetchDataPayload) {
    const currentPage = state.pagination.page
    const startRow: number = (payload.page - 1) * state.pagination.rowsPerPage
    commit('fetching', payload.page)
    try {
      const response: AxiosResponse<JsonResponse<EvolveJsonLines>> = await Axios.get(
        '/api/evolve/linesOf/' + state.characterName,
        {
          params: {
            startRow,
            count: state.pagination.rowsPerPage,
            sortBy: state.pagination.sortBy,
            descending: state.pagination.descending
          }
        }
      )
      // @ts-ignore // TODO
      const lines = response.data.data.lines
      commit('fillTable', lines)
      commit('fetched')
    } catch (error) {
      console.log(error)
      showErrorMessage()
      commit('fetched', currentPage)
      throw error
    }
  }
}

export default actions
