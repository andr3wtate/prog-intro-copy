public class IntListInd extends IntList {

    private int trueNumb, indRowFirst, indColFirst;

    IntListInd(int indRowFirst, int indColFirst){
        super();
        this.indRowFirst = indRowFirst;
        this.indColFirst = indColFirst;
    }

    public int getTrueNumb() {
        return trueNumb;
    }

    public int getRowIndFirst(){
        return indRowFirst;
    }

    public int getColIndFirst() {
        return indColFirst;
    }

    public void addWords(int cnt) {
        trueNumb += cnt;
    }

}