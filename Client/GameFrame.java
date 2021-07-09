import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class GameFrame extends JFrame implements KeyListener, WindowListener {
    //どのviewを表示しているか、を保存する変数
    static int viewMode = 0;

    public GameFrame() {
        //表示ウィンドウの設定
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("TypingEats");
        this.setSize(800, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.GRAY);
        this.setVisible(true);
        
        //キー、ウィンドウ検知の詳細設定
        addKeyListener(this);
        addWindowListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        //タイピングゲーム設定の初期化
        try {
            ClientConnection.requestGameData();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void changeView(JPanel view) {
        //現在のviewを消してから
        getContentPane().removeAll();   
        //新しいviewを追加
        super.add(view);

        validate();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //入力された文字を保存
        char key = e.getKeyChar();

        //キー入力がENTERの場合は、タイピングの判定をしない(ボタンを押すキーだから)
        if(e.getKeyChar() == '\n') return;

        //GameFrame.viewModeで場合分け
        switch(GameFrame.viewMode) {
        //タイトル画面
        case TitleView.viewMode:
            break;

        //ゲーム画面
        case GameView.viewMode:
            Typing.Process(key);
            break;

        //スコア画面
        case ScoreView.viewMode:
            break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            ClientConnection.requestFinish();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    //以下、要らない子たち
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
}
