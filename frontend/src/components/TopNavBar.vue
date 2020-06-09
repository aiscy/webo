<template lang="pug">
  q-toolbar.bg-purple.text-white.shadow-2
    q-btn-group
      router-link(to='/', exact)
        template(v-slot='props')
          q-btn(label='homepage', v-bind='buttonProps(props)')
      router-link(to='/evolve')
        template(v-slot='props')
          q-btn(label='evolve', v-bind='buttonProps(props)')
    q-space
</template>

<script lang="ts">
  import Vue from 'vue'

  export default Vue.extend({
  name: 'TopNavBar',
  methods: {
    buttonProps ({ href, route, isActive, isExactActive }) { // TODO
      const props = {
        color: '',
        noCaps: false,
        outline: false,
        to: href
      }

      if (isActive === true) {
        props.color = 'deep-purple'
      }
      return props
    }
  }
})
</script>
