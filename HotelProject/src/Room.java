
public class Room {
	int RoomID;
	Double price;
	int beds;
	int stars;
     
	Room() {}
	Room (int RoomID,Double price,int beds,int stars) {
		this.RoomID= RoomID;
		this.price=price;
		this.beds = beds;
		this.stars= stars;
	}
}
