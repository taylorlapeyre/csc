import re
import tokens

class Scanner:
  def __init__(self, string):
    self.string = string

  def read_next_char(self):
    char = ""
    try:
      char = self.string[0]
      self.string = self.string[1:]
    except IndexError:
      char = None
    return char

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
      return tokens.Token("QUOTE")

    # Left Parenthesis
    if char == "(":
      return tokens.Token("LPAREN")

    # Right Parenthesis
    if char == ")":
      return tokens.Token("RPAREN")

    # Dot
    if char == ".":
      return tokens.Token("DOT")

    # Boolean Constants
    ################################################
    if char == "#":
      next_char = self.read_next_char()
      if next_char == 't':
        return tokens.Token("TRUE")
      elif next_char == 'f':
        return tokens.Token("FALSE")
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
      return tokens.IntegerToken(int(number))

    # Identifiers
    ################################################
    valid_variable_regex = re.compile("[a-zA-Z_$]")
    if valid_variable_regex.match(char):
      identifier = ""
      while valid_variable_regex.match(char):
        identifier += char
        char = self.read_next_char()
      self.string = char + self.string
      return tokens.IdentifierToken(identifier)

    # Invalid Characters
    ################################################
    raise "invalid character"
