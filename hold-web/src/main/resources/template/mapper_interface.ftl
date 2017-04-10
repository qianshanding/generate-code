package ${daoPackage};

import ${doPackage}.${className_Java}DO;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @Author ${author}
 * @Date ${createDate}
 */
@Repository
public interface ${className_Java}Mapper {
    /**
     * 
     * 方法insert的功能描述：新增${tableComment}
     * 
     * @param record
     * @return int
     */
    public int insert(${className_Java}DO record);

    /**
     *
     * 方法batchInsert的功能描述：批量插入${className_Java}DO
     *
     * @param records
     * @return int
     */
    public int batchInsert(List<${className_Java}DO> records);

    /**
     * 
     * 方法deleteById的功能描述：根据Id删除${tableComment}
     * 
     * @param id
     * @return int
     */
    public int deleteById(Long id);

    /**
     * 
     * 方法updateById的功能描述：根据Id更新${tableComment}
     * 
     * @param record
     * @return int
     */
    public int updateById(${className_Java}DO record);

    /**
     * 
     * 方法findById的功能描述：根据Id查询${tableComment}
     * 
     * @param id
     * @return ${className_Java}DO
     */
    public ${className_Java}DO findById(Long id);
}