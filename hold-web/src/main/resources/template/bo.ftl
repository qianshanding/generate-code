package ${boPackage};

import lombok.Data;

/**
 * @Author ${author}
 * @Date ${createDate}
 */
@Data
public class ${className_Java}BO {

    public ${className_Java}BO() {
    }

    <#list tableCarrays as tableCarray>
    <#if tableCarray.carrayName!="createTime"&&tableCarray.carrayName!="updateTime">
    /**
     * ${tableCarray.comment}
     */
    private ${tableCarray.carrayType} ${tableCarray.carrayName};
    </#if>
    </#list>
}