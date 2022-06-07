package com.flightapp.userservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.userservice.Entity.Passengers;
import com.flightapp.userservice.Repo.PassengerRepository;

@Service
public class PassengersService {
	
	@Autowired
	PassengerRepository repo;
	
	public void addPassenger(List<Passengers> passengerlist)
	{
		passengerlist.forEach(passenger-> repo.save(passenger));
	}

	public void addPassengers(Passengers thepass) {
		repo.save(thepass);
	}

	public void updatePassengers(List<Passengers> listofPassengers) {
		listofPassengers.forEach(passenger-> repo.save(passenger));
	}

	public void deletePassenegrById(Integer pid) {
		repo.deleteById(pid);
	}
	
	

}

