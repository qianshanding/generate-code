package ${doPackage};

import java.io.Serializable;
import lombok.Data;

/**
 * ${tableComment}
 * @Author ${author}
 * @Date ${createDate}
 */
@Data
public class ${className_Java}DO implements Serializable {

    public ${className_Java}DO() {
    }

    <#list tableCarrays as tableCarray>
    /**
     * ${tableCarray.comment}
     */
    private ${tableCarray.carrayType} ${tableCarray.carrayName};
    </#list>
}