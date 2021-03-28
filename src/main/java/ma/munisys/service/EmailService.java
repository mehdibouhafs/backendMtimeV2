package ma.munisys.service;

import java.util.Date;

import ma.munisys.entities.Activity;

public interface EmailService {
	
	public void notifyByActivity(Activity activity,String username);
	
	public void sendEtatWeekly(Date startDate,Date endDate);
	
	public void sendEtatWeeklyTest(Date startDate,Date endDate);
	
	public void notifyHotlineByActivity(Activity activity, String mailHotline,Boolean newActivity);
}
