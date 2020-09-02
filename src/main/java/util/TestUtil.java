package util;

import java.text.NumberFormat;


public class TestUtil {

    private static Runtime runtime = Runtime.getRuntime();
    private static NumberFormat format = NumberFormat.getInstance();

    private static long time;
    private static long maxMemory;
    private static long allocatedMemory;
    private static long freeMemory;

    public static void init() {
        time = System.currentTimeMillis();
        maxMemory = runtime.maxMemory();
        allocatedMemory = runtime.totalMemory();
        freeMemory = runtime.freeMemory();
    }


    public static void updateTime() {
        time = System.currentTimeMillis();
    }

    public static void printTimeInfo() {
        System.out.println((- time + (time = System.currentTimeMillis())) / 1000f);
    }

    public static void printTimeInfo(String message) {
        System.out.println(message + ": " + (- time + (time = System.currentTimeMillis())) / 1000f);
    }

    public static void printMemoryInfo() {
        printMemoryInfo(0);
    }

    public static void printMemoryInfo(int numOfImages) {
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        System.out.println("" +
                "max: " + format.format(maxMemory / 1024) + ", " +
                "allocated: " + format.format(allocatedMemory / 1024) + ", " +
                "used: " + format.format((allocatedMemory - freeMemory) / 1024) + ", " +
                "free: " + format.format(freeMemory / 1024) + ", " +
                "reserved: " + format.format((maxMemory - allocatedMemory) / 1024) + ", " +
                "usable: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) +
                " - " +
                format.format(numOfImages == 0 ? (allocatedMemory - freeMemory) / 1024 : (allocatedMemory - freeMemory) / 1024 / numOfImages) + " / " + numOfImages +
                "");

        if (maxMemory > TestUtil.maxMemory) {
            long addedMaxSpace = maxMemory - TestUtil.maxMemory;
            System.out.print("max扩容: " + format.format(addedMaxSpace / 1024) + " + ");
        } else if (maxMemory < TestUtil.maxMemory) {
            long decreasedMaxSpace = TestUtil.maxMemory - maxMemory;
            System.out.print("max减容: " + format.format(decreasedMaxSpace / 1024) + " + ");
        }


        if (allocatedMemory > TestUtil.allocatedMemory) {
            long addedSpace = allocatedMemory - TestUtil.allocatedMemory;
            System.out.print("alloc扩容: " + format.format(addedSpace / 1024));

            long increasedSpace = freeMemory - TestUtil.freeMemory;
            if (increasedSpace > addedSpace) {
                System.out.print(" + GC: " + format.format((increasedSpace - addedSpace) / 1024));
            }
            System.out.println();
        } else if (allocatedMemory == TestUtil.allocatedMemory) {
            long increasedSpace = freeMemory - TestUtil.freeMemory;
            if (increasedSpace > 0) {
                System.out.println("GC: " + format.format(increasedSpace / 1024));
            }
        } else {
            long reducedSpace = TestUtil.allocatedMemory - allocatedMemory;
            System.out.print("alloc减容: " + format.format(reducedSpace / 1024));

            long decreasedSpace = TestUtil.freeMemory - freeMemory;
            if (decreasedSpace < reducedSpace) {
                System.out.print(" + GC: " + format.format((reducedSpace - decreasedSpace) / 1024));
            }
            System.out.println();
        }


        System.out.println();

        TestUtil.maxMemory = maxMemory;
        TestUtil.allocatedMemory = allocatedMemory;
        TestUtil.freeMemory = freeMemory;
    }

}
