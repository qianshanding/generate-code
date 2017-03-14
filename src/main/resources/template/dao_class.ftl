package ${daoPackage};

import ${doPackage}.${className_Java}DO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ${tableComment} Dao
 * 
 * @Author ${author}
 * @Date ${createDate}
 */
@Repository
public class ${className_Java}DAO {

    @Autowired
    private SqlSession sqlSession;

    /**
     * 
     * 方法insert的功能描述：新增${tableComment}
     * 
     * @param record
     * @return int
     */
    public int insert(${className_Java}DO record) {
        return sqlSession.insert("${className_Java}Dao.insert", record);
    }

    /**
     * 
     * 方法deleteById的功能描述：根据Id删除${tableComment}
     * 
     * @param id
     * @return int
     */
    public int deleteById(Long id) {
        return sqlSession.delete("${className_Java}Dao.deleteById", id);
    }

    /**
     * 
     * 方法updateById的功能描述：根据Id更新${tableComment}
     * 
     * @param record
     * @return int
     */
    public int updateById(${className_Java}DO record) {
        return sqlSession.update("${className_Java}Dao.updateById", record);
    }

    /**
     * 
     * 方法findById的功能描述：根据Id查询${tableComment}
     * 
     * @param id
     * @return ${className_Java}DO
     */
    public ${className_Java}DO findById(Long id) {
        return sqlSession.selectOne("${className_Java}Dao.findById", id);
    }
}