package mi;


class MyPair implements Comparable<MyPair>
{
int rid;
int val;

public MyPair()
{
}

public MyPair(int paramInt1, int paramInt2)
{
  this.rid = paramInt1; this.val = paramInt2;
}
public int compareTo(MyPair paramMyPair) {
  return this.val != paramMyPair.val ? paramMyPair.val - this.val : this.rid - paramMyPair.rid;
}
}