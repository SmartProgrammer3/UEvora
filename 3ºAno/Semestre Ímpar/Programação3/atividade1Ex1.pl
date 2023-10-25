homem(joao).
homem(rui).
homem(manuel).
homem(ricardo).
homem(artur).
homem(jacinto).
homem(jose).
homem(almeida).
homem(rodrigo).

mulher(maria).
mulher(ana).
mulher(rita).
mulher(silvia).
mulher(joana).
mulher(gertrudes).
mulher(raquel).
mulher(alice).
mulher(sofia).

progenitor(joao, maria).
progenitor(joao, rui).
progenitor(manuel, joao).
progenitor(manuel, artur).
progenitor(ricardo, manuel).
progenitor(silvia, jacinto).
progenitor(silvia, gertrudes).
progenitor(ana, rui).
progenitor(joana, maria).
progenitor(rita, joao).
progenitor(rita, silvia).
progenitor(maria, jose).
progenitor(maria, alice).
progenitor(rui, almeida).
progenitor(rui, raquel).
progenitor(rodrigo, silvia).
progenitor(sofia, artur).

mae(A, B) :- progenitor(A, B), mulher(A).
pai(A, B) :- progenitor(A, B), homem(A).

filho(A, B) :- progenitor(B, A), homem(A).
filha(A, B) :- progenitor(B, A), mulher(A).

irmaos(A, B) :- progenitor(X, A), progenitor(X, B), A \= B.
irmao(A, B) :- progenitor(X, A), progenitor(X, B), homem(A), A \= B.
irma(A, B) :- progenitor(X, A), progenitor(X, B), mulher(A), A \= B.

avo(A, B) :- progenitor(A, X), progenitor(X, B).

tios(A, B) :- irmaos(A, X), progenitor(X, B).
tio(A, B) :- irmao(A, X), progenitor(X, B).
tia(A, B) :- irma(A, X), progenitor(X, B).

primos(A, B) :- progenitor(X, A), progenitor(Y, B), irmaos(X, Y).
primo(A, B) :- progenitor(X, A), progenitor(Y, B), irmaos(X, Y), homem(A).
prima(A, B) :- progenitor(X, A), progenitor(Y, B), irmaos(X, Y), mulher(A).
antepassado(A, B) :- progenitor(A, B).
antepassado(A, B) :- progenitor(A, X), antepassado(X, B).

descendente(A, B) :- antepassado(B, A).

meioirmao(A, B) :- progenitor(X, A), progenitor(X, B), progenitor(Y, A), progenitor(Z, B), A \= B, X \= Y, X \= Z, Y \= Z.

primosegundo(A, B) :- antepassado(X, A), antepassado(Y, B), irmaos(X, Y), A \= B.

parentesco(A, B) :- antepassado(X, A), antepassado(X, B), A \= B.
