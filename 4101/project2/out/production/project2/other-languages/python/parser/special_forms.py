class Special:
  pass

class Quote(Special):
  def pprint(cons, n, p):
    print("'")
    cons.cdr.pprint(n, True)

class Lambda(Special):
  def pprint(cons, n, p):
    print('(lambda ')
    second_node = cons.get_cdr().get_car()
    if second_node.is_pair():
      second_node.pprint(n, p)
    else:
      raise "Expecting a cons as the second form in Lambda"

    third_node = cons.get_cdr().get_cdr()
    if third_node.is_pair():
      third_node.pprint(n + 2, True)
    else:
      raise "Expecting a cons as the third form in Lambda"

class Begin(Special):
  def pprint(cons, n, p):
    print('(begin')
    if cons.get_cdr().is_pair():
      cons.cdr.pprint(n + 2, p)
    else:
      raise "Expecting a cons as the second form in Begin"

class If(Special):
  pass

class Regular(Special):
  pass
