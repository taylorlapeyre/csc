import re

class Token:
  def __init__(self, token_type):
    self.type = token_type

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


class Scanner:
  def __init__(self, string):
    self.string = string

  def read_next_char(self):
    if len(self.string) == 0:
      return None
    elif len(self.string) == 1:
      char = self.string
      self.string = ""
      return char
    else:
      first_char  = self.string[0]
      rest        = self.string[1:]
      self.string = rest
      return first_char

  def get_next_token(self):
    char = self.read_next_char()

    # Nothing
    ################################################
    if char == None:
      return None

    # Whitespace
    ################################################
    if char in [" ", "\n"]:
      return self.get_next_token()

    # Comments
    ################################################
    if char == ";":
      while char not in ["\n", None]:
        char = self.read_next_char()
      return self.get_next_token()

    # Strings
    ################################################
    if char == '"':
      string = ""
      char = self.read_next_char()
      while not char == '"':
        string += char
        char    = self.read_next_char()
      return StringToken(string)

    # Special Characters
    ################################################
    # Quote
    if char == "'":
      return Token("QUOTE")

    # Left Parenthesis
    if char == "(":
      return Token("LPAREN")

    # Right Parenthesis
    if char == ")":
      return Token("RPAREN")

    # Dot
    if char == ".":
      return Token("DOT")

    # Boolean Constants
    ################################################
    if char == "#":
      next_char = self.read_next_char()
      if next_char == 't':
        return Token("TRUE")
      elif next_char == 'f':
        return Token("FALSE")
      else:
        raise "Invalid boolean constant"

    # Integer Constants
    ################################################
    number_regex = re.compile("\d+")
    if number_regex.match(char):
      number = ""
      while number_regex.match(char):
        number += char
        char = self.read_next_char()
      self.string = char + self.string
      return IntegerToken(int(number))

    # Identifiers
    ################################################
    valid_variable_regex = re.compile("[a-zA-Z_$]")
    if valid_variable_regex.match(char):
      identifier = ""
      while valid_variable_regex.match(char):
        identifier += char
        char = self.read_next_char()
      self.string = char + self.string
      return IdentifierToken(identifier)

    # Invalid Characters
    ################################################
    raise "invalid character"

while True:
  print("> "),

  console_input = raw_input()
  if console_input == "exit":
    exit()

  scanner = Scanner(console_input)
  token   = scanner.get_next_token()

  while token:
    print(token)
    token = scanner.get_next_token()
