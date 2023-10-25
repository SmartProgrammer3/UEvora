'''
Questão:
Add code to the following cell to swap variables a and b 
(so that a refers to the object previously referred to by b and vice versa).
'''

########### Setup code - don't touch this part ######################
# If you're curious, these are examples of lists. We'll talk about 
# them in depth a few lessons from now. For now, just know that they're
# yet another type of Python object, like int or float.
a = [1, 2, 3]
b = [3, 2, 1]
# q2.store_original_ids()
######################################################################
aux = a
a = b
b = aux
print(a)
print(b)
######################################################################

# Ou mais fácil ainda
#a, b = b, a

# Check your answer
#q2.check()