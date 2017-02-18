package lang

class BootStrap {

  def schedulerService

  def init = { servletContext ->
    schedulerService.start()
  }

  def destroy = {
  }
}
