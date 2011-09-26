package core;

import java.util.GregorianCalendar;

public class Event {

	private String name;
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private boolean isPrivate;
	/**
	 * 
	 * @param name
	 * @param startDate
	 * @param endDate
	 * @param isPrivate
	 */
	public Event(String name, GregorianCalendar startDate, GregorianCalendar endDate, boolean isPrivate){
		assert(!startDate.after(endDate)); //startDate <= endDate should be possible.
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPrivate = isPrivate;
	}
	public boolean isPrivate(){
		return this.isPrivate;
	}
	
	//getters, setters
	public GregorianCalendar getStartDate(){
		return this.startDate;
	}
	public GregorianCalendar getEndDate(){
		return this.endDate;
	}
}
