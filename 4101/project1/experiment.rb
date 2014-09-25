class Token
  def initialize(token_type)
    @type = token_type
  end

  def get_string_val
    ""
  end

  def get_integer_val
    0
  end

  def get_name
    ""
  end

  def to_s
    %Q(
      Token {
        type:        #{@type.inspect}
        integer_val: #{get_integer_val.inspect}
        string_val:  #{get_string_val.inspect}
        name:        #{get_name.inspect}
      }
    )
  end
end

class IntegerToken < Token
  def initialize(int_val)
    @type = :INTEGER
    @val = int_val
  end

  def get_integer_val
    @val
  end
end

class StringToken < Token
  def initialize(str_val)
    @type = :STRING
    @val = str_val
  end

  def get_string_val
    @val
  end
end

class IdentifierToken < Token
  def initialize(name)
    @type = :IDENT
    @name = name
  end

  def get_name
    @name
  end
end

class Scanner
  def initialize(string)
    @string = string
  end

  def read_next_char
    @string.slice!(0)
  end

  def get_next_token
    char = read_next_char


    # Nothing
    ################################################
    return nil if char.nil?


    # Whitespace
    ################################################
    if [' ', "\n"].include? char
      return get_next_token
    end


    # Comments
    ################################################
    if char == ';'
      until ["\n", nil].include? char
        char = read_next_char
      end
      return get_next_token
    end


    # Strings
    ################################################
    if char == '"'
      string = ""
      char = read_next_char
      until char == '"'
        string += char
        char = read_next_char
      end
      return StringToken.new(string)
    end


    # Special Characters
    ################################################
    # Quote
    if char == "'"
      return Token.new(:QUOTE)
    end

    # Left Parenthesis
    if char == '('
      return Token.new(:LPAREN)
    end

    # Right Parenthesis
    if char == ')'
      return Token.new(:RPAREN)
    end

    # Dot
    if char == '.'
      return Token.new(:DOT)
    end


    # Boolean Constants
    ################################################
    if char == '#'
      constant = '#'
      next_char = read_next_char
      if next_char == 't'
        constant += next_char
        return Token.new(:TRUE)
      elsif next_char == 'f'
        constant += next_char
        return Token.new(:FALSE)
      else
        throw_syntax_error("Invalid boolean constant")
        return
      end
    end


    # Integer Constants
    ################################################
    if char =~ /\d/
      return IntegerToken.new(char.to_i)
    end


    # Identifiers
    ################################################
    IdentifierToken.new(char)
  end


  # Just a helper method
  ################################################
  def throw_syntax_error(message)
    begin
      fail SyntaxError, message
    rescue Exception => e
      p e
    end
  end
end


# REPL
################################################
loop do
  print "> "

  input   = gets.chomp # "chomp" to get rid of newline at the end
  scanner = Scanner.new(input)
  token   = scanner.get_next_token

  while token
    puts token
    token = scanner.get_next_token
  end
end
