import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Stick implements Runnable{
	private Thread myThread; 
	private boolean go;
	private int x;
	private int y;
	private boolean isSecond;
	private final int width = 25;
	private final int height = 90;
	private byte sleep;
	private BallTask bt;
	private boolean w=false;
	private boolean s=false;
	private boolean up=false;
	private boolean down=false;
	private int stickSpd= 5;
	
	public Stick(boolean isSecond, BallTask bt) {
		KeyListener listener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			    int key = e.getKeyCode();
			    if (key == KeyEvent.VK_W) {
			    	setW(true);
			    }
			    if (key == KeyEvent.VK_S) {
			    	setS(true);
			    }
			    if (key == KeyEvent.VK_UP) {
			        setUp(true);
			    }
			    if (key == KeyEvent.VK_DOWN) {
			        setDown(true);
			    }
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			    int key = e.getKeyCode();
			    if (key == KeyEvent.VK_W) {
			    	setW(false);
			    }
			    if (key == KeyEvent.VK_S) {
			    	setS(false);
			    }
			    if (key == KeyEvent.VK_UP) {
			        setUp(false);
			    }
			    if (key == KeyEvent.VK_DOWN) {
			        setDown(false);
			    }
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        };
        bt.getV().addKeyListener(listener);
		this.go=true;
		this.isSecond=isSecond;
		this.sleep=16;
		this.bt=bt;
		if(isSecond) {
			this.x=600-width-(width/2);
			this.y=280;
		}else {
			this.x=0;
			this.y=280;
		}
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
	
	public void move() {
		if(isSecond) {
			if(up==true && y > 0) {
				y-=stickSpd;
			}
			if(down==true && y < 560-height) {
				y+=stickSpd;
			}
		}else {
			if(w==true && y > 0) {
				y-=stickSpd;
			}
			if(s==true && y < 560-height) {
				y+=stickSpd;
			}
		}
	}
	//600 x 560
	
	public void paintSelf(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x,y,width,height);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	} 

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isW() {
		return w;
	}

	public void setW(boolean w) {
		this.w = w;
	}

	public boolean isS() {
		return s;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	
}