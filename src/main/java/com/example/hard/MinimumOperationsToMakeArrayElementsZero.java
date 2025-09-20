package com.example.hard;

import java.util.ArrayList;
import java.util.List;

public class MinimumOperationsToMakeArrayElementsZero {
    //Ban chat cua viec / 4 la dich trai 2 bit
    public long minOperations(int[][] queries) {
        long operation = 0;
        for (int[] query : queries) {
            //Tinh so phep toan can cho khoang [1, l - 1] va [1, r]
            //--> dap an = ([1, l] - [1, l - 1] + 1) / 2
            //Phai + 1 chia 2 la do ham value dang tinh so buoc de chuyen 1 so ve 0, ma cau hoi co the chuyen 2 so 1 lan
            long oneToRight = value(query[1]), oneToLeftMinusOne = value(query[0] - 1);
            operation += (oneToRight - oneToLeftMinusOne + 1) / 2;
        }
        return operation;
    }

    private long value(int number) {
        //cur = So be nhat ma dang nhi phan la numberBit bit
        //numberBit = So bit ma current hien tai dang so huu
        //count = So phep toan de dua so co numberBit bit ve 0
        int current = 1, numberBit = 1;
        long count = 0;
        while (current <= number) {
            //So lon nhat co toi da numberBit ma be hon number
            int maxButLessThanNumber = Math.min(number, current * 2 - 1);
            //maxButLessThanNumber - current + 1 = So so hang co numberBit bit
            //(numberBit + 1) / 2 = So buoc dich trai 2 lan de cho so do tro ve 0
            count += (long) (maxButLessThanNumber - current + 1) * ((numberBit + 1) / 2);
            //current thay bang so be nhat chua numberBit + 1 bit
            current *= 2;
            //Tang so bit current hien tai dang so huu
            numberBit++;
        }
        return count;
    }
}
