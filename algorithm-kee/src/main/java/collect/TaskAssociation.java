package collect;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 算法 - 任务分配
 * @author eric
 * @date 2022/3/15 1:01 下午
 */
public class TaskAssociation {

    public static void main(String[] args) {
        Map<String, Integer> taskOfUser = new HashMap<>();
        taskOfUser.put("1", 10);
        taskOfUser.put("2", 6);
        taskOfUser.put("3", 4);

        int toDoCount = 10;

        Map<String, Integer> association = association(taskOfUser, toDoCount);

        System.out.println(association);
    }

    private static Map<String, Integer> association(Map<String, Integer> taskOfUser, int toDoCount) {
        Map<String, Integer> todoOfUser =  new HashMap<>();
        if (null != taskOfUser && taskOfUser.size() > 0) {
            double sum = taskOfUser.values().stream().mapToDouble(Integer::doubleValue).sum();
            int total = Integer.parseInt(Double.toString(sum + toDoCount));

            int avg = total / taskOfUser.size();
            int left = total % taskOfUser.size();

            for (Map.Entry<String, Integer> entry : taskOfUser.entrySet()) {
                int toAss = avg - entry.getValue();

                if (toAss > 0) {
                    toAss = left-- > 0 ? ++toAss : toAss;
                    todoOfUser.put(entry.getKey(), toAss);
                }
            }

            if (left > 0) {
                for (Map.Entry<String, Integer> entry : taskOfUser.entrySet()) {
                    int toAss = avg - entry.getValue();

                    if (toAss == 0 && left > 0) {
                        todoOfUser.put(entry.getKey(), entry.getValue() + 1);
                        left--;
                    }
                }
            }
        }

        return todoOfUser;
    }
}
