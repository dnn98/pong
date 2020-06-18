import java.awt.Color;
import java.awt.Graphics;

public class Ball implements Runnable{
	private Thread myThread; 
	private int x;
	private int y;
	private int xSpd;
	private int ySpd;
	private int width;
	private int height;
	private Color col;
	private boolean go;
	private byte sleep;
	private BallTask bt;
	
	public Ball(BallTask bt) {
		this.bt=bt;
		this.myThread=new Thread(this);
		myThread.start();
	}
	public Ball(int x, int y, int xSpd, int ySpd,int w, int h, Color c, BallTask bt) {
		this.bt=bt;
		this.go=true;
		this.x=x;
		this.y=y;
		this.xSpd=xSpd;
		this.ySpd=ySpd;
		if(xSpd==0)this.xSpd=1;
		if(ySpd==0)this.ySpd=1;
		this.width=w;
		this.height=h;
		this.col=c;
		this.sleep=16;
		this.myThread=new Thread(this);
		myThread.start();
	}
	
	public void run() {
		while(go) {
			try {
				myThread.sleep(sleep);			
				move();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//600 x 560
	
	public void move() throws InterruptedException {
		if(y<0 || y+height>560) {
			ySpd*=-1;
		}
		if(bt.isSecond()) {//derecha
			if(x+width<0) { //Send ball
				kill();
				bt.sendBall(this);
			}else if(x+width>600-bt.getStick_right().getWidth() && (y >= bt.getStick_right().getY() && y <= bt.getStick_right().getY()+bt.getStick_right().getHeight())){
				xSpd*=-1;
			}else if(x>600){
				//logica de puntos
				bt.getScore_right().setScore_right_plusone();
				xSpd=0;
				ySpd=0;
				y=280;
				x=10;
				Thread.sleep(600);
				xSpd=4;
				ySpd=3;

			}
		}else {//izquierda
			if(x>600) { //Send ball
				kill();
				bt.sendBall(this);
			}else if(x+width-bt.getStick_left().getWidth()<0 && (y >= bt.getStick_left().getY() && y <= bt.getStick_left().getY()+bt.getStick_left().getHeight()))  {
				xSpd*=-1;
			}else if(x+width<0) {
				//logica de puntos
				bt.getScore_left().setScore_left_plusone();
				xSpd=0;
				ySpd=0;
				y=280;
				x=510;
				Thread.sleep(600);
				xSpd=-4;
				ySpd=-3;

			}
		}
		x+=xSpd;
		y+=ySpd;
		
	}
	
	
	public void paintSelf(Graphics g) {
		g.setColor(col);
		g.fillOval(x, y, width, height);
	}
	
	public void kill() {
		this.go=false;
	}
	
	public int getX() {
		return x;	
	}
	
	public int getY() {
		return y;	
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
		
	public int getxSpd() {
		return xSpd;
	}
	public void setxSpd(int xSpd) {
		this.xSpd = xSpd;
	}
	public int getySpd() {
		return ySpd;
	}
	public void setySpd(int ySpd) {
		this.ySpd = ySpd;
	}
	public Color getCol() {
		return col;
	}
	public void setCol(Color col) {
		this.col = col;
	}
	
}
