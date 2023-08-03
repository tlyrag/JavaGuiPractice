
public abstract class Players {
	
	int Position;
	int health;
	int score;
	boolean status = true;
	Players(int Position) {
		this.Position=Position;
	}
	public abstract void attack();
}
