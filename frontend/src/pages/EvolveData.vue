<template lang="pug">
  q-page(padding)
    q-ajax-bar(position='bottom', color='purple', size='15px')
    q-table(:pagination.sync='pagination', :data='tableData', :columns='tableColumns', :filter='filter', row-key='id',
      :loading='loading', @request='fetchData', binary-state-sort, :rows-per-page-options='[25]', dense, flat
    )
      template(v-slot:body-cell-audio='props')
        q-td(:props='props', auto-width=true)
          .column
            .col(v-for='item in props.row.filePath' :key='item')
              q-media-player(dense, type='audio', :volume='volume', :source='item', preload='none'
                hide-volume-slider, no-video, cross-origin='anonymous', background-color='deep-purple-3'
              )
                template(v-slot:volume)
                  div
      template(v-if='isDataFetched', v-slot:top-right='props')
        q-pagination(v-model='pagination.page', color='purple', :max='props.pagesNumber', :max-pages='maxAmountOfPagesAtTime')
      template(v-if='isDataFetched', v-slot:pagination='props')
        q-pagination(v-model='pagination.page', color='purple', :max='props.pagesNumber', :max-pages='maxAmountOfPagesAtTime')
      template(v-if='isDataFetched', v-slot:top-left)
        q-badge(label='Volume')
        q-slider(v-model='volume', :min='0', :max='100', label, style={'width': '200px'})

</template>

<script lang="ts">
  import Vue from 'vue'
  import {EvolveCharacterLine, Pagination, TableColumns} from 'components/models'
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
      const response: AxiosResponse<number> = await this.$axios.get<number>('/api/evolve/rowsNumberOf', { params: { name: name } })
      this.pagination.rowsNumber = response.data
    },
    async fetchData(props): Promise<void> {
      const name: string | undefined = this.$route.params.name
      if (!name) {
        return
      }
      this.loading = true // TODO Vuex?
      const { page, rowsPerPage, sortBy, descending } = props.pagination: Pagination
      const filter: string = props.filter
      let response: AxiosResponse<Array<EvolveCharacterLine>>
      try {
        response = await this.$axios.get<Array<EvolveCharacterLine>>('/api/evolve/linesOf', { params: { name: name } })
      } catch (error) {
        this.loading = false
        this.$q.notify({
          timeout: 10000,
          multiLine: true,
          message: `An error occurred while trying to fetch data from the server!\n
               Very not nice of the server! ):<`,
          type: 'negative'
        })
        throw error
      }
      this.tableData = response.data // TODO Is it ok?
      this.loading = false
    }
  },
  computed: {
    isDataFetched: function(): boolean {
      return this.tableData.length > 0
    }
  },
  watch: {
    $route: 'fetchData'
  },
  created() {
    this.setRowsNumber()
    this.fetchData()
  },
  beforeRouteUpdate(to, from, next) {
    if (this.isDataFetched) {
      this.tableData = []
    }
    next()
  }
})
</script>

<style scoped>
  /*#slider {
    height: 100px;
    width: 100px;
  }*/
</style>
