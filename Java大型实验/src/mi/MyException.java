package mi;
class MyException extends Exception
{
  private static final long serialVersionUID = 1L;
  int wm;

  public MyException(String paramString)
  {
    super(paramString);
  }
  public MyException(String paramString, int paramInt) {
    super(paramString);
    this.wm = paramInt;
  }
}