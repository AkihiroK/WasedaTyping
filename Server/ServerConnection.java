import java.io.*;
import java.net.*;
import java.util.*;

public class ServerConnection {
    // ポート番号を設定
    public static final int PORT = 8080;
    //受信メッセージ受け取り用
    public static String message = "";

    public static void process() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        
        try {
            Socket socket = serverSocket.accept();
            try {
                System.out.println("\nConnection accepted: " + socket);

                // データ送受信用バッファの設定
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);  

                //メッセージを読み込み
                message = in.readLine();
                if(message == null) return;
                
                //受信メッセージによって処理を分ける
                System.out.println("Client Request: " + message);
                switch(message) {
                    //ゲームデータの要求
                    case "sendGameData":
                        Scanner scanner_problems = new Scanner(new File("data/problems.txt"));
                        while(scanner_problems.hasNext()) {
                            out.println(scanner_problems.nextLine());
                        }
                        System.out.println("Finished.");
                        break;
                    
                    //ランキングの要求
                    case "sendRanking":
                        Scanner scanner_ranking = new Scanner(new File("data/ranking.txt"));
                        while(scanner_ranking.hasNext()) {
                            out.println(scanner_ranking.nextLine());
                        }
                        System.out.println("Finished.");
                        break;

                    //ランキング更新の要求
                    case "updateRanking":
                        FileWriter fileWriter = new FileWriter("data/ranking.txt");
                        PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));

                        for(int i=0; i<3; i++) {
                            printWriter.println(in.readLine());
                            printWriter.println(in.readLine());
                        }

                        printWriter.close();
                        break;

                    //通信終了の要求
                    case "finish":
                        ServerMain.loop = false;
                        System.out.println("Disconnecting...");
                        System.out.println("Finished.");
                        break;
                }

                //ソケットを閉じて終了
                socket.close();
                serverSocket.close();
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
