package rs.ac.singidunum.pj.projekat.Exceptions;


public class GuestNotFoundException extends RuntimeException {


    public GuestNotFoundException(Integer id) {

        super("Nije pronadjen gost sa ID-em " + id);

    }


}
