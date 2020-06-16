<template lang="pug">
  q-layout(view='hHh Lpr fFf')
    q-header(elevated)
      TopNavBar
    q-drawer(v-model='leftDrawer', show-if-above, :width='250', :breakpoint='700', elevated)
      q-list
        q-item-label(header) Assaults
        EvolveMenuItem(character-name='Aleksey Markov', image-src='statics/icons/evolve/markov.png', to='/evolve/markov')
        EvolveMenuItem(character-name='Ida Lennox', image-src='statics/icons/evolve/lennox.png', to='/evolve/lennox')
        EvolveMenuItem(character-name='Hyde', image-src='statics/icons/evolve/hyde.png', to='/evolve/hyde')
        EvolveMenuItem(character-name='James Parnell', image-src='statics/icons/evolve/parnell.png', to='/evolve/parnell')
        EvolveMenuItem(character-name='Torvald Stavig', image-src='statics/icons/evolve/torvald.png', to='/evolve/torvald')
        q-item-label(header) Medics
        EvolveMenuItem(character-name='Alex "Slim"', image-src='statics/icons/evolve/slim.png', to='/evolve/slim')
        EvolveMenuItem(character-name='Caira Diaz', image-src='statics/icons/evolve/caira.png', to='/evolve/caira')
        EvolveMenuItem(character-name='Ðorde “Lazarus” Živkovic', image-src='statics/icons/evolve/lazarus.png', to='/evolve/lazarus')
        EvolveMenuItem(character-name='E.M.E.T.', image-src='statics/icons/evolve/emet.png', :to='{ path: "emet", query: {"page": 1} }')
        EvolveMenuItem(character-name='Valerie "Val" Wolski', image-src='statics/icons/evolve/val.png', to='/evolve/val')
        <!--EvolveMenuItem(v-for='chr in medics', :character-name='chr.name', :image-src='chr.imageSrc', :to='chr.to')-->
        q-item-label(header) Trappers
        EvolveMenuItem(character-name='Abraham "Abe" Presley', image-src='statics/icons/evolve/abe.png', to='/evolve/abe')
        EvolveMenuItem(character-name='Jack Arthur Lennox', image-src='statics/icons/evolve/scrap.png', to='/evolve/scrap')
        EvolveMenuItem(character-name='Griffin Hallsey', image-src='statics/icons/evolve/griffin.png', to='/evolve/griffin')
        EvolveMenuItem(character-name='Khovalyg "Crow"', image-src='statics/icons/evolve/crow.png', to='/evolve/crow')
        EvolveMenuItem(character-name='Margaret "Maggie" Lumumba', image-src='statics/icons/evolve/maggie.png', to='/evolve/maggie')
        q-item-label(header) Supports
        EvolveMenuItem(character-name='Bucket', image-src='statics/icons/evolve/bucket.png', to='/evolve/bucket')
        EvolveMenuItem(character-name='Henry “Hank” Allen', image-src='statics/icons/evolve/hank.png', to='/evolve/hank')
        EvolveMenuItem(character-name='Kala Kapur', image-src='statics/icons/evolve/tech.png', to='/evolve/tech')
        EvolveMenuItem(character-name='Sunny Yú', image-src='statics/icons/evolve/sunny.png', to='/evolve/sunny')
        EvolveMenuItem(character-name='William Cabot', image-src='statics/icons/evolve/cabot.png', to='/evolve/cabot')
    q-page-container
      router-view
</template>

<script lang="ts">
  import Vue from 'vue'
  import TopNavBar from 'components/TopNavBar.vue'
  import EvolveMenuItem from 'components/EvolveMenuItem.vue'

  export default Vue.extend({
  name: 'EvolveLayout',
  components: {
    TopNavBar,
    EvolveMenuItem
  },
  data: function() {
    const leftDrawer = true
    /* const medics: Array<CharacterItemModel> = [
      {
        name: 'E.M.E.T',
        imageSrc: 'statics/icons/evolve/emet.png',
        to: '/evolve/emet'
      }
    ] */
    return { leftDrawer }
  }
})
</script>
