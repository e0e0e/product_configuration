package pl.sda.pms.order;

public enum Status {
	TO_DO("FireBrick"), IN_WORK("GoldenRod"), DONE("ForestGreen");

	private String kolor;

	private static Status[] vals = values();

	public Status next() {
		Integer result = this.ordinal() + 1;
		if (result > vals.length - 1) {

			return vals[this.ordinal()];
		}

		return vals[result];
	}

	public Status previous() {
		Integer result = this.ordinal() - 1;
		if (result < 0) {
			return vals[this.ordinal()];
		}

		return vals[result];
	}

	public String getKolor() {
		return kolor;
	}

	Status(String kolor) {

		this.kolor = kolor;
	}

	public void setKolor(String kolor) {

		this.kolor = kolor;
	}
};