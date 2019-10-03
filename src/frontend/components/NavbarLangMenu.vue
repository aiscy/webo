<template lang="pug">
    b-dropdown(v-model='currentLanguage', aria-role='list')
        b-button.button(type='button', slot='trigger', icon-right='angle-down')
            span
                | {{ $i18n.locale.toUpperCase() }}
        b-dropdown-item(v-for='lang in $i18n.availableLocales', :key='lang', :value='lang', aria-role='listitem')
            | {{ lang.toUpperCase() }}
</template>

<script lang="ts">
    import Vue from 'vue'
    import BDropdown from 'buefy/src/components/dropdown/Dropdown.vue'
    import BDropdownItem from 'buefy/src/components/dropdown/DropdownItem.vue'

    export default Vue.component('navbar-lang-menu', {
        components: { BDropdown, BDropdownItem },
        data() {
            return {
                currentLanguage: this.$i18n.locale
            }
        },
        watch: {
            currentLanguage: function(val) {
                this.$i18n.locale = val
                localStorage.setItem('lang', val)
            },
        }
    })
</script>

<style scoped>
    a.dropdown-item.is-active {
        background-color: rgba(121, 87, 213, 0.8);
    }
</style>
