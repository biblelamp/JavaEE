package Timer;

import java.util.Date;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Timer;
import javax.ejb.Schedule;

@Singleton
@LocalBean
public class ScheduleTimer {

	@Schedule(second="15,35", minute="*", hour="*")
	public void execute(Timer timer) {
		System.out.println("Executing ...");
		System.out.println("Execution Time : " + new Date());
		System.out.println("____________________________________________");
	}
}