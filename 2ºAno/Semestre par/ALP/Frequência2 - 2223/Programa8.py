def is_rule(x):
    if isinstance(x, tuple) and len(x) == 2: 
        var, word = x
        if isinstance(var, str) and var.isupper() and isinstance(word, tuple):  
            for symbol in word:
                if not isinstance(symbol, str) or symbol == '':
                    return False 
            return True
        else:
            return False
    else:
        return False

def initial_item(x):
    if is_rule(x):
        return (x, 0)  
    else:
        return None  