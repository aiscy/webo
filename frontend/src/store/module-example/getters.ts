import {GetterTree} from 'vuex'
import {Store} from '../index'
import {ExampleStateInterface} from './state'

const getters: GetterTree<ExampleStateInterface, Store> = {
  someAction(/* context */) {
    // your code
  }
}

export default getters
