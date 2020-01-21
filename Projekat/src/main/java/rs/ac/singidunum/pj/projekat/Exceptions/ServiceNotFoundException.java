package rs.ac.singidunum.pj.projekat.Exceptions;


public class ServiceNotFoundException extends RuntimeException {

    public ServiceNotFoundException(Integer id) {

        super("Nije pronadjena usluga sa ID-em " + id);

    }

    public ServiceNotFoundException(String message) {

        super(message);

    }


}
