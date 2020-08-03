<template lang="pug">
  q-page(padding)
    q-table(:pagination.sync='pagination', :data='tableData', :columns='tableColumns', row-key='name',
      :loading='isFetchingData', binary-state-sort, :rows-per-page-options='[25]', dense, flat
    )
      template(v-slot:body-cell-audio='props')
        q-td(:props='props', auto-width=true)
          .column
            .col(v-for='item in props.row.files' :key='item')
              q-media-player(dense, type='audio', :volume='volume', :source='item', preload='none'
                hide-volume-slider, cross-origin='anonymous', background-color='indigo-3'
              )
                template(v-slot:volume)
                  div
      template(v-slot:loading)
        q-inner-loading(showing, color='primary')
      template(v-if='hasData', v-slot:top-right='props')
        q-pagination(:value='pagination.page', color='purple', :max='props.pagesNumber',
          :max-pages='maxPagesAtTime', @input='onRequest', :to-fn='createPaginationLink'
        )
      template(v-if='hasData', v-slot:pagination='props')
        q-pagination(:value='pagination.page', color='purple', :max='props.pagesNumber',
          :max-pages='maxPagesAtTime', @input='onRequest', :to-fn='createPaginationLink'
        )
    q-page-scroller(position='bottom', :scroll-offset='250', :offset='[0, 5]')
      q-btn(fab, icon='keyboard_arrow_up', color='accent')
    q-page-sticky(position='bottom-left', :offset='[10, 10]')
      q-btn(round, color='primary', :icon='selectVolumeButtonIcon')
        q-popup-edit(v-model='volume', auto-save, :cover='false', :offset='[0, 5]')
          q-field(borderless, label='')
            template(v-slot:control)
              q-slider(v-model='volume', :min='0', :max='100', label, label-always, style={'width': '200px'})
</template>

<script lang="ts">
import Vue from 'vue'
import { createNamespacedHelpers } from 'vuex'

const { mapState, mapActions, mapGetters } = createNamespacedHelpers('evolveTable')

export default Vue.extend({
  name: 'EvolveData',
  data() {
    return {
      volume: 80
    }
  },
  methods: {
    ...mapActions([
      'terminate',
      'initialize',
      'fetchData'
    ]),
    onRequest(page: number): void {
      this.fetchData({ page })
    },
    init(): void {
      const page = this.$route.query.page
      if (page) {
        this.initialize({ name: this.$route.params.name, page: +page })
      } else {
        this.initialize({ name: this.$route.params.name, page: 1 })
      }
    }
  },
  computed: {
    ...mapState([
      'tableData',
      'tableColumns',
      'pagination',
      'isFetchingData',
      'maxPagesAtTime'
    ]),
    ...mapGetters([
      'hasData'
    ]),
    createPaginationLink(): Function {
      return (page: number) => ({ query: { page } })
    },
    selectVolumeButtonIcon(): string {
      const { volume } = this
      if (volume === 0) {
        return 'volume_off'
      } else if (volume >= 1 && volume <= 29) {
        return 'volume_mute'
      } else if (volume >= 30 && volume <= 59) {
        return 'volume_down'
      } else {
        return 'volume_up'
      }
    }
  },
  watch: {
    $route: function() {
      this.init()
    }
  },
  mounted() {
    this.init()
  },
  beforeRouteUpdate(to, from, next) {
    if (to.path !== from.path) {
      this.terminate()
    }
    next()
  },
  beforeRouteLeave(to, from, next) {
    this.terminate()
    next()
  }
})
</script>
