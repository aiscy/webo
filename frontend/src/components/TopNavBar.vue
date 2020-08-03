<template lang="pug">
  q-toolbar.row.no-wrap.bg-purple.text-white.shadow-1
    .col-2
      q-btn(v-if='showLeftDrawerButton', flat, round, dense, icon='menu', @click='toggleLeftDrawer')
    .col-10
      q-btn-group
        router-link(to='/', exact)
          template(v-slot='props')
            q-btn(label='homepage', v-bind='buttonProps(props)')
        router-link(to='/evolve')
          template(v-slot='props')
            q-btn(label='evolve', v-bind='buttonProps(props)')
        q-btn(label='vermintide2', disable)
</template>

<script lang="ts">
import Vue from 'vue'
import { createNamespacedHelpers } from 'vuex'

const { mapActions } = createNamespacedHelpers('evolveLayout')

export default Vue.extend({
  name: 'TopNavBar',
  props: {
    showLeftDrawerButton: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    ...mapActions([
      'toggleLeftDrawer'
    ]),
    buttonProps(x: { href: string; isActive: boolean }) {
      const props = {
        color: '',
        noCaps: false,
        outline: false,
        to: x.href
      }

      if (x.isActive) {
        props.color = 'deep-purple'
      }
      return props
    }
  }
})
</script>
