export interface Todo {
  id: number
  content: string
}

export interface Meta {
  totalCount: number
}

export interface EvolveCharacterLine {
  id: number
  lineName: string
  lineText: string
  filePath: string[]
}

export interface TableColumns { // TODO
  /**
   * Unique id, identifies column, (used by pagination.sortBy, 'body-cell-[name]' slot, ...)
   */
  name: string
  /**
   * Label for header
   */
  label: string
  /**
   * Row Object property to determine value for this column or function which maps to the required property
   */
  field: string | Function
  /**
   * If we use visible-columns, this col will always be visible
   */
  required? : boolean
  /**
   * Horizontal alignment of cells in this column
   */
  align? : string
  /**
   * Tell QTable you want this column sortable
   */
  sortable? : boolean
  /**
   * Compare function if you have some custom data or want a specific way to compare two rows
   */
  sort? : Function
  /**
   * Function you can apply to format your data
   */
  format? : Function
  /**
   * Style to apply on normal cells of the column
   */
  style? : string
  /**
   * Classes to add on normal cells of the column
   */
  classes? : string
  /**
   * Style to apply on header cells of the column
   */
  headerStyle? : string
  /**
   * Classes to add on header cells of the column
   */
  headerClasses? : string
}

export interface CharacterItemModel {
  imageSrc: string
  to: string
  name: string
}

export interface Pagination {
  /**
   * Column name (from column definition)
   */
  sortBy: string
  /**
   * Is sorting in descending order?
   */
  descending: boolean
  /**
   * Page number (1-based)
   */
  page: number
  /**
   * How many rows per page? 0 means Infinite
   */
  rowsPerPage: number
  /**
   * For server-side fetching only. How many total database rows are there to be added to the table. If set, causes the QTable to emit @request when data is required.
   */
  rowsNumber: number
}
