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

I oppgave 1 så opprettet vi den dobelte linkede listen ved først å se om de første verdiene er null, hvis de er så blir hode den første verdien som ikke er null og den blir første node (current). etter det så søkes det gjennom listen, om en element er null så skippes den og hvis a[i] != null så lages en ny node (ny). for å få relasjon med hverandre så er ny sin forrige current og current sin neste ny. etter relasjonen så blir nye noden current. når det skjer så har vi klart å få en ny node inn så blir antall plussa. når løkken er ferdig så blir halen current siden current var den siste noden.  

I oppgave 2 så brukte vi 

I oppgave 3.a lagde vi først hjelpemetoden Node<T> finnNode(int indeks). Så sammenlignet vi indeks og antall/2 ved å bruke en if løkka. Hvis indeksen er mindre enn antal/2, skal nåværende verdi være lik hode.Så lagde vi en for løkka som skal letes etter noden starte fra den nåværende verdien og gå mot høyre ved hjelp av neste pekeren.
Neste steg var å lage  else løkka (hvis antall/2 er ikke mindre enn indeksen ) som tar inn en for løkka og returnerer den nåværende verdien. Hvis  antall/2 er ikke mindre enn indeksen betyr det at nåværende verdi er lik hale og for løkkan skal letes etter noden starte fra halen og gå mot venstre ved hjelp av forrige pekeren. 
Så lagde vi metoden public T hent(int indeks) ved å bruke finnNode() og metoden skal returnere "nåværende.verdi". For å sjekke indeksen la vi til  metoden indeksKontroll inn i metoden oppdater. Metoden indeksKontroll var allerede i Liste.java. Derfor lagde vi ikke en ny indeksKontroll metoden i DobbeltLenketListe.java filen. Til slutt lagde vi metoden oppdater som skal ersatte verdien på plass indeks med nyverdi og returnere 
"nåværende.verdi". For å kunne sjekke at null verdier ikke legges inn , skrev vi  Objects.requireNonNull(nyverdi, "verdi er null") innenfor metoden oppdater. Så la vi til endringer++ innenfor metoden oppdater for at variabelen endringer skal økes.
I oppgave 3.b lagde vi først metoden Liste<T>  subliste(int  fra,  int  til). Først lagde vi en hjelpemetode for å kunne sjekke om indeksene fra og til er  lovlige. Så byttet vi ord antall med ortdet tablengde. Så antall=til-fra. Så lagde vi en if løkka som skal returneres en tom liste hvis tabellengde/antall er lik eller mindre enn null. Vi brukte deretter en while-løkke for å skrive ut listeelementene med to spesifiserte intervaller. Hvis antall er større enn 0, vil elementene i de to spesifiserte intervaller bli skrevet ut ved hjelp av leggInn-metoden.

I oppgave 4 startet vi med metoden indeksTil(T verdi). Vi brukte en while-løkke som går gjennom listen fra starten. Vi setter en Node current til verdi av hode (den første Noden) og sjekker om verdien av noden er lik verdien vi søker etter gjennom løkka. Vi har også lagt til en int indeks som teller indeksen vi foreløpig er på. Hvis verdien vi søker etter er lik verdien til Node current returneres indeksen, hvis ikke økes indeksen med 1 og current endres til current.neste. Så lenge current ikke er null vil det søkes videre til slutten av listen og vil returnere -1 hvis verdien ikke finnes. 
Del 2 av oppgaven lagde vi public boolean inneholder(T verdi). Denne returnerer enten true eller false. Da brukte vi en if-setning som bruker indeksTil-funksjonen og sjekker verdien. Hvis verdien ikke er i listen vil funksjonen indeksTil returnere -1 så da vil funksjonen inneholder(T verdi) returnere false. Hvis indeksTil returnerer noe som helst annet enn -1 skal funksjonen returnere true. 
