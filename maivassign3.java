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
						System.out.println("Min interval: " + minIntr);
						System.out.println("Max interval: " + maxIntr);
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

					//Plan: fill out table of max value up to that position and go from endpoint back to find solution(?)
				}
				//TODO: remove this, test print
				// for(int x = 0; x < 7; x++){
				// 	curr = signs.get(x);
				// 	System.out.print(curr.getDistance() + " " + curr.getRev() + " \r");
				// }
			//	sortbyFinish(main);
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
}

/* ------------------------------------- */

/*
Used to store billboard position information
Stores position intially to use for getting rev value
*/
class sign{
	int distance, position, rev;

	sign(int distance, int position){
		this.distance = distance;
		this.position = position;
	}

	public void setRev(int currPosition, int rev){
		if(currPosition == position){
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
