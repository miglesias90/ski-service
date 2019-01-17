package ch.juventus.hf.skiservice.entity;

public enum State {
    PENDING("PEndent"),
    DONE("Abgeschlossen");

    private String label;

    State(String label){
        this.label = label;
    }
}
