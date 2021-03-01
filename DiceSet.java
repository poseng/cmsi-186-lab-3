import java.util.ArrayList;
import java.util.List;

/*
 * A dice set holds a collection of Die objects. All of the die objects have
 * the same number of sides.
 */
public class DiceSet {

    // TODO add fields

    private int sidesOnEachDie;
    private int numberOfDice;
    private Die[] DS = null;
    // private Die Dice;
    // public ArrayList<Die> SetOfDice = new ArrayList<>();

    /**
     * Creates a DiceSet containing the given number of dice, each with the given
     * number of sides. All die values start off as 1. Throws an
     * IllegalArgumentException if either less than two dice were provided or if it
     * is asked to make dice with less than 4 sides.
     */
    public DiceSet(int sidesOnEachDie, int numberOfDice) {
        if (numberOfDice < 2) {
            throw new IllegalArgumentException("At least two dice required");
        } else if (sidesOnEachDie < 4) {
            throw new IllegalArgumentException("Dice must have at least four sides");
        }
        this.sidesOnEachDie = sidesOnEachDie;
        this.numberOfDice = numberOfDice;
        this.DS = new Die[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            // All die values start of as 1.
            this.DS[i] = new Die(sidesOnEachDie, 1);
        }
    }

    /**
     * Creates a DiceSet where each die has the given number of sides, with the
     * given values.
     */
    public DiceSet(int sidesOnEachDie, int... values) {
        // TODO
        int count_dies = values.length;
        if (count_dies < 2) {
            throw new IllegalArgumentException("At least two dice required");
        }
        for (int value : values) {
            if (value < 1 || value > sidesOnEachDie) {
                throw new IllegalArgumentException("Die value not legal for die shape");
            }
        }
        this.numberOfDice = count_dies;
        this.sidesOnEachDie = sidesOnEachDie;
        this.DS = new Die[numberOfDice];
        for (int i = 0; i < numberOfDice; i++) {
            this.DS[i] = new Die(sidesOnEachDie, values[i]);
        }
    }

    /**
     * Returns the descriptor of the dice set; for example "5d20" for a set with
     * five dice of 20 sides each; or "2d6" for a set of two six-sided dice.
     */
    public String descriptor() {
        // TODO
        return numberOfDice + "d" + sidesOnEachDie;
    }

    /**
     * Returns the sum of the values of each die in the set.
     */
    public int sum() {
        // TODO

        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += DS[i].getValue();
        }
        return total;
    }

    /**
     * Rolls all the dice in the set.
     */
    public void rollAll() {
        for (int i = 0; i < DS.length; i++) {
            DS[i].roll();
        }
    }

    /**
     * Rolls the ith die, updating its value.
     */
    public void rollIndividual(int i) {
        // TODO
        // in arraylist, use get to return the element at index ith
        if (i < 0 || i > this.numberOfDice) {
            throw new IllegalArgumentException();
        }
        DS[i].roll();

    }

    /**
     * Returns the value of the ith die.
     */
    public int getIndividual(int i) {
        // TODO
        if (i < 0 || i > numberOfDice - 1) {
            throw new IndexOutOfBoundsException();
        }
        return DS[i].getValue();
    }

    /**
     * Returns the values of each of the dice in a list.
     */
    public List<Integer> values() {
        // TODO
        List<Integer> values = new ArrayList<Integer>();
        // for (int i = 0; i < numberOfDice; i++) {
        // values.add(getIndividual(i));
        // }
        for (int i = 0; i < DS.length; i++) {
            values.add(getIndividual(i));
        }
        return values;
    }

    /**
     * Returns whether this dice set has the same distribution of values as an other
     * dice set. The two dice sets must have the same number of dice and the same
     * number of sides per dice, and there must be the same number of each value in
     * each set.
     */
    public boolean isIdenticalTo(DiceSet diceSet) {
        // This first if statement checks the count and the sides. This is the
        // easy way as in "1)" in the note
        if ((this.numberOfDice != diceSet.numberOfDice) || (this.sidesOnEachDie != diceSet.sidesOnEachDie)) {
            return false; // number of dice and/or number of sides mis-match!
        }

        // This part is a *bit* more complex, but still straightforward:
        // we know now that they are the same size, and same number of faces on each
        // create a new dice set to use for tracking, then iterate through the two sets
        // whenever a match is found, we set THAT particular die to a new instance
        // the new instance has a "tracking value" of 999 sides; this works because
        // in the "Die" class, I initialize the die's value to the number of sides
        // after all the dice in the set have been checked, if they are all equal and
        // accounted for, there should be NO DICE that are NOT "99"s
        for (int i = 0; i < this.numberOfDice; i++) {
            for (int j = 0; j < this.numberOfDice; j++) {
                if (this.getIndividual(i)!= diceSet.getIndividual(j)) {
                    diceSet.DS[j] = new Die(this.sidesOnEachDie, this.sidesOnEachDie);
                }
            }
        }
        for (int i = 0; i < this.numberOfDice; i++) {
            if (diceSet.getIndividual(i) != this.sidesOnEachDie) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a string representation in which each of the die strings are joined
     * without a separator, for example: "[2][5][2][3]".
     */
    @Override
    public String toString() {
        // TODO
        String diceSetString = "";
        for (int i = 0; i < DS.length; i++) {
            diceSetString += DS[i].toString();
        }
        return diceSetString;
    }
}
