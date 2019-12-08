import java.util.Random;

// バブルソート
public class CombSort {
    public static void main(String[] args) {
      int n=300;
      List l = new List(n); // 引数はデータの個数
      
      l.makeList();
      //l.printList();
      
      long t1 = System.currentTimeMillis(); // ソート前の時刻の保持(ms)
      l.combSort(n);
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
    
    void combSort(int sum){
	Cell head=header.next;
	Cell tail=header.next;
	int span = (int)(sum / 1.3);
	int c =0;
	
	if (span==9 || span==10){
	    span=11;
	}
	//System.out.println(span);
	
	for(int i =0 ; i<span ;i++){
	    tail=tail.next;
	}
	
	while(tail != sentinel){
	    Object obj1 = head.data;
	    String objStr1 = obj1.toString();
	    int num1 = new Integer(objStr1); // object to int
	    
	    Object obj2 = tail.data;
	    String objStr2 = obj2.toString();
	    int num2 = new Integer(objStr2); // object to int
	    
	    if(num1>num2){
		swapContents(head,tail);
		c++;
	    }
	    head=head.next;
	    tail=tail.next;
	}
	if(c!=0){
	    combSort(span);
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