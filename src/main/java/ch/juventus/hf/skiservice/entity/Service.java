package ch.juventus.hf.skiservice.entity;

public enum Service {
    SMALL_SERVICE("Kleiner Service"),
    BIG_SERVICE("Grosser Service"),
    RACE_SKI_SERVICE("Rennski-Service"),
    BINDING_MOUNTING_ADJUSTMENT("Bindung montieren und einstellen"),
    SKI_SKIN_CUTTING("Fell zuschneiden"),
    HOT_WAXING("Heisswachen");

    private String label;

    Service(String label) {
        this.label = label;
    }
}
