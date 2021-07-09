import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class ScoreView extends JPanel implements ActionListener {
    static final int viewMode = 5;
    
    public static String[] rankingName = new String[3];
    public static int[] rankingScore = new int[3];
    public static String playerName = "";
    
    public ScoreView() {
        //レイアウトを変更可能に設定
        this.setLayout(null);

        //プレイヤー名を読み込む
        try {
            Scanner scanner = new Scanner(new File("data/playerName.txt"));
            playerName = scanner.nextLine();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        //ゲーム開始ボタンの追加
        JButton gameRestartButton = new JButton("RETRY");
        gameRestartButton.setSize(250,60);
        gameRestartButton.setLocation(100,350);
        gameRestartButton.addActionListener(this);
        gameRestartButton.setActionCommand("gameRestart");
        this.add(gameRestartButton);

        //タイトル画面に戻るボタンの追加
        JButton goTitleButton = new JButton("Back to Title");
        goTitleButton.setSize(250,60);
        goTitleButton.setLocation(450,350);
        goTitleButton.addActionListener(this);
        goTitleButton.setActionCommand("backToTitle");
        this.add(goTitleButton);

        //スコアのテキストラベルの追加
        JLabel scoreTextLabel = new JLabel("Your Score");
        scoreTextLabel.setSize(300,100);
        scoreTextLabel.setLocation(75,50);
        scoreTextLabel.setFont(new Font(null, Font.PLAIN, 55));
        scoreTextLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(scoreTextLabel);

        //スコア表示ラベルの追加
        JLabel scoreLabel = new JLabel(String.valueOf(Typing.score));
        scoreLabel.setSize(300,150);
        scoreLabel.setLocation(75,150);
        scoreLabel.setFont(new Font(null, Font.PLAIN, 80));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(scoreLabel);

        //ランキングを要求
        try {
            ClientConnection.requestRanking();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        //スコアが良ければ、ランキングに追加
        addRanking();
        
        //ランキング関連のラベル
        //スコア表示ラベルの追加
        JLabel rankingTextLabel = new JLabel("-Ranking-");
        rankingTextLabel.setSize(200,50);
        rankingTextLabel.setLocation(450,50);
        rankingTextLabel.setFont(new Font(null, Font.PLAIN, 30));
        this.add(rankingTextLabel);

        //ランキング名前ラベルの追加
        JLabel firstNameLabel = new JLabel("【1】 " + rankingName[0]);
        firstNameLabel.setSize(275,50);
        firstNameLabel.setLocation(450,125);
        firstNameLabel.setFont(new Font(null, Font.PLAIN, 20));
        this.add(firstNameLabel);

        JLabel secondNameLabel = new JLabel("【2】 " + rankingName[1]);
        secondNameLabel.setSize(275,50);
        secondNameLabel.setLocation(450,175);
        secondNameLabel.setFont(new Font(null, Font.PLAIN, 20));
        this.add(secondNameLabel);

        JLabel thirdNameLabel = new JLabel("【3】 " + rankingName[2]);
        thirdNameLabel.setSize(275,50);
        thirdNameLabel.setLocation(450,225);
        thirdNameLabel.setFont(new Font(null, Font.PLAIN, 20));
        this.add(thirdNameLabel);

        //ランキングスコアラベルの追加
        JLabel firstScoreLabel = new JLabel(String.valueOf(rankingScore[0]));
        firstScoreLabel.setSize(300,50);
        firstScoreLabel.setLocation(725,125);
        firstScoreLabel.setFont(new Font(null, Font.PLAIN, 20));
        this.add(firstScoreLabel);

        JLabel secondScoreLabel = new JLabel(String.valueOf(rankingScore[1]));
        secondScoreLabel.setSize(300,50);
        secondScoreLabel.setLocation(725,175);
        secondScoreLabel.setFont(new Font(null, Font.PLAIN, 20));
        this.add(secondScoreLabel);

        JLabel thirdScoreLabel = new JLabel(String.valueOf(rankingScore[2]));
        thirdScoreLabel.setSize(300,50);
        thirdScoreLabel.setLocation(725,225);
        thirdScoreLabel.setFont(new Font(null, Font.PLAIN, 20));
        this.add(thirdScoreLabel);

        //GameFrame.viewModeをScoreViewに変更
        GameFrame.viewMode = ScoreView.viewMode;
    }

    public static void addRanking() {
        //ランキング外なら、関係なし
        if(Typing.score < rankingScore[2]) return;

        //一位の場合
        if(rankingScore[0] <= Typing.score) {
            rankingName[2] = rankingName[1];
            rankingName[1] = rankingName[0];
            rankingName[0] = playerName;

            rankingScore[2] = rankingScore[1];
            rankingScore[1] = rankingScore[0];
            rankingScore[0] = Typing.score;
        }

        //二位の場合
        if(rankingScore[1] <= Typing.score && Typing.score < rankingScore[0]) {
            rankingName[2] = rankingName[1];
            rankingName[1] = playerName;

            rankingScore[2] = rankingScore[1];
            rankingScore[1] = Typing.score;
        }

        //三位の場合
        if(rankingScore[2] <= Typing.score && Typing.score < rankingScore[1]) {
            rankingName[2] = playerName;

            rankingScore[2] = Typing.score;
        }

        //ランキングの更新
        try {
            ClientConnection.updateRanking();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, ClientMain.frame.getWidth(), ClientMain.frame.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "backToTitle") ClientMain.frame.changeView(new TitleView());
        if(e.getActionCommand() == "gameRestart") ClientMain.frame.changeView(new GameStartView());
    }
}
