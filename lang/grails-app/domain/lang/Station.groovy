package lang

class Station {

  String slug
  String name
  String stationUrl
  String streamingUrl

  static constraints = {
            slug blank: false, nullable: false // , unique: true
      stationUrl blank: false, nullable: false // , unique: true
    streamingUrl blank: false, nullable: false // , unique: true
            name blank: false, nullable: false // , unique: true
  }

  static mapping = {
    table 'stn'
            slug column: 'stn_slug'
            name column: 'stn_name'
      stationUrl column: 'stn_station_url'
    streamingUrl column: 'stn_streaming_url'
  }
}
