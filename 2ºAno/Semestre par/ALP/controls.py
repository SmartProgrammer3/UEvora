def controls(delta):
    return { p for (p, _, _) in delta} or { q for (_, _, q) in delta}