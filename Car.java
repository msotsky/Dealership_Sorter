/**
Represents a car.
@author Leah Bidlake
@editor Maxime Sotsky -throwing InvalidVehicleException
*/
public class Car extends Vehicle{
	
	/**
	The type of trunk.
	*/
	private char type;

	/**
	The number of doors the car has.
	*/
	private int doors;

	/**
	Constructs a Car object.
	@param code the code for the car.
	@param mileage the initial mileage on the car.
	@param type the type of car trunk.
	@param doors the number of doors the car has.

	@throws InvalidVehicleException if type or doors has invalid values
	*/
	public Car(String code, int mileage, char type, int doors) throws InvalidVehicleException{

		super(code, mileage);

		if(type != 'T' && type != 'H')
			throw new InvalidVehicleException(type + " is not a valid type.");
		if(doors != 2 && doors != 4)
			throw new InvalidVehicleException(doors + " is not a valid door number.");
		else{
			this.type = type;
			this.doors = doors;
		}
	}

	/**
	Calculates the cost of the vehicle.
	@return the calcuated cost of the vehicle.
	*/
	public double calculatePrice(){
		double cost = 10000;
		if(doors == 4){
			cost += cost * 1.05;
		}
		if(type == 'H'){
			cost += 1000;
		}
		return cost;
	}

}