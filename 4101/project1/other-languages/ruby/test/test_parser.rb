require "minitest/autorun"
require_relative "../scanner/scanner"
require_relative "../parser/parser"

class TestParser < Minitest::Test
  def test_it_can_parse_an_identifier
    parser = Scheme::Parser.new(Scheme::Scanner.new("testing"))
    root = parser.parse_next_exp
    assert_kind_of Scheme::Ident, root
  end

  def test_it_can_parse_a_boolean_constant
    parser = Scheme::Parser.new(Scheme::Scanner.new("#t #f"))
    root = parser.parse_next_exp
    assert_kind_of Scheme::BooleanLit, root
    root = parser.parse_next_exp
    assert_kind_of Scheme::BooleanLit, root
  end

  def test_it_can_create_a_cons
    parser = Scheme::Parser.new(Scheme::Scanner.new("(hello)"))

    root = parser.parse_next_exp
    assert_kind_of Scheme::Cons, root
    assert_kind_of Scheme::Ident, root.car
    assert_kind_of Scheme::Nil, root.cdr

    root = parser.parse_next_exp
    assert_kind_of nil, root
  end
end
