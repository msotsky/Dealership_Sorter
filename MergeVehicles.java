import java.io.*;
/**
 * MergeVehicles reads in 2 binary files that hold sorted vehicle data
 * outputs the merged and sorted binary files to terminal.
 * 
 * @author Maxime Sotsky
 */
public class MergeVehicles {
    /**
     * @param args - arguments for main method
     * takes in 2 binary files representing sorted vehicle list in an invenotory
     * merges them and sorts them -diplayed to the terminal.
     * 
     * @throws IllegalArgumentException if not able to access files
     * @throws EOFException - if the end of the binary file is reached
     * @throws IOException - if trouble reading binary files
     * @throws ClassNotFoundException -if data does not match Vehicle object
     */
    public static void main(String[] args){
        try{
            File f1 = new File(args[0]);
            File f2 = new File(args[1]);

            FileInputStream in1 = new FileInputStream(f1);
            FileInputStream in2 = new FileInputStream(f2);

            ObjectInputStream objIn1 = new ObjectInputStream(in1);            
            ObjectInputStream objIn2 = new ObjectInputStream(in2);

            boolean eof1 = false;
            boolean eof2 = false;

            Vehicle temp1 = (Vehicle) objIn1.readObject();
            Vehicle temp2 = (Vehicle) objIn2.readObject();

            while(!eof1 && !eof2){

                if(temp1.compareTo(temp2) < 0){
                    System.out.println(temp1);
                    try{
                        temp1 = (Vehicle) objIn1.readObject();
                    }catch(EOFException e){
                        eof1 = true;
                        while(!eof2){
                
                            try{
                                System.out.println(temp2);
                                temp2 = (Vehicle) objIn2.readObject();
                            }catch(EOFException e2){
                                eof2 = true;
                            }
                        }
                    }
                }else{
                    System.out.println(temp2);
                    try{
                        temp2 = (Vehicle) objIn2.readObject();
                    }catch(EOFException e){
                        eof2 = true;
                        while(!eof1){
                            try{
                                System.out.println(temp1);
                                temp1 = (Vehicle) objIn1.readObject();
                            }catch(EOFException e2){
                                eof1 = true;
                            }
                        }
                    }
                }
            }
            objIn1.close();
            objIn2.close();
        }catch(FileNotFoundException e){
		    System.out.println("Not able to access file.");
	    }catch(IOException e){
		    System.out.println("Problem reading from file.");
	    }catch(ClassNotFoundException e){
		    System.out.println("Class not found");
	    }
    }

}
