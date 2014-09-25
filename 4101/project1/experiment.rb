input = "(+ 1 2)"

class Scanner
  def initialize(string)
    @string = string
  end

  def read
    @string.slice!(0)
  end

  def getNextToken()
    bite = read
    return if bite.nil?

    puts
    if [' ', "\n"].include?(bite)
      puts "[log]: detected whitespace, skipping" 
      getNextToken
    else
      puts "[log]: found a token: #{bite.inspect}"
    end
    puts

    formatToken(bite)
  end


  def formatToken(token)
    print "[parse]: "
    if token.match(/\A[+-]?\d+?(\.\d+)?\Z/) == nil ? false : true
      "INTEGER: #{token}"
    else
      case token
      when '('
        "LPAREN: #{token}"
      when ')'
        "RPAREN: #{token}"
      else
        "IDENTIFIER: #{token}"
      end
    end
  end
end

class Main
  def self.run
    loop do
      print "> "
      scanner = Scanner.new(gets.chomp)

      tok = scanner.getNextToken
      while tok
        puts tok
        tok = scanner.getNextToken
      end
    end
  end
end


Main.run
