class Parser:
  def __init__(self, scanner):
    self.scanner = scanner

  def parse_next_token(self):
    parse_exp(self.scanner.get_next_token())

  def parse_exp(self, token):
    token_type = token.get_type()

    if token_type == "LPAREN":
      return parse_rest()
    elif token_type == "TRUE":
      return BooleanLit(True)
    elif token_type == "FALSE":
      return BooleanLit(False)
    elif token_type == "QUOTE":
      return Cons(Ident("quote"), Cons(parse_next_exp(), Nil()))
    elif token_type == "INTEGER":
      return IntegerLit(token.get_integer_val())
    elif token_type == "STRING":
      return StringLit(token.get_string_val())
    elif token_type == "IDENT":
      return Ident(token.get_name())
    else:
      print("Something broke parseexp")
      return None

  def parse_rest():
    token = self.scanner.get_next_token()
    token_type = token.get_type()

    if token_type == "RPAREN":
      return Nil()
    elif token_type == "DOT":
      return Cons(parse_next_exp(), parse_rest())
    else:
      return Cons(parse_exp(token), parse_rest())
