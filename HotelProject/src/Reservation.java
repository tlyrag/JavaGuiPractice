
public class Reservation {
	int GuestID;
	String name;
	int RoomID;
	int PricePaid;
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}
	Reservation(int GuestID,String name,int RoomID,int PricePaid) {
		this.GuestID=GuestID;
		this.name=name;
		this.RoomID=RoomID;
		this.PricePaid=PricePaid;
	}
}
