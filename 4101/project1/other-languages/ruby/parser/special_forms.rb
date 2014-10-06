module Scheme
  class Special
    # abstract class
  end

  class Quote < Special
    def pprint(node, n, p) # what does p stand for?
      cdr = node.cdr
      print "'"
      node.cdr.pprint(n, p: true)
    end
  end

  class Lambda < Special
    def pprint(node, n, p)
      print '(lambda '
      second_node = node.cdr.car
      if second_node.is_pair?
        second_node.pprint(n, p)
      else
        fail SyntaxError, "Invalid expression, was expecting a cons."
      end

      print "\n"

      third_node = nodr.cdr.cdr
      if third_node.is_pair?
        third_node.pprint(n, true)
      else
        fail SyntaxError, "Invalid expression, was expecting a cons."
      end
    end
  end

  class Begin < Special
    def pprint(node, n, p)
      print '(begin'
      if node.cdr
        node.cdr.pprint(n, p)
      else
        fail SyntaxError, "Invalid expression, was expecting a cons."
      end
    end
  end

  class If < Special
    def pprint(node, n, p)
      print(' ' * n)
      print '(if '

      predicate = node.cdr.car
      if predicate
        predicate.pprint(0, p)
      else
        fail SyntaxError, "Invalid If statement, missing predicate."
      end

      print(' ' * n)

      then_clause = node.cdr.cdr.car
      if then_clause
        then_clause.pprint(n + 2, p)
      else
        fail SyntaxError, "Invalid If statement, missing then statement."
      end

      else_clause = node.cdr.cdr.car
      if else_clause
        else_clause.pprint(n + 2, p)
      else
        fail SyntaxError, "Invalid If statement, missing else statement."
      end

      print(' ' * n)
      puts ')'
    end
  end

  # WIP
end
