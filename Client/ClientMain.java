public class ClientMain {
    public static GameFrame frame;
    public static void main(String[] args) throws Exception {    
        frame = new GameFrame();
        frame.changeView(new TitleView());
    }
}