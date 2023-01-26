import java.rmi.RemoteException;

public class TicTacClientException extends RemoteException {
    public TicTacClientException(String error) {
        super(error);
    }
}
