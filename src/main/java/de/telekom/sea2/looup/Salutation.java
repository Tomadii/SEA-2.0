package de.telekom.sea2.looup;

public enum Salutation {

	FRAU,
	HERR,
	DIVERS;
	
	public static Salutation fromString(String string) {
		switch (string.toUpperCase()) {
			case "FRAU":
			case "F":
				return FRAU;
			case "HERR":
			case "H":
			case "MANN":
			case "M":
				return HERR;
			case "DIVERS":
			case "D":
				return DIVERS;
			default:
				throw new IllegalArgumentException("Unerwarteter Wert: " + string);
		
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
			case FRAU:
				return "Frau";
			case HERR:
				return "Herr";
			case DIVERS:
				return "Divers";
			default:
				throw new IllegalArgumentException("Unerwarteter Fall");
		}
	}
	
}
