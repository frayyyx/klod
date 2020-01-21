package rs.ac.singidunum.pj.projekat.Controllers;

import rs.ac.singidunum.pj.projekat.Entities.Guest;
import rs.ac.singidunum.pj.projekat.Entities.Room;
import rs.ac.singidunum.pj.projekat.Exceptions.GuestNotFoundException;
import rs.ac.singidunum.pj.projekat.Exceptions.RoomNotFoundException;
import rs.ac.singidunum.pj.projekat.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {


    @Autowired
    RoomRepository roomRepository;

    //READ ALL
    @GetMapping("/rooms")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    //READ ONE
    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable Integer id) {

        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));

    }

    //CREATE
    @PostMapping("/rooms")
    public Room createRoom(@RequestBody Room room) {

        if (room.getBeds() < 1 || room.getBeds() > 5) {

            throw new RoomNotFoundException("Broj kreveta mora biti broj izmedju 1 i 5.");

        }

        return roomRepository.save(room);

    }

    //UPDATE
    @PutMapping("/rooms/{id}")
    public Room updateRoom(@PathVariable Integer id, @RequestBody Room room) {

        if (room.getBeds() < 1 || room.getBeds() > 5) {

            throw new RoomNotFoundException("Broj kreveta mora biti broj izmedju 1 i 5.");

        }

        Room newRoom = roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));

        newRoom.setBeds(room.getBeds());
        newRoom.setNumber(room.getNumber());

        return roomRepository.save(newRoom);

    }


    //DELETE
    @DeleteMapping("/rooms/{id}")
    public String deleteRoom(@PathVariable Integer id) {

        roomRepository.deleteById(id);

        return "Uspesno obrisana soba sa ID-em " + id;


    }

    //DELETE ALL
    @DeleteMapping("/rooms")
    public String deleteAllRooms() {
        roomRepository.deleteAll();
        return "Uspesno obrisane sve sobe!";
    }


    //READ BY NUMBER OF BEDS
    @GetMapping("/rooms/beds/{numBeds}")
    public List<Room> getRoomByBeds(@PathVariable Integer numBeds) {

        return roomRepository.findAllByBeds(numBeds);

    }


}
