package ca.mcgill.ecse321.eventregistration.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;


import java.util.ArrayList;
import java.util.List;


@Service
public class EventRegistrationService {
private RegistrationManager rm;
public EventRegistrationService(RegistrationManager rm)
{
  
  this.rm = rm;
}

public Participant createParticipant(String name) throws InvalidInputException
{
	if (name == null || name.trim().length() == 0) {
	    throw new InvalidInputException("Participant name cannot be empty!");
	  }

  Participant p = new Participant(name);
  rm.addParticipant(p);
  PersistenceXStream.saveToXMLwithXStream(rm);
  return p;
}

public List<Participant> findAllParticipants() {
	  return rm.getParticipants();
	}
public List<Event> findAllEvents(){
	return rm.getEvents();
}

public List<Event> getEventsForParticipant(Participant p) {
	// TODO Auto-generated method stub
	//new list for events
	// new list =  rm.getRegistrations();
	//for all registration (r) in list 
	//   if r.getPartipant().getName() == p.getName()
	//     put event in event list
	//return event list
	List<Event> returnList = new ArrayList<Event>();
	List<Registration> rl = rm.getRegistrations();
	for (Registration r:rl) {
		if(r.getParticipant().getName().equals(p.getName())) {
			returnList.add(r.getEvent());
		}
	}
	return returnList;
}

public Event createEvent(String name, Date date, Time startTime, Time endTime) throws InvalidInputException {
	 // To be completed
	if (name.trim().length() == 0 || name.equals(" ")) {
	    throw new InvalidInputException("Event name cannot be empty!");
	  }
	if (name ==null||date == null|| startTime == null|| endTime == null) {
		throw new InvalidInputException("Event name cannot be empty! Event date cannot be empty! Event start time cannot be empty! Event end time cannot be empty!");
	}
	
	if(startTime.after(endTime)) {
		throw new InvalidInputException("Event startTime is after endTime");
	}
	
	Event e = new Event(name, date, startTime, endTime);
    rm.addEvent(e);
    PersistenceXStream.saveToXMLwithXStream(rm);
    return e;
	}

public Registration register(Participant participant, Event event) throws InvalidInputException {
	 // To be completed
	
	if(participant == null || event ==  null) {
		throw new InvalidInputException("Participant needs to be selected for registration! Event needs to be selected for registration!");
	}
	
	Registration r = new Registration(participant,event);
    rm.addRegistration(r);
    PersistenceXStream.saveToXMLwithXStream(rm);
	 return r;
	}
	
	public Event getEventByName(String name) {
		List<Event> list = rm.getEvents();
		for(Event ee: list) {
			if(ee.getName().equals(name)) {
				return ee;
			}
		}
		return null;
	}
	public Participant getParticipantByName (String name) {
		List<Participant> list = rm.getParticipants();
		for (Participant pp : list) {
			if(pp.getName().equals(name)) {
				return pp;
			}
		}
		return null;
	}
}

