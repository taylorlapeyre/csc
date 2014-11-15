import java.io.*;

class Set extends Special
{
    private Cons cons;

    public Set() {}

    void print(Node c, int n, boolean p) {
        Printer.printSet(c, n, p);
    }

    public Node eval(Node t, Environment env) {
        Node var = t.getCdr().getCar();
        Node val = t.getCdr().getCdr().getCar();

        Node currentVar = env.lookup(var);

        if (currentVar.isNull()) {
            // throw an error or something
        } else {
            env.assign(var, val.eval(env));
        }
        return val;
    }
}
