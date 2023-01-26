import java.rmi.Remote;
public interface TicTacInterface extends Remote {
    public boolean setO(int row, int col);
    public boolean setX(int row, int col);
}