import java.io.IOException;

public class ShortestPath {
	Map cityMap;
	
	public ShortestPath (String filename) {
		try {
			cityMap = new Map(filename);
		}
		catch (IOException e) {
			System.out.println("You did not enter a valid file name.");
		}
	}
	
	public static void main (String[] args) {
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file.");
			System.exit(0);
		}
		String mapFileName = args[0];
		
		try {
			ShortestPath input = new ShortestPath(mapFileName);
			DLList<MapCell> list = new DLList<MapCell>();		
			
			//ALGORITHM HERE
			list.insert(input.cityMap.getStart(), 0);
			input.cityMap.getStart().isMarkedInList();
			boolean destReached = false;
			MapCell S;
			MapCell C;
			int D;
			int P;
			
			while (!list.isEmpty() && destReached == false) {
				
				boolean done = false;
				S = list.getSmallest();
				S.isMarkedOutList();
				if (S.isCustomer()) {
					destReached = true;
					break;
				}
				while (done == false) {
					C = input.nextCell(S);
					if (C == null) done = true;
					else {
						D = 1 + S.getDistanceToStart();
						
						if (C.getDistanceToStart() > D) {
							C.setDistanceToStart(D);
							C.setPredecessor(S);
						}
						
						P = C.getDistanceToStart();
						if (C.isMarkedInList() && P < list.getDataValue(C)) {
							list.changeValue(C, P);
						}
						if (!C.isMarkedInList()) {
							list.insert(C, P);
							C.markInList();
						}
					}
					
				}
				
			}
			if (destReached == true) {
				System.out.println("Customer reached! The shortest path contains ___"+" cells.");
			}
			
		}
		catch (Exception e) {
			System.out.println("yoyoyo exception lol");
			System.out.println(e);
		}
	}
	
	//do try catch for null pointer exception
	private MapCell nextCell(MapCell cell) {

		for (int i = 0; i < 4; i++) {			
			//top and bottom
			if (i%2 == 0 && cell.getNeighbour(i) != null) {
				if (!cell.getNeighbour(i).isMarkedInList() && (cell.isVerticalSwitch() || cell.isOmniSwitch() || cell.isPowerStation())) {
					if (cell.getNeighbour(i).isVerticalSwitch() || cell.getNeighbour(i).isOmniSwitch() || cell.getNeighbour(i).isCustomer()) {
						return cell.getNeighbour(i);					
					}
				}
			}
			
			//Left and Right
			if (i%2 != 0 && cell.getNeighbour(i) != null) {
				if (!cell.getNeighbour(i).isMarkedInList() && (cell.isHorizontalSwitch() || cell.isOmniSwitch() || cell.isPowerStation())) {
					if (cell.getNeighbour(i).isHorizontalSwitch() || cell.getNeighbour(i).isOmniSwitch() || cell.getNeighbour(i).isCustomer()) {
						return cell.getNeighbour(i);
					}
				}
			}
			
		}
		return null;
	}
	
}
