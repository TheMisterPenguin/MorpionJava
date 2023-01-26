import java.rmi.*;

public class TicTacComm {
    private TicTacInterface remoteObj;

    public TicTacComm(TicTacInterface obj, String player, String address, Integer port) throws TicTacClientException, TicTacServerException{
        // On setup la connection RMI
        // Coté serveur
        try {
            Naming.rebind("rmi://localhost" + ":" + port.toString() + "/Player" + player, obj);
        } catch (Exception e) {
            throw new TicTacServerException("Impossible de préparer la réception rmi : " + e.getMessage());
        }
        // Coté client
        try {
            remoteObj = (TicTacInterface) Naming
                    .lookup("rmi://" + address + ":" + port.toString() + "/Player" + (player == "X" ? "O" : "X"));
        } catch (Exception e) {
            throw new TicTacClientException("Impossible de préparer la réception rmi : " + e.getMessage());
        }
    }

    public TicTacInterface remoteObj() {
        return this.remoteObj;
    }
}