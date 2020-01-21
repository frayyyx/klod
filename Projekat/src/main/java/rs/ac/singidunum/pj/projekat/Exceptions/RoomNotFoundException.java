package rs.ac.singidunum.pj.projekat.Exceptions;


public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException(Integer id) {

        super("Nije pronadjena soba sa ID-em " + id);

    }

    public RoomNotFoundException(String message) {

        super(message);

    }


}
