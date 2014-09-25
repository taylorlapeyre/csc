module Scheme
  class BooleanLit
    def initialize(b)
      @val = b
    end

    def pprint(n)
      print(' ' * n)
      print(@val ? '#t' : '#f')
    end
  end
end
