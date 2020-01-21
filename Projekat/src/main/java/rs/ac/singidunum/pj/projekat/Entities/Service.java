package rs.ac.singidunum.pj.projekat.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int service_id;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/Belgrade")
    private Date arrival_at;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/Belgrade")
    private Date departure_at;

    private int meals;

    @Column(name = "guest_id")
    private int guestId;
    private double price;

    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "Europe/Belgrade")
    private Timestamp created_at;

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public Date getArrival_at() {
        return arrival_at;
    }

    public void setArrival_at(Date arrival_at) {
        this.arrival_at = arrival_at;
    }

    public Date getDeparture_at() {
        return departure_at;
    }

    public void setDeparture_at(Date departure_at) {
        this.departure_at = departure_at;
    }

    public int getMeals() {
        return meals;
    }

    public void setMeals(int meals) {
        this.meals = meals;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }


}
