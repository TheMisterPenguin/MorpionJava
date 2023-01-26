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

    public TicTacComm build() throws TicTacClientException, TicTacServerException{
        return new TicTacComm(locObj, player, address, port);
    }
}

