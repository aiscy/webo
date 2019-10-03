<template lang="pug">
    section
        b-button(@click='login') Auth
        b-button(@click='info') Info
</template>

<script lang="ts">
    import Vue from 'vue'

    export default Vue.component('playground', {
        name: "Playground",
        methods: {
            login: function() {
                localStorage.setItem('redirectTo', window.location.pathname)
                window.location.pathname = '/user/login'
            },
            info: function() {
                this.$http
                    .get('/user/info')
                    .then(response => {
                        this.$toast.open(response.data)
                    })
            }
        }
    })
</script>

<style scoped>

</style>
