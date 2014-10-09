require_relative 'scanner/scanner'
require_relative 'parser/parser'

# REPL
################################################
puts "Lexical Analyzer REPL v1"
puts 'Type "exit" to quit'

loop do
  print "> "

  input   = gets.chomp # "chomp" to get rid of newline at the end
  exit if input == "exit"

  scanner = Scheme::Scanner.new(input)
  parser  = Scheme::Parser.new(scanner)

  root = parser.parse_next_exp
  while root
    root.pprint
    root = parser.parse_next_exp
  end
end
