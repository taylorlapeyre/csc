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
        type:        #{@type}
        integer_val: #{get_integer_val}
        string_val:  #{get_string_val}
        name:        #{get_name}
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

    ################################################
    # Nothing
    ################################################
    return nil if char.nil?

    ################################################
    # Whitespace
    ################################################
    if [' ', "\n"].include?(char)
      puts "[log]: skipping whitespace..."
      return get_next_token
    end

    ################################################
    # Strings
    ################################################
    if char == '"'
      string = ""
      char = read_next_char
      until char == '"'
        string += char
        char = read_next_char
      end
      puts "[log]: found a string: #{string.inspect}"
      return StringToken.new(string)
    end

    ################################################
    # Special Characters
    ################################################
    # Quote
    if char == "'"
      puts "[log]: found a quote: #{char.inspect}"
      return Token.new(:QUOTE)
    end

    # Left Parenthesis
    if char == '('
      puts "[log]: found a left parentheses: #{char.inspect}"
      return Token.new(:LPAREN)
    end

    # Right Parenthesis
    if char == ')'
      puts "[log]: found a right parentheses: #{char.inspect}"
      return Token.new(:RPAREN)
    end

    # Dot
    if char == '.'
      puts "[log]: found a dot: #{char.inspect}"
      return Token.new(:DOT)
    end

    ################################################
    # Boolean Constants
    ################################################
    if char == '#'
      constant = '#'
      next_char = read_next_char
      if next_char == 't'
        constant += next_char
        puts "[log]: found a truth constant: #{constant.inspect}"
        return Token.new(:TRUE)
      elsif next_char == 'f'
        constant += next_char
        puts "[log]: found a false constant: #{constant.inspect}"
        return Token.new(:FALSE)
      else
        raise "Invalid boolean constant"
      end
    end

    ################################################
    # Integer Constants
    ################################################
    if char =~ /\d/
      puts "[log]: found an integer: #{char.inspect}"
      return Token.new(char.to_i)
    end

    ################################################
    # Identifiers
    ################################################
    puts "[log]: found an identifier: #{char.inspect}"
    IdentifierToken.new(char)
  end
end

loop do
  print "> "
  scanner = Scanner.new(gets.chomp)

  token = scanner.get_next_token
  while token
    puts token
    puts
    token = scanner.get_next_token
  end
end
