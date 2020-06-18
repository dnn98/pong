import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class Viewer extends Canvas implements Runnable{
	private BufferedImage img;
	private BufferedImage background;
	private byte[] backup;
	private BufferStrategy bs;
	private Graphics2D graph2d;
	private Graphics g;
	private Thread myThread;
	private Ball ball;
	private Stick stick_left;
	private Stick stick_right;
	private Score score_left;
	private Score score_right;
	
	public Viewer(Ball b) {
		this.setVisible(true);
		try {
			this.background=ImageIO.read(new File("sample.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		img= new BufferedImage(600, 600, BufferedImage.TYPE_4BYTE_ABGR);
		graph2d= img.createGraphics();
		g=this.getGraphics();
		ball = b;
		this.myThread= new Thread(this);
	}
	
	public void init() {
		this.createBufferStrategy(2);
		bs=this.getBufferStrategy();
		
		int size=img.getData().getDataBuffer().getSize();
		backup= new byte[size];
		for(int i=0; i<size; ++i) {
			backup[i]=0;
		}
		
		myThread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			myPaint();
			try {
				Thread.sleep(32);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
		
	public synchronized void myPaint() {
		g=bs.getDrawGraphics();
		if (g==null) {
			return;
		}
		img.getRaster().setDataElements(0, 0,600,600, backup);
		if (ball!=null)
			ball.paintSelf(graph2d);
		if (stick_left!=null)
			stick_left.paintSelf(graph2d);
		if (stick_right!=null)
			stick_right.paintSelf(graph2d);
		if(score_left!=null) {
			score_left.paintSelf(graph2d);
		}
		if(score_right!=null) {
			score_right.paintSelf(graph2d);
		}
		g.drawImage(background,0,0,600,600,null);
		g.drawImage(img,0,0,600,600,null);
		bs.show();
		g.dispose();
	}
	
	public void paint(Graphics g) {}
	
	public void setBall(Ball b) {
		this.ball = b;
	}

	public void setStick_left(Stick stick_left) {
		this.stick_left = stick_left;
	}

	public void setStick_right(Stick stick_right) {
		this.stick_right = stick_right;
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

