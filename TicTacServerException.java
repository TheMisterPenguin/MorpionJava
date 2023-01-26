import java.rmi.RemoteException;

public class TicTacServerException extends RemoteException{
    public TicTacServerException(String error){
        super(error);
    }
}
