
def lru(input_string, number_of_page_frames: 5)
  values = input_string.split(', ').map(&:to_i)

  total_hits = 0
  total_misses = 0
  page_frames = []
  things_that_were_used = []

  values.each do |val|
    puts "=" * 30
    puts "Evaluating next value in input: #{val}"
    if page_frames.include? val
      puts "HIT: found #{val} in the page frames (#{page_frames})"
      total_hits = total_hits + 1
    else
      total_misses = total_misses + 1
      if page_frames.length == number_of_page_frames
        puts "Looks like the page frames are full. Time to LRU!"

        puts "(Vals from least recently used to most: #{things_that_were_used})"
        puts "Thing we're kicking out of the page frames (#{page_frames}) is #{things_that_were_used.first}"
        least_recently_used = things_that_were_used.shift
        index_of_lru = page_frames.index(least_recently_used)
        until index_of_lru
          puts "Whoops, looks like that value isn't in the page frames. Let's get the next most recently used."
          puts "(Vals from least recently used to most: #{things_that_were_used})"
          puts "Thing we're kicking out of the page frames (#{page_frames}) is #{things_that_were_used.first}"
          least_recently_used = things_that_were_used.shift
          index_of_lru = page_frames.index(least_recently_used)
        end

        page_frames[index_of_lru] = val
        puts "Done with LRU! Page frames are now #{page_frames}"
      else
        puts "Looks like there's empty page frames. Gonna add this to it."
        page_frames << val
      end
    end

    things_that_were_used = things_that_were_used.reject { |e| e == val }
    things_that_were_used << val
    puts
  end

  puts "Total hits: #{total_hits}"
  puts "Total misses: #{total_misses}"
end


lru "0, 1, 2, 3, 2, 4, 5, 3, 4, 1, 6, 3, 7, 8, 7, 8, 4, 9, 7, 8, 1, 2, 9, 5, 4, 5, 0, 2"
