import java.io.*;
import java.net.*;

public class ClientConnection {
    // ポート番号を設定
    public static final int PORT = 8080;
    //受信メッセージ受け取り用
    public static String message = "";
    
    public static void requestRanking() throws IOException {
        //IPアドレスの取得
        InetAddress addr = InetAddress.getByName("localhost"); 
        //ソケットの作成
        Socket socket = new Socket(addr, PORT);

        try {
            // データ送受信用バッファの設定
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //メッセージを送信
            out.println("sendRanking");
            System.out.println("\nRequesting: sendRanking");

            for(int i=0; i<3; i++) {
                ScoreView.rankingName[i] = in.readLine();
                ScoreView.rankingScore[i] = Integer.parseInt(in.readLine());
            }

            //ソケットを閉じて終了
            System.out.println("Finished.");
            socket.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void updateRanking() throws IOException {
        //IPアドレスの取得
        InetAddress addr = InetAddress.getByName("localhost"); 
        //ソケットの作成
        Socket socket = new Socket(addr, PORT);

        try {
            // データ送受信用バッファの設定
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //メッセージを送信
            out.println("updateRanking");
            System.out.println("\nRequesting: updateRanking");

            for(int i=0; i<3; i++) {
                out.println(ScoreView.rankingName[i]);
                out.println(ScoreView.rankingScore[i]);
            }

            //ソケットを閉じて終了
            System.out.println("Finished.");
            socket.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void requestGameData() throws IOException {
        //IPアドレスの取得
        InetAddress addr = InetAddress.getByName("localhost"); 
        //ソケットの作成
        Socket socket = new Socket(addr, PORT);
    
        try {
            // データ送受信用バッファの設定
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //メッセージを送信
            out.println("sendGameData");
            System.out.println("\nRequesting: sendGameData");

            //メッセージを受信
            int index = Integer.parseInt(in.readLine());
            for(int i=0; i<index; i++) {
                message = in.readLine();
                Typing.words.add(message);
            }
        
            //ソケットを閉じて終了
            System.out.println("Finished.");
            socket.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void requestFinish() throws IOException {
        //IPアドレスの取得
        InetAddress addr = InetAddress.getByName("localhost"); 
        //ソケットの作成
        Socket socket = new Socket(addr, PORT);

        try {
            // データ送受信用バッファの設定
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //メッセージを送信
            out.println("finish");
            System.out.println("\nRequesting: finish");

            //ソケットを閉じて終了
            System.out.println("Finished.");
            socket.close();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}