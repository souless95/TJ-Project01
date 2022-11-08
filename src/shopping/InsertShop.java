package shopping;


//DB연결을 위한 클래스를 상송한다.
public class InsertShop extends IConnectImpl{
	
	//생성자 : 부모클래스의 생성자를 호출하여 연결한다.
	public InsertShop() {
		super(ORACLE_DRIVER, "education", "1234");
	}
	
	//쿼리 실행을 위한 멤버메서드
	@Override
	public void execute() {
		try {

			String query = "INSERT INTO sh_goods VALUES (seq_total_idx.nextval, ?, ?, sysdate, ?)";
			

			psmt= con.prepareStatement(query);
			
			psmt.setString(1, scanValue("상품명"));
			psmt.setString(2, scanValue("상품가격"));
			psmt.setString(3, scanValue("상품코드"));
			
			int affected = psmt.executeUpdate();
			System.out.println(affected + "개 상품이 입력되었습니다.");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		new InsertShop().execute();
	}

}
