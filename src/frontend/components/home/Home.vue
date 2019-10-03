<template lang="pug">
    section.hero.is-link.is-fullheight-with-navbar
        .hero-body
            .container.has-text-centered
                h1.title.unselectable
                    | {{ $t('text') }} {{ randomAsciiSmile }}
</template>

<script lang="ts">
    import Vue from 'vue'

    const asciiSmiles: Array<String> = [
        '¯\\_(ツ)_/¯',
        '(╯°□°）╯︵ ┻━┻',
        '┬─┬ ノ( ゜-゜ノ)',
        '( ͡° ͜ʖ ͡°)',
        'ಠ_ಠ',
        '(╯°□°)╯︵ ʞooqǝɔɐℲ',
        '( ˘ ³˘)♥'
    ]

    export default Vue.component('home', {
        computed: {
            randomAsciiSmile: function() {
                return asciiSmiles[Math.floor(Math.random() * asciiSmiles.length)]
            }
        }
    })
</script>

<style scoped>
    .unselectable {
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }
</style>

<i18n>
    {
        "en": {
            "text": "Nothing here atm "
    },
        "ru": {
            "text": "Здесь пока пустовато "
        }
    }
</i18n>
