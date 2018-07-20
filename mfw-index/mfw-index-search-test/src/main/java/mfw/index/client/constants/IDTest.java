package mfw.index.client.constants;


public enum IDTest{


    ID(8, "test");

    private int value;
    private String displayName;

    private IDTest(int value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public int value() {
        return this.value;
    }

    public String displayName() {
        return this.displayName;
    }
}
