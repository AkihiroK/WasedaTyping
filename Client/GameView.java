import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameView extends JPanel implements ActionListener {
    public static JLabel wordLabel;
    public static JLabel scoreLabel;
    public static JLabel timeLabel;
    public static GameTime gameTime;

    static final int viewMode = 3;

    public GameView() {
        //レイアウトを変更可能に設定
        this.setLayout(null);

        //タイトル画面に戻るボタンの追加
        JButton goTitleButton = new JButton("Back to Title");
        goTitleButton.setSize(100, 40);
        goTitleButton.setLocation(0, 0);
        goTitleButton.addActionListener(this);
        goTitleButton.setActionCommand("backToTitle");
        this.add(goTitleButton);

        //お題表示ラベルの追加
        wordLabel = new JLabel("start", SwingConstants.CENTER);
        wordLabel.setSize(800,100);
        wordLabel.setLocation(0,100);
        wordLabel.setFont(new Font(null, Font.PLAIN, 40));
        this.add(wordLabel);

        //スコアのテキストラベルの追加
        JLabel scoreTextLabel = new JLabel("Score");
        scoreTextLabel.setSize(200,100);
        scoreTextLabel.setLocation(550,275);
        scoreTextLabel.setFont(new Font(null, Font.PLAIN, 40));
        scoreTextLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(scoreTextLabel);

        //スコア表示ラベルの追加
        scoreLabel = new JLabel("0");
        scoreLabel.setSize(100,100);
        scoreLabel.setLocation(600,325);
        scoreLabel.setFont(new Font(null, Font.PLAIN, 40));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(scoreLabel);

        //タイマーのテキストラベルの追加
        JLabel timeTextLabel = new JLabel("Time");
        timeTextLabel.setSize(200,100);
        timeTextLabel.setLocation(50,275);
        timeTextLabel.setFont(new Font(null, Font.PLAIN, 40));
        timeTextLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(timeTextLabel);

        //タイマー表示ラベルの追加
        timeLabel = new JLabel("30");
        timeLabel.setSize(100,100);
        timeLabel.setLocation(100,325);
        timeLabel.setFont(new Font(null, Font.PLAIN, 40));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(timeLabel);

        //タイマーの設定(カウントダウン30秒)
        gameTime = new GameTime(30);

        //ゲームスコアの初期化
        Typing.score = 0;

        //GameFrame.viewModeをGameViewに変更
        GameFrame.viewMode = GameView.viewMode;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, ClientMain.frame.getWidth(), ClientMain.frame.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "backToTitle") {
            gameTime.timer.cancel();
            ClientMain.frame.changeView(new TitleView());
        }
    }
}

