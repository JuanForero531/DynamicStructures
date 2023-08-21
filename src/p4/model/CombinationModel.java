package p4.model;


import java.util.HashSet;
import java.util.Set;

 public class CombinationModel {
    public Set<Set<String>> generatePowerSet(String input) {
        String[] elements = input.split(",");
        Set<String> set = new HashSet<>();
        for (String element : elements) {
            set.add(element.trim());
        }
        return calculatePowerSet(set);
    }

    private Set<Set<String>> calculatePowerSet(Set<String> set) {
        Set<Set<String>> powerSet = new HashSet<>();
        powerSet.add(new HashSet<>()); // Add the empty set

        for (String element : set) {
            Set<Set<String>> tempSet = new HashSet<>();
            for (Set<String> subset : powerSet) {
                Set<String> newSubset = new HashSet<>(subset);
                newSubset.add(element);
                tempSet.add(newSubset);
            }
            powerSet.addAll(tempSet);
        }

        return powerSet;
    }
}
