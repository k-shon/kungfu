package top.annokshon.kungfu.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author kshon
 * @description
 * @date 2019-10-04 12:38
 */
@Component
public class ObjectUtils {

    /*复制没有修改的对象属性到另一个对象，并返回这个对象*/
    public Object copyByUnModify(Object newObject,Object oldObject,Object... objects) throws Exception{
        Field[] oldFields = oldObject.getClass().getDeclaredFields();
        Field[] newFields = newObject.getClass().getDeclaredFields();
        //处理外键
        for (Object fk:objects){
            fk = fk.getClass().newInstance();
            String[] names = fk.getClass().getName().split("\\.");  //.需要转义
            String name = names[names.length-1];
            name = name.substring(0,1).toLowerCase()+name.substring(1,name.length());
            for(int i=0;i<oldFields.length;i++){
                oldFields[i].setAccessible(true);
                newFields[i].setAccessible(true);
                if(oldFields[i].getName().equals(name)){
                    Method getMethod = newObject.getClass().getMethod("get"+names[names.length-1]); //调用对象中获取外键的方法
//                    fk = getMethod.invoke(newObject);  //获取前端的外键对象，这里如果用getMethod.invoke(newObject)得到的是null，直接用参数objects就行
                    Object oldFk = getMethod.invoke(oldObject); //获取数据库的外键对象
                    Field[] fkField = fk.getClass().getDeclaredFields();
                    Field[] oldFkField = oldFk.getClass().getDeclaredFields();
                    for(int j=0;j<oldFkField.length;j++){
                        fkField[j].setAccessible(true);
                        oldFkField[j].setAccessible(true);
                        if(fkField[j].get(fk)!=null && fkField[j].get(fk)!=new Integer(0)){  //如果外键的属性值不为空和0，则赋值给数据库的对象
                            oldFkField[j].set(oldFk,fkField[j].get(fk));
                        }
                    }
                    //将修改后的外键设置回数据库对象
                   Method setMethod = oldObject.getClass().getMethod("set"+names[names.length-1],new Class[]{fk.getClass()});
                   setMethod.invoke(oldObject,oldFk);
                }else{
                    if(newFields[i].get(newObject)!=null&&newFields[i].get(newObject)!=new Integer(0)){ //属性值不为空和0，则赋值给数据库的对象
                        oldFields[i].set(oldObject,newFields[i].get(newObject));
                    }
                }
            }
        }
        System.out.println(oldObject);
        return oldObject;
    }
}
