package domain;

public enum Kind {
    
    PERSON(1),
    ENTITY(2),
    SENSOR(3);
    
    private int value;

    private Kind(int value) {
	this.setValue(value);
    }

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
    }
}
