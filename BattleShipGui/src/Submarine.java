
public class Submarine extends WarShip{

	Submarine(int MapMaxPosition) {
		this.size=2;
		this.shield=2;
		this.status=true;
		Deploy(MapMaxPosition);
	}
	

	
	@Override
	public void Deploy(int MapMaxPosition) {
		// TODO Auto-generated method stub
		int randomX = (int)(Math.random()*MapMaxPosition);
		int maxY = MapMaxPosition-size;
		
		int randomY = (int)(Math.random()*maxY);
	
		
		this.x = randomX;
		int[] yArr = new int[this.size];
		yArr[0] = randomY;
		
		for(int i=1;i<yArr.length;++i) {
			yArr[i]=yArr[i-1]+1;
		}
		this.y = yArr;
	}

}
