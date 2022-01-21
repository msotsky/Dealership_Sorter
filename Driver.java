import java.util.*;
import java.io.*;
import java.text.NumberFormat;

/**
 * Driver class to test exceptions and the creations of binary files
 * 
 * @author Maxime Sotsky
 */

public class Driver{

	/**
	 * @param args -the arguments passed to main method (invenotory dat file and out dat file
	 * 	to be created representing a binary files to be later used for merge sort)
	 */

	public static void main(String[] args){
		try{
			File fIn = new File(args[0]);
			File fOut = new File(args[1]);

			NumberFormat form = NumberFormat.getCurrencyInstance();

			FileOutputStream out = new FileOutputStream(fOut);
			ObjectOutputStream objOut = new ObjectOutputStream(out);

			Scanner scan = new Scanner(fIn);
	
			String name = scan.nextLine();
			int size = scan.nextInt();
			scan.nextLine();
			Vehicle[] inventory = new Vehicle[size];
			for(int i = 0; i < size; i++){
				try{
					String line = scan.nextLine();
					Scanner sc = new Scanner(line);
					String code = sc.next();
					int mileage = sc.nextInt();
					Vehicle temp;
					if(code.charAt(0) == 'C'){
						int doors = sc.nextInt();
						char type = sc.next().charAt(0);
						temp = new Car(code, mileage, type, doors);
					}
					else if(code.charAt(0) == 'T'){
						String bed = sc.next();
						temp = new Truck(code, mileage, bed);
							
					}
					else{
						String bool = sc.next();
						boolean isElect = false;
						if(bool.equals("true")){
							isElect = true;
						}
						temp = new Van(code, mileage, isElect);
					}
					inventory[i] = temp;
					sc.close();
				}catch(InvalidVehicleException e){
					System.out.println(e.getMessage());
					size--;
					i--;
					inventory = Arrays.copyOf(inventory,inventory.length-1);
				}
				
			}	
			Dealership dealer = new Dealership(name, inventory);
	
			System.out.println(dealer);
	
			System.out.println("\nSorted Data:\n");
			
			Sorter<Vehicle> sort = new Sorter<Vehicle>();
			Vehicle[] copy = dealer.getInventory();

			sort.selectionSort(copy);
	
			System.out.println(dealer.getName());
			
			for(Vehicle v : copy){
				System.out.println(v.getCode() + "\t" + form.format(v.calculatePrice()) + "\n");
				objOut.writeObject(v);
			}
	
			while(scan.hasNext()){
				String searchCode = scan.nextLine();
				System.out.println("Vehicle " + ((dealer.search(searchCode) == null)?"not":"") + " found");

			}
		scan.close();
		out.close();
		}catch(FileNotFoundException e){
			System.out.println("Not able to access file.");
		}catch(IOException e){
			System.out.println("Problem reading from file.");
		}
	}

		
}