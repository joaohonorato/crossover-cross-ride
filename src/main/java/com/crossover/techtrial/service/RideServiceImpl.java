/**
 * 
 */
package com.crossover.techtrial.service;

import com.crossover.techtrial.exceptions.GlobalExceptionHandler;
import com.crossover.techtrial.model.Person;
import com.crossover.techtrial.model.Ride;
import com.crossover.techtrial.repositories.RideRepository;
import com.crossover.techtrial.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author crossover
 *
 */
@Service
public class RideServiceImpl implements RideService{

  @Autowired
  private RideRepository rideRepository;

  @Autowired
  private PersonService personService;
  
  public Ride save(Ride ride) throws GlobalExceptionHandler {
    if(!(validateDriver(ride) && validateDate(ride))){
      throw new GlobalExceptionHandler();
    }
    return rideRepository.save(ride);
  }

  private Boolean validateDriver(Ride ride) {
    Person driver = personService.findById(ride.getDriver().getId());
    if( driver != null && driver.equals(ride.getDriver())){
      return true;
    }
    return false;
  }

  private Boolean validateDate(Ride ride) throws GlobalExceptionHandler {
    Date startTime = DateUtil.strToDate(ride.getStartTime());
    Date endTime = DateUtil.strToDate(ride.getEndTime());
    return startTime.before(endTime);
  }

  public Ride findById(Long rideId) {
    Optional<Ride> optionalRide = rideRepository.findById(rideId);
    if (optionalRide.isPresent()) {
      return optionalRide.get();
    }else return null;
  }

}
