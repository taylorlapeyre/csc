from scanner import Scanner

if __name__ == "__main__":
  while True:
    print("> "),

    console_input = raw_input()
    if console_input == "exit": exit()

    analyzer = Scanner(console_input)

    token   = analyzer.get_next_token()
    while token:
      print(token)
      token = analyzer.get_next_token()
