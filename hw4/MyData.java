class MyData{
    public Integer index;
    public Integer count;
    public String value;
    public MyData() {
        index = Integer.MAX_VALUE;
        count = 0;
        value = "";
    }
    public MyData(Integer a, Integer b, String c) {
        index = a;
        count = b;
        value = c;
    }
}
