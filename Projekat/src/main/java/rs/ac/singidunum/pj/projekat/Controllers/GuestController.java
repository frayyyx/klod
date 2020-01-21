package rs.ac.singidunum.pj.projekat.Controllers;

import rs.ac.singidunum.pj.projekat.Entities.Guest;
import rs.ac.singidunum.pj.projekat.Exceptions.GuestNotFoundException;
import rs.ac.singidunum.pj.projekat.Repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestController {

    @Autowired
    GuestRepository guestRepository;

    //READ ALL
    @GetMapping("/guests")
    public List<Guest> getGuests() {
        return guestRepository.findAll();
    }

    //READ ONE
    @GetMapping("/guests/{id}")
    public Guest getGuestById(@PathVariable Integer id) {

        return guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException(id));

    }


    //CREATE
    @PostMapping("/guests")
    public Guest createGuest(@RequestBody Guest guest) {

        return guestRepository.save(guest);

    }

    //UPDATE
    @PutMapping("/guests/{id}")
    public Guest updateGuest(@PathVariable Integer id, @RequestBody Guest guest) {

        Guest newGuest = guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException(id));

        newGuest.setForename(guest.getForename());
        newGuest.setSurname(guest.getSurname());
        newGuest.setCountry(guest.getCountry());
        newGuest.setAddress(guest.getAddress());
        newGuest.setZipcode(guest.getZipcode());
        newGuest.setId_type(guest.getId_type());
        newGuest.setGender(guest.getGender());
        newGuest.setBirth_date(guest.getBirth_date());
        newGuest.setPhone_number(guest.getPhone_number());
        newGuest.setEmail(guest.getEmail());
        newGuest.setRoomId(guest.getRoomId());

        return guestRepository.save(newGuest);

    }


    //DELETE
    @DeleteMapping("/guests/{id}")
    public String deleteGuest(@PathVariable Integer id) {

        guestRepository.deleteById(id);

        return "Uspesno obrisan gost sa ID-em " + id;


    }

    //DELETE ALL
    @DeleteMapping("/guests")
    public String deleteAllGuests() {
        guestRepository.deleteAll();
        return "Uspesno obrisani svi gosti!";
    }

    //GEY BY FORENAME AND SURNAME

    @GetMapping("/guests/{forename}/{surname}")
    public List<Guest> getGuestsByForenameAndSurname(@PathVariable String forename, @PathVariable String surname) {

        return guestRepository.findAllByForenameAndSurname(forename, surname);


    }


    //GET BY ROOM ID
    @GetMapping("guests/rooms/{roomId}")
    public Guest getGuestsByRoomId(@PathVariable Integer roomId) {

        return guestRepository.findByRoomId(roomId);

    }


}
