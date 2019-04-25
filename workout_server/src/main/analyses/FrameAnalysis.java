package main.analyses;

public class FrameAnalysis {
    private FormAnalysis formAnalysis;
    private boolean isNewRep;

    public FrameAnalysis(FormAnalysis formAnalysis, boolean isNewRep) {
        this.formAnalysis = formAnalysis;
        this.isNewRep = isNewRep;
    }

    public FormAnalysis getFormAnalysis() {
        return formAnalysis;
    }

    public boolean isNewRep() {
        return isNewRep;
    }

    @Override
    public String toString() {
        return "FrameAnalysis{" +
                "formAnalysis=" + formAnalysis +
                ", isNewRep=" + isNewRep +
                '}';
    }
}
