<template lang="pug">
    b-dropdown(position='is-bottom-left', aria-role='menu')
        b-button.is-primary(type='button', slot='trigger')
            span {{ $t('loginAction') }}
        b-dropdown-item(aria-role='menu-item', custom='', v-bind:paddingless='true')
            form(action='')
                .modal-card(style='width:300px;')
                    section.modal-card-body
                        b-field(label='Email')
                            b-input(type='email', placeholder='Your email', required='')
                        b-field(label='Password')
                            b-input(type='password', password-reveal='', placeholder='Your password', required='')
                        b-checkbox Remember me
                    footer.modal-card-foot
                        button.button.is-primary Login
</template>

<script lang="ts">
    import Vue from 'vue'

    export default Vue.component('navbar-user-menu', {

    })
</script>

<style scoped>
</style>

<i18n>
    {
        "en": {
            "loginAction": "Login with OAuth"
        },
        "ru": {
            "loginAction": "Авторизация через OAuth"
        }
    }
</i18n>
