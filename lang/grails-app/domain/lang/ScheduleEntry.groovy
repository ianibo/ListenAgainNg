package lang

class ScheduleEntry {

  Station stn
  String programmeSlug
  String programmeName
  Long start_day
  Long start_hour
  Long start_minute
  Long duration

  static constraints = {
              stn blank: false, nullable: false // , unique: true
    programmeSlug blank: false, nullable: true // , unique: true
    programmeName blank: false, nullable: false // , unique: true
        start_day blank: false, nullable: false // , unique: true
       start_hour blank: false, nullable: false // , unique: true
     start_minute blank: false, nullable: false // , unique: true
         duration blank: false, nullable: false // , unique: true
  }

  static mapping = {
    table 'schedule_entry'
              stn column: 'se_stn'
    programmeSlug column: 'se_prog_slug'
    programmeName column: 'se_prog_name'
        start_day column: 'se_start_day'
       start_hour column: 'se_start_hour'
     start_minute column: 'se_start_minute'
         duration column: 'se_duration'
  }

}
