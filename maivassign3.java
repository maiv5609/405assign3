import java.util.*;
import java.io.*;


public class maivassign3{
	public static void main(String [] args){
		Scanner	read = new Scanner(System.in);
		String fileName = null;
		ArrayList<sign> main = new ArrayList<sign>();

		int lineNum = 0;
		int minIntr = 0;
		int maxIntr = 0;
		String currLine = null;
		FileReader fileReader = null;
		BufferedReader reader = null;

		ArrayList<sign> signs = new ArrayList<sign>();
		sign curr = null;

		System.out.println("Please enter in file name");
		fileName = read.nextLine();

		try{
			fileReader = new FileReader(fileName);
		}catch(FileNotFoundException ex){
			System.out.println("Unable to open file");
		}finally{
			try{
				//Read in input file and store
				reader = new BufferedReader(fileReader);
				while((currLine = reader.readLine()) != null){
					String split [] = currLine.split("\\s+");
					if(lineNum == 0){
						//intervals
						minIntr = Integer.parseInt(split[0]);
						maxIntr = Integer.parseInt(split[1]);
					}else if (lineNum == 1){
						//positions
						for(int x = 0; x < split.length; x++){
							//Add to arraylist
							curr = new sign(Integer.parseInt(split[x]), x);
							signs.add(curr);
						}
					}else{
						//rev values
						for(int x = 0; x < split.length; x++){
							//Add to arraylist
							curr = signs.get(x);
							int temp = Integer.parseInt(split[x]);
							curr.setRev(x, temp);
						}
					}
					lineNum++;
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		System.out.println("\nMax rev from input file is: " + findMaxRev(signs, minIntr, maxIntr));

	}
	public static int findMaxRev(ArrayList<sign>signs, int minIntr, int maxIntr){
		int diff = 0;
		int next = 0;
		int prevIndex = 0;
		sign curr = null;

		//Create arrays of positions and rev
		int[] positions = new int [signs.size()];
		int[] rev = new int [signs.size()];

		for(int i = 0; i < positions.length; i++){
			curr = signs.get(i);
			positions[i] = curr.getDistance();
			rev[i] = curr.getRev();
		}

		//TODO: delete this
		// for(int i = 0; i < positions.length; i++){
		// 	System.out.print("Position: " + positions[i]);
		// 	System.out.print(" Rev: " + rev[i] + "\n");
		// }

		curr = signs.get(signs.size()-1);
		System.out.println(curr.getDistance());
		int[] MV = new int[curr.getDistance()+1];

		MV[0] = 0;

		//Assign first position
		next = 0;

		//Go through each possible sign position
		//Building table based on minIntr
		for (int x = 1; x < MV.length; x++){
			if(next < MV.length){
				//Check if valid position
				if(positions[next] != x){
					//None add prev rev value
					MV[x] = MV[x-1];
					System.out.println("M[" + x +"]: " + MV[x] + " Prev");
				}else{
					//Check lower constraint
					if(x >= minIntr){
						MV[x] = Math.max(MV[x-minIntr] + rev[next], MV[x-1]);
						prevIndex = x;
						System.out.println("M[" + x +"]: " + MV[x] + " New");
					}else{
						//No billboard placed yet
						MV[x] = rev[next];
						System.out.println("M[" + x +"]: " + MV[x] + " first");
					}
					next++;
				}
			}

		}
		
		//Table built starting from end of table, jump backwards minIntr and look up to maxIntr for value change
		
		
		return 0;
	}

}

/* ------------------------------------- */

/*
Used to store billboard position information
Stores index intially to use for getting rev value
*/
class sign{
	int distance, index, rev;

	sign(int distance, int index){
		this.distance = distance;
		this.index = index;
	}

	public void setRev(int currIndex, int rev){
		if(currIndex == index){
			this.rev = rev;
		}
	}

	public int getDistance(){
		return distance;
	}
	public int getRev(){
		return rev;
	}
}
