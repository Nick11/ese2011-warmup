package core;

import java.util.ArrayList;

public class Calendar {

	private String name;
	private User user;
	private ArrayList<Event> events;
	
	public Calendar(User user){
		this.name = user.getName()+"'s Calendar";
		this.user = user;
		this.events = new ArrayList<Event>();
		
	}
	
	public Event addEvent(Event event, User user){
		assert isOwner(user);
		events.add(event);
		return event;
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
	public ArrayList<Event> getEvents(User user){
		assert isOwner(user);
		return this.events;
	}
	
	public void setName(String name, User user){
		assert isOwner(user);
		this.name = name;
	}
	
	
}
