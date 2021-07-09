import java.util.Timer;
import java.util.TimerTask;

public class GameTime {
    public int counter;
    public Timer timer;
	public TimerTask task;
    
    public GameTime(int count) {
        //各プロパティの初期化
        counter = count;
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                switch(GameFrame.viewMode) {
                //ゲーム開始画面の場合
                    case GameStartView.viewMode:
                    if(counter > 0) {
                        counter -= 1;
                        if(counter == 0) {
                            //AppManager.startSE();
                            GameStartView.timeLabel.setText("START!!!!!");
                        }else {
                            //AppManager.countSE();
                            GameStartView.timeLabel.setText(String.valueOf(counter));
                        }
                    }else {
                        timer.cancel();
                        if(GameFrame.viewMode == GameStartView.viewMode) ClientMain.frame.changeView(new GameView());
                    }
                    break;

                //ゲーム画面の場合
                    case GameView.viewMode:
                    counter -= 1;
                    if(counter == 0) {
                        //AppManager.startSE();
                        timer.cancel();
                        ClientMain.frame.changeView(new GameFinishView());
                    }else {
                        //AppManager.countSE();
                        GameView.timeLabel.setText(String.valueOf(counter));
                    }
                    break;
                
                //ゲーム終了画面の場合
                    case GameFinishView.viewMode:
                    timer.cancel();
                    ClientMain.frame.changeView(new ScoreView());
                    break;
                }
            }
        };

        //タイマー起動
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }
}
