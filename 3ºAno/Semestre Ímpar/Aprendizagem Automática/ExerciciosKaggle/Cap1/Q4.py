'''
Questão:

Alice, Bob and Carol have agreed to pool their Halloween candy and split it evenly among themselves. 
For the sake of their friendship, any candies left over will be smashed.
For example, if they collectively bring home 91 candies, they'll take 30 each and smash 1.

Write an arithmetic expression below to calculate how many candies they must smash for a given haul.
'''

# Variables representing the number of candies collected by alice, bob, and carol
alice_candies = 121
bob_candies = 77
carol_candies = 109

# Your code goes here! Replace the right-hand side of this assignment with an expression
# involving alice_candies, bob_candies, and carol_candies
total = alice_candies + bob_candies + carol_candies #soma todos
to_smash = total % 3 #Resto do total por 3 amigos
print(to_smash)
# Check your answer
#q4.check()