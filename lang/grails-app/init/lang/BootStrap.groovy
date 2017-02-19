package lang

class BootStrap {

  def schedulerService

  def init = { servletContext ->
    schedulerService.start()
    registerStation('cmr','Celtic Music Radio','http://www.celticmusicradio.net/','http://www.celticmusicradio.net/cmr/celtic_live.m3u');
    registerProgramme('cmr','Test1',6,18,0,60);
  }

  def destroy = {
  }

  def registerStation(slug, name, stationUrl, streamUrl) {
    def stn = Station.findByName(name) ?: new Station(slug:slug, name:name,stationUrl:stationUrl,streamingUrl:streamUrl).save(flush:true, failOnError:true);
  }

  def registerProgramme(stnSlug, programmeName, day, hour, minute, duration) {
    def stn = Station.findBySlug(stnSlug);
    def se = ScheduleEntry.findByStnAndProgrammeSlug(stn,programmeSlug)
    if ( se == null ) {
    }
  }
}
