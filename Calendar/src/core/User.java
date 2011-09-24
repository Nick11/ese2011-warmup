package core;

import java.util.ArrayList;

public class User {

	private String name;
	private ArrayList<Calendar> calendars;
	
	public User(){
		name = "";
		calendars = new ArrayList<Calendar>();
	}
	
	public Calendar createCalendar(){
		Calendar calendar = new Calendar(this);
		calendars.add(calendar);
		return calendar;
	}
	
	
	//getters and setters
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	public ArrayList<Calendar> getCalendars(){
		return this.calendars;
	}
	
}
