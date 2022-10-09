# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Ylli Gashi, s364574, s364574@oslomet.no
* Sena Uysun, s364728, s364728@oslomet.no
* Håkon Skaftun, s364776, s364776@oslomet.no
* Oliver Preber, s364763, s364763@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Ylli har hatt hovedansvar for oppgave 1, 2, 4, 5. 
* Sena har hatt hovedansvar for oppgave 3 og 10. 
* Håkon har hatt hovedansvar for oppgave 4 og 7.
* Oliver har hatt hovedansvar for oppgave ...

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å ...

I oppgave 2 så brukte vi en ... til å ...



I oppgave 4 startet vi med metoden indeksTil(T verdi). Vi brukte en while-løkke som går gjennom listen fra starten. Vi setter en Node current til verdi av hode (den første Noden) og sjekker om verdien av noden er lik verdien vi søker etter gjennom løkka. Vi har også lagt til en int indeks som teller indeksen vi foreløpig er på. Hvis verdien vi søker etter er lik verdien til Node current returneres indeksen, hvis ikke økes indeksen med 1 og current endres til current.neste. Så lenge current ikke er null vil det søkes videre til slutten av listen og vil returnere -1 hvis verdien ikke finnes. 
Del 2 av oppgaven lagde vi public boolean inneholder(T verdi). Denne returnerer enten true eller false. Da brukte vi en if-setning som bruker indeksTil-funksjonen og sjekker verdien. Hvis verdien ikke er i listen vil funksjonen indeksTil returnere -1 så da vil funksjonen inneholder(T verdi) returnere false. Hvis indeksTil returnerer noe som helst annet enn -1 skal funksjonen returnere true. 
