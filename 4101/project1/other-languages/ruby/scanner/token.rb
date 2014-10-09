module Scheme
  class Token
    attr_reader :type

    DEFAULT_STRING_VAL  = ""
    DEFAULT_INTEGER_VAL = 0
    DEFAULT_NAME        = ""

    def initialize(token_type)
      @type = token_type
    end

    def get_string_val
      DEFAULT_STRING_VAL
    end

    def get_integer_val
      DEFAULT_INTEGER_VAL
    end

    def get_name
      DEFAULT_NAME
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
end
