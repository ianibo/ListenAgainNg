package lang

class Station {

  String name
  String baseUrl

  static constraints = {
    baseUrl blank: false, nullable: false // , unique: true
       name blank: false, nullable: false // , unique: true
  }

  static mapping = {
    table 'stn'
       name column: 'stn_name'
    baseUrl column: 'stn_base_url'
  }
}
