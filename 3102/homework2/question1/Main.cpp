// Main.cpp -- the main program
#include <iostream>

using namespace std;

static void showUsage(string name);

int main(int argc, char ** argv)
{
	showUsage(argv[0]);

	for (int i = 1; i < argc; ++i)
	{
		string arg = argv[i];
		if ((arg == "-h") || (arg == "--help"))
		{
			showUsage(argv[0]);

			return 0;
		}
		else if ((arg == "-f") || (arg == "--file"))
		{
			cerr << "Loading file...\n";
			if (i + 1 < argc)
			{ // Make sure we aren't at the end of argv!
				

			}
			else
			{
				// Uh-oh, there was no argument to the destination option.
				cerr << "--file option requires one argument." << endl;

				return 1;
			}
		}
	}

	return 0;
}

static void showUsage(string name)
{
	cout << "Trie Test\n"
	     << "Usage: " << name << " -<option> <input>\n"
	     << "Options:\n"
	     << "\t-h,--help\t\tShow this help message\n"
	     << "\t-f,--file <filename>\tLoad graph data file\n";
}