package les.donations.backendspring.exceptions;

public class NotFoundEntityException extends Exception{

    public NotFoundEntityException(String message){
        super(message);
    }
}
