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
	
	public static Salutation fromShort(Short s ) {
		switch ( s ) {
			case 1:
				return FRAU;
			case 2:
				return HERR;
			case 3:
				return DIVERS;
			default:
				throw new IllegalArgumentException("Unerwarteter Wert: " + s);
		
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
	
	public short toShort() {
		switch (this) {
		case FRAU:
			return 1;
		case HERR:
			return 2;
		case DIVERS:
			return 3;
		default:
			throw new IllegalArgumentException("Unerwarteter Fall");
		}
	}

}
