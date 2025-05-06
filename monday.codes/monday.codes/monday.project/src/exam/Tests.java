package exam;

import datastructures.bag.SB;
import datastructures.tree.binary.BT;
import datastructures.tree.general.GT;

import java.util.Comparator;

class Tests {
  public static void main(String[] args) {
    test.unit.TestSuite.run(
        test_SB_insert
        , test_BT_height
        , test_BT_balanced
        , test_BT_zigZag
        , test_GT_heap
    );
  }

  static SB.Node<Integer> sbEmpty() {return null; }
  static SB.Node<Integer> sb_10_1() { return
      SB.Node.of(10, 1, null);
  }
  static SB.Node<Integer> sb_10_2() { return
      SB.Node.of(10, 2, null);
  }
  static SB.Node<Integer> sb_5_1_10_2() { return
      SB.Node.of(5, 1, SB.Node.of(10, 2, null));
  }
  static SB.Node<Integer> sb_10_2_15_1() { return
      SB.Node.of(10, 2, SB.Node.of(15, 1, null));
  }
  static SB.Node<Integer> sb_10_2_12_1_15_1() { return
      SB.Node.of(10, 2, SB.Node.of(12, 1, SB.Node.of(15, 1, null)));
  }
  static SB.Node<Integer> sb_10_2_12_2_15_1() { return
      SB.Node.of(10, 2, SB.Node.of(12, 2, SB.Node.of(15, 1, null)));
  }
  static SB.Node<Integer> sb_10_1_20_1_rec() {
    var node = SB.Node.of(20, 1, null);
    node.next = node;
    return SB.Node.of(10, 1, node);
  }

  static test.unit.TestSuite test_SB_insert = new test.unit.TestSuite("SB insert",
      new test.unit.EqualBy<>("SB insert 10 on empty bag",
          datastructures.bag.Ops::sameElements,
          () -> Exam.insert(10, sbEmpty(), Comparator.naturalOrder()),
          sb_10_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB insert 10 twice on empty bag",
          datastructures.bag.Ops::sameElements,
          () -> Exam.insert(10, sb_10_1(), Comparator.naturalOrder()),
          sb_10_2(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB insert front",
          datastructures.bag.Ops::sameElements,
          () -> Exam.insert(5, sb_10_2(), Comparator.naturalOrder()),
          sb_5_1_10_2(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB insert back",
          datastructures.bag.Ops::sameElements,
          () -> Exam.insert(15, sb_10_2(), Comparator.naturalOrder()),
          sb_10_2_15_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB insert middle",
          datastructures.bag.Ops::sameElements,
          () -> Exam.insert(12, sb_10_2_15_1(), Comparator.naturalOrder()),
          sb_10_2_12_1_15_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("SB insert middle repeated",
          datastructures.bag.Ops::sameElements,
          () -> Exam.insert(12, sb_10_2_12_1_15_1(), Comparator.naturalOrder()),
          sb_10_2_12_2_15_1(),
          datastructures.bag.Ops::toString
      )
      ,
      new test.unit.Assert("SB insert middle uses order",
          () -> {
            var sb = Exam.insert(15, sb_10_1_20_1_rec(), Comparator.naturalOrder());
            return sb.element == 10 && sb.next.element == 15 && sb.next.next.element == 20
                && sb.occurrences == 1 && sb.next.occurrences == 1 && sb.next.next.occurrences == 1;
          }
      )
      ,
      new test.unit.Assert("SB insert front uses order",
          () -> {
            var sb = Exam.insert(10, sb_10_1_20_1_rec(), Comparator.naturalOrder());
            return sb.element == 10 && sb.next.next.element == 20
                && sb.occurrences == 2 && sb.next.occurrences == 1;
          }
      )
  );

  static BT.Node<Integer> btEmpty() {
    return
        null;
  }
  static BT.Node<Integer> bt_1() { return
      BT.Node.of(1);
  }
  static BT.Node<Integer> bt_1_2_3() { return
      BT.Node.of(1,
          BT.Node.of(2),
          BT.Node.of(3)
      );
  }
  static BT.Node<Integer> bt_1_2_null() { return
      BT.Node.of(1,
          BT.Node.of(2),
          null
      );
  }
  static BT.Node<Integer> bt_1_null_2() { return
      BT.Node.of(1,
          null,
          BT.Node.of(2)
      );
  }
  static BT.Node<Integer> bt_1_2_4_5_3() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(4),
              BT.Node.of(5)
          ),
          BT.Node.of(3)
      );
  }
  static BT.Node<Integer> bt_1_2_3_4_5() { return
      BT.Node.of(1,
          BT.Node.of(2),
          BT.Node.of(3,
              BT.Node.of(4),
              BT.Node.of(5)
          )
      );
  }
  static BT.Node<Integer> bt_1_2_3_4_5_6_7() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3),
              BT.Node.of(4)
          ),
          BT.Node.of(5,
              BT.Node.of(6),
              BT.Node.of(7)
          )
      );
  }
  static BT.Node<Integer> bt_balanced_1_2_3_4_5_6_7() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(4),
              BT.Node.of(5)
          ),
          BT.Node.of(3,
              BT.Node.of(6),
              BT.Node.of(7)
          )
      );
  }
  static BT.Node<Integer> bt_balanced_1_2_3_4_5_6_7_8() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(4),
              BT.Node.of(5)
          ),
          BT.Node.of(3,
              BT.Node.of(6,
                  BT.Node.of(8),
                  null
              ),
              BT.Node.of(7)
          )
      );
  }
  static BT.Node<Integer> bt_not_balanced_1_2_3_4_5_6_7_8_9() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(4,
                  BT.Node.of(5),
                  null
              ),
              null
          ),
          BT.Node.of(3,
              BT.Node.of(6,
                  BT.Node.of(8),
                  BT.Node.of(9)
              ),
              BT.Node.of(7)
          )
      );
  }
  static BT.Node<Integer> bt_not_balanced_1_2_3_4_5_6_7() { return
      BT.Node.of(1,
          null,
          BT.Node.of(3,
              BT.Node.of(6,
                  BT.Node.of(7),
                  null
              ),
              BT.Node.of(8)
          )
      );
  }
  static BT.Node<Integer> bt_not_balanced_1_2_3() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3, null, null),
              null
          ),
          null);
  }

  static BT.Node<Integer> bt_zig_zag_before1() { return
      null;
  }
  static BT.Node<Integer> bt_zig_zag_after1() { return
      BT.Node.of(1);
  }
  static BT.Node<Integer> bt_zig_zag_before2() { return
      BT.Node.of(1);
  }
  static BT.Node<Integer> bt_zig_zag_after2() { return
      BT.Node.of(1, BT.Node.of(2), null);
  }
  static BT.Node<Integer> bt_zig_zag_before3() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3),
              BT.Node.of(4)
          ),
          BT.Node.of(5)
      );
  }
  static BT.Node<Integer> bt_zig_zag_after3() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3),
              BT.Node.of(4,
                  BT.Node.of(6),
                  null
              )
          ),
          BT.Node.of(5)
      );
  }
  static BT.Node<Integer> bt_zig_zag_before4() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3,
                  BT.Node.of(4),
                  null
              ),
              BT.Node.of(5,
                  BT.Node.of(6),
                  null
              )
          ),
          BT.Node.of(6,
              BT.Node.of(7,
                  BT.Node.of(8),
                  null
              ),
              BT.Node.of(9,
                  BT.Node.of(10),
                  null
              )
          )
      );
  }
  static BT.Node<Integer> bt_zig_zag_after4() { return
      BT.Node.of(1,
          BT.Node.of(2,
              BT.Node.of(3,
                  BT.Node.of(4),
                  null
              ),
              BT.Node.of(5,
                  BT.Node.of(6,
                      null,
                      BT.Node.of(11)
                  ),
                  null
              )
          ),
          BT.Node.of(6,
              BT.Node.of(7,
                  BT.Node.of(8),
                  null
              ),
              BT.Node.of(9,
                  BT.Node.of(10),
                  null
              )
          )
      );
  }


  static test.unit.TestSuite test_BT_height = new test.unit.TestSuite("BT height",
      new test.unit.Equal<>("BT height empty",
          () -> Exam.height(btEmpty()), 0
      )
      ,
      new test.unit.Equal<>("BT height 1",
          () -> Exam.height(bt_1()), 1
      )
      ,
      new test.unit.Equal<>("BT height 2",
          () -> Exam.height(bt_1_2_3()), 2
      )
      ,
      new test.unit.Equal<>("BT height 3",
          () -> Exam.height(bt_1_2_null()), 2
      )
      ,
      new test.unit.Equal<>("BT height 4",
          () -> Exam.height(bt_1_null_2()), 2
      )
      ,
      new test.unit.Equal<>("BT height 5",
          () -> Exam.height(bt_1_2_4_5_3()), 3
      )
      ,
      new test.unit.Equal<>("BT height 6",
          () -> Exam.height(bt_1_2_3_4_5()), 3
      )
      ,
      new test.unit.Equal<>("BT height 7",
          () -> Exam.height(bt_1_2_3_4_5_6_7()), 3
      )
  );

  static test.unit.TestSuite test_BT_balanced = new test.unit.TestSuite("BT balanced",
      new test.unit.Assert("BT balanced empty",
          () -> Exam.isBalanced(btEmpty())
      )
      ,
      new test.unit.Assert("BT balanced unitary",
          () -> Exam.isBalanced(bt_1())
      )
      ,
      new test.unit.Assert("BT balanced 1",
          () -> Exam.isBalanced(bt_1_2_3())
      )
      ,
      new test.unit.Assert("BT balanced 2",
          () -> Exam.isBalanced(bt_1_2_null())
      )
      ,
      new test.unit.Assert("BT balanced 3",
          () -> Exam.isBalanced(bt_1_null_2())
      )
      ,
      new test.unit.Assert("BT balanced 4",
          () -> Exam.isBalanced(bt_1_2_4_5_3())
      )
      ,
      new test.unit.Assert("BT balanced 5",
          () -> Exam.isBalanced(bt_1_2_3_4_5())
      )
      ,
      new test.unit.Assert("BT balanced 6",
          () -> Exam.isBalanced(bt_balanced_1_2_3_4_5_6_7())
      )
      ,
      new test.unit.Assert("BT balanced 7",
          () -> Exam.isBalanced(bt_balanced_1_2_3_4_5_6_7_8())
      )
      ,
      new test.unit.Refute("BT not balanced 8",
          () -> Exam.isBalanced(bt_not_balanced_1_2_3_4_5_6_7_8_9())
      )
      ,
      new test.unit.Refute("BT not balanced 9",
          () -> Exam.isBalanced(bt_not_balanced_1_2_3_4_5_6_7())
      )
      ,
      new test.unit.Refute("BT not balanced 10",
          () -> Exam.isBalanced(bt_not_balanced_1_2_3())
      )
  );

  static test.unit.TestSuite test_BT_zigZag = new test.unit.TestSuite("BT zig zag insert",
      new test.unit.EqualBy<>("BT zig zag 1",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.zigZagInsert(1, bt_zig_zag_before1()),
          bt_zig_zag_after1(),
          datastructures.tree.binary.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("BT zig zag 2",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.zigZagInsert(2, bt_zig_zag_before2()),
          bt_zig_zag_after2(),
          datastructures.tree.binary.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("BT zig zag 3",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.zigZagInsert(6, bt_zig_zag_before3()),
          bt_zig_zag_after3(),
          datastructures.tree.binary.Ops::toString
      )
      ,
      new test.unit.EqualBy<>("BT zig zag 4",
          datastructures.tree.binary.Ops::sameElements,
          () -> Exam.zigZagInsert(11, bt_zig_zag_before4()),
          bt_zig_zag_after4(),
          datastructures.tree.binary.Ops::toString
      )
  );

  static GT.Node<Integer> gt_heap_empty() { return
      null;
  }
  static GT.Node<Integer> gt_heap1() { return
      GT.Node.of(1);
  }
  static GT.Node<Integer> gt_heap2() { return
      GT.Node.of(1,
          GT.Node.of(2), GT.Node.of(3), GT.Node.of(1)
      );
  }
  static GT.Node<Integer> gt_heap3() { return
      GT.Node.of(1,
          GT.Node.of(2,
              GT.Node.of(4), GT.Node.of(5)
          ),
          GT.Node.of(3,
              GT.Node.of(6), GT.Node.of(7,
                  GT.Node.of(8)
              )
          ),
          GT.Node.of(1,
              GT.Node.of(8), GT.Node.of(9,
                  GT.Node.of(10)
              )
          )
      );
  }
  static GT.Node<Integer> gt_heap4() { return
      GT.Node.of(1,
          GT.Node.of(2,
              GT.Node.of(4), GT.Node.of(5)
          ),
          GT.Node.of(3,
              GT.Node.of(6), GT.Node.of(7)
          ),
          GT.Node.of(1,
              GT.Node.of(8), GT.Node.of(9,
                  GT.Node.of(10,
                      GT.Node.of(11), GT.Node.of(12)
                  )
              )
          )
      );
  }
  static GT.Node<Integer> gt_not_heap1() { return
      GT.Node.of(1,
          GT.Node.of(2), GT.Node.of(0), GT.Node.of(1)
      );
  }
  static GT.Node<Integer> gt_not_heap2() { return
      GT.Node.of(1,
          GT.Node.of(2,
              GT.Node.of(4), GT.Node.of(5)
          ),
          GT.Node.of(3,
              GT.Node.of(6), GT.Node.of(7,
                  GT.Node.of(8)
              )
          ),
          GT.Node.of(1,
              GT.Node.of(8), GT.Node.of(9,
                  GT.Node.of(1)
              )
          )
      );
  }
  static GT.Node<Integer> gt_not_heap3() { return
      GT.Node.of(1,
          GT.Node.of(2,
              GT.Node.of(4), GT.Node.of(5)
          ),
          GT.Node.of(3,
              GT.Node.of(6), GT.Node.of(2)
          ),
          GT.Node.of(1,
              GT.Node.of(8), GT.Node.of(9,
                  GT.Node.of(10,
                      GT.Node.of(11), GT.Node.of(0)
                  )
              )
          )
      );
  }


  static test.unit.TestSuite test_GT_heap = new test.unit.TestSuite("GT heap",
      new test.unit.Assert("GT heap empty",
          () -> Exam.isHeap(gt_heap_empty())
      )
      ,
      new test.unit.Assert("GT heap 1",
          () -> Exam.isHeap(gt_heap1())
      )
      ,
      new test.unit.Assert("GT heap 2",
          () -> Exam.isHeap(gt_heap2())
      )
      ,
      new test.unit.Assert("GT heap 3",
          () -> Exam.isHeap(gt_heap3())
      )
      ,
      new test.unit.Assert("GT heap 4",
          () -> Exam.isHeap(gt_heap4())
      )
      ,
      new test.unit.Refute("GT not heap 1",
          () -> Exam.isHeap(gt_not_heap1())
      )
      ,
      new test.unit.Refute("GT not heap 2",
          () -> Exam.isHeap(gt_not_heap2())
      )
      ,
      new test.unit.Refute("GT not heap 3",
          () -> Exam.isHeap(gt_not_heap3())
      )
  );
}

