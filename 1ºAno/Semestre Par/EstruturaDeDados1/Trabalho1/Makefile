main: main.o doubleLList.o bigInt.o	
	gcc -o $@ $^	
	
doubleLList.o: doubleLList.c
	gcc -c -o $@ $^
	 
bigInt.o: bigInt.c
	gcc -c -o $@ $^
	
main.o: main.c
	gcc -c -o $@ $^

clean:
	rm -rf main
	 