class Token:
  def __init__(self, token_type):
    self.type = token_type

  def get_type(self):
    return self.type

  def get_string_val(self):
    return ""

  def get_integer_val(self):
    return 0

  def get_name(self):
    return ""

  def __str__(self):
    return """
    Token (
      Type: {0}
      String Val: {1}
      Integer Val: {2}
      Name: {3}
    )
    """.format(
      self.type,
      self.get_string_val(),
      self.get_integer_val(),
      self.get_name()
    )


class IntegerToken(Token):
  def __init__(self, int_val):
    self.type = "INTEGER"
    self.val  = int_val

  def get_integer_val(self):
    return self.val


class StringToken(Token):
  def __init__(self, string_val):
    self.type = "STRING"
    self.val  = string_val

  def get_string_val(self):
    return self.val


class IdentifierToken(Token):
  def __init__(self, name):
    self.type = "IDENT"
    self.name  = name

  def get_name(self):
    return self.name
