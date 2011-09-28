package core;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

public class Calendar {

	private String name;
	private User user;
	private HashMap<GregorianCalendar, ArrayList<Event>> events;
	
	public Calendar(User user){
		this.name = user.getName()+"'s Calendar";
		this.user = user;
		this.events = new HashMap<GregorianCalendar, ArrayList<Event>>();
		
	}
	/**
	 * Adds Event to events
	 * @param event
	 * @param user
	 * @return new Event
	 */
	public Event addEvent(Event event, User user){
		assert isOwner(user);
		GregorianCalendar beginningOfDay = getBeginningOfDay(event.getStartDate());
		if(!events.containsKey(beginningOfDay) ){ //is there already another event with the same startDate?
			ArrayList<Event> dateList = new ArrayList<Event>();
			dateList.add(event);
			events.put(beginningOfDay, dateList);
		}
		else{
			ArrayList<Event> list = events.get(beginningOfDay);
			for(Event eventInList: list){
				if(!event.getStartDate().after(eventInList.getStartDate())){ //event.getStartDate() <= eventInList.getStartDate()
					list.add(list.indexOf(eventInList), event);
					break;
				}
					
			}
			if (!list.contains(event))
				list.add(event);
		}
		return event;
	}
	public ArrayList<Event> listOneDaysEvents(GregorianCalendar date, User user){
		HashMap<GregorianCalendar, ArrayList<Event>> allEvents;
		ArrayList<Event> daysEvents = new ArrayList<Event>();
		date = getBeginningOfDay(date);
		if(isOwner(user))
			allEvents = getAllEvents(user);
		else
			allEvents = getPublicEvents();
		if(allEvents.containsKey(date))
			return allEvents.get(date);
		else
			return new ArrayList<Event>();
	}
	
	public Iterator<Event> iterateFromDate(GregorianCalendar date, User user){
		date = getBeginningOfDay(date);
		ArrayList<Event> eventsFromDate = new ArrayList<Event>();
		GregorianCalendar[] dates = events.keySet().toArray(new GregorianCalendar[events.size()]); //unsure about this
		for(int i=0; i<dates.length; i++){
			if(!dates[i].before(date)){
				eventsFromDate.addAll(listOneDaysEvents(dates[i],user));
			}
		}
		return eventsFromDate.iterator();
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
	public HashMap<GregorianCalendar, ArrayList<Event>> getAllEvents(User user){
		assert isOwner(user);
		return this.events;
	}
	/**
	 * Public events are visible for everybody
	 * @return Public Events
	 */
	public HashMap<GregorianCalendar, ArrayList<Event>> getPublicEvents(){
		ArrayList<Event> dateList;
		ArrayList<Event> publicList = new ArrayList<Event>();
		GregorianCalendar currentDate;
		
		HashMap<GregorianCalendar, ArrayList<Event>> publicEvents = new HashMap<GregorianCalendar, ArrayList<Event>>();
		Iterator<ArrayList<Event>> iterator = events.values().iterator();
		
		while(iterator.hasNext()){
			dateList = deepCloneArrayList(iterator.next());
			assert dateList.size()>0; //this is a bit unsave, if the remove method is not implementet carfully. there could be an empty ArrayList
			currentDate = getBeginningOfDay(dateList.get(0).getStartDate()); 
			
			for( Event event: dateList){
				if(!event.isPrivate())
					publicList.add(event);
			}
			if(publicList.size() > 0)
				publicEvents.put(currentDate, publicList);
			publicList = new ArrayList<Event>();
		}
		return publicEvents;
	}
	
	//public for testing! is there a better way?
	public ArrayList deepCloneArrayList(ArrayList originalList){
		ArrayList newList = new ArrayList();
		for(Object object: originalList){
			newList.add(object);
		}
		return newList;
	}
	//public for testing! is there a better way?
	public GregorianCalendar getBeginningOfDay(GregorianCalendar date){
		GregorianCalendar beginningOfDay = (GregorianCalendar) date.clone();
		beginningOfDay.set(GregorianCalendar.HOUR_OF_DAY, 0);
		beginningOfDay.set(GregorianCalendar.MINUTE, 0);
		return beginningOfDay;
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
