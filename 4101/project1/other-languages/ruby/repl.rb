require_relative 'scanner/scanner'

# REPL
################################################
puts "Lexical Analyzer REPL v1"
puts 'Type "exit" to quit'

loop do
  print "> "

  input   = gets.chomp # "chomp" to get rid of newline at the end
  exit if input == "exit"

  scanner = Scheme::Scanner.new(input)
  token   = scanner.get_next_token

  while token
    puts token
    token = scanner.get_next_token
  end
end
