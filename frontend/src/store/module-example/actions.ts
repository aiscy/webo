import {ActionTree} from 'vuex'
import {Store} from '../index'
import {ExampleStateInterface} from './state'

const actions: ActionTree<ExampleStateInterface, Store> = {
  fetchData(/* context */) {
    // your code
  }
}

export default actions
