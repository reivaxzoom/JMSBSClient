package elte.client.view;

/**
 *
 * @author Xavier
 */
public class DefaultOptionParse extends OptionParser {

	private int shoppingCars;
	private int itemsPerCar;
    static final Option sender = new Option("sender",
            null,
            "sender Operations",
            null,
            null,
            String.class);

    static final Option receiver = new Option("receiver",
            null,
            "receiver operations",
            null,
            null,
            String.class);




    static {
    	addOption(receiver);
    	addOption(sender);
    }

    public DefaultOptionParse(String[] args, String usage, String desc) throws Exception {
        super(args, usage, desc);

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
