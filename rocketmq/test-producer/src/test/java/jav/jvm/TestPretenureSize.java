package jav.jvm;

public class TestPretenureSize {
    private static final int _1M = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void main(String[] args) {
        byte[] allocation1;
        allocation1 = new byte[4 * _1M];//年轻代空间不足，老年代担保直接进入老年代
    }
}
