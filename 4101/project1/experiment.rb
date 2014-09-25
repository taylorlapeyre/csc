input = "(+ 1 2)"

class Scanner
  def initialize(string)
    @string = string
  end

  def read()
    first = @string[0]
    rest  = @string[1..-1]
    @string = rest
    first
  end

  def getNextToken()
    bite = read()

    if [' ', "\n"].include?(bite)
      getNextToken()
    else
      puts formatToken(bite)
    end
  end


  def formatToken(token)
    case token
    when '('
      'LPAREN'
    when ')'
      'RPAREN'
    when ' '
      ''
    when "\n"
      ''
    when /\d+/
      'INT'
    else
      'INDENT'
    end
  end
  
  def scan
    while thing = getNextToken
    
end

scanner = Scanner.new("(+ 1 2)")
scanner.getNextToken()
