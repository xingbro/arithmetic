package prime;

/**
 * 判断一个数是否为质数
 * prime: 主要的，最好的，基本的
 *
 * @Author lx
 * @Date 2021/3/2 10:41
 * @Version 1.0
 */
public class PrimeNumber {

    public static void main(String[] args) {
        for (int i = 5; i < 100007; i += 6) {
            System.out.println(i + (isPrimeNumber(i) ? " 是质数" : " 不是质数"));
            System.out.println((i + 2) + (isPrimeNumber(i + 2) ? " 是质数" : " 不是质数"));
            System.out.println("==================分割线==================");
        }
    }

    public static boolean isPrimeNumber(long num) {
        /**
         * 一个数是 6n - 1 和 6n + 1 才有可能是质数（n > 0）
         * 证明：①目的为判断一个数是否为质数，所以先不考虑 1,2,3,4 因为答案显而易见，证明周期范围就是[6n - 1, 6n + 4](n > 0)
         *       ②反证法
         *          假设 6n、6n + 2、6n + 3、6n + 4 为质数，
         *          那么有 2 * 3 * n、2 * (n + 1)、3 * (n + 1)、2 * (n + 2) 为质数
         *          显然与质数定义相悖，所以原假设不成立
         *       ③排除法，已经确定周期内 4 个（种）数为合数，另外俩都能找到分别为质数及合数的特例，所以排除 4 个合数，
         *       仅判断两种有可能的情况，
         * [1] 判断逻辑为 num 是否可以被 2 ~ √nums 中的某个数整除
         * [2] 迭代过程只看 6n - 1 和 6n + 1
         */
        if (num < 4) {
            return num > 1;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        long sqrt = (long) Math.sqrt(num);
        // 这里选取 i <= sqrt 作为条件，那要从 i = 5，也就是 6n - 1 开始，避免漏掉最后一个 6n - 1
        for (int i = 5; i <= sqrt; i += 6) {
            if (num % i == 0) {
                System.out.println("6 倍数附近因子是：" + i);
                return false;
            } else if (num % (i + 2) == 0) {
                System.out.println("6 倍数附近因子是：" + (i + 2));
                return false;
            }
        }
        return true;
    }
}
