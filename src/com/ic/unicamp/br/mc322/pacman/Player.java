package src.com.ic.unicamp.br.mc322.pacman;

class Player {

	private int life;
	private Point pos;
	private int maxLife;
	private Direction direction;
	private bool isOnPowerUp;
	private final char[] symbols;
	
	public Player(int l, Point p, char up, char down, char left, char right) {
		this.life = l;
		this.pos = p;
		this.maxLife = l;
		this.isOnPowerUp = false;
		this.symbols = new char[4] {up, down, left, right};
	}
	
	public void move(Point vector) {
		this.pos.move(vector);
	}
	
	public void takeHit(int damage) {
		this.life -= damage;
	}
	
	public void resetLife() {
		this.life = this.maxLife;
	}
	
	public void draw(Console c) {
		c.gotoxy(this.pos);
		c.place(this.symbols[this.direction.ordinal()]);
	}
}
