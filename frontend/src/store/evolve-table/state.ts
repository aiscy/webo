import {EvolveLinesModel, Pagination, TableColumns} from 'components/models'

export interface EvolveTableState {
  pagination: Pagination
  maxPagesAtTime: number
  tableData: Array<EvolveLinesModel>
  tableColumns: Array<TableColumns>
  characterName: string
  isFetchingData: boolean
}

const state: EvolveTableState = {
  pagination: {
    sortBy: 'name',
    descending: false,
    page: 1,
    rowsPerPage: 25,
    rowsNumber: 0
  },
  maxPagesAtTime: 10,
  tableData: [],
  tableColumns: [
    {
      name: 'name',
      required: true,
      label: 'lineName',
      align: 'left',
      field: (row: EvolveLinesModel) => row.name,
      sortable: false
    },
    {
      name: 'text',
      required: true,
      label: 'lineText',
      align: 'center',
      field: (row: EvolveLinesModel) => row.text,
      sortable: false
    },
    {
      name: 'audio',
      required: true,
      label: 'lineAudio',
      align: 'right',
      field: (row: EvolveLinesModel) => row.files,
      sortable: false
    }
  ],
  characterName: '',
  isFetchingData: false
}

export default state
