package com.flightapp.userservice.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightapp.userservice.Entity.Discount;
import com.flightapp.userservice.Entity.Passengers;
import com.flightapp.userservice.Entity.Ticket;
import com.flightapp.userservice.Entity.User;
import com.flightapp.userservice.Repo.TicketRepository;
import com.flightapp.userservice.Repo.UserRepository;
import com.flightapp.userservice.Service.PassengersService;
import com.flightapp.userservice.Service.TicketService;


@RestController
@CrossOrigin
@RequestMapping("/flight")
public class TicketController {
	
	@Autowired
	TicketService service;
	
	@Autowired 
	RestTemplate template;
	
	@Autowired
	PassengersService passservice;
	
	@Autowired
	TicketRepository repo;
	
	@Autowired
	UserRepository userrepo;
	
	@GetMapping(value="/tickets")
	Iterable<Ticket> getAllTickets()
	{
		return service.getAllTickets();
	}
	
	@GetMapping(value="/tickets/ticket/{ticketid}")
	Optional<Ticket> getByTicketId(@PathVariable Integer ticketid)
	{
		return service.getByTicketId(ticketid);
	}
	
	@GetMapping(value="/tickets/{userid}")
	List<Ticket> getAllBookedTicketsByUser(@PathVariable Integer userid)
	{
		Optional<User> user=userrepo.findById(userid);
		return repo.findByUserdetails(user);
	}
	

	@PostMapping(value="/tickets/create/{couponcode}")
	void createTicketwithCoupon(@PathVariable Integer couponcode, @RequestBody Ticket newTicket)
	{
			List<Passengers> listofPassengers= newTicket.getPassengers();
	    	passservice.addPassenger(listofPassengers);
	    	newTicket.setPassengers(listofPassengers);
	    	service.createTicketWithCoupon(newTicket,couponcode);
	    	    	
	    	
	}
	
	@PostMapping(value="/tickets/create")
	String createTicket2(@RequestBody Ticket newTicket)
	{
			List<Passengers> listofPassengers= newTicket.getPassengers();
	    	passservice.addPassenger(listofPassengers);
	    	newTicket.setPassengers(listofPassengers);
	    	service.createTicket(newTicket);
	    	System.out.print("in createTicket2");
	    return "success";
	}
	
	@PutMapping(value="/tickets/update")
	void updateTicket(@RequestBody Ticket newTicket)
	{
		List<Passengers> listofPassengers= newTicket.getPassengers();
		passservice.updatePassengers(listofPassengers);
		newTicket.setPassengers(listofPassengers);
	    service.updateTicket(newTicket);
	}
	
	@DeleteMapping(value="/tickets/delete/{ticketid}")
	void deleteTicket(@PathVariable Integer ticketid)
	{
		service.deleteTicketById(ticketid);
	}

}
