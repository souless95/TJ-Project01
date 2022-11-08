package banking;

public class NormalAccount extends Account {
	int interest;

	public NormalAccount(String actNum, String name, int bankBal,int interest) {
		super(actNum, name, bankBal);
		this.interest = interest;
	}
	
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("[기본이자] "+ interest);
	}
	
	@Override
	public int InterestCal(int dptMoney) {
		return bankBal = (int) (bankBal +(bankBal*(interest*0.01)) + dptMoney);
	}
	
	@Override
	public String toString() {
		return super.toString() + " 기본이자:" + interest;
	}
	
	
}
