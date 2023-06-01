def terminals(grammar):
    terminal_set = set()
    for production in grammar:
        variable, word = production
        for symbol in word:
            if not symbol.isupper():  # If the symbol is not a variable, it is a terminal
                terminal_set.add(symbol)
    return terminal_set