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

    public Progress getNext() {
        return this.ordinal() < Progress.values().length - 1 ? Progress.values()[this.ordinal() + 1] : null;
    }
    public Progress getPrevious() {
        return this.ordinal() > 0 ? Progress.values()[this.ordinal() - 1] : null;
    }

}
