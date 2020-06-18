import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

public class BallTask extends JFrame{
	private ServerConnection sc;
	private ClientConnection cc;
	private RemoteBallTask rbt;
	private Viewer v;
	private Ball ball;
	private Stick stick_left;
	private Stick stick_right;
	private boolean second;
	private Score score_left;
	private Score score_right;
	public static void main(String[] args) {
		BallTask s= new BallTask();
	}
	
	public BallTask() {
		rbt= new  RemoteBallTask(this);
		this.ball = null;
		System.out.println("New Screen");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
		v= new Viewer(ball);
		v.setSize(600, 600);
		this.add(v);
		v.init();
		sc=new ServerConnection(this);
		sc.start();
		cc=new ClientConnection(this);
		cc.start();
	}
	
	public synchronized void createBall(Ball b) {
		this.ball=b;
		v.setBall(b);
	}
	
	public void setRemoteBallTaskSocket(Socket s) {
		rbt.setSocket(s);
	}
	public boolean rbtActive() {
		return rbt.hasSocket();
	}
	
	public void ping() {
		rbt.ping();
	}
	
	public synchronized void sendBall(Ball b) {
		this.ball = null;
		rbt.sendBall(b);
	}
	
	public void setClientPort(int port) {
		if(port==666) {
			this.second = true;
			this.ball = new Ball(100, 100, 3, 3, 10, 10, Color.GREEN, this);
			this.stick_right = new Stick(second, this);
			this.score_right = new Score(second);
			v.setStick_right(stick_right);
			v.setBall(ball);
			v.setScore_right(score_right);
		}else {
			Music m= new Music();
			this.second = false;
			this.stick_left = new Stick(second, this);
			this.score_left = new Score(second);
			v.setStick_left(stick_left);
			v.setScore_left(score_left);
		}
		cc.setPort(port);
	}
	public void resetConnections() {
		sc.reset();
	}

	public boolean isSecond() {
		return second;
	}

	public Stick getStick_left() {
		return stick_left;
	}

	public Stick getStick_right() {
		return stick_right;
	}

	public Viewer getV() {
		return v;
	}

	public Score getScore_left() {
		return score_left;
	}

	public void setScore_left(Score score_left) {
		this.score_left = score_left;
	}

	public Score getScore_right() {
		return score_right;
	}

	public void setScore_right(Score score_right) {
		this.score_right = score_right;
	}
	
	
}
