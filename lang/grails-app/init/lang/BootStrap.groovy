package lang

class BootStrap {

  def schedulerService

  def init = { servletContext ->
    schedulerService.start()
    registerStation('cmr','Celtic Music Radio','http://www.celticmusicradio.net/','http://www.celticmusicradio.net/cmr/celtic_live.m3u');
  }

  def destroy = {
  }

  def registerStation(slug, name, stationUrl, streamUrl) {
    def stn = Station.findByName(name) ?: new Station(slug:slug, name:name,stationUrl:stationUrl,streamingUrl:streamUrl).save(flush:true, failOnError:true);
  }
}
