module Scheme
  class Special
    # abstract class
  end

  class Quote < Special
    def pprint(cons, n, p) # what does p stand for?
      cdr = cons.cdr
      print "'"
      cons.cdr.pprint(n+ 10, p: true)
    end
  end

  class Lambda < Special
    def pprint(cons, n, p)
      print '(lambda '
      second_node = cons.cdr.car
      if second_node.is_pair?
        second_node.pprint(n, p)
      else
        fail SyntaxError, "Invalid expression, was expecting a cons."
      end

      print "\n"

      third_node = cons.cdr.cdr
      if third_node.is_pair?
        third_node.pprint(n + 2, true)
      else
        fail SyntaxError, "Invalid expression, was expecting a cons."
      end
    end
  end

  class Begin < Special
    def pprint(cons, n, p)
      print '(begin'
      if cons.cdr
        cons.cdr.pprint(n, p)
      else
        fail SyntaxError, "Invalid expression, was expecting a cons."
      end
    end
  end

  class If < Special
    def pprint(cons, n, p)
      print(' ' * n)
      print '(if '

      predicate = cons.cdr.car
      if predicate
        predicate.pprint(n, p)
      else
        fail SyntaxError, "Invalid If statement, missing predicate."
      end

      puts
      print ' ' * n

      then_clause = cons.cdr.cdr.car
      if then_clause
        then_clause.pprint(n + 2, p)
      else
        fail SyntaxError, "Invalid If statement, missing then statement."
      end

      else_clause = cons.cdr.cdr.car
      if else_clause
        puts
        else_clause.pprint(n + 2, p)
      else
        fail SyntaxError, "Invalid If statement, missing else statement."
      end

      print(' ' * n)
      puts ')'
    end
  end

  class Regular < Special
    def pprint(cons, n, p)
      print ' ' * n
      print "(" unless p
      cons.car.pprint(0)
      unless cons.cdr.is_null?
        cons.cdr.pprint(1, true)
      end
    end
  end
end
