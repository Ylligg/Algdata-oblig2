package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen


    public DobbeltLenketListe() {
    }


    // målet er å ha en liste som kan gå fram og tilbake ved hjelp av hode og hale pekeren


    public DobbeltLenketListe(T[] a) { // skal lage listen

        if(a == null) { // kaster en feil hvis tabellen er null
            throw new NullPointerException( "Tabellen a er null!"); // gir en melding hvis tabellen er null
        }

        if(a.length == 0){
            return;
        }

            int v = 0; // har en variabel for effektiviteten av søket
            for(int i =0; i < a.length;i++) { // ser gjennom om de første verdiene er null, hvis ikke så setter den  verdien til å bli hode
                if (a[v] == null){ // fortsetter til den ikke er null
                    v++;
                }

                else { // når den ikke er null så er den hode og løkka er ferdig
                    hode = new Node<>(a[v]);
                    antall=1;
                    break;
                }
            }

            if(v == a.length){
                return;
            }

            Node current = hode; // lager en variabel for å finne
            for(int i =v+1; i < a.length;i++){ // gjør det mer effektiv hvis de første veridene er null så starter vi med løkka til når det ikke er null

                if(a[i]==null) {
                    continue;
                }

                Node ny = new Node(a[i]);
                ny.forrige = current;
                current.neste = ny;
                current = ny;
                antall++;

            }
            hale = current;
            endringer =0;
    }

    public Liste<T> subliste(int fra, int til) {
        //----------------OPPGAVE 3.b START--------------
        fraTilKontroll(antall,fra,til); //Sjekkes om indeksene fra og til er lovlige.

        Liste<T> liste= new DobbeltLenketListe<>();
        //Bytt  ut ordet tablengde med ordet antall

        int antall=til-fra;

        if (antall<=0){
            return liste;
        }

        Node<T> nåværende = finnNode(fra);

        while (antall > 0) {
            liste.leggInn(nåværende.verdi);
            nåværende = nåværende.neste;
            antall--;
        }

        return liste;

    }
    //----------------OPPGAVE 3.b FERDIG--------------
    //-----------------Oppgave 3.b hjelpemetode-------
    // Fikk hjelp fra     Programkode 1.2.3 a)
    private void fraTilKontroll(int fra, int til) {

        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall (" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    //---------Oppgave 3.b hjelpemetode ferdig--------


    @Override // riktig
    public int antall() {
        return antall; // returnerer antall
    }

    @Override //riktig
    public boolean tom() { // ser om tabellen er tom
        if(antall != 0) {return false;}
        return true;
    }

    @Override
    public boolean leggInn(T verdi) {

        if (verdi == null) {
            throw new NullPointerException("du prøver å legge inn en null");
        } else {

            if (antall == 0) {
                Node node = new Node(verdi);
                hode = node;
                hale = hode;

                endringer++;
                antall++;
                return true;
            } else {
                Node node = new Node(verdi);
                node.forrige = hale;
                hale.neste = node;
                hale = node;

                endringer++;
                antall++;
                return true;
            }
        }
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        if(verdi == null) { // kaster en feil hvis tabellen er null
            Objects.requireNonNull(verdi, "Tabellen a er null!"); // gir en melding hvis tabellen er null
        }

        // kaster en feil hvis indeks  er mindre enn 0

        if(indeks < 0){
            throw new IndexOutOfBoundsException("indeksen er mindre enn 0");
        }

        if(antall == 0 && indeks > 0){
            throw new IndexOutOfBoundsException("du prøver å legge inn en indeks til en tom liste");
        }

        try {
            if (indeks >= 0 && indeks <= antall) {
                antall++;
                endringer++;
            }
            if (indeks == 0) {
            }

        } catch (IndexOutOfBoundsException e){

        }
    }

    // --------------------Oppgave 4 del 2 START -------------------------------
    // må testes, ikek sikker om dett funker ennå
    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) == -1){
            return false;
        }
        return true;
    }
     // --------------------Oppgave 4 del 2 SLUTT -------------------------------

    @Override
    public T hent(int indeks) { // noe funker ikke
        indeksKontroll(indeks,false);
        Node<T> h = finnNode(indeks);

        if(h.verdi == null){
            throw new NullPointerException("");
        } else{
            return finnNode(indeks).verdi;
        }
    }


    // --------------------Oppgave 4 del 1 START -------------------------------
    @Override
    public int indeksTil(T verdi) {
        /*
        int indeks = 0;
        Node current = hode;
        while (current == null) {
            if (current.verdi == verdi) {
                return indeks;
            } else {
                indeks++;
                current = current.neste;
              }
        }
        return -1;

         */




        return -1;


    }
    // --------------------Oppgave 4 del 1 SLUTT -------------------------------

    @Override
    public T oppdater(int indeks, T nyverdi) {

        //------------------------OPPGAVE 3.a.3 START-------------------------------------
//****Den  skal  erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før***

        Objects.requireNonNull(nyverdi, "verdi er null");
        indeksKontroll(indeks,false);

        Node<T> nåværende = finnNode(indeks);

        T  gammel = nåværende.verdi;

        nåværende.verdi = nyverdi;
        endringer++;
        return gammel;
    }
//-----------------------OPPGAVE 3.a.3 FERDIG---------------------------------------

    @Override
    public boolean fjern(T verdi) {

      int fant = 0;


      if(fant==0){
          return false;
      } else{
          return true;
      }
    }

    @Override
    public T fjern(int indeks) {

    if(antall == 0){ // om tabellen er tom så kan ingenting fjernes
        throw new IndexOutOfBoundsException("du prøver å finne indeks til en tom tabell");
    }
    Node<T> cur = hode;
    int tall =0;

    if(indeks >= 0 && indeks <= antall){
            for(int i = 0; i <= indeks; i++){
                cur = cur.neste;
                tall++;
            }

            antall--;
            endringer++;

    } else{// indeksen kan ikke være større enn siste verdien
            throw new IndexOutOfBoundsException("indeksen er utenfor tabellen");
    }

    return cur.verdi;

    }

    //Oppgave 7 første del ------------------------------
    // Måte 1 er hentet fra kildekoden for metoden clear() i klassen LinkedList i Java som beskrevet i oppgaven.
    // Link til hvor kildekode er hentet fra: https://developer.classpath.org/doc/java/util/LinkedList-source.html
    @Override
    public void nullstill() {

        //Måte 1:
        for (Node temp= hode; temp != null;) {
            Node next = temp.neste;
            temp.verdi = null;
            temp.neste = null;
            temp.forrige = null;
            temp = next;
        }
        hode=hale=null;
        antall=0;
        endringer++;

      /*  Måte 2:
     Node temp = this.hode;

     while(temp != null) {
        fjern(0);
     }
        hode=null;
        hale=null;
        antall=0;
        endringer++; */
    }

    //Oppgave 7 slutt --------------------------------------

    @Override
    public String toString() { // oppgave 2 hode->hale (finn en måte å adde inn veridene)
       StringBuilder builder = new StringBuilder();

        if(antall == 0){
            builder.append("[]");
            return builder.toString();
        }

        builder.append("[");

        Node<T> cur = hode;


        for(int i =0; i < antall; i++){

            if(i == antall-1){
                builder.append(cur.verdi);

            } else {
                builder.append(cur.verdi).append(", ");
                cur=cur.neste;
            }
        }

        builder.append("]");

        return builder.toString();
    }

    public String omvendtString() { // oppgave 2 hale->hode
        StringBuilder builder = new StringBuilder();

        if(antall == 0){
            builder.append("[]");
            return builder.toString();
        }

        builder.append("[");

        Node<T> cur = hale;


        for(int i =antall-1; i >= 0; i--){

            if(i == 0){
                builder.append(cur.verdi);

            } else {
                builder.append(cur.verdi).append(", ");
                cur=cur.forrige;
            }
        }

        builder.append("]");

        return builder.toString();

    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // "denne" starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne=finnNode(indeks);
            fjernOK=false;
            iteratorendringer=endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (!hasNext())throw new NoSuchElementException("Ingen verdier");

            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("endringer stemmer ikke");
            }

            fjernOK=true;
            T temp=denne.verdi;
            denne=denne.neste;

            return denne.verdi;
        }

        /*Kode fra foresesningen programkode 3.3.4.c

 public T next()
 {
   if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");

   fjernOK = true;            // nå kan remove() kalles
   T denneVerdi = p.verdi;    // tar vare på verdien i p
   p = p.neste;               // flytter p til den neste noden

   return denneVerdi;         // returnerer verdien
 }*/

        @Override
        public void remove() {

            Node<T> p = denne;
            if(antall == 0){
                throw new IllegalStateException("kan ikke fjerne, siden det ikke finnes noen elementer");
            }

            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("de er forskjellige");
            }

                fjernOK = false;
                if(antall == 1){
                    hale = null;
                    hode = null;
                }

                if(denne == null){
                    hale = hale.forrige;
                }

                if(denne.forrige == hode){
                    hode = hode.neste;
                }

                endringer++;
                iteratorendringer++;
                antall--;

        }

    } // class DobbeltLenketListeIterator

    //----------------OPPGAVE 10 START-----------
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        for(int i=0; i< liste.antall();i++){
            for (int j=0; j< liste.antall();j++){
                int sammenlgn=c.compare(liste.hent(i),liste.hent(j));
                if (sammenlgn<0){
                    T temp=liste.hent(i);
                    liste.oppdater(i, liste.hent(i));
                    liste.oppdater(j,liste.hent(j));
                }
            }
        }
    }
    //----------------OPPGAVE 10 FERDIG-----------

    
    
    //------------------Oppgave 3 a.1--------//
    private Node<T> finnNode(int indeks) {
        Node<T>nåværende;

        if (indeks<antall/2) {

            nåværende = hode;
            for (int i = 0; i < indeks; i++) {
                nåværende = nåværende.neste;
            }
            return nåværende;

        }
        else{
            nåværende=hale;
            for (int i = antall - 1; i > indeks; i--) {
                nåværende = nåværende.forrige;
            }
            return nåværende;
        }
    }
    //------------------Oppgave 3 a.1 er ferdig--------//

} // class DobbeltLenketListe


