import java.io.IOException;

public class ServerMain {
    public static boolean loop = true;
    public static void main(String[] args) throws IOException {  
        while(loop){
            ServerConnection.process();
        }
    }
}