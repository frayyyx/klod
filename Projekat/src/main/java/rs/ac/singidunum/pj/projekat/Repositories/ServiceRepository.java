package rs.ac.singidunum.pj.projekat.Repositories;

import rs.ac.singidunum.pj.projekat.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {

    List<Service> findAllByGuestId(Integer guest_id);


    @Query(value = "SELECT SUM(price) from service", nativeQuery = true)
    double pricesSum();


    List<Service> findAllByPriceBetween(double price1, double price2);


}
