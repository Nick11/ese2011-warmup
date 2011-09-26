package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import core.*;

public class CalendarTest {
	private Calendar calendar;
	private User user;
	private User user1; 
	private GregorianCalendar date0 = new GregorianCalendar(2001,GregorianCalendar.JANUARY,1,10,30);
	private GregorianCalendar date1 = new GregorianCalendar(2001,GregorianCalendar.JANUARY,2,9,00);
	
	@Before
	public void before(){
		user = new User();
		user1 = new User();
		calendar= user.createCalendar();
		
	}
	
	@Test
	public void shouldHaveOwner(){
		assert(calendar.getUser() != null);
	}
	
	@Test
	public void shouldHaveName() {
		assertEquals(calendar.getName(user), "'s Calendar");
	}
	
	@Test
	public void shouldBeOwnedByOwner(){
		assert( calendar.getUser().getCalendars().contains(calendar));
	}
	
	@Test
	public void sholdHaveEvents(){
		calendar.addEvent(new Event("event0", date0, date1, true),user);
		assertEquals(calendar.getAllEvents(user).size(), 1);
	}
	
	@Test
	public void shouldOnlyBeAccessableByOwner(){
		try{
			calendar.getAllEvents(user);
		}
		catch(AssertionError e){
			assert false;
		}
		boolean assertionErrorHappend = false;
		try{
			calendar.getAllEvents(user1);
		}
		catch(AssertionError e){
			assertionErrorHappend = true;
		}
		assert(assertionErrorHappend);
	}
	
	@Test
	public void allEventsShouldContainPublicEvents(){
		calendar.addEvent(new Event("event0", date0, date1, true), user);
		calendar.addEvent(new Event("event0", date0, date1, false), user);
		assert calendar.getAllEvents(user).containsAll(calendar.getPublicEvents());
		assertFalse( calendar.getPublicEvents().containsAll(calendar.getAllEvents(user)) );
	}
	
	
	@Test
	public void sholdFail(){
		assert false;
	}

}
