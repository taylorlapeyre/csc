output = File.open("todo.txt", "w")

Dir.foreach(File.dirname(__FILE__)) do |file|
  if file.include? '.java'
    output.puts file
    has_todos = false
    File.open(file).each do |line|
      if line.include?("// TODO:")
        has_todos = true
        output.puts line.gsub('// TODO:', '')
      end
    end
    output.puts "Nothing to do here!" unless has_todos
    output.puts
  end
end
