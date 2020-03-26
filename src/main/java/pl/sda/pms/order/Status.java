package pl.sda.pms.order;

public enum Status {
    TO_DO, IN_WORK, DONE;


    private static Status[] vals = values();
    public Status next()
    {
        return vals[(this.ordinal()+1) % vals.length];
    }
	public Status previous() {
		return vals[Math.abs((this.ordinal()-1) % vals.length)];
	}


};