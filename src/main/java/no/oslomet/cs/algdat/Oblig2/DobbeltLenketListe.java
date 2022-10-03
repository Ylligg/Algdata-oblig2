package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


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
    this.hode = null;
    this.hale = null;


        if(a == null) { // riktig
            Objects.requireNonNull(a, "Tabellen a er null!"); // gir en melding hvis tabellen er null
        }

        if(a.length > 0){
            int i =0;
            for(; i < a.length;i++) {

                if (a[i] != null) { // om verdien er ikke null så addes den inn i listen
                    hode = new Node<>(a[i]); // lager en ny node
                    antall++; // øker variabel antall av antall elementer i tabellen
                    break;
                }
            }
            hale = hode;
            i++;
            for(; i < a.length;i++){
                if (a[i] != null) {

            }
        }



        // må finne en måte å implimintere koden for linked lists


            antall++;

            if (a[i] == null) { // finne posisjonen til node tabellen, om den er null så skal den ikke tas med
               antall--;// om det er en posisjon som er null så reduseres antallet med en
            }


        }
        //System.out.println(liste + " hei");
    }


    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

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
    public boolean leggInn(T verdi) { // finn en måte å legge inn verdier på idk how.

        // må finne en måte å imlimintere hode slik at den kan oppdateres til å peke mot den nye Noden siden når vi adder ny Node så

        if(verdi == null){
            Objects.requireNonNull(verdi, "Verdien er null!"); // gir en melding hvis tabellen er null
            return false;
        } else{
            liste.add(verdi);
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
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() { // oppgave 2 hode->hale (finn en måte å adde inn veridene)
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(liste.toString());

        return joiner.toString();
    }


    public String omvendtString() { // oppgave 2 hale->hode
        StringJoiner joiner = new StringJoiner(","); // trenger denne

        // dette er bare feil

        for(int i = liste.size(); i>0;i--){
            omvendtliste.add(i);
        }
        joiner.add(omvendtliste.toString());
        omvendtliste.clear(); // skal egt ikke ha dette men må pga løkka

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

} // class DobbeltLenketListe


