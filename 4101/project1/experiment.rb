input = "(+ 1 2)"

class Scanner
  def initialize(string)
    @string = string
  end

  def read_next_char
    @string.slice!(0)
  end

  def get_next_token
    token = read_next_char

    ################################################
    # Nothing
    ################################################
    return nil if token.nil?

    ################################################
    # Whitespace
    ################################################
    if [' ', "\n"].include?(token)
      puts "[log]: found whitespace, skipping..."
      return get_next_token
    end

    ################################################
    # Strings
    ################################################
    if token == '"'
      string = ""
      token = read_next_char
      until token == '"'
        string += token
        token = read_next_char
      end
      puts "[log]: found a string: #{string.inspect}"
      return format_token(string)
    end

    ################################################
    # Special Characters
    ################################################
    # Quote
    if token == "'"
      puts "[log]: found a quote: #{token.inspect}"
      return format_token(token)
    end

    # Left Parenthesis
    if token == '('
      puts "[log]: found a left parentheses: #{token.inspect}"
      return format_token(token)
    end

    # Right Parenthesis
    if token == ')'
      puts "[log]: found a right parentheses: #{token.inspect}"
      return format_token(token)
    end

    # Dot
    if token == '.'
      puts "[log]: found a dot: #{token.inspect}"
      return format_token(token)
    end

    ################################################
    # Boolean Constants
    ################################################
    if token == '#'
      constant = '#'
      next_char = read_next_char
      if next_char == 't'
        constant += next_char
        puts "[log]: found a truth constant: #{constant.inspect}"
        return format_token(constant)
      elsif next_char == 'f'
        constant += next_char
        puts "[log]: found a false constant: #{constant.inspect}"
        return format_token(constant)
      else
        raise "Invalid boolean constant"
      end
    end

    ################################################
    # Integer Constants
    ################################################
    if token =~ /\d/
      number = token
      token = read_next_char
      while token =~ /\d/
        number += token
        token = read_next_char
      end
      puts "[log]: found an integer: #{number.inspect}"
      return format_token(number)
    end

    ################################################
    # Identifiers
    ################################################
    puts "[log]: found an identifier: #{token.inspect}"
    format_token(token)

  end

  def format_token(token)
    case token
    when '('
      "LPAREN"
    when ')'
      "RPAREN"
    when /\d+/
      "INTEGER"
    when /("|').*("|')/
      "STRING"
    when "."
      "DOT"
    when "'"
      "QUOTE"
    when /#(t|f)/
      "BOOLEAN"
    else
      "IDENTIFIER"
    end
  end
end

loop do
  print "> "
  scanner = Scanner.new(gets.chomp)

  tok = scanner.get_next_token
  while tok
    puts tok
    puts
    tok = scanner.get_next_token
  end
end
