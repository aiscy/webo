import { AttrField, OrmModel } from 'vuex-orm-decorators'
import { Model } from '@vuex-orm/core'

@OrmModel('generic')
export class Generic extends Model {
  @AttrField()
  public success!: boolean

  @AttrField()
  public message!: string

  /* @AttrField()
  public data!: any */
}
