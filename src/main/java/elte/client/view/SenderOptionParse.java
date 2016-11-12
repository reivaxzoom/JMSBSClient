package elte.client.view;

/**
 *
 * @author Xavier
 */
public class SenderOptionParse extends OptionParser {

	private int shoppingCars;
	private int itemsPerCar;
    static final Option numShopCars = new Option("c",
            "carts",
            "Specify the number of shopping cars to be generated",
            "2",
            "1",
            Integer.class);

    static final Option numItemsPerCar = new Option("i",
            "items",
            "Number of items inside the shopping cart",
            "6",
            "5",
            Integer.class);



    static {
    	addOption(numItemsPerCar);
    	addOption(numShopCars);
        addOption(CONFIG);
//        addOption(BROKER);
//        addOption(HELP);
//        addOption(TIMEOUT);
//        addOption(CON_OPTIONS);
//        addOption(BROKER_OPTIONS);
    }

    public SenderOptionParse(String[] args, String usage, String desc) throws Exception {
        super(args, usage, desc);

         shoppingCars = Integer.parseInt(getOp(numShopCars));
         itemsPerCar = Integer.parseInt(getOp(numItemsPerCar));
    }

	public int getShoppingCars() {
		return shoppingCars;
	}

	public void setShoppingCars(int shoppingCars) {
		this.shoppingCars = shoppingCars;
	}

	public int getItemsPerCar() {
		return itemsPerCar;
	}

	public void setItemsPerCar(int itemsPerCar) {
		this.itemsPerCar = itemsPerCar;
	}
    
    
}
