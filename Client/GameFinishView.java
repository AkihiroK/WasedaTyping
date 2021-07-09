import java.awt.*;
import javax.swing.*;

public class GameFinishView extends JPanel {
    static final int viewMode = 4;

    public GameFinishView() {
        //レイアウトを変更可能に設定
        this.setLayout(null);

        //カウント表示ラベルの追加
        JLabel timeLabel = new JLabel("FINISH!!", SwingConstants.CENTER);
        timeLabel.setSize(800,500);
        timeLabel.setLocation(0,0);
        timeLabel.setFont(new Font(null, Font.PLAIN, 100));
        timeLabel.setForeground(Color.WHITE);
        this.add(timeLabel);

        //タイマーの設定(カウントダウン3秒)
        new GameTime(1);

        //GameFrame.viewModeをCountDownViewに変更
        GameFrame.viewMode = GameFinishView.viewMode;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, ClientMain.frame.getWidth(), ClientMain.frame.getHeight());
    }
}
