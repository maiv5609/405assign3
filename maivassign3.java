import java.util.*;
import java.io.*;

public class maivassign3{
	public static void main(String [] args){
		Scanner	read = new Scanner(System.in);
		String fileName = null;
		ArrayList<position> main = new ArrayList<position>();

		int lineNum = 0;
		int minIntr = 0;
		int maxInter
		String currLine = null;
		String currTok = null;
		FileReader fileReader = null;
		BufferedReader reader = null;
		StringTokenizer tokens = null;

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
					//Break each line into token
					tokens = new StringTokenizer(currLine, " ");
					while(tokens.hasMoreTokens()){
						currTok = tokens.nextToken();
						if(lineNum == 0){
							//intervals
							String split[] = currTok.split(" ", 2);
						}else if (lineNum == 1){
							//positions
						}else{
							//values
						}
						lineNum++;

						//Plan: fill out table of max value up to that position and go from endpoint back to find solution(?)

						//Parse number from tokens

						firstHalf = split[0].substring(1);
						secondHalf = split[1].substring(0, split[1].length()-1);
						firstNum = Double.parseDouble(firstHalf);
						secondNum = Double.parseDouble(secondHalf);

						//Add to arraylist
						curr = new intPair(firstNum, secondNum);
						main.add(curr);
					}
				}

				sortbyFinish(main);
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	/*
	Takes in ArrayList of intPair and sorts intervals by finishing time
	Sorts using simple bubblesort
	 */
	public static void sortbyFinish(ArrayList<intPair> intrList){
		int end = intrList.size();
		intPair temp;
		int i = 0;
		boolean swapped = true;

		while(swapped == true && i < (end-1)){
			swapped = false;
			for(int j = 0; j< end-i-1; j++){
					if(intrList.get(j).getEnd() > intrList.get(j+1).getEnd()){
						//bigger, swap
						temp = intrList.get(j);
						intrList.set(j, intrList.get(j+1));
						intrList.set(j+1, temp);
						swapped = true;
					}
			}
			i++;
		}
	}

}

/*
Used to store billboard position information
Stores position intially to use for getting rev value
*/
class position{
	int distance, position, rev;

	intPair(double distance, int position){
		this.distance = distance;
		this.position = position;
	}

	public void setRev(int currPosition, int rev){
		if(currPosition == position){
			this.rev = rev;
		}
	}

	public double getDistance(){
		return distance;
	}
	public double getRev(){
		return rev;
	}
}
