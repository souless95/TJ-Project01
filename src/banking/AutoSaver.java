package banking;

public class AutoSaver extends Thread{
	
	AccountManager amanager = new AccountManager();
	
	public AutoSaver(AccountManager amanager) {
		this.amanager = amanager;
	}

	@Override
	public void run() {

		while(true) {
			try {
				sleep(5000);
				System.out.println();
				System.out.println("***계좌정보가 저장되었습니다.(5초단위)***");
				
				amanager.autoSave();
				
				}
			catch(InterruptedException e) {
				System.out.println("자동저장 탈출");
				return;
			}
			catch(Exception e) {
				System.out.println("");
			}
		}
	}
}
