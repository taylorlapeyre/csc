require_relative 'parser/parser'

# REPL
################################################
puts "Scheme Pretty Printer v2"
puts 'Type "exit" to quit'

loop do
  print "> "

  input = gets.chomp # "chomp" to get rid of newline at the end
  exit if input == "exit"

  parser = Scheme::Parser.new(input)
  parser.pretty_print
  puts
end
