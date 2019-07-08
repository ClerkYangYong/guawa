package Guawa.Guawa;

import com.google.common.collect.Range;

/**
 * 区间 https://www.cnblogs.com/amei0/p/9988896.html
 */
public class Ranges {

    /**
     * 数字区间，(a..b)，
     *
     * @param args
     */

    public static void main(String[] args) {

        //区间运算
        System.out.println("1:" + Range.closed(1, 10).contains(8));

        //查询运算
        //isEmpty是否是一个空区间
        System.out.println("2:" + Range.closed(1, 10).isEmpty());
        //hasLowerBound 是否含有下限
        System.out.println("3:" + Range.closed(1, 10).hasLowerBound());
        //hasUpperBound 是否含有上限
        System.out.println("4:" + Range.closed(1, 10).hasUpperBound());
        //lowerBoundType 下边界类型，如果区间没有对应的边界，抛出IllegalStateException
        System.out.println("5:" + Range.greaterThan(10).lowerBoundType());
        //upperBoundType 上边界类型，如果区间没有对应的边界，抛出IllegalStateException
        System.out.println("6:" + Range.closed(2, 10).upperBoundType());
        //lowerEndpoint 下限值，如果区间没有对应的边界，抛出IllegalStateException
        System.out.println("7:" + Range.closed(2, 10).lowerEndpoint());
        //upperEndpoint 上限值，如果区间没有对应的边界，抛出IllegalStateException
        System.out.println("8:" + Range.closed(2, 10).upperEndpoint());

        //关系运算
        //encloses 是否包含
        System.out.println("9:" + Range.closed(2, 10).encloses(Range.closed(3, 3)));
        //isConnected 是否相连
        System.out.println("10:" + Range.closed(2, 10).isConnected(Range.closed(12, 30)));
        //intersection 交集 ，当且仅当两个区间是相连的，它们才有交集。如果两个区间没有交集，该方法将抛出IllegalArgumentException
        System.out.println("11:" + Range.closed(2, 10).intersection(Range.closed(8, 13)));
        //span 并集
        System.out.println("12:" + Range.closed(2, 8).span(Range.closed(12, 13)));

    }

}
