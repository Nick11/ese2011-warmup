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
	
	@Before
	public void before(){
		user = new User();
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
		calendar.addEvent(new Event(),user);
		assertEquals(calendar.getEvents(user).size(), 1);
	}
	
	@Test
	public void shouldOnlyBeAccessableByOwner(){
		try{
			calendar.getEvents(user);
		}
		catch(AssertionError e){
			assert false;
		}
		User user1 = new User();
		boolean assertionErrorHappend = false;
		try{
			calendar.getEvents(user1);
		}
		catch(AssertionError e){
			assertionErrorHappend = true;
		}
		assert(assertionErrorHappend);
	}
	
	
	
	@Test
	public void sholdFail(){
		assert false;
	}

}
