<template lang="pug">
    nav.navbar.is-dark(role='navigation', aria-label='main navigation')
        .navbar-brand
            router-link.navbar-item(to='/')
                b-icon.logo(pack='fas', icon='mug-hot', size='is-medium', custom-size='fa-2x')
            a.navbar-burger.burger(
                role='button',
                aria-label='menu',
                aria-expanded='false',
                @click='isBurgerMenuVisible =! isBurgerMenuVisible',
                :class="{ 'is-active': isBurgerMenuVisible }"
            )
                span(aria-hidden='true')
                span(aria-hidden='true')
                span(aria-hidden='true')
        .navbar-menu(:class="{ 'is-active': isBurgerMenuVisible }")
            .navbar-start
                router-link.navbar-item(to='/pastekode')
                    b-icon(pack='fas', icon='code', size='is-medium', custom-size='fa-lg')
                    span PasteKode
                router-link.navbar-item(to='/dumbkord')
                    b-icon(pack='fas', icon='comments', size='is-medium', custom-size='fa-lg')
                    span Dumbkord
            .navbar-end
                .navbar-item
                    .buttons
                        navbar-lang-menu
                        navbar-user-menu
</template>

<script lang="ts">
    import Vue from 'vue'
    import BIcon from 'buefy/src/components/icon/Icon.vue'
    import BButton from 'buefy/src/components/button/Button.vue'
    import BDropDown from 'buefy/src/components/dropdown/Dropdown.vue'
    import BDropdownItem from 'buefy/src/components/dropdown/DropdownItem.vue'
    import Auth from '@/components/Auth.vue'
    import NavbarLang from '@/components/NavbarLangMenu.vue'
    import NavbarUserMenu from '@/components/NavbarUserMenu.vue'

    export default Vue.component('navbar', {
        data() {
            return {
                isBurgerMenuVisible: false,
                isComponentModalActive: false,
            }
        },
        components: { BButton, BIcon, BDropDown, BDropdownItem, Auth, NavbarLang, NavbarUserMenu },
    })
</script>

<style scoped>
    .logo {
        padding-left: 35px;
        padding-right: 35px;
    }
    /*.no-top-padding {*/
    /*    padding-top: 0px;*/
    /*}*/
</style>
