public class Apple implements Comparable<Apple>{
    public int deliciousness;
    public int type;

    public Apple(int type, int deliciousness){
        this.deliciousness = deliciousness;
        this.type = type;
    }


    @Override
    public int compareTo(Apple o) {

        if(this.deliciousness < o.deliciousness)
            return -1;
        if(this.deliciousness > o.deliciousness)
            return 1;
        return 0;
    }
}
