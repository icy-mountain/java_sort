import java.util.Random;

// バブルソート
public class BubbleSort {
  public static void main(String[] args) {
    List l = new List(10000); // 引数はデータの個数

    l.makeList();
    //l.printList();

    long t1 = System.currentTimeMillis(); // ソート前の時刻の保持(ms)
    l.bubbleSort();
    long t2 = System.currentTimeMillis(); // ソート後の時刻の保持(ms)
    System.out.println((t2 - t1) + "ms");
    //l.printList();
  }
}


// リストのクラス
class List {

  // ヘッダと「番兵」の作成
  Cell header = new Cell("HEADER");
  Cell sentinel = new Cell("");

  int dataNum; // セルの個数を格納するフィールド

  // コンストラクタ（引数 num を dataNum に設定，リストの末尾に「番兵」を設定）
  List(int num) {
    dataNum = num; // セルの個数を設定
    header.next = sentinel; // リストの末尾に「番兵」を設定
  }
  
  // リストの中身の作成（0～dataNumの整数をランダムな順番でリストに格納する）
  void makeList() {
    int[] tmpList = new int[dataNum]; // セルに格納する値を格納する配列を作成

    for (int i = 0; i < dataNum; i++) { // 配列に0～dataNum-1までの数値を格納
      tmpList[i] = i;
    }
    
    for (int i = 0; i < dataNum - 1; i++) { // 配列の数値をシャッフル
      Random rnd = new Random();
      int offset = rnd.nextInt(dataNum - i);
      int tmp;
      
      tmp = tmpList[i];
      tmpList[i] = tmpList[i + offset];
      tmpList[i + offset] = tmp;
    }
    
    for (int i = 0; i < dataNum; i++) { // 配列に格納された値をセルに格納
      insertTop(new Cell(new Integer(tmpList[i])));
    }
  }

  // バブルソート
  void bubbleSort() {
      Cell curr = header.next;
      int i = 0; // counter

      // evaluate function
      while(curr.next != sentinel && curr != sentinel){
	  Object obj1 = curr.data;
	  String objStr1 = obj1.toString();
	  int num1 = new Integer(objStr1); // object to int
	  
	  Object obj2 = curr.next.data;
	  String objStr2 = obj2.toString();
	  int num2 = new Integer(objStr2); // object to int
	 
	  // down state  
	  if(num1>num2){
	      swapContents(curr , curr.next);
	      i+=1;
	  }
	  curr = curr.next;
      }
      // recall
      if(i!=0){
	  bubbleSort();
      }
  }
  
  // セル c1 とセル c2 の中身を入れ替え
  void swapContents(Cell c1, Cell c2) {
    if ((c1 != null) && (c2 != null)) {
      Object tmp = c1.data;
      c1.data = c2.data;
      c2.data = tmp;
    }
  }

  // 先頭（headerの後ろ）にセルを挿入する
  void insertTop(Cell c) {
      c.next=header.next;
      header.next=c;
  }
  
  // リストを先頭から表示（ヘッダと番兵は除く）
  void printList() {
    Cell curr = header.next;
      
    while (curr.next != null) {
      System.out.print(curr.data + " ");
      curr = curr.next;
    }
    System.out.println();
  }
}


// セルのクラス
class Cell {
  Cell next;
  Object data;

  Cell(Object obj) {
    next = null;
    data = obj;
  }
}