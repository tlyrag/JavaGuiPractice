import javax.swing.*;

public interface Play {
	public abstract void CalDistance();
	public abstract void Fire(JButton btn, WarShip[] warShips,JButton[][] btns ,JLabel statusLabel);
	
}
