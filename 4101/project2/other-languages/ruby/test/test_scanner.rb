require "minitest/autorun"
require_relative "../scanner/scanner"

class TestScanner < Minitest::Test
  def test_it_skips_whitespace
    scanner = Scheme::Scanner.new("   hello")
    first_token = scanner.get_next_token
    assert_equal first_token.type, :IDENT
  end

  def test_it_skips_comments
    input = %Q(
      ; Ignored....
      hello
    )
    scanner = Scheme::Scanner.new(input)
    first_token = scanner.get_next_token
    assert_equal first_token.type, :IDENT
  end

  def test_it_scans_strings_correctly
    scanner = Scheme::Scanner.new('"I am a string"')
    first_token = scanner.get_next_token
    assert_equal first_token.type, :STRING
    assert_equal first_token.get_string_val, "I am a string"
  end

  def test_it_detects_special_characters
    scanner = Scheme::Scanner.new("' ( ) .")
    assert_equal scanner.get_next_token.type, :QUOTE
    assert_equal scanner.get_next_token.type, :LPAREN
    assert_equal scanner.get_next_token.type, :RPAREN
    assert_equal scanner.get_next_token.type, :DOT
  end

  def test_it_detects_both_boolean_constants
    scanner = Scheme::Scanner.new("#f #t")
    assert_equal scanner.get_next_token.type, :FALSE
    assert_equal scanner.get_next_token.type, :TRUE
  end

  def test_it_scans_integers_correctly
    scanner = Scheme::Scanner.new("234")
    token = scanner.get_next_token
    assert_equal token.type, :INTEGER
    assert_equal token.get_integer_val, 234
  end

  def test_it_scans_identifiers_correctly
    scanner = Scheme::Scanner.new("hello")
    token = scanner.get_next_token
    assert_equal token.type, :IDENT
    assert_equal token.get_name, "hello"
  end
end
