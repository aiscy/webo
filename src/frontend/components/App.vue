<template lang="pug">
    div(id='app')
        navbar
        router-view
</template>

<script lang="ts">
    import Vue from 'vue'
    import Navbar from '@/components/Navbar.vue'

    export default Vue.component('app', {
        data() {
            return {
                isBurgerMenuVisible: false,
                isComponentModalActive: false,
            }
        },
        components: { Navbar },
        /*created(): void {
            const preferredLang = localStorage.getItem('lang')
            if (preferredLang) {
                this.$i18n.locale = preferredLang
            }
        }*/
    })
</script>
<style>
    html {
        overflow-y: auto
    }
</style>
