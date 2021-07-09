import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Typing extends JPanel {
    public static ArrayList<String> words = new ArrayList<>();

    private static final int[] points = {1,10,1};
    private static final int[] penalties = {5,1,10};
    private static final int[] completes = {5,20,3};

    public static int score;

    public static int gameMode = 0;

    public static String getRandomWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    //ゲームモードの設定
    public static void setGameMode(int gameMode) {
        Typing.gameMode = gameMode;
    }

    //正解の文字を打った時、スコア加算
    public static void point() {
        score += points[gameMode];
    }

    //誤字を打った時、スコア減算
    public static void penalty() {
        score -= penalties[gameMode];
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    //お題を打ち終えた時、スコア加算（pointの代わりにcompleteが加算される）
    public static void complete() {
        score += completes[gameMode];
    }

    public static void Process(char key) {
        //お題の文字列を読み込む
        String word = GameView.wordLabel.getText();

        if(word.charAt(0) == key) {
            //正解の場合は黒文字で文字表示
            GameView.wordLabel.setForeground(Color.BLACK);
            //お題が打ち込み終わったかを判定
            if(word.length() == 1) {
                //お題を全てタイプされたら、新しいお題へ
                GameView.wordLabel.setText(getRandomWord());
                complete();
            }else {
                //お題の残りがあるなら、先頭文字を一文字消去
                GameView.wordLabel.setText(word.substring(1));
                point();
            } 
        }else {
            //間違えたキーを打つと赤文字表示
            GameView.wordLabel.setForeground(Color.RED);
            penalty();
        }

        //新しいスコアを画面に表示
        GameView.scoreLabel.setText(String.valueOf(score));
        }
}
