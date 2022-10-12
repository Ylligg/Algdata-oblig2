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

            if(v == a.length){ // hvis hele tabellen er null så skjer ingenting
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
        fraTilKontroll(fra,til); //Sjekkes om indeksene fra og til er lovlige.

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

            Objects.requireNonNull(verdi, "verdien er null!"); // gir en melding hvis tabellen er null
            indeksKontroll(indeks, true);

            if(antall == 0 || antall == indeks){
                leggInn(verdi);
                return;
            }

            Node<T> r = finnNode(indeks);
            Node<T> p = r.forrige;
            Node<T> q = new Node<>(verdi,p, r);

            if(indeks == 0){
                r.forrige = q;
                hode = q;
            } else {
                p.neste = q;
                r.forrige = q;
            }

        endringer++;
        antall++;
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) == -1){
            return false;
        }
        return true;
    }


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

        int indeks = 0;
        if(tom()){
            return -1;
        }
        if(verdi == null){
            return -1;
        }

        Node<T> current = hode;
        while (current != null) {
            if (current.verdi.equals(verdi)) {
                return indeks;

            } else {
                indeks++;
                current = current.neste;
              }
        }

        return -1;
    }
    // --------------------Oppgave 4 del 1 SLUTT -------------------------------

    @Override
    public T oppdater(int indeks, T nyverdi) {

        Objects.requireNonNull(nyverdi, "verdi er null");
        indeksKontroll(indeks,false);

        Node<T> nåværende = finnNode(indeks);

        T  gammel = nåværende.verdi;
        nåværende.verdi = nyverdi;
        endringer++;
        return gammel;
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) return false;          // ingen nullverdier i listen
        boolean funnet = false;
        Node<T> q = hode;
        Node<T> p = null;
        Node<T> r = hode.neste;     // hjelpepekere

        while (q != null) {   // noe feil her                   // q skal finne verdien t

            if (q.verdi.equals(verdi)) {
                antall--;                                 // en node mindre i listen
                endringer++;
                funnet = true;
                break;       // verdien funnet

            }
            p = q;
            q = q.neste;
            if(q!=null)r = q.neste;                     // p er forgjengeren til q
        }


        if (q == null) return false;              // fant ikke verdi
        if(antall==0){
            hode=null;
            hale=null;
        }
        else if (q == hode) {
            hode = hode.neste;                    // går forbi q
            hode.forrige = null;
        }
        else if (q == hale) {
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            p.neste = r;
            r.forrige=p;  // går forbi q
        }

      return funnet;
    }

    @Override
    public T fjern(int indeks) {

        if(antall == 0){
            throw new IndexOutOfBoundsException("Det er ikke noe å fjerne");
        }

        if(indeks >= antall){
            throw new IndexOutOfBoundsException("Den posisjonen du prøver å fjerne er utenfor tabellen");
        }

        T temp;                              // hjelpevariabel
        if (indeks < 0 || indeks > antall) {
            throw new IndexOutOfBoundsException(melding(indeks));
        } else {

            if (indeks == 0)                     // skal første verdi fjernes?
            {
                temp = hode.verdi;                 // tar vare på verdien som skal fjernes
                hode = hode.neste;                 // hode flyttes til neste node
                if (antall == 1) hale = null;      // det var kun en verdi i listen
            } else if (indeks == antall-1) {
                temp = hale.verdi;
                hale = hale.forrige;
            } else {

                Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
                Node<T> q = p.neste;               // q skal fjernes
                temp = q.verdi;                    // tar vare på verdien som skal fjernes
                Node<T> r = q.neste;
                p.neste= r;
                r.forrige=p;
            }

            antall--;                            // reduserer antallet
            endringer++;
        }

        return temp;                         // returner fjernet verdi

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

        if(antall == 0){
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
        builder.append("[");

        Node<T> cur = hode;

            for(int i=0; i < antall; i++){
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
        if(antall == 0){
            return "[]";
        }

        StringBuilder builder = new StringBuilder();
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
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException("endringer stemmer ikke");
            }

            if (!hasNext())throw new NoSuchElementException("Ingen verdier");

            fjernOK=true;
            T temp=denne.verdi;
            denne=denne.neste;
            return temp;
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

            if(!fjernOK){
                throw new IllegalStateException("du kan ikke fjerne");
            }

            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("de er forskjellige");
            }

            fjernOK = false;

            if(antall == 1){
                hale = null;
                hode = null;
            }

            else if(denne == null){
                hale = hale.forrige;
                hale.neste = null;
            }

            else if(denne.forrige == hode){
                hode = hode.neste;
                hode.forrige = null;
            }

            else{
                Node<T> r = denne;
                Node<T> p = denne.forrige.forrige;
                p.neste = r;
                r.forrige = p;

            }

                endringer++;
                iteratorendringer++;
                antall--;

        }

    } // class DobbeltLenketListeIterator

    //----------------OPPGAVE 10 START-----------
    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {

        for(int i=0; i< liste.antall();i++){
            for (int j=1; j< liste.antall()-i;j++){

                T value1 = liste.hent(j);
                T value2 = liste.hent(j-1);

                int sammenlgn=c.compare(value1,value2);

                if (sammenlgn<0){
                    T temp=liste.hent(j);
                    liste.oppdater(j, liste.hent(j-1));
                    liste.oppdater(j-1,temp);
                }
            }
        }
    }


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

} // class DobbeltLenketListe


