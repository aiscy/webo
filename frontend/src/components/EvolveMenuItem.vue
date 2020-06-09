<template lang="pug">
  q-item(clickable, v-ripple, :to='to', :inset-level='insetLevel')
    q-item-section(avatar)
      q-avatar(rounded, size='70px')
        img(:src='imageSrc')
    q-item-section
      q-item-label
        | {{ characterName }}
</template>

<script lang="ts">
  import Vue from 'vue'

  export default Vue.extend({
  name: 'EvolveMenuItem',
  props: {
    imageSrc: {
      type: String,
      required: true
    },
    to: {
      type: String,
      required: true
    },
    characterName: {
      type: String,
      required: true
    },
    insetLevel: {
      type: Number,
      default: 0.2
    }
  }
})
</script>

<style scoped>

</style>
