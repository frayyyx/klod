package rs.ac.singidunum.pj.projekat.Controllers;

import rs.ac.singidunum.pj.projekat.Entities.Guest;
import rs.ac.singidunum.pj.projekat.Entities.Room;
import rs.ac.singidunum.pj.projekat.Entities.Service;
import rs.ac.singidunum.pj.projekat.Exceptions.GuestNotFoundException;
import rs.ac.singidunum.pj.projekat.Exceptions.RoomNotFoundException;
import rs.ac.singidunum.pj.projekat.Exceptions.ServiceNotFoundException;
import rs.ac.singidunum.pj.projekat.Logic.Logic;
import rs.ac.singidunum.pj.projekat.Repositories.GuestRepository;
import rs.ac.singidunum.pj.projekat.Repositories.RoomRepository;
import rs.ac.singidunum.pj.projekat.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RestController
public class ServiceController {


    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    RoomRepository roomRepository;


    private Logic logic = new Logic();

    private Room room;
    private Guest guest;


    //READ ALL
    @GetMapping("/services")
    public List<Service> getServices() {
        return serviceRepository.findAll();
    }

    //READ ONE
    @GetMapping("/services/{id}")
    public Service getServiceById(@PathVariable Integer id) {

        return serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

    }

    //CREATE
    @PostMapping("/services")
    public Service createService(@RequestBody Service service) {


        guest = guestRepository.findById(service.getGuestId()).orElseThrow(() -> new GuestNotFoundException(service.getGuestId()));

        room = roomRepository.findById(guest.getRoomId()).orElseThrow(() -> new RoomNotFoundException(guest.getRoomId()));


        if (service.getMeals() < 1 || service.getMeals() > 3) {
            throw new ServiceNotFoundException("Broj obroka mora biti broj izmedju 1 i 3.");
        }


        service.setPrice(logic.calculatePrice(service.getArrival_at(), service.getDeparture_at(), room.getBeds(), service.getMeals()));


        return serviceRepository.save(service);

    }

    //UPDATE
    @PutMapping("/services/{id}")
    public Service updateService(@PathVariable Integer id, @RequestBody Service service) {


        if (service.getMeals() < 1 || service.getMeals() > 3) {
            throw new ServiceNotFoundException("Broj obroka mora biti broj izmedju 1 i 3.");
        }

        guest = guestRepository.findById(service.getGuestId()).orElseThrow(() -> new GuestNotFoundException(service.getGuestId()));

        room = roomRepository.findById(guest.getRoomId()).orElseThrow(() -> new RoomNotFoundException(guest.getRoomId()));

        Service newService = serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

        newService.setArrival_at(service.getArrival_at());
        newService.setDeparture_at(service.getDeparture_at());
        newService.setMeals(service.getMeals());
        newService.setGuestId(service.getGuestId());
        newService.setPrice(logic.calculatePrice(service.getArrival_at(), service.getDeparture_at(), room.getBeds(), service.getMeals()));

        return serviceRepository.save(newService);

    }


    //DELETE
    @DeleteMapping("/services/{id}")
    public String deleteService(@PathVariable Integer id) {

        serviceRepository.deleteById(id);

        return "Uspesno obrisana usluga sa ID-om " + id;


    }

    //DELETE ALL
    @DeleteMapping("/services")
    public String deleteAllServices() {
        serviceRepository.deleteAll();
        return "Uspesno obrisane sve usluge!";
    }

    //READ ONE PRICE
    @GetMapping("/services/prices/{id}")
    public String getPriceByGuestId(@PathVariable Integer id) {


        Service service = serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

        double price = service.getPrice();

        return "Racun usluge sa ID-em " + id + " je " + price + " dinara.";


    }


    //GET ALL SERVICES WITH GUEST
    @GetMapping("/services/guests/{guestId}")
    public List<Service> getServiceByGuestId(@PathVariable Integer guestId) {


        return serviceRepository.findAllByGuestId(guestId);

    }


    //GET SUM OF PRICES
    @GetMapping("/services/prices/sum")
    public String getPricesSum() {

        return "Ukupan prihod je " + serviceRepository.pricesSum() + " dinara.";

    }

    //GET ALL SERVICES WITH PRICE BETWEEN
    @GetMapping("/services/prices/{price1}/{price2}")
    public List<Service> getServicesByPriceBetween(@PathVariable double price1, @PathVariable double price2) {


        return serviceRepository.findAllByPriceBetween(price1, price2);

    }


}
