package elte.client.view;


public class ReceiverOptionParse extends OptionParser{
   
	private long timeout;
	private boolean forever;
    static final Option FOREVER = new Option("f",
            "forever",
            "ignore timeout and wait forever",
            null,
            null,
            Boolean.class);

    static final Option COUNT = new Option ("c",
            "count",
            "read c messages, then exit",
            "COUNT",
            "0",
            Integer.class);
                                                

    static 
    {        
    	addOption(COUNT);
    	addOption(FOREVER);
        addOption(TIMEOUT);
//        addOption(HELP);
//        addOption(BROKER);
//        addOption(CON_OPTIONS);
//        addOption(BROKER_OPTIONS);
    }
    
    public ReceiverOptionParse(String[] args, String usage, String desc) throws Exception
    {   
        super(args, usage, desc);        
        timeout = Long.parseLong(getOp(TIMEOUT));
        forever = Boolean.parseBoolean(getOp(FOREVER));
    }

	

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}



	public boolean isForever() {
		return forever;
	}

	public void setForever(boolean forever) {
		this.forever = forever;
	}
   
//    public static void main(String[] args) throws Exception
//    {
//        String u = "Usage: drain [OPTIONS] 'ADDRESS'";
//        String d = "Drains messages from the specified address."; 
//         args=new String[]{"requestQueue"};
//         
//        new Receiver(args,u,d);        
//}
    
    
}
