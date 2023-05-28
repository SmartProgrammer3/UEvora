othello: othello.o othello_func.o
	gcc -o $@ $^ -Wall

othello.o: othello.c othello_func.h
	gcc -c -o $@ $< -Wall

othello_func.o: othello_func.c othello_func.h
	gcc -c -o $@ $< -Wall


clean:
	rm -rf othello *.o