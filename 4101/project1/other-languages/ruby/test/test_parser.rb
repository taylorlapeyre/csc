require "minitest/autorun"
require_relative "../parser/parser"

class TestParser < Minitest::Test
  make_my_diffs_pretty!

  def test_it_can_parse_an_identifier
    parser = Scheme::Parser.new("testing")
    root = parser.parse_next_exp
    assert_kind_of Scheme::Ident, root
  end

  def test_it_can_parse_a_boolean_constant
    parser = Scheme::Parser.new("#t #f")
    root = parser.parse_next_exp
    assert_kind_of Scheme::BooleanLit, root
    root = parser.parse_next_exp
    assert_kind_of Scheme::BooleanLit, root
  end

  def test_it_can_create_a_cons
    parser = Scheme::Parser.new("(hello)")

    root = parser.parse_next_exp
    assert_kind_of Scheme::Cons, root
    assert_kind_of Scheme::Ident, root.car
    assert_kind_of Scheme::Nil, root.cdr

    root = parser.parse_next_exp
    assert_nil root
  end

  def test_it_can_print_a_begin_statement
    input = '(begin (println "Hello World"))'
    expected_output = '(begin
  (println "Hello World"))'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end

  def test_it_can_print_an_if_statement
    input = '(if (equal 1 2) (true) (false))'
    expected_output = '(if (equal 1 2)
  (true)
  (false))'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end

  def test_it_can_print_a_let_statement
    input = '(let (x 3) (print x))'
    expected_output = '(let (x 3)
  (print x))'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end

  def test_it_can_print_a_lambda_statement
    input = '(lambda (x) (print x))'
    expected_output = '(lambda (x)
  (print x))'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end

  def test_it_can_print_a_cond_statement
    input = '(cond ((equal 1 2) #t) ((equal 3 3) #f))'
    expected_output = '(cond
  ((equal 1 2) #t)
  ((equal 3 3) #f))'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end

  def test_it_can_print_a_setbang_statement
    input = '(set! x 43)'
    expected_output = '(set! x 42)'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end

  def test_it_can_print_a_define_statement
    input = '(define x 42)'
    expected_output = '(define x 42)'

    parser = Scheme::Parser.new(input)
    out = capture_io do
      parser.pretty_print
    end

    assert_equal out.join, expected_output
  end
end
