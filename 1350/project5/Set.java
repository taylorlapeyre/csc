/**
 * @file Set.java
 * @author Taylor Lapeyre
 * @date 2012/4/15
 * Description: The class file for a Set object
 * Course: CS1350.s2
 * PAWS ID: tlapey1
 * Project #: 5
 * Instructor: Duncan
 */
 
import java.util.*;

public class Set {

    private ArrayList<String> elements;

    /**
     * creates an empty set
     */
    public Set() {
        elements = null;
    }

    /**
     * creates a set using the elements of the ArrayList s.
     * @param s the ArrayList whose elements are used to
     *        create this set.
     * @throws IllegalArgumentException if s contains duplicity.
     */
    public Set(ArrayList<String> s) {
        int i;

        elements = new ArrayList<String>();

        for(i = 0; i < s.size(); i++) {
            if ( elements.contains(s.get(i)) ) {
                throw new IllegalArgumentException("Set(ArrayList<String>):duplicity not allowed in sets");
            }
            elements.add(s.get(i));
        }
    }

    /**
     * creates a set using the elements of the array s.
     * @param s the array whose elements are used to
     *        create this set.
     * @throws IllegalArgumentException if s contains duplicity.
     */
    public Set(String[] s) {
        int i;

        elements = new ArrayList<String>();

        for(i = 0; i < s.length; i++) {
            if (elements.contains(s[i])) {
                throw new IllegalArgumentException("Set(String[]):duplicity not allowed in sets");
            }
            elements.add(s[i]);
        }
    }

    /**
     * determines whether a set contains the specified element
     * @param elt an element
     * @return true if elt is an element of this set; otherwise, false
     */
    public boolean isElement(String elt) {
        return elements.contains(elt);
    }

    /**
     * determines the size of this set.
     * @return the size of this set.
     */
    public int cardinality() {
        return elements.size();
    }

    /**
     * computes the intersection of this set and the
     * specified set.
     * @param s a set
     * @return a set representing the intersection of this set
     *          and s.
     */
    public Set intersect(Set s) {
        int i;
        ArrayList<String> result = new ArrayList<String>();

        for (i = 0; i < s.cardinality(); i++) {
            if ( this.isElement(s.elements.get(i)) ) {
                result.add(s.elements.get(i));
            }
        }

        return new Set(result);             
    }

    /**
     * computes the union of this set and the specified set.
     * @param s a sets
     * @return a set representing the union of this set
     *          and s.
     */
    public Set union(Set s) {
        ArrayList<String> result = new ArrayList<String>();


        for (String elt : elements) {
            result.add(elt);
        }

        for (int i = 0; i < s.cardinality(); i++) {
            if (s.isEmpty()) {
                break;
            } else if (!result.contains(s.elements.get(i))) {
                result.add(s.elements.get(i));
            }
        }

        return new Set(result);
    }

    /**
     * computes the difference between this set and the
     * specified set.
     * @param s a set
     * @return a set representing the difference between
     *          this set and s.
     */
    public Set diff(Set s) {
        ArrayList<String> result = new ArrayList<String>();

        if (s.isEmpty()) {
            return new Set(elements);
        } else if (elements.isEmpty()) {
            return new Set(s.elements);
        }

        for (String str : elements) {
            if ( !s.isElement(str) && !result.contains(str) )
                result.add(str);
        }


        return new Set(result);
    }
    
    /**
     * computes the symmetric difference between this set
     * and the specified set.
     * @param s a set
     * @return a set representing the symmetrix difference
     *          between this set and s.
     */
    public Set symDiff(Set s) {
        Set result = new Set();
        Set tmp1 = new Set();
        Set tmp2 = new Set();
        Set elementSet = new Set(elements);

        tmp1 = s.diff(elementSet);
        tmp2 = elementSet.diff(s);

        result = tmp1.union(tmp2);

        return result;
    }

    /**
     * computes the Cartesian product for this set
     * and the specified set.
     * @param s a set
     * @return a set representing the Cartesian product
     *          of this set and s.
     */
    public Set xProduct(Set s) {
        //implement this method
        return new Set();
    }


    /**
     * determines whether a set is empty
     * @return true if this set is empty; otherwise, false
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * determines whether this set is equal to the specified
     * set.
     * @param s a set
     * @return true if this set is equal to s; otherwise, false
     */
    public boolean equals(Set s) {
        boolean result = false;

        if (this.diff(s).cardinality() == 0) {
            result = true;
        }

        return result;
    }

    /**
     * determines whether this set is a subset of the specified set.
     * @param s a set
     * @return true if this set is a subset of s; otherwise, false
     */
    public boolean subset(Set s) {
        boolean result = false;

        for (String element : elements) {
            if (s.isElement(element)) {
                result = true;
            } else {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     * determines whether this set is a proper subset of the specified set.
     * @param s a set
     * @return true if this set is a proper subset of s; otherwise, false
     */
    public boolean properSubset(Set s) {
        boolean result = false;

        if ( !(s.equals(this.elements)) ) {
            for (String element : elements) {
                if (s.isElement(element)) {
                    result = true;
                } else {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }
    
    /**
     * returns a string {x1,x2,...,xn} representing this set,
     * where x1,x2,...,xn are elements of this set.
     * @return a string representation of this set formatted
     *          as specified.
     */
    public String toString() {
        String result = "";

        if (this.isEmpty()) {
            result = "{}";
        } else {
            result = "{";
        }

        for (int i = 0; i < this.elements.size(); i++) {         
            if (i == this.elements.size()-1) {
                result += elements.get(i) + "}";
            } else {
                result += elements.get(i) + ", ";
            }
        }

        return result;
    }    
}