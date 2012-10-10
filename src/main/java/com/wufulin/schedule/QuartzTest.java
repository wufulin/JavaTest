package com.wufulin.schedule;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// Grab the Scheduler instance from the Factory
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(HelloJob.class).withIdentity("myJob",
					"group1").build();

			// Trigger the job to run now, and then every 40 seconds
			Trigger trigger = newTrigger()
					.withIdentity("myTrigger", "group1")
					.startNow()
					.withSchedule(simpleSchedule()
							.withIntervalInSeconds(40)
							.repeatForever())
					.build();

			scheduler.scheduleJob(job, trigger);

			// Start if off
			scheduler.start();

			try {
	            // wait 65 seconds to show job
	            Thread.sleep(65L * 1000L); 
	            // executing...
	        } catch (Exception e) {
	        }
			
			// shutdown
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
