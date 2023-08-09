
public  class client {
	String Name;
	int accountNumbber;
	int password;
	Double funds;
	
	client(String name,int accountNumber,int password,Double funds) {
		this.Name=name;
		this.accountNumbber=accountNumber;
		this.password=password;
		this.funds=funds;
		
	}
	client() {}
	public String withdraw(Double money,int password) {
		
		if(this.password!=password) {
			return "Incorrect Password";
		}
		if(this.funds<money) {
			return "Not enough funds";
		}
		else {
			this.funds-=money;
			StringBuilder success = new StringBuilder();
			success.append("Operation Successfull\n");
			success.append("Money Withdrawn:" + money+"\n");
			success.append("Current Funds:" +this.funds);
			return success.toString();
		}
	}
	public String deposit(Double money,int password) {
		
		if(this.password!=password) {
			return "Incorrect Password";
		}
		
			this.funds+=money;
			StringBuilder success = new StringBuilder();
			success.append("Operation Successfull\n");
			success.append("Money Deposited:" + money+"\n");
			success.append("Current Funds:" +this.funds);
			return success.toString();
	}
	public String checkBalance(int password) {
		
		if(this.password!=password) {
			return "Incorrect Password";
		}
		
		StringBuilder success = new StringBuilder();
		success.append("Current Funds:" +this.funds);
		return success.toString();
	}
}
