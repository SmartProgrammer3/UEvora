def is_item(x):
    if isinstance(x, tuple) and len(x) == 2:
        rule, pos = x
        if isinstance(rule, tuple) and len(rule) == 2:
            v, p = rule
            if isinstance(v, str) and v.isupper() and isinstance(p, tuple):
                return all(isinstance(symbol, str) and symbol != '' for symbol in p)
    return False

def item_pos(x):
    if is_item(x):
        return x[1]
    return "none"