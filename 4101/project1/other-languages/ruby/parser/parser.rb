require_relative 'literals'

module Scheme
  class Parser
    def initialize(scanner)
      @scanner = scanner
    end

    def parse_next_exp
      token = @scanner.get_next_token
      parse_exp(token)
    end

    def parse_exp(token)

      # Debugging Purposes
      puts "Lexical Analysis Found:"
      puts token
      puts "parsing..."

      case token.type
      when :LPAREN
        parse_rest
      when :TRUE
        Scheme::BooleanLit.new(true)
      when :FALSE
        Scheme::BooleanLit.new(false)
      when :QUOTE
        Scheme::Cons.new(
          Scheme::Ident.new("quote"),
          Scheme::Cons.new(parse_next_exp, Scheme::Nil.instance)
        )
      when :INTEGER
        Scheme::IntegerLit.new(token.get_integer_val)
      when :STRING
        Scheme::StringLit.new(token.get_string_val)
      when :IDENT
        Scheme::Ident.new(token.get_name)
      else
        puts "Something broke parse_exp"
        nil
      end
    end

    def parse_rest
      token = @scanner.get_next_token
      case token.type
      when :RPAREN
        Scheme::Nil.instance
      when :DOT
        parse_dot # ???
      else
        Scheme::Cons.new(parse_exp(token), parse_rest)
      end
    end
  end
end
