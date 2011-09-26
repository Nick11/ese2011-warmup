package core;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Calendar {

	private String name;
	private User user;
	private ArrayList<Event> events;
	
	public Calendar(User user){
		this.name = user.getName()+"'s Calendar";
		this.user = user;
		this.events = new ArrayList<Event>();
		
	}
	/**
	 * 
	 * @param event
	 * @param user
	 * @return new Event
	 */
	public Event addEvent(Event event, User user){
		assert isOwner(user);
		events.add(event);
		return event;
	}
	//TODO: implementation
	public ArrayList<Event> listOneDaysEvents(GregorianCalendar date, User user){
		return null;
	}
		
	//TODO: implementation
	public ArrayList<Event> iterateFromDate(User user){
		return null;
	}
	
	//is'es
	/**
	 * should be checked before every public method. Except getUser()
	 * @param user
	 * @return
	 */
	private boolean isOwner(User user){
		return this.user.equals(user);
	}
	
	//getters and setters
	public String getName(User user){
		assert isOwner(user);
		return this.name;
	}
	public User getUser(){
		return this.user;
	}
	/**
	 * 
	 * @param user For Identification
	 * @return ArrayList of all Events. public and private. 
	 */
	public ArrayList<Event> getAllEvents(User user){
		assert isOwner(user);
		return this.events;
	}
	/**
	 * Public events are visible for everybody
	 * @return Public Events
	 */
	public ArrayList<Event> getPublicEvents(){
		ArrayList<Event> publicEvents = new ArrayList<Event>();
		for(Event event: events){
			if(!event.isPrivate())
				publicEvents.add(event);
		}
		return publicEvents;
	}
	/**
	 * Changes calendar's name.
	 * @param name calendar's new name
	 * @param user For identification
	 */
	public void setName(String name, User user){
		assert isOwner(user);
		this.name = name;
	}
	
	
}
