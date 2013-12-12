# complexity: O(n)
def make_change(amount)
	currency = [
		{
			:name => "dollar",
			:weight => 100
		},
		{
			:name => "quarter",
			:weight => 25
		},
		{
			:name => "dime",
			:weight => 10
		},
		{
			:name => "nickel",
			:weight => 5
		},
		{
			:name => "penny",
			:weight => 1
		}
	]

	currency.each do |coin|
		num_that_fit = amount / coin[:weight]
		amount      -= num_that_fit * coin[:weight]

		puts "Use #{num_that_fit} #{coin[:name]}"
	end
end

make_change(35)
