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

    LinkedList liste = new LinkedList();
    public DobbeltLenketListe(T[] a) { // skal lage listen

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
    // Fikk hjelp fra     Programkode 1.2.3 a)
    private void fraTilKontroll(int tabellengde, int fra, int til) {
        tabellengde=til-fra;

        if (fra < 0)                                  // fra er negativ
            throw new ArrayIndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > tabellengde)                          // til er utenfor tabellen
            throw new ArrayIndexOutOfBoundsException
                    ("til(" + til + ") > tablengde(" + tabellengde + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
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

        if(verdi == null){
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
                liste.add(indeks, verdi);
            }

            if (liste.size() == 0 && indeks == 0) {
                liste.add(indeks, verdi);
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
    public T hent(int indeks) {
        Node <T>nåværende=finnNode(indeks);
        return nåværende.verdi;
    }


    // --------------------Oppgave 4 del 1 START -------------------------------
    @Override
    public int indeksTil(T verdi) {
        int indeks = 0;
        Node current = hode;
        while (current != null) {
            if (current.verdi == verdi) {
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

        //------------------------OPPGAVE 3.a.3 START-------------------------------------
//****Den  skal  erstatte verdien på plass indeks med nyverdi og returnere det som lå der fra før***

    if(nyverdi == null) {
        Objects.requireNonNull(nyverdi, "verdi er null");
    }
        indeksKontroll(indeks,false);
        Node<T> nåværende = finnNode(indeks);

        endringer++;
        nåværende.verdi = nyverdi;
        return nåværende.verdi;
    }
//-----------------------OPPGAVE 3.a.3 FERDIG---------------------------------------

    @Override
    public boolean fjern(T verdi) {

      int fant = 0;
      for(int i =0; i < liste.size();i++){
        if(liste.get(i).equals(verdi)){
            fant++;
            liste.remove(i);
            break;

        }
      }

      if(fant==0){
          return false;
      } else{
          return true;
      }

    }

    @Override
    public T fjern(int indeks) {

        if(liste.size() == 0){ // om tabellen er tom så kan ingenting fjernes
            throw new IndexOutOfBoundsException("du prøver å finne indeks til en tom tabell");
        }
        int index = 0;
        Node<T> cur = hode;
        if(indeks >= 0 && indeks <= liste.size()-1){

            for(int i = 0; i <= indeks; i++){
               cur = cur.neste;
               index++;
            }
            antall--;
            endringer++;
            liste.remove(index);

        } else{// indeksen kan ikke være større enn siste verdien
            throw new IndexOutOfBoundsException("indeksen er utenfor tabellen");
        }

        return cur.verdi;



    }

    //Oppgave 7 første del ------------------------------
    //1 av 2 deler skrevet, ikke testet ennå
    @Override
    public void nullstill() {
         Node temp = this.hode;

        while (temp != null) {
            temp = null;
            endringer ++;
            temp = temp.neste;
        }
        hode=null;
        hale=null;
        antall=0;
    }
    //Oppgave 7 slutt --------------------------------------

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
            throw new UnsupportedOperationException();
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
        }}
    //------------------Oppgave 3 a.1 er ferdig--------//

} // class DobbeltLenketListe


