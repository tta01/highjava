package prt;

	class Ngc {
		
		private Object val;

		public Object getVal() {
			return val;
		}

		public void setVal(Object val) {
			this.val = val;
		}
	}
	class Mg<T> { // 어떤 타입을 쓸지 모르는 상태이기 때문에 T
		private T val;

		public T getVal() {
			return val;
		}

		public void setVal(T val) {
			this.val = val;
		}
	}
public class Gnc {
	public static void main(String[] args) { // 메인 클래스 안에만(?) 메인메소드가 올 수 있음!! 까먹지마 제발
		
		Ngc ng = new Ngc();
		ng.setVal("비 오지마라");
		
		Ngc ng2 = new Ngc();
		ng2.setVal(300);
		
		String rtnVal1 = (String)ng.getVal(); // 타입 꼭 써
		System.out.println("문자열 반환값 : " + rtnVal1);
				
		Mg<String> mg1 = new Mg<String>();
		
		mg1.setVal("집에 가고 싶다...");
		
		rtnVal1 = mg1.getVal();
		System.out.println("제너릭 문자열 : " + rtnVal1 );
		
		
	}

}