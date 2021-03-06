// BuiltIn.java -- the data structure for function closures

// Class BuiltIn is used for representing the value of built-in functions
// such as +.  Populate the initial environment with
// (name, new BuiltIn(name)) pairs.

// The object-oriented style for implementing built-in functions would be
// to include the Java methods for implementing a Scheme built-in in the
// BuiltIn object.  This could be done by writing one subclass of class
// BuiltIn for each built-in function and implementing the method apply
// appropriately.  This requires a large number of classes, though.
// Another alternative is to program BuiltIn.apply() in a functional
// style by writing a large if-then-else chain that tests the name of
// of the function symbol.

class BuiltIn extends Node {
private Node symbol;

public BuiltIn(Node s) {
        symbol = s;
}

public Node getSymbol() {
        return symbol;
}

// TODO: The method isProcedure() should be defined in
// class Node to return false.
public boolean isProcedure() {
        return true;
}

public void print(int n) {
        // there got to be a more efficient way to print n spaces
        for (int i = 0; i < n; i++)
                System.out.print(' ');
        System.out.println("#{Built-in Procedure");
        symbol.print(n+3);
        for (int i = 0; i < n; i++)
                System.out.print(' ');
        System.out.println('}');
}

// TODO: The method apply() should be defined in class Node
// to report an error.  It should be overwritten only in classes
// BuiltIn and Closure.
public Node apply (Node args, Environment env) {
        if (args == null) return null;

        String symbolName = symbol.getName();
        Node arg1 = args.getCar();
        Node arg2 = args.getCdr().getCar();

        // BINARY OPERATORS
        if (symbolName == "b+") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new IntLit(arg1.getValue() + arg2.getValue());
                } else {
                        return System.err.println("Arguments for + must be numbers.");
                }
        }

        if (symbolName == "b-") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new IntLit(arg1.getValue() - arg2.getValue());
                } else {
                        return System.err.println("Arguments for - must be numbers.");
                }
        }

        if (symbolName == "b*") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new IntLit(arg1.getValue() * arg2.getValue());
                } else {
                        return System.err.println("Arguments for * must be numbers.");
                }
        }

        if (symbolName == "b/") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new IntLit(arg1.getValue() / arg2.getValue());
                } else {
                        return System.err.println("Arguments for / must be numbers.");
                }
        }

        if (symbolName == "b<") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new BooleanLit(arg1.getValue() < arg2.getValue());
                } else {
                        return System.err.println("Arguments for < must be numbers.");
                }
        }

        if (symbolName == "b>") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new BooleanLit(arg1.getValue() > arg2.getValue());
                } else {
                        return System.err.println("Arguments for > must be numbers.");
                }
        }

        if (symbolName == "b=") {
                if (arg1.isNumber() && arg2.isNumber()) {
                        return new BooleanLit(arg1.getValue() == arg2.getValue());
                } else {
                        return System.err.println("Arguments for + must be numbers.");
                }
        }

        // TYPE TESTS
        if (symbolName == "number?") {
                return new BooleanLit(arg1.isNumber());
        }

        if (symbolName == "symbol?") {
                return new BooleanLit(arg1.isSymbol());
        }

        if (symbolName == "null?") {
                return new BooleanLit(arg1.isNull());
        }

        if (symbolName == "pair?") {
                return new BooleanLit(arg1.isPair());
        }

        if (symbolName == "procedure?") {
                return new BooleanLit(env.lookup(arg1).isProcedure());
        }

        // LISPY STUFF
        if (symbolName == "car") {
                if (arg1.isNull()) {
                        return arg1;
                } else {
                        return arg1.getCar();
                }
        }

        if (symbolName == "cdr") {
                if (arg1.isNull()) {
                        return arg1;
                } else {
                        return arg1.getCdr();
                }
        }

        if (symbolName == "set-car!") {
                arg1.setCar(arg2);
                return arg1;
        }

        if (symbolName == "set-cdr!") {
                arg1.setCdr(arg2);
                return arg1;
        }

        if (symbolName == "cons") {
                return new Cons(arg1, arg2);
        }

        if (symbolName == "eq?") {
                return new BooleanLit(arg1 == arg2);
        }

        if (symbolName == "display" || symbolName == "eval") {
                return arg1;
        }

        if (symbolName == "apply") {
                return arg1.apply(arg2, env);
        }

        return null;
}
}
