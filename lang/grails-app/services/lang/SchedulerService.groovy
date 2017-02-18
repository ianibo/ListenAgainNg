package lang

import grails.transaction.Transactional
import org.joda.time.Duration
import org.joda.time.DateTime


@Transactional
class SchedulerService {

  def executorService

  long last_check = 0;
  boolean running = true;

  @javax.annotation.PostConstruct
  def init() {
    log.debug("init Scheduler service");

    // Kick off the scheduler thread
    // Work out how long we have to go to the next event, and sleep for 5 mins or that time, whichever is shorter
    runAsync {
      schedulingLoop()
    }
  }

  def start() {
    log.debug("Start");
  }


  private void schedulingLoop() {
    while ( running ) {

      long time_of_this_check = System.currentTimeMillis();

      log.debug("In scheduling loop");

      // Either this is the first time through the loop, or we've woken from a wait 
      // because a job is due to start, we've reached the max wait time, OR someone
      // changed the schedule and called notify on the service to ask us to re-caclulate
      // when the next job is. Either way -- look to see if we should have kicked off any
      // tasks
      startJobsStartBetween(last_check,time_of_this_check)

      // Done all our work, figure out how long we can snooze for before the next job
      long time_to_sleep = 10000 // 60*5*1000 // 5 mins
      long time_in_week = getTimeInWeek()
    
      def time_of_next_scheduled_event = findTimeOfNextEventAfter(last_check)

      if ( time_of_next_scheduled_event == null ) {
        // there are no scheduled events, sleep for 5 mins unless we are woken by a change
      }
      else {
        time_to_sleep = ( time_of_next_scheduled_event - time_in_week ) * 1000
      }

      // Record the time of our last check, then go to sleep until we need to do something.
      last_check = time_of_this_check

      log.debug("Sleeping ${time_to_sleep}");
      synchronized(this) {
        this.wait(time_to_sleep)
      }
      log.debug("scheduling thread woken up...");
    }
  }

  private void startJobsStartBetween(start,end) {
    log.debug("startJobsStartBetween(${start},${end})");
  }

  private Long findTimeOfNextEventAfter(long time) {
    Long result = null;
    result
  }

  /**
   *  Work out how many seconds into the week we are - scheduled events are a # of seconds past 00:00 monday morning.
   */
  private long getTimeInWeek() {
    DateTime now = new DateTime();
    DateTime start_of_week = now.withDayOfWeek(1).withTimeAtStartOfDay()
    Duration duration = new Duration(start_of_week, now);
    long seconds = duration.toStandardSeconds().getSeconds();
    log.debug("getTimeInWeek() returns ${seconds}");
    seconds
  }

}
