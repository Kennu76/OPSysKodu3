import java.util.*;

/**
 * Created by alk_ on 10/26/16.
 * Funktsioonide klass on avalik aga inimesed kellele see jagatud on teadlikud, et kopeerimine on akadeemiline petturlus
 * Kirjutas Ott Oopkaup
 */
public class Functions {


	//Helping methods
	private static List<String> chars() {
		List<String> chars = new ArrayList();
		chars.addAll(Arrays.asList(new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"}));
		return chars;
	}

	private static String[][] genEmpty(){
		String[][] out = new String[10][51];

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 51; j++) {
				out[i][j] = "-";
			}
		}

		return out;
	}

	private static int[][] parse(String in){
		int[][] out = new int[10][2];
		String[] processes = in.split("[;]");

		for (int i = 0; i < processes.length; i++) {
			out[i][0] = Integer.parseInt(processes[i].split("[,]")[0]);
			out[i][1] = Integer.parseInt(processes[i].split("[,]")[1]);
		}

		return out;
	}

	//firstFit implementation
	//Since alot of the code is the same, most of the comments will be on this functions
	public static String[][] firstFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		String[] mem = new String[51]; //memory is used as a buffer to power the logic behind each algorithm
		int[] addr = new int[10]; //address is used to indicate the relative position of each process in the buffer

		for (int i = 0; i < processes.length; i++) {
			//clear memory
			for (int d = 0; d < 51; d++) {
				mem[d] = "-";
			}
			//adds the process metadata to the buffer
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]);
			for (int j = 0; j <= i; j++) {
				//Delete old processes and write the ones still running
				//actually doesnt delete them, just doesnt write old processes
				if(j<i){
					if(processes[j][1] >= 1){
						for (int k = 0; k < processes[j][0]; k++) {
							mem[addr[j]+1+k] = chars.get(j);
						}
					}
				}else{ //Find first free memory where it fits, if it is a new process
					for (int k = 0; k < 50; k++) {
						boolean flag = true;
						//Flagged loop will see if it finds a gap large enough
						for (int l = 0; l < processes[i][0]; l++) {
							if(! mem[k+l+1].equals("-")){
								flag = false;
								break;
							}
						}
						//if succesful will index the new process
						if(flag){
							addr[j] = k;
							break ;
						}
					}
					//Finally the new process is written into the memory at
					for (int k = 0; k < processes[i][0]; k++) {
						try {
							mem[addr[j] + k + 1] = chars.get(j);
						}catch (Exception e){
							//the catch block sees if the index runs over 51, if it does throws a null into output
							//and terminates, the null block is caught by the GUI method createNewPane
							out[i] = null;
							return out;
						}
					}
				}
				//Remember to decrement each process so it lasts the correct length
				processes[j][1]--;
			}
			//Since java is finnicky with assingments, unless copied will pass references and
			//output will all look like the last line of mem
			out[i] = Arrays.copyOf(mem,mem.length);
		}
		return out;
	}

	//should work
	public static String[][] worstFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		String[] mem = new String[51];
		int[] addr = new int[10];

		for (int i = 0; i < processes.length; i++) {
			//clear memory
			for (int d = 0; d < 51; d++) {
				mem[d] = "-";
			}
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]);
			for (int j = 0; j <= i; j++) {
				//Delete old processes and write the ones still running
				if(j<i){
					if(processes[j][1] >= 1){
						for (int k = 0; k < processes[j][0]; k++) {
							mem[addr[j]+1+k] = chars.get(j);
						}
					}
				}else{ //Find largest free memory where it fits, if it is a new process
					//Key is the size, value will be index
					HashMap<Integer,Integer> map = new HashMap<>();
					loop:
					for (int k = 0; k < 50; k++) {
						if(mem[k].equals("-")){
							for (int l = 0; l < 50-k; l++) {
								if(!mem[k+l].equals("-")){
									map.put(l,k-1);
									k+= l-1;
									continue loop;
									//important to continue so as the code below wont get executed
								}
							}
							//if the loop terminates because it ran to the end, write the K,V pair appropriate
							map.put(50-k,k-1);
							break;
						}

					}

					int size = 0;
					//Finds the largest block of unallocated memory and writes it'index into the index array
					for (Integer integer : map.keySet()) {
						if(integer > size){
							size = integer;
							addr[j] = map.get(integer);
						}
					}

					//write into output
					for (int k = 0; k < processes[i][0]; k++) {
						try {
							mem[addr[j] + k + 1] = chars.get(j);
						}catch (Exception e){
							out[i] = null;
							return out;
						}
					}
				}
				processes[j][1]--;
			}
			out[i] = Arrays.copyOf(mem,mem.length);
		}
		return out;
	}
	//should work
	public static String[][] bestFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		String[] mem = new String[51];
		int[] addr = new int[10];

		for (int i = 0; i < processes.length; i++) {
			//clear memory
			for (int d = 0; d < 51; d++) {
				mem[d] = "-";
			}
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]);
			for (int j = 0; j <= i; j++) {
				//Delete old processes and write the ones still running
				if(j<i){
					if(processes[j][1] >= 1){
						for (int k = 0; k < processes[j][0]; k++) {
							mem[addr[j]+1+k] = chars.get(j);
						}
					}
				}else{ //Find smallest free memory where it fits, if it is a new process
					//Key is the size, value will be index
					//Reused code from worstfit, just now will check for minimal size
					HashMap<Integer,Integer> map = new HashMap<>();
					loop:
					for (int k = 0; k < 51; k++) {
						if(mem[k].equals("-")){
							for (int l = 0; l < 51-k; l++) {
								if(!mem[k+l].equals("-")){
									map.put(l,k-1);
									k+= l;
									continue loop;
								}
							}
							map.put(51-k,k-1);
							break;
						}

					}

					int size = 50;
					//checks for the smallest memory block that can be used to allocate this process
					for (Integer integer : map.keySet()) {
						if(integer < size && integer >= processes[i][0]){
							size = integer;
							addr[j] = map.get(integer);
						}
					}

					//write into output
					for (int k = 0; k < processes[i][0]; k++) {
						try {
							mem[addr[j] + k + 1] = chars.get(j);
						}catch (Exception e){
							out[i] = null;
							return out;
						}
					}
				}
				processes[j][1]--;
			}
			out[i] = Arrays.copyOf(mem,mem.length);
		}
		return out;
	}

	public static String[][] randomFit(String in){
		String[][] out = genEmpty();
		int[][] processes = parse(in);
		List<String> chars = chars();
		String[] mem = new String[51];
		int[] addr = new int[10];

		for (int i = 0; i < processes.length; i++) {
			//clear memory
			for (int d = 0; d < 51; d++) {
				mem[d] = "-";
			}
			mem[0] = Integer.toString(processes[i][0]) + "," + Integer.toString(processes[i][1]);
			for (int j = 0; j <= i; j++) {
				//Delete old processes and write the ones still running
				if(j<i){
					if(processes[j][1] >= 1){
						for (int k = 0; k < processes[j][0]; k++) {
							mem[addr[j]+1+k] = chars.get(j);
						}
					}
				}else{ //Find smallest free memory spaces where it fits, then select one at random and go for it
					//Key is the size, value will be index
					HashMap<Integer,Integer> map = new HashMap<>();
					loop:
					for (int k = 0; k < 51; k++) {
						if(mem[k].equals("-")){
							for (int l = 0; l < 51-k; l++) {
								if(!mem[k+l].equals("-")){
									map.put(l,k-1);
									k+= l;
									continue loop;
								}
							}
							map.put(51-k,k-1);
							break;
						}

					}
					//Copies all the K,V pairs that are big enough to allocate process
					HashMap<Integer,Integer> rMap = new HashMap<>();
					for (Integer integer : map.keySet()) {
						if(integer >= processes[j][0]){
							rMap.put(integer,map.get(integer));
						}
					}
					//if you dont check for null now risk a chance where it throws a runtime error
					//or it allocates the index as 0 and just overwrites the buffer
					if(rMap.size() == 0){
						out[i] = null;
						return out;
					}else{
						Random r = new Random();
						//Korralik oneliner
						//Selects random index, basically converts keyset into array and then selects
						//a random one from the keyset and returns the value of that key in rMap
						Integer indx = rMap.get((Integer)rMap.keySet().toArray()[r.nextInt(rMap.size())]);
						addr[j] = indx;
					}

					//write into output
					for (int k = 0; k < processes[i][0]; k++) {
						try {
							mem[addr[j] + k + 1] = chars.get(j);
						}catch (Exception e){
							out[i] = null;
							return out;
						}
					}
				}
				processes[j][1]--;
			}
			out[i] = Arrays.copyOf(mem,mem.length);
		}
		return out;
	}

}
