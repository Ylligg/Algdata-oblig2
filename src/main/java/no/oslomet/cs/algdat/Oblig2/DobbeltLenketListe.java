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


    //--------Oppgave1 -----------------
    LinkedList liste = new LinkedList();
    public DobbeltLenketListe(T[] a) { // skal lage listen dobbel lenket liste

        DobbeltLenketListe<Integer> list = new DobbeltLenketListe<>();
        System.out.println(list.toString() + " " + list.omvendtString());
        for (int i = 1; i <= 2; i++) {
            list.leggInn(i);
            System.out.println(list.toString() + " " + list.omvendtString());
        }

        if(a == null) { // kaster en feil hvis tabellen er null
            Objects.requireNonNull(a, "Tabellen a er null!"); // gir en melding hvis tabellen er null
        }

        if(a.length > 0){
            int v = 0; // har en variabel for effektiviteten av søket

                for(int i =0; i < a.length;i++){ // ser gjennom om de første verdiene er null, hvis ikke så setter den  verdien til å bli hode
                    if(a[v] == null) v++; // fortsetter til den ikke er null

                    else { // når den ikke er null så er den hode og løkka er ferdig
                       hode= new Node<>(a[v]);
                       break;
                    }
                }

            hode = new Node<>(a[0]); // ellers så er første posisjonen hode
            Node current = hode; // lager en variabel for å finne

            for(int i =v; i < a.length;i++){ // gjør det mer effektiv hvis de første veridene er null så starter vi med løkka til når det ikke er null
                Node ny = new Node(a[i]);
                ny.forrige = current;
                current.neste = ny;
                current = ny;
                antall++;

                if(a[i]==null) {
                    antall--;
                    continue;
                } else{
                    liste.add(a[i]);
                }
            }
            hale = current;

        }
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
    private void fraTilKontroll(int tabellengde, int fra, int til) {
        tabellengde=til-fra;
        if (fra < 0 || til > antall) {
            throw new IndexOutOfBoundsException();
        }
        if (fra > til) {
            throw new IllegalArgumentException();
        }
    }
    //---------Oppgave 3.b hjelpemetode ferdig--------


    //--------Oppgave1 -----------------
    @Override
    public int antall() {
        return antall; // returnerer antall
    }

    //--------Oppgave1 -----------------
    @Override
    public boolean tom() { // ser om tabellen er tom
        if(antall != 0) {return false;}
        return true;
    }
    //--------Oppgave1 -----------------
    @Override
    public boolean leggInn(T verdi) {

        if(verdi == null){
            Objects.requireNonNull(verdi, "Verdien er null!"); // gir en melding hvis tabellen er null
            return false;
        }

        Node lagtinn = new Node(verdi);
        endringer++;
        antall++;
        liste.add(lagtinn.verdi);
        return true;

    }


    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();

        //------------------------OPPGAVE 3.a.3 START-------------------------------------
//****Den  skal  erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før***


    }
//-----------------------OPPGAVE 3.a.3 FERDIG---------------------------------------

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }
    
    //-----------------------Oppgave 7------------------------
    // 1.utkast
    @Override
    public void nullstill() {
        Node temp = new Node<>("");

        while (this.hode != null) {
            temp = this.hode;
            this.hode = this.hode.neste;
            temp = null;
            endringer ++;
        }
        hode=null;
        hale=null;
        antall=0;
    }

    @Override
    public String toString() { // oppgave 2 hode->hale (finn en måte å adde inn veridene)
        StringJoiner joiner = new StringJoiner(", ", "[","]");

        if(liste.size() == 0){
            joiner.add("");
            return joiner.toString();
        }
            hode = new Node(liste.get(0));
            for (int i = 0; i < liste.size(); i++) {
                Node høyre = new Node(liste.get(i));
                hode.neste = høyre;
                joiner.add(hode.neste.verdi.toString());
            }

        return joiner.toString();
    }


    public String omvendtString() { // oppgave 2 hale->hode
        StringJoiner joiner = new StringJoiner(", ", "[", "]"); // trenger denne

        if(liste.size() == 0){
            joiner.add("");
            return joiner.toString();
        }
            hale = new Node(liste.get(liste.size()-1));
            for (int i = liste.size()-1; i >= 0; i--) {
                Node venstre = new Node(liste.get(i));
                hale.forrige = venstre;
                joiner.add(hale.forrige.verdi.toString());
            }

        return joiner.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
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
        }}
    //------------------Oppgave 3 a.1 er ferdig--------//

} // class DobbeltLenketListe


