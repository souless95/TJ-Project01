package banking;

import java.io.Serializable;

public abstract class Account implements Serializable{
	
	//멤버변수
	String actNum;
	String name;
	int bankBal;
	
	
	//생성자 초기화
	public Account(String actNum, String name, int bankBal) {
		this.actNum = actNum;
		this.name = name;
		this.bankBal = bankBal;
	}
	
	public void showAccInfo() {
		System.out.println("--------------- 계좌정보출력 ---------------");
		System.out.println("[계좌번호] "+ actNum);
		System.out.println("[고객이름] "+ name);
		System.out.println("[잔고] "+ bankBal);
	}
	
	public int InterestCal(int money) {
		return bankBal;
	}
	
	@Override
	public int hashCode() {
		int returnCode = this.actNum.hashCode();
		return returnCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		Account account = (Account)obj;
		if(account.actNum.equals(this.actNum)) {
			return true;			
		}
		else {
			return false;				
		}

	}
	
	@Override
	public String toString() {
		return "계좌번호:" + actNum + " 이름:" + name + " 잔고:" + bankBal;
	}
	
}
