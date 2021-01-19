package unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author lx
 * @Date 2021/1/19 15:20
 * @Version 1.0
 */
public class EmailUnion {

    /**
     * 账户 account[i] 存放的是["James", "james@163.com", "james@qq.com"]，第一个是人名，后边都是邮箱
     * 有相同邮箱的两个账户（名字也一定相同），属于同一个人
     * 名字相同的两个账户可以不是同一个人
     *
     * 将同一个人的邮箱合并到同一个账户
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> lists = new ArrayList<>();
        if (accounts == null || accounts.size() < 1) {
            return lists;
        }
        // 按照啥找队伍，就准备啥
        HashMap<String, Integer> emailIndexMap = new HashMap<>();
        HashMap<String, String> emailNameMap = new HashMap<>();
        int emailCount = 0;
        for (List<String> account : accounts) {
            int size = account.size();
            String name = account.get(0);
            for (int i = 1; i < size; ++i) {
                if (!emailIndexMap.containsKey(account.get(i))) {
                    emailIndexMap.put(account.get(i), emailCount++);
                    emailNameMap.put(account.get(i), name);
                }
            }
        }
        // 数好不同 email 的个数，将他们分堆儿，属于同一个人的 email ，value 指向同一个
        UnionFind uf = new UnionFind(emailCount);
        for (List<String> account : accounts) {
            int size = account.size();
            int index1 = emailIndexMap.get(account.get(1));
            for (int i = 2; i < size; ++i) {
                uf.union(index1, emailIndexMap.get(account.get(i)));
            }
        }
        // 分完堆儿之后，按照结果建立最终结果的一部分
        HashMap<Integer, List<String>> indexEmailsMap = new HashMap<>();
        for (String email : emailIndexMap.keySet()) {
            int index = uf.find(emailIndexMap.get(email));
            List<String> account = indexEmailsMap.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexEmailsMap.put(index, account);
        }
        // 把一个人所有的 email 放在同一个索引下了，再封装一下就好
        for (List<String> emails : indexEmailsMap.values()) {
            Collections.sort(emails);
            String name = emailNameMap.get(emails.get(0));
            List<String> list = new ArrayList<>();
            list.add(name);
            list.addAll(emails);
            lists.add(list);
        }
        return lists;
    }
}
