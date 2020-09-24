import java.lang.Math;

public class Test {

  public static void main(String[] args) throws NumberFormatException {
    printInBinaryFormat(UTF32_to_UTF8(Integer.valueOf(args[0], 16)));
    System.out.println();
    //System.out.println("0x4F36 In Binary Format: " + Integer.toBinaryString(0x4F36));
  }

  public static byte[] UTF32_to_UTF8(int unicode){
    byte[] utf8;
    if (unicode <= Math.pow(2, 7)) {
      System.out.println("unicode <= Math.pow(2, 7)");
      utf8 = new byte[1];
      utf8[0] = (byte) unicode;
      System.out.println(unicode);
      System.out.println(utf8[0]);

    } else if (unicode <= Math.pow(2, 11)) {
      System.out.println("unicode <= Math.pow(2, 11)");
      utf8 = new byte[2];
      utf8[1] = (byte) (unicode % (int)Math.pow(2, 6) | (byte) 0b1000_0000);
      unicode = unicode >>> 6;
      utf8[0] = (byte)(unicode | ((byte) 0b1100_0000));

    } else if (unicode <= Math.pow(2, 16)) {
      System.out.println("unicode <= Math.pow(2, 16)");
      utf8 = new byte[3];
      utf8[2] = (byte) (unicode % (int)Math.pow(2, 6) | (byte) 0b1000_0000);
      unicode = unicode >>> 6;
      utf8[1] = (byte) (unicode % (int)Math.pow(2, 6) | (byte) 0b1000_0000);
      unicode = unicode >>> 6;
      utf8[0] = (byte)(unicode | ((byte) 0b1110_0000));

    } else if (unicode <= Math.pow(2, 21)) {
      System.out.println("unicode <= Math.pow(2, 21)");
      utf8 = new byte[4];

      utf8[3] = (byte) (unicode % (int)Math.pow(2, 6) | (byte) 0b1000_0000);
      unicode = unicode >>> 6;
      utf8[2] = (byte) (unicode % (int)Math.pow(2, 6) | (byte) 0b1000_0000);
      unicode = unicode >>> 6;
      utf8[1] = (byte) (unicode % (int)Math.pow(2, 6) | (byte) 0b1000_0000);
      unicode = unicode >>> 6;
      utf8[0] = (byte)(unicode | ((byte) 0b1111_0000));
    } else {
      System.out.println("ERROR too big");
      return null;
    }

    return utf8;
  }

  public static void printInBinaryFormat(byte[] byteArray) {
    System.out.print("In Binary Format:");
    for (int i = 0; i < byteArray.length; i++) {
      //System.out.println(byteArray[i]);
      String str = String.format("%8s", Integer.toBinaryString(byteArray[i] & 0xff)).replace(' ', '0');
      str = new StringBuilder(str).insert(4, " ").toString();
      System.out.print(str + "  ");
    }
    System.out.println();


  }

}
