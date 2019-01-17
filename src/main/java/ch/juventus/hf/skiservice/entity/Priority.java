package ch.juventus.hf.skiservice.entity;

public enum Priority {

    LOW("Tief"),
    STANDARD("Standard"),
    EXPRESS("Express");

    private String label;

    Priority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
