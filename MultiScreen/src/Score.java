import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score{
	private int score_left;
	private int score_right;
	private boolean second;
	private String points;
	private int x;
	private int y;
	public Score(boolean isSecond) {
		this.score_left=0;
		this.score_right=0;
		this.second=isSecond;
	}
	public void paintSelf(Graphics g) {
		 g.setFont(new Font("MS Gothic", Font.BOLD, 22));
		if(second) {
			points=String.valueOf(score_right);
		     g.drawString("Fallos: "+points, 440, 40);
		}else {
			points=String.valueOf(score_left);
		     g.drawString("Fallos: "+points, 40, 40);
		}
	}
	public int getScore_left() {
		return score_left;
	}
	public void setScore_left(int score_left) {
		this.score_left = score_left;
	}
	public int getScore_right() {
		return score_right;
	}
	public void setScore_right(int score_right) {
		this.score_right = score_right;
	}
	public void setScore_left_plusone() {
		this.score_left+=1;
	}
	public void setScore_right_plusone() {
		this.score_right+=1;
	}
}