package com.util.sort;



import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Comparator;
/**
 * sortUtil
 * 
 * 对象按指定规则排序
 * 
 * @author dick
 * 
 */
public class Sort implements Comparator<Object> {
    /**
     * 按属性排序类型必须为String 或基本数据类型
     */
    private String sort;
    /**
     * 排序方式
     */
    private String dir;

    public Sort() {
    }


    public Sort(String sort, String dir) {
        super();
        this.sort = sort;
        this.dir = dir;
    }


    @Override
    public int compare(Object obj, Object obj2) {
        try {
            //拼接方法
            String methodName = "get" + sort.substring(0, 1).toUpperCase() + sort.substring(1, sort.length());
            //根据obj放射得到改对象的方法
            Method method = obj.getClass().getMethod(methodName, new Class[]{});

            //反射得到属性类型
            //Field field=obj.getClass().getDeclaredField(sort);
            //Class type=field.getType();
            //通过方法得到方法返回类型
            Type type = method.getGenericReturnType();
            //反射执行该对象的方法
            Object result1 = method.invoke(obj, new Class[]{});
            Object result2 = method.invoke(obj2, new Class[]{});
            //String类型比较大小
            if (type == String.class) {
                if (dir.equals("asc")) {
                    return ((String) result1).compareTo((String) result2);
                } else {
                    return -((String) result1).compareTo((String) result2);
                }
            }
            //int类型比较大小
            if (type == int.class) {
                if (dir.equals("asc")) {
                    return (Integer) result1 - (Integer) result2;
                } else {
                    return -((Integer) result1 - (Integer) result2);
                }
            }
            //long类型比较大小......
            if (type == long.class) {
                if (dir.equals("asc")) {
                    return (int) ((Long) result1 - (Long) result2);
                } else {
                    return (int) -((Long) result1 - (Long) result2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public String getSort() {
        return sort;
    }


    public void setSort(String sort) {
        this.sort = sort;
    }


    public String getDir() {
        return dir;
    }


    public void setDir(String dir) {
        this.dir = dir;
    }
    /*public static void main(String[] args) {
        Page page1=new Page();
		Page page2=new Page();
		Page page3=new Page();
		Page page4=new Page();
		page1.setPageSize(4);
		page2.setPageSize(2);
		page3.setPageSize(6);
		page4.setPageSize(1);
		List<Page> list=new ArrayList<Page>();
		list.add(page1);
		list.add(page2);
		list.add(page3);
		list.add(page4);
		Sort s=new Sort("pageSize", "desc");
		System.out.println("排序前：");
		for (Page page : list) {
			System.out.println(page.getPageSize());
		}
		Collections.sort(list, s);
		System.out.println("排序后：");
		for (Page page : list) {
			System.out.println(page.getPageSize());
		}
	}*/
}
