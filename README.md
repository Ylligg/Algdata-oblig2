# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:

* Ylli Gashi, s364574, s364574@oslomet.no
* Sena Uysun, s364728, s364728@oslomet.no
* Håkon Skaftun, s364776, s364776@oslomet.no
* Oliver Preber, s364763, s364763@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Ylli har hatt hovedansvar for oppgave 1, 2, 4, 5, 9. 
* Sena har hatt hovedansvar for oppgave 3 og 10. 
* Håkon har hatt hovedansvar for oppgave 4 og 7.
* Oliver har hatt hovedansvar for oppgave ...

# Oppgavebeskrivelse

I oppgave 1 så opprettet vi den dobelte linkede listen ved først å se om de første verdiene er null, hvis de er så blir hode den første verdien som ikke er null og den blir første node (current). etter det så søkes det gjennom listen, om en element er null så skippes den og hvis a[i] != null så lages en ny node (ny). for å få relasjon med hverandre så er ny sin forrige current og current sin neste ny. etter relasjonen så blir nye noden current. når det skjer så har vi klart å få en ny node inn så blir antall plussa. når løkken er ferdig så blir halen current siden current var den siste noden.  

I oppgave 2 så lagde vi tostring() og omvendtstring(). hvis antall er 0 så vil det returne en tom liste []. etter det så blir en stringbuilder opprettet og vi lager en node som heter cur. vi går gjennom en løkke for å adde elementene i rekkefølge ved å appende cur.verdi og når det er gjort så går vi videre til cur.neste. For det siste elementet så blir det appenda kun verdien og ikke en komma slik at tabellen ender som "4]" og ikke "4,]". når alt er ferdig så returner den ut i tostring(). det samme skjer i omvendtstring() bare at det starter på den siste posisjonen av løkka og går mot 0. på den måten så kan vi skrive ut listen i motsatt retning.

I oppgave 3.a lagde vi først hjelpemetoden Node<T> finnNode(int indeks). Så sammenlignet vi indeks og antall/2 ved å bruke en if løkka. Hvis indeksen er mindre enn antal/2, skal nåværende verdi være lik hode.Så lagde vi en for løkka som skal letes etter noden starte fra den nåværende verdien og gå mot høyre ved hjelp av neste pekeren.
Neste steg var å lage  else løkka (hvis antall/2 er ikke mindre enn indeksen ) som tar inn en for løkka og returnerer den nåværende verdien. Hvis  antall/2 er ikke mindre enn indeksen betyr det at nåværende verdi er lik hale og for løkkan skal letes etter noden starte fra halen og gå mot venstre ved hjelp av forrige pekeren. 
Så lagde vi metoden public T hent(int indeks) ved å bruke finnNode() og metoden skal returnere "nåværende.verdi". For å sjekke indeksen la vi til  metoden indeksKontroll inn i metoden oppdater. Metoden indeksKontroll var allerede i Liste.java. Derfor lagde vi ikke en ny indeksKontroll metoden i DobbeltLenketListe.java filen. Til slutt lagde vi metoden oppdater som skal ersatte verdien på plass indeks med nyverdi og returnere 
"nåværende.verdi". For å kunne sjekke at null verdier ikke legges inn , skrev vi  Objects.requireNonNull(nyverdi, "verdi er null") innenfor metoden oppdater. Så la vi til endringer++ innenfor metoden oppdater for at variabelen endringer skal økes.
I oppgave 3.b lagde vi først metoden Liste<T>  subliste(int  fra,  int  til). Først lagde vi en hjelpemetode for å kunne sjekke om indeksene fra og til er  lovlige. Så byttet vi ord antall med ortdet tablengde. Så antall=til-fra. Så lagde vi en if løkka som skal returneres en tom liste hvis tabellengde/antall er lik eller mindre enn null. Vi brukte deretter en while-løkke for å skrive ut listeelementene med to spesifiserte intervaller. Hvis antall er større enn 0, vil elementene i de to spesifiserte intervaller bli skrevet ut ved hjelp av leggInn-metoden.

I oppgave 4 startet vi med metoden indeksTil(T verdi). Vi brukte en while-løkke som går gjennom listen fra starten. Vi setter en Node current til verdi av hode (den første Noden) og sjekker om verdien av noden er lik verdien vi søker etter gjennom løkka. Vi har også lagt til en int indeks som teller indeksen vi foreløpig er på. Hvis verdien vi søker etter er lik verdien til Node current returneres indeksen, hvis ikke økes indeksen med 1 og current endres til current.neste. Så lenge current ikke er null vil det søkes videre til slutten av listen og vil returnere -1 hvis verdien ikke finnes. 
Del 2 av oppgaven lagde vi public boolean inneholder(T verdi). Denne returnerer enten true eller false. Da brukte vi en if-setning som bruker indeksTil-funksjonen og sjekker verdien. Hvis verdien ikke er i listen vil funksjonen indeksTil returnere -1 så da vil funksjonen inneholder(T verdi) returnere false. Hvis indeksTil returnerer noe som helst annet enn -1 skal funksjonen returnere true. 

I oppgave 5 så lagde vi legginn med indeks og verdi. det vi gjør først er å ha en requirenonnull og indekskontroll for å se om det er mulig for å sette inn en ny node. det som skjer etter det er om antallet er 0 eller lik indeks (altså siste node) så legges inn en ny node. etter det så oppretter vi tre pekere q,p og r, hvor r er den noden vi skal finne og p er elemente før r og q er en ny node som skal være mellom q og r. om indeks er 0 så blir r sin forrige q og q blir hode siden den er første node. om indeks ikke er 0 så vil p sin neste være q og r sin forrige vil være q. når det er gjort så vil antallet og endringer økes.

I oppgave 9 så fiksa vi remove(). det vi gjorde først var å se ulike feil som hvis fjernok var false da kastes det en illegalstateexception. om endringer ikke er lik iteratorendringer så er det feil også. når disse kravene er fullført så vil vi gjøre fjerneok false. hvis antallet er 1 så vil vi ende opp med ingenting altså hode=null og hale=null. om denne er null så fjernes hale og hale.forrige blir nye halen. neste er for hode, denne.forrige er hode, men vi gjør hode blir neste element og hode sin gammle posisjon blir null. ellers så har vi p og r som blir connected sammen som gjør at q ikke har noen relasjoner som gjør at den blir borte. når alt er ferdig så økes endringer og iteratorendringer og antall synker