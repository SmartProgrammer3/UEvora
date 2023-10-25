'''
Complete the body of the following function according to its docstring.
HINT: Python has a built-in function round.

def round_to_two_places(num):
    """Return the given number rounded to two decimal places. 
    
    >>> round_to_two_places(3.14159)
    3.14
    """
    # Replace this body with your own code.
    # ("pass" is a keyword that does literally nothing. We used it as a placeholder
    # because after we begin a code block, Python requires at least one line of code)
    pass

# Check your answer
q1.check()
'''

def round_to_two_places(num):
    numeroARredondado = round(num,2) #Arredondar 2 casas decimais
    return numeroARredondado

print(round_to_two_places(3.4895))