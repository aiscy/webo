<template lang="pug">
  q-page(padding)
    q-ajax-bar(position='bottom', color='purple', size='15px')
    q-table(:pagination.sync='pagination', :data='tableData', :columns='tableColumns', :filter='filter', row-key='id',
      :loading='loading', binary-state-sort, :rows-per-page-options='[25]', dense, flat
    )
      template(v-slot:body-cell-audio='props')
        q-td(:props='props', auto-width=true)
          .column
            .col(v-for='item in props.row.filePath' :key='item')
              q-media-player(dense, type='audio', :volume='volume', :source='item', preload='none'
                hide-volume-slider, cross-origin='anonymous', background-color='deep-purple-3'
              )
                template(v-slot:volume)
                  div
      template(v-if='isDataFetched', v-slot:top-right='props')
        q-pagination(v-model='pagination.page', color='purple', :max='props.pagesNumber',
          :max-pages='maxAmountOfPagesAtTime', @input='onRequest', :to-fn='toFn'
        )
      template(v-if='isDataFetched', v-slot:pagination='props')
        q-pagination(v-model='pagination.page', color='purple', :max='props.pagesNumber',
          :max-pages='maxAmountOfPagesAtTime', @input='onRequest', :to-fn='toFn'
        )
      template(v-if='isDataFetched', v-slot:top-left)
        q-badge(label='Volume')
        q-slider(v-model='volume', :min='0', :max='100', label, style={'width': '200px'})
</template>

<script lang="ts">
  import Vue from 'vue'
  import {EvolveCharacterLine, TableColumns} from 'components/models'
  import {AxiosResponse} from 'axios'
  import {QMediaPlayer} from '@quasar/quasar-ui-qmediaplayer'

  export default Vue.extend({
  name: 'EvolveData',
  components: {
    QMediaPlayer
  },
  data: function() {
    const tableData: Array<EvolveCharacterLine> = []
    const tableColumns: Array<TableColumns> = [
      {
        name: 'id',
        required: true,
        label: 'id',
        align: 'left',
        field: (row: EvolveCharacterLine) => row.id,
        sortable: true
      },
      {
        name: 'name',
        required: true,
        label: 'lineName',
        align: 'left',
        field: (row: EvolveCharacterLine) => row.lineName,
        sortable: true
      },
      {
        name: 'text',
        required: true,
        label: 'lineText',
        align: 'center',
        field: (row: EvolveCharacterLine) => row.lineText,
        sortable: false
      },
      {
        name: 'audio',
        required: true,
        label: 'lineAudio',
        align: 'right',
        field: (row: EvolveCharacterLine) => row.filePath,
        sortable: false
      }
    ]
    const filter = ''
    const maxAmountOfPagesAtTime = 10
    const pagination = {
      sortBy: 'desc',
      descending: false,
      page: 1,
      rowsPerPage: 25,
      rowsNumber: 0
    }
    const loading = false
    const volume = 100
    return {
      tableData, tableColumns, pagination, maxAmountOfPagesAtTime, filter, loading, volume
    }
  },
  methods: {
    async setRowsNumber(): Promise<void> {
      console.log('setRowsNumber')
      const name: string = this.$route.params.name
      const response: AxiosResponse<number> = await this.$axios.get<number>('/api/evolve/rowsNumberOf/' + name) // TODO
      this.pagination.rowsNumber = response.data
    },
    /* async onRequest(props): Promise<void> {
      console.log('onRequest')
      const { page, rowsPerPage, sortBy, descending } = props.pagination
      const filter: string = props.filter
      const startRow: number = (page - 1) * rowsPerPage
      const data: Array<EvolveCharacterLine> = await this.fetchData(startRow, rowsPerPage, filter)
      this.tableData.splice(0, this.tableData.length, ...data)

      this.pagination.page = page
      this.pagination.rowsPerPage = rowsPerPage
      this.pagination.sortBy = sortBy
      this.pagination.descending = descending
    }, */
    async onRequest(page: number): Promise<void> {
      this.loading = true
      const startRow: number = (page - 1) * this.pagination.rowsPerPage
      const data: Array<EvolveCharacterLine> = await this.fetchData(startRow, this.pagination.rowsPerPage)
      this.tableData.splice(0, this.tableData.length, ...data)

      this.pagination.page = page
      this.loading = false
    },
    async fetchData(startRow = 0, count = 25, filter = '', sortBy = 'desc', descending = true): Promise<Array<EvolveCharacterLine>> {
      const name: string = this.$route.params.name
      let response: AxiosResponse<Array<EvolveCharacterLine>>
      try {
        response = await this.$axios.get<Array<EvolveCharacterLine>>(
          '/api/evolve/linesOf/' + name, // TODO
          {
            params: {
              startRow: startRow,
              count: count,
              sortBy: sortBy,
              descending: descending
            }
          }
        )
      } catch (error) {
        // this.loading = false
        this.$q.notify({
          timeout: 10000,
          multiLine: true,
          message: `An error occurred while trying to fetch data from the server!\n
               Very not nice of the server! ):<`,
          type: 'negative'
        })
        throw error
      }
      this.loading = false
      return response.data
    },
    setupTable(): void {
      this.setRowsNumber()
      const page = this.$route.query.page
      if (page) {
        this.pagination.page = +page
      }
      this.onRequest(this.pagination.page)
    }
  },
  computed: {
    isDataFetched: function(): boolean {
      return this.tableData.length > 0
    },
    toFn: function(): Function {
      return (page: number) => ({ query: { page } })
    }
  },
  watch: {
    $route: function() {
      this.setupTable()
    }
  },
  mounted() {
    this.setupTable()
  },
  beforeRouteUpdate(to, from, next) {
    if (this.isDataFetched) {
      this.tableData = []
    }
    next()
  }
})
</script>
