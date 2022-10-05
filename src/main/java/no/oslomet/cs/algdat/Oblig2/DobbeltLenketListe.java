package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;


import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Objects;
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


        if(a == null) {
            Objects.requireNonNull(a, "Tabellen a er null!"); // gir en melding hvis tabellen er null
        }

        if(a.length > 0){
            int i =0;
            for(; i < a.length;i++) {

                if (a[i] != null) { // om verdien er ikke null så lages det en ny node
                    hode = new Node<>(a[i]); // den nye noden blir hoden siden den blir adda fra ventre siden
                    antall++; // øker variabel antall av antall elementer i tabellen
                    break; // går ut av løkka
                }
            }
            hale = hode; // hvis det er bare en verdi så vil hode og hale være i samme posisjon
            i++;
            for(; i < a.length;i++){
                if (a[i] != null) {
                    hale = new Node<>(a[i]); // nye noden blir hale
                    hale.neste = hale; // halen fortsetter til slutten av tabellen
                    antall++;
                }
            }
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

        // må finne en måte å imlimintere hode slik at den kan oppdateres til å peke mot den nye Noden siden når vi adder ny Node så

        if(verdi == null){
            Objects.requireNonNull(verdi, "Verdien er null!"); // gir en melding hvis tabellen er null
            return false;
        } else{
            antall++;
            return true;
        }

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
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks,false);
        Node<T> nåværende = finnNode(indeks);

        endringer++;

        nåværende.verdi = nyverdi;

        return nåværende.verdi;
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

    //Oppgave 7 1.utkast
    @Override
    public void nullstill() {
        Node temp = new Node<>();

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
        StringJoiner joiner = new StringJoiner(",");


        return joiner.toString();
    }


    public String omvendtString() { // oppgave 2 hale->hode
        StringJoiner joiner = new StringJoiner(","); // trenger denne



        return joiner.toString(); // riktig

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


