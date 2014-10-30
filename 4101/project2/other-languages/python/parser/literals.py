import special_forms

class Node:
  def pprint(self):
    print("WTF")

  def is_boolean(self):
    return False

  def is_integer(self):
    return False

  def is_string(self):
    return False

  def is_symbol(self):
    return False

  def is_null(self):
    return False

  def is_pair(self):
    return False


class BooleanLit(Node):
  def __init__(self, b):
    self.val = b

  def pprint(self, n):
    print(' ' * n)
    print('#t' if self.val else '#f')

  def is_boolean(self):
    return True


class StringLit(Node):
  def __init__(self, s):
    self.val = s

  def pprint(self, n):
    print(' ' * n)
    print("\"{0}\"".format(self.s))

  def is_string(self):
    return True


class IntegerLit(Node):
  def __init__(self, i):
    self.val = i

  def pprint(self, n):
    print(' ' * n)
    print(self.val)

  def is_integer(self):
    return True


class Nil(Node):
  def pprint(self, n, p=False):
    print(' ' * n)
    print(')' if p else '()')

  def is_null(self):
    return True


class Ident(Node):
  def __init__(self, name):
    self.val = name

  def pprint(self, n):
    print(' ' * n)
    print(self.name)

  def is_symbol(self):
    return True

  def get_name(self):
    return self.name


class Cons(Node):
  def __init__(self, car, cdr):
    self.car = car
    self.cdr = cdr

  def parse_list(self):
    if self.car.is_symbol():
      name = self.car.get_name()
      if name == 'quote':
        return special_forms.Quote()
      elif name == 'lambda':
        return special_forms.Lambda()
      elif name == 'begin':
        return special_forms.Begin()
      elif name == 'if':
        return special_forms.If()
      elif name == 'let':
        return special_forms.Let()
      elif name == 'cond':
        return special_forms.Cond()
      elif name == 'define':
        return special_forms.Define()
      elif name == 'set!':
        return special_forms.Set()
      else:
        return special_forms.Regular()
    else:
      return special_forms.Regular()

  def pprint(self, n, p=False):
    parse_list().pprint(self, n, p)

  def is_pair(self):
    return True

  def get_cdr(self):
      return self.cdr

  def get_car(self):
      return self.car
