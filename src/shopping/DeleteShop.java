package shopping;

import java.sql.Types;

public class DeleteShop extends IConnectImpl{

	public DeleteShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {

			csmt = con.prepareCall("{call ShopDeleteGoods(?,?)}");

			csmt.setString(1, scanValue("삭제할 상품 일련번호"));

			csmt.registerOutParameter(2, Types.NUMERIC);

			csmt.execute();

			System.out.println(csmt.getString(2) + "개 상품을 삭제했습니다.");				
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	public static void main(String[] args) {
		new DeleteShop().execute();
	}

}
