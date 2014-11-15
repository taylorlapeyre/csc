import java.io.*;
class Ident extends Node {
  private String name;

  public Ident(String n) { name = n; }

  public void print(int n) {
    for (int i = 0; i < n; i++)
      System.out.print(" ");

    System.out.print(name);
  }

  public String getName() {
    return name;
  }

  public boolean isSymbol() {
      return true;
  }

  public Node eval(Node node, Environment env) {
    Node a = new Cons(new Ident(node.getName()), new Nil());
    Node someArgs = evalList(a, env);
    if(!someArgs.isNull()) {
      if(someArgs.getCar().isNumber()) {
        return new IntLit(someArgs.getCar().getValue());
      } else if(someArgs.getCar().isPair()) {
        return new StrLit("");
      } else if(someArgs.getCar().isString()) {
        return new StrLit(someArgs.getCar().getString());
      } else if(someArgs.getCar().isBool()) {
        return new BooleanLit(someArgs.getCar().getBool());
      } else {
        return new Nil();
      }

    } else {
      return null;
    }
  }

  public Node evalList(Node node, Environment env) {
    if(node.isNull() || node == null) {
      Node evalList = new Cons(new Nil(), new Nil());
      return evalList;
    } else {
      Node arg = node.getCar();
      Node rest = node.getCdr();

      if(arg.isSymbol()) {
        arg = env.lookup(arg);
      }

      if(arg == null || arg.isNull()) {
        return new Nil();
      }

      Node evalList = new Cons(arg.eval(env), evalList(rest, env));
      return evalList;
    }
  }
}
