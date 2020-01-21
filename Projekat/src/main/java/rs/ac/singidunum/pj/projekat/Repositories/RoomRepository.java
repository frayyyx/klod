package rs.ac.singidunum.pj.projekat.Repositories;

import rs.ac.singidunum.pj.projekat.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    List<Room> findAllByBeds(Integer beds);




}
