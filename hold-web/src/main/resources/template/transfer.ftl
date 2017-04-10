package ${transferPackage};

import ${doPackage}.${className_Java}DO;
import ${boPackage}.${className_Java}BO;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ${author}
 * @Date ${createDate}
 */
public class ${className_Java}Transfer {

    private ${className_Java}Transfer(){
    }

    public static ${className_Java}DO ${className}BOToDO(${className_Java}BO srcObj) {
        if (srcObj == null) {
            return null;
        }
        ${className_Java}DO targetObj = new ${className_Java}DO();
        <#list tableCarrays as tableCarray>
        <#if tableCarray.carrayName!="createTime"&&tableCarray.carrayName!="updateTime">
        targetObj.set${tableCarray.carrayName_Java}(srcObj.get${tableCarray.carrayName_Java}());//${tableCarray.comment}
        </#if>
        </#list>
        return targetObj;
    }

    public static ${className_Java}BO ${className}DOToBO(${className_Java}DO srcObj) {
        if (srcObj == null) {
            return null;
        }
        ${className_Java}BO targetObj = new ${className_Java}BO();
        <#list tableCarrays as tableCarray>
        <#if tableCarray.carrayName!="createTime"&&tableCarray.carrayName!="updateTime">
        targetObj.set${tableCarray.carrayName_Java}(srcObj.get${tableCarray.carrayName_Java}());//${tableCarray.comment}
        </#if>
        </#list>
        return targetObj;
    }

    public static List<${className_Java}DO> toDOList(List<${className_Java}BO> boList) {
        if (boList == null) {
            return null;
        }
        List<${className_Java}DO> doList = new ArrayList<>();
        for (${className_Java}BO bo : boList) {
            doList.add(${className}BOToDO(bo));
        }
        return doList;
    }

    public static List<${className_Java}BO> toBOList(List<${className_Java}DO> doList) {
        if (doList == null) {
            return null;
        }
        List<${className_Java}BO> boList = new ArrayList<>();
        for (${className_Java}DO d : doList) {
            boList.add(${className}DOToBO(d));
        }
        return boList;
    }
}