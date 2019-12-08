import java.util.Random;

// マージソート
public class MergeSort {
  public static void main(String[] args) {
      List l = new List(100); // 引数はデータの個数

    l.makeList();
    //l.printList();

    long t1 = System.currentTimeMillis(); // ソート前の時刻の保持(ms)
    l.mergeSort();
    long t2 = System.currentTimeMillis(); // ソート後の時刻の保持(ms)
    System.out.println((t2 - t1) + "ms");
    // System.out.println(" l Data "+l.header.next.data);
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
    
  // マージソート（最初に1度だけ呼ぶメソッド）
  void mergeSort() {
    int center = dataNum / 2;
    Cell curr = header.next;
    
    for (int i = 0; i < center; i++) curr = curr.next;
    List list1 = mergeSort(header.next, curr, center);
    List list2 = mergeSort(curr, sentinel, dataNum - center);

    List list3 = merge(list1, list2);

    header = list3.header;
    sentinel = list3.sentinel;
  }

    // マージソート（再帰部分：セル start からセル end の一つ手前までの num 個のデータをマージソート）
    List mergeSort(Cell start, Cell end, int num) {    
	/* この部分を作成する */
	if(num>=2){
	    //System.out.println("At 3 ");
	     int center = num/ 2;
	     Cell curr = start;
    
	     for (int i = 0; i < center; i++) curr = curr.next;
    
	     List list1 = mergeSort(start, curr, center);
	     List list2 = mergeSort(curr, sentinel, num - center);

	     List list3 = merge(list1, list2);

	     header = list3.header;
	     sentinel = list3.sentinel;
	  return list3;
       }else if(num==1){
	    // System.out.println("At 1 ");
	    List listAt1=new List(0);
	    listAt1.header.next=null;
	    listAt1.insertTop(start);
	    return listAt1;
	}else{
	    List listAt0=new List(0);
	    //System.out.println("At 0 ");
	    listAt0.header.next=null;
	    return listAt0;
	}
    }  


  // マージ（リスト list1 とリスト list2 をマージして一つのリストにして返す）
  List merge(List list1, List list2) {
      List listInMerge=new List(0);
      listInMerge.header.next=null;
      Cell curr1=list1.header.next;
      Cell curr2=list2.header.next;
      
      //System.out.println("curr1Data "+curr1.data);
      //System.out.println("curr2Data "+curr2.data);
      
      while((curr1 != null) && (curr2 != null)){
	  /* Object obj1 = curr1.data;
	     String objStr1 = obj1.toString();*/
	  int num1 = new Integer(curr1.data.toString()); // object to int
	  
	  /* Object obj2 = curr2.data;
	     String objStr2 = obj2.toString();*/
	  int num2 = new Integer(curr2.data.toString()); // object to int
	  if(num1<num2){
	      Cell test1=curr1.next;
	      Cell currC=listInMerge.header;
	      while(currC.next != null ){
		  currC=currC.next;
	      }
	      curr1.next=null;
	      currC.next=curr1;
	      curr1=test1;
	  }else{
	      Cell test2=curr2.next;
	      Cell currD=listInMerge.header;
	      while(currD.next != null ){
		  currD=currD.next;
	      }
	      curr2.next=null;
	      currD.next=curr2;
	      curr2=test2;
	  }
      }
      
      Cell curr3=listInMerge.header;
      
      if(curr1 == null){
	  while(curr3.next != null ){
	      curr3=curr3.next;
	  }
	  curr3.next=curr2;
      }else if(curr2 == null){
	  while(curr3.next != null ){
	      curr3=curr3.next;
	  }
	  //System.out.println("  curr3Data     "+currTest1.data);
	  curr3.next=curr1;
      }
      /*
       while(currTest1 != null ){
	   System.out.println("  curr3Data     "+currTest1.data);
	  currTest1=currTest1.next;    
	  }*/
      return listInMerge;
      /* この部分を作成する */
      
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
    /* 練習8で作成 */
  }
  
  // リストを先頭から表示（ヘッダと番兵は除く）
  void printList() {
    Cell curr = header.next;
      
    while (curr != null) {
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
