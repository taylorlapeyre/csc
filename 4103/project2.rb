class PageReplacementSimulator
  attr_reader :frames, :number_of_page_frames

  def initialize(frames: [], number_of_page_frames: 5)
    @frames, @number_of_page_frames = frames, number_of_page_frames
    @total_hits   = 0
    @total_misses = 0
  end

  def print_page_frames
    puts "-----"
    @frames.each { |f| puts "| #{f} |"}
    puts "-----"
  end

  def on_algorithm_complete
    puts "Total hits:   #{@total_hits}"
    puts "Total misses: #{@total_misses}"
  end

  def on_hit(val)
    @total_hits = @total_hits + 1
  end

  def on_miss(val)
    @total_misses = @total_misses + 1
  end

  def on_eviction(val)
    # default: noop
  end

  def on_allocation(val)
    # default: noop
  end

  def on_complete(val)
    print_page_frames
  end

  def frame_to_be_evicted
    # default: replace a random page frame
    @frames.index(@frames.sample)
  end

  def run(input_string)
    values = input_string.split(', ').map(&:to_i)

    values.each do |val|
      if @frames.include? val
        # Hit: val was found in our page frames.
        on_hit(val)
      else
        # Miss: Value was not found in our page frames.
        on_miss(val)

        if @frames.length == @number_of_page_frames
          # Eviction: A frame must be replaced.
          @frames[frame_to_be_evicted] = val
          on_eviction(val)
        else
          # Allocation: There are empty page frames. Set val to one of them.
          @frames << val
          on_allocation(val)
        end
      end

      on_complete(val)
    end

    on_algorithm_complete
  end
end

class LRUSimulator < PageReplacementSimulator
  def initialize(frames: [], number_of_page_frames: 5)
    super
    @most_recently_used_vals = []
  end

  def on_complete(val)
    @most_recently_used_vals << val
    print_page_frames
  end

  def frame_to_be_evicted
    least_recently_used = @most_recently_used_vals.shift
    index_of_lru = @frames.index(least_recently_used)

    if index_of_lru
      index_of_lru
    else
      # Keep popping items off of the list until we hit a val in the frames.
      frame_to_be_evicted
    end
  end
end


input = "0, 1, 2, 3, 2, 4, 5, 3, 4, 1, 6, 3, 7, 8, 7, 8, 4, 9, 7, 8, 1, 2, 9, 5, 4, 5, 0, 2"
lru = LRUSimulator.new(number_of_page_frames: 5)
lru.run(input)
