import java.awt.EventQueue;

public class Example {
	public static void main(String[]args) {
		AirportSimulatorGUI obj = new AirportSimulatorGUI();
		synchronized(obj) {
		try {
			obj.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Number of Flights of Type 1:"+obj.getNF1()+"\nBoarding Time 1:"+obj.getBT1()+"\nRunway Time 1:"+obj.getRT1());
		System.out.println("Number of Flights of Type 2:"+obj.getNF2()+"\nBoarding Time 2:"+obj.getBT2()+"\nRunway Time 2:"+obj.getRT2());
		System.out.println("Number of Flights of Type 3:"+obj.getNF3()+"\nBoarding Time 3:"+obj.getBT3()+"\nRunway Time 3:"+obj.getRT3());
		}
		OutputGUI obj1 = new OutputGUI(4,5,1);
		double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };
		Graph obj2 = new Graph(data);
	}
}
