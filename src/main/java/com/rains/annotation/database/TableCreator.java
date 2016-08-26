package com.rains.annotation.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rains on 2016/8/23.
 */
public class TableCreator {

    public static void main(String[] args) throws Exception {
        if(args.length < 1){
            System.out.println("需传递使用注解的类");
        }

        for(String className : args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.out.println(className + "类中没有dbTable注解");
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for(Field field : cl.getDeclaredFields()){
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length <1){
                    continue;
                }
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if(sInt.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }else{
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()) );
                }

                if(anns[0] instanceof SQLString){
                    SQLString sString = (SQLString)anns[0];
                    if(sString.name().length() < 1 ){
                        columnName = field.getName().toUpperCase();
                    }else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraint()));
                }

            }
            StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(" );
            for(String columnDef : columnDefs){
                createCommand.append("\n    " + columnDef + " ,");
            }
            String tableCreate = createCommand.substring(0 , createCommand.length() -1) + ");";
            System.out.println(className + "对应数据表的创建Sql为：" + tableCreate);
        }
    }

    public static String getConstraints(Constraints con){
        String constrains = "";
        if(!con.allNull()){
            constrains += "NOT NULL";
        }
        if(con.primaryKey()){
            constrains += " PRIMARY KEY";
        }
        if(con.unique()){
            constrains += " UNIQUE";
        }
        return  constrains;
    }
}
