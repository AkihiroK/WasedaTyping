import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TitleView extends JPanel implements ActionListener {
    static final int viewMode = 1;
    
    public TitleView() {
        //レイアウトを変更可能に設定
        this.setLayout(null);
        setFocusable(true);

        //スタートボタンの追加
        JButton startButton = new JButton("GAME START");
        startButton.setSize(300,60);
        startButton.setLocation(250,350);
        startButton.addActionListener(this);
        startButton.setActionCommand("start");
        this.add(startButton);
        //Enterでスタートボタンを押せるように設定
        ClientMain.frame.getRootPane().setDefaultButton(startButton);

        //タイトルラベルの追加
        JLabel titleLabel = new JLabel("Waseda Typing");
        titleLabel.setSize(500,100);
        titleLabel.setLocation(180,100);
        titleLabel.setFont(new Font(null, Font.PLAIN, 60));
        this.add(titleLabel);

        //GameFrame.viewModeをTitleViewに変更
        GameFrame.viewMode = TitleView.viewMode;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0, 0, ClientMain.frame.getWidth(), ClientMain.frame.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand() == "start") {
            ClientMain.frame.changeView(new GameStartView());
        }
    }
}
