package rs.ac.singidunum.pj.projekat.Repositories;

import rs.ac.singidunum.pj.projekat.Entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {

    List<Guest> findAllByForenameAndSurname(String forename, String surname);
    Guest findByRoomId(Integer room_id);


}
