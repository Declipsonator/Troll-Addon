package me.declipsonator.trolladdon.AlternativeClasses;

import java.util.Arrays;

public class AlternativeObject {
    String name;
    String[] altNames;
    String[] altDescriptions;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String[] getAltNames() {
        return altNames;
    }
    public void setAltNames(String[] altNames) {
        this.altNames = altNames;
    }
    public String[] getAltDescriptions() {
        return altDescriptions;
    }
    public void setAltDescriptions(String[] altDescriptions) {
        this.altDescriptions = altDescriptions;
    }

    @Override
    public String toString() {
        return "Module {name=" + name + ", altNames=" + Arrays.toString(altNames) + ", altDescriptions=" + Arrays.toString(altDescriptions) + "}";
    }
}
