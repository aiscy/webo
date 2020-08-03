import {MutationTree} from 'vuex'
import {EvolveLayoutState} from './state'

const mutation: MutationTree<EvolveLayoutState> = {
  toggleLeftDrawer(state) {
    state.leftDrawer = !state.leftDrawer
  }
}

export default mutation
