import axios from 'axios'
import VuexORM from '@vuex-orm/core'
import VuexORMAxios from '@vuex-orm/plugin-axios'
import {EvolveModel} from 'src/store/evolve-table/models'

VuexORM.use(VuexORMAxios, { axios })

const database = new VuexORM.Database()

database.register(EvolveModel)

export default database
