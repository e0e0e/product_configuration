package pl.sda.pms.progres;

public enum Progress {
    TO_DO(0),
    IN_PROGRESS(1),
    DONE(2);

    public int getValue() {
        return value;
    }

    private final  int value;

    Progress(int value) {
        this.value=value;
    }


}
