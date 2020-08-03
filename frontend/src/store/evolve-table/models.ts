import {Model} from '@vuex-orm/core'
import {AttrField, HasManyField, OrmModel, PrimaryKey} from 'vuex-orm-decorators'

@OrmModel('evolveLine')
export class EvolveLine extends Model {
  @PrimaryKey()
  @AttrField()
  public name!: string

  @AttrField()
  public text!: string

  @AttrField()
  public files!: Array<string>
}

@OrmModel('evolve')
export class EvolveModel extends Model {
  @HasManyField(EvolveLine, 'name')
  public lines!: Array<EvolveLine>

  static fetchByName(name: string, startRow: number, count: number, sortBy: string, descending: boolean) {
    return this.api().get(new URL(name, '/api/evolve/linesOf/').href, {
      persistBy: 'create',
      params: {
        startRow,
        count,
        sortBy,
        descending
      },
      dataTransformer: (response) => {
        return response.data
      }
    })
  }
}
