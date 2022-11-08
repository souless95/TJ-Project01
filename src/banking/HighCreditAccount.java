package banking;

public class HighCreditAccount extends Account implements ICustomDefine{
	
	int interest;
	String cRating;
	
	public HighCreditAccount(String actNum, String name, int bankBal, int interest, String cRating) {
		super(actNum, name, bankBal);
		this.interest = interest;
		this.cRating = cRating;
	}
	
	@Override
	public int InterestCal(int dptMoney) {
		
		int bnsInterest;
		
		if (cRating.equalsIgnoreCase("a")) {
			bnsInterest = RATE_A;
		}
		else if (cRating.equalsIgnoreCase("b")) {
			bnsInterest = RATE_B;			
		}
		else if (cRating.equalsIgnoreCase("c")) {
			bnsInterest = RATE_C;			
		}
		else {
			bnsInterest = 0;
			System.out.println("신용등급을 잘못입력했습니다.");
		}
		
		return bankBal = (int)(bankBal + (bankBal*(interest*0.01)) + (bankBal*(bnsInterest*0.01)) + dptMoney);
	}
	
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("[기본이자] "+ this.interest);
		System.out.println("[신용등급] "+ this.cRating);
	}
	
	@Override
	public String toString() {

		return super.toString() + " 기본이자:" + interest + " 신용등급:" + cRating;
	}
	
}
