
public class Node {
	public int RADIUS = 20;
	private int id;
	private int initX;
	private int initY;
	private int x;
	private int y;
	
	public Node(int id, int x, int y) {
		this.id = id;
		this.initX = x;
		this.initY = y;
		this.x = this.initX;
		this.y = this.initY;
	}
	
	public int getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getInitX() {
		return initX;
	}
	
	public int getInitY() {
		return initY;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}
}
