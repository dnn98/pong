import java.awt.Color;

public class BallProtocol {
	private String message;
	private String[] strings;
	private int[] data;
	private Ball ball;
	private BallTask bt;
	public BallProtocol(BallTask b) {
		strings= new String[7];
		data= new int[7];
		this.bt= b;
	}
	
	public String sendBall(Ball b) {
		message=(String.valueOf(b.getX())+" "+String.valueOf(b.getY())+" "
				+String.valueOf(b.getxSpd())+" "+String.valueOf(b.getySpd())+" "
				+String.valueOf(b.getWidth())+" "+String.valueOf(b.getHeight())+" "
				+b.getCol().getRGB())+" ";
		return message;
	}
	public void createBall(String s) {
		strings=s.split(" ");
		for (byte i=0; i<7; ++i) {
			data[i]=Integer.parseInt(strings[i]);
		}
		if(data[0]>600) {
			data[0]=0;
		}else {
			data[0]=599;
		}
		Color c = new Color(data[6]);
		this.ball= new Ball(
					data[0],data[1],
					data[2],data[3],
					data[4],data[5],
					c,bt);
		bt.createBall(ball);		
	}
	
}
