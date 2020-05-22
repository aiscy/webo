<template lang="pug">
    .container
        b-field(label='Character')
            b-select(@input="loadData", placeholder='Select a character')
                optgroup(label='Assault')
                    option(value='markov') Aleksey Markov
                    option(value='hyde') Hyde
                    option(value='lennox') Ida Lennox
                    option(value='parnell') James Parnell
                    option(value='torvald') Torvald Stavig
                optgroup(label='Medic')
                    option(value='slim') Alex "Slim"
                    option(value='caira') Caira Diaz
                    option(value='lazarus') Ðorde “Lazarus” Živkovic
                    option(value='emet') E.M.E.T
                    option(value='val') Valerie "Val" Wolski
                optgroup(label='Trapper')
                    option(value='abe') Abraham "Abe" Presley
                    option(value='jack') Jack Arthur Lennox
                    option(value='griffin') Griffin Hallsey
                    option(value='crow') Khovalyg "Crow"
                    option(value='maggie') Margaret "Maggie" Lumumba
                optgroup(label='Support')
                    option(value='bucket') Bucket
                    option(value='hank') Henry “Hank” Allen
                    option(value='kala') Kala Kapur
                    option(value='sunny') Sunny Yú
                    option(value='cabot') William Cabot
        b-table(:data='tableData', :loading='loading', :paginated='isDataFetched', pagination-position='bottom', :per-page='20')
            b-input(v-if='!props.column.numeric', slot='searchable', slot-scope='props', v-model='props.filters[props.column.field]', placeholder='Search...', size='is-small')
            template(v-slot:default='props')
                b-table-column(field='id', label='ID', width='40', numeric, sortable)
                    | {{ props.row.id }}
                b-table-column(field='lineName', label='Name', sortable, searchable=true)
                    | {{ props.row.lineName }}
                b-table-column(field='lineText', label='Text', sortable, searchable=true)
                    | {{ props.row.lineText }}
                b-table-column(field='filePath', label='Audio', width='300', sortable)
                    ul
                        li(v-for='item in props.row.filePath' :key='item')
                            audio(:src='item', preload='none', controls, controlslist='nodownload')
</template>

<script lang="ts">
    import Vue from 'vue'
    import {AxiosError, AxiosResponse} from 'axios'
    import {EvolveCharacterLine} from '@/EvolveCharacterLines'

    export default Vue.extend({
    name: 'evolve',
    data: function() {
        const tableData: Array<EvolveCharacterLine> = []
        let loading: boolean = false
        return {
            tableData, loading
        }
    },
    computed: {
        isDataFetched: function(): boolean {
            return this.tableData.length > 0
        },
    },
    methods: {
        loadData(characterName: string): void {
            if (this.tableData.length > 0) {
                this.tableData = []
            }
            this.loading = true
            this.axios.get<Array<EvolveCharacterLine>>(
                `/api/evolve/getLinesOf`,
                {
                    params: {
                        name: characterName
                    }
            })
                .then((response: AxiosResponse<Array<EvolveCharacterLine>>) => {
                    this.loading = false
                    this.tableData = response.data
                })
                .catch((error: AxiosError) => {
                    this.loading = false
                    this.$buefy.toast.open({
                        duration: 5000,
                        message: 'An error occurred while trying to fetch data from the server!',
                        position: 'is-bottom',
                        type: 'is-danger'
                    })
                    throw error
                })
        },
        showMessage(): void {
            this.$buefy.dialog.alert(
                `These audio files presented here for fan use ONLY!
                Intellectual rights belong to 2K Games.`
            )
        }
    },
    mounted(): void {
        this.showMessage()
    }
})
</script>

<style scoped>

</style>
