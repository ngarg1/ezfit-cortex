package main.analyses;


import main.frame_analyzer.Exercise;

import java.util.ArrayList;

public class FormAnalysis {
    private FormQuality formQuality;
    private Exercise exercise;
    private ArrayList<String> notes;

    public FormAnalysis(Exercise exercise) {
        this.exercise = exercise;
        formQuality = FormQuality.OK;
        notes = new ArrayList<>();
    }

    public FormQuality getFormQuality() {
        return formQuality;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setFormQuality(FormQuality formQuality) {
        this.formQuality = formQuality;
    }

    public void addNotes(String note) {
        notes.add(note);
    }

    @Override
    public String toString() {
        return "FormAnalysis{" +
                "formQuality=" + formQuality +
                ", exercise=" + exercise +
                ", notes=" + notes +
                '}';
    }
}
