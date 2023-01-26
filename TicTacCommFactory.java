import java.rmi.*;

public class TicTacCommFactory{
    private String address = "localhost";
    private Integer port = 1099;
    private TicTacInterface locObj;
    private String player;

    public TicTacCommFactory(TicTacInterface o, String player){
        locObj = o;
        this.player = player;
    }

    public TicTacCommFactory address(String address){
        this.address = address;
        return this;
    }

    public TicTacCommFactory port(Integer port){
        this.port = port;
        return this;
    }

    public TicTacComm getInstance(){
        return new TicTacComm(locObj, player, address, port);
    }
}

class TicTacComm {
    private TicTacInterface remoteObj;

    public TicTacComm(TicTacInterface obj, String player, String address, Integer port){
        // On setup la connection RMI
        // Coté serveur
        try {
            Naming.rebind("rmi://localhost" + ":" + port.toString() + "/Player" + player, obj);
        } catch (Exception e) {
            // TODO: handle exception
        }
        // Coté client
        try {
            remoteObj = (TicTacInterface) Naming.lookup("rmi://" + address + ":" + port.toString() + "/Player" + (player == "X" ? "O" : "X"));
        }
        catch(Exception e) {

        }
    }

    public TicTacInterface remoteObj(){
        return this.remoteObj;
    }
}
