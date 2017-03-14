package com.qianshanding.generate.utils;

import com.qianshanding.generate.db.entity.Table;
import com.qianshanding.generate.db.entity.TableBind;
import com.qianshanding.generate.db.entity.TableCarray;
import com.qianshanding.generate.db.entity.TableIndex;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 表结构工具类
 *
 * @author fish
 */
public class TableUtils {
    /**
     * 取得表名称集合
     *
     * @return
     * @throws SQLException
     */
    public static final List<String> getTableNames(Connection conn, List<String> tableList)
            throws SQLException {
        DatabaseMetaData dme = conn.getMetaData();
        List<String> tableNames = new ArrayList<String>();
        if (tableList == null || tableList.size() == 0) {
            ResultSet rs = dme.getTables("", "", "%", new String[]{"TABLE"});
            while (rs.next()) {
                tableNames.add(rs.getString("TABLE_NAME"));
            }
        } else {
            for (String tableName : tableList) {
                ResultSet rs = dme.getTables("", "", tableName, new String[]{"TABLE"});
                while (rs.next()) {
                    tableNames.add(rs.getString("TABLE_NAME"));
                }
            }
        }
        return tableNames;
    }

    /**
     * 取得表中的字段
     *
     * @throws Exception
     */
    public static final List<Map<String, Object>> getCarrays(Connection conn,
                                                             String tableName) throws Exception {
        String sql = String.format("SELECT * FROM `%s` LIMIT 1", tableName);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Map<String, Object>> list = getColumns(rs);
        return list;
    }

    /**
     * 取得表注释
     *
     * @throws Exception
     */
    public static final String getTableComment(Connection conn,
                                               String tableName) throws Exception {
        String sql = String.format("show table status where name = '%s'", tableName);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            return rs.getString("Comment");
        }
        return null;
    }

    /**
     * 取得表中的字段注释
     *
     * @throws Exception
     */
    public static final Map<String, Object> getComment(Connection conn,
                                                       String tableName) throws Exception {
        String sql = String.format("show full fields from `%s`", tableName);
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        Map<String, Object> map = getComment(rs);
        return map;
    }

    /**
     * 取得注释
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static final Map<String, Object> getComment(ResultSet rs)
            throws Exception {
        Map e = new HashMap();
        while (rs.next()) {
            e.put(rs.getString("Field"), rs.getString("Comment"));
        }

        return e;
    }

    /**
     * 取得表中的索引
     *
     * @return
     * @throws SQLException
     */
    public static final List<Map> getIndexs(Connection conn, String tableName,
                                            boolean unique) throws SQLException {
        DatabaseMetaData dmd = conn.getMetaData();
        ResultSet rs = dmd.getIndexInfo(null, null, tableName, unique, true);
        return resToList(rs);
    }

    /**
     * 取得主外键关系
     *
     * @param conn
     * @param tableName
     * @return
     * @throws Exception
     */
    public static final Map getBinds(Connection conn, String tableName)
            throws Exception {
        DatabaseMetaData dmd = conn.getMetaData();
        Map map = new HashMap();
        ResultSet rs = null;
        rs = dmd.getExportedKeys(null, null, tableName);
        map.put("ExportedKeys", resToList(rs));
        rs = dmd.getImportedKeys(null, null, tableName);
        map.put("ImportedKeys", resToList(rs));
        return map;
    }

    /**
     * ResultSet转List
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static final List<Map> resToList(ResultSet rs) throws SQLException {
        List<Map> list = new ArrayList<Map>();
        while (rs.next()) {
            list.add(resToMap(rs));
        }
        return list;
    }

    /**
     * ResultSet转Map
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static final Map resToMap(ResultSet rs) throws SQLException {
        Map map = new HashMap();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++)
            map.put(rsmd.getColumnName(i), rs.getObject(i));

        return map;
    }

    /**
     * 取得字段详情
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static final List<Map<String, Object>> getColumns(ResultSet rs)
            throws Exception {
        List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        for (int i = 1; i <= count; i++) {
            String columnName = rsmd.getColumnName(i);
            int columnType = rsmd.getColumnType(i);
            String columnLabel = rsmd.getColumnLabel(i);
            String columnTypeName = rsmd.getColumnTypeName(i);
            String catalogName = rsmd.getCatalogName(i);
            String columnClassName = rsmd.getColumnClassName(i);
            int precision = rsmd.getPrecision(i);
            int scale = rsmd.getScale(i);
            String schemaName = rsmd.getSchemaName(i);
            String tableName = rsmd.getTableName(i);
            int columnDisplaySize = rsmd.getColumnDisplaySize(i);
            boolean isAutoIncrement = rsmd.isAutoIncrement(i);
            boolean isCaseSensitive = rsmd.isCaseSensitive(i);
            boolean isCurrency = rsmd.isCurrency(i);
            boolean isDefinitelyWritable = rsmd.isDefinitelyWritable(i);
            int isNullable = rsmd.isNullable(i);
            boolean isReadOnly = rsmd.isReadOnly(i);
            boolean isSearchable = rsmd.isSearchable(i);
            boolean isSigned = rsmd.isSigned(i);
            boolean isWritable = rsmd.isWritable(i);

            Map e = new HashMap();
            e.put("i", i);
            e.put("columnName", columnName);
            e.put("columnType", columnType);
            e.put("columnLabel", columnLabel);
            e.put("columnTypeName", columnTypeName);
            e.put("catalogName", catalogName);
            e.put("columnClassName", columnClassName);
            e.put("precision", precision);
            e.put("scale", scale);
            e.put("schemaName", schemaName);
            e.put("tableName", tableName);
            e.put("columnDisplaySize", columnDisplaySize);
            e.put("isAutoIncrement", isAutoIncrement);
            e.put("isCaseSensitive", isCaseSensitive);
            e.put("isCurrency", isCurrency);
            e.put("isDefinitelyWritable", isDefinitelyWritable);
            e.put("isNullable", isNullable);
            e.put("isReadOnly", isReadOnly);
            e.put("isSearchable", isSearchable);
            e.put("isSigned", isSigned);
            e.put("isWritable", isWritable);
            e.put("javaForType", JavaType.getType(rsmd, columnLabel));
            ret.add(e);
        }
        return ret;
    }

    public static final List<Table> getTables(Connection conn, String author, List<String> tableList) throws Exception {
        List<Table> tables = new ArrayList<Table>();
        Table table = null;
        List<String> tabelNames = getTableNames(conn, tableList);

        for (String tableName : tabelNames) {
            String className = StringUtils.tableFieldName2JavaFieldName(tableName);// 小写表名称
            String className_d = StringUtils.upperFirst(tableName);// 大写表名称
            String className_x = StringUtils.lowerFirst(tableName);// 小写表名称
            String className_ml = StringUtils.middleLine(tableName);// 中横线表名称

            String className_Java = StringUtils.tableName2JavaBeanName(tableName);// 小写表名称


            List<TableCarray> tableCarrays = getTableCarrays(conn, tableName);// 表字段
            List<TableIndex> tableIndexs = getTableIndexs(conn, tableName);// 表索引
            List<TableBind> tableBinds = getTableBinds(conn, tableName);// 表主外键
            String tableComment = getTableComment(conn, tableName); //表注释
            String createDate = new SimpleDateFormat("yyyy年MM月dd日").format(new java.util.Date());//文件生成日期

            Set<String> importPojos = new HashSet<String>();
            for (TableBind tableBind : tableBinds) {
                importPojos.add(tableBind.getTableName_d());
            }
            importPojos.add(className_d);
            String stringCarrayNames1 = "";
            String stringCarrayNames2 = "";
            String stringCarrayNames3 = "";
            String stringCarrayNames4 = "";
            String stringCarrayNames5 = "";
            for (TableCarray tableCarray : tableCarrays) {
                if (!"".endsWith(stringCarrayNames1)) {
                    stringCarrayNames1 += ", ";
                }
                if (!"".endsWith(stringCarrayNames2)) {
                    stringCarrayNames2 += ", ";
                }
                if (!"".endsWith(stringCarrayNames3)) {
                    stringCarrayNames3 += ", ";
                }
                if (!"".endsWith(stringCarrayNames4)) {
                    stringCarrayNames4 += ", ";
                }
                if (!"".endsWith(stringCarrayNames5)) {
                    stringCarrayNames5 += ", ";
                }
                stringCarrayNames1 += tableCarray.getCarrayName_x();
                stringCarrayNames2 += tableCarray.getCarrayType() + " "
                        + tableCarray.getCarrayName_x();
                if (!"id".equals(tableCarray.getCarrayName_x())) {
                    stringCarrayNames3 += tableCarray.getCarrayName_x();
                    stringCarrayNames4 += String.format("#{%s}", tableCarray
                            .getCarrayName());
                }

                stringCarrayNames5 += String.format("%s=#%s#", tableCarray
                        .getCarrayName(), tableCarray.getCarrayName_x());
            }
            table = new Table(className, className_d, className_x, className_ml, className_Java, tableCarrays, tableIndexs,
                    tableBinds, importPojos, stringCarrayNames1,
                    stringCarrayNames2, stringCarrayNames3, stringCarrayNames4,
                    stringCarrayNames5, tableComment, createDate, author);
            tables.add(table);
        }
        return tables;
    }

    public static final List<TableCarray> getTableCarrays(Connection conn,
                                                          String tableName) throws Exception {
        List<TableCarray> tableCarrays = new ArrayList<TableCarray>();
        TableCarray tabelCarray = null;

        List<Map<String, Object>> carrays = getCarrays(conn, tableName);
        Map<String, Object> commentMap = getComment(conn, tableName);
        for (Map<String, Object> map : carrays) {
            String columnLabel = map.get("columnLabel").toString();
            String carrayName = StringUtils.tableFieldName2JavaFieldName(columnLabel);
            String carrayName_d = StringUtils.upperFirst(columnLabel);// 首字母大写
            String carrayName_x = StringUtils.lowerFirst(columnLabel);// 首字母小写
            String carrayName_Java = StringUtils.tableName2JavaBeanName(columnLabel);
            String carrayType = map.get("javaForType").toString();// 字段类型
            String carrayType_d = map.get("columnTypeName").toString();// 字段类型
            if ("JAVA.UTIL.DATE".equals(carrayType_d) || "DATETIME".equals(carrayType_d)) {
                carrayType_d = "TIMESTAMP";
            }
            if ("INT".equals(carrayType_d)) {
                carrayType_d = "INTEGER";
            }

            String comment = null;
            if (null != commentMap.get(carrayName_x)) {
                comment = commentMap.get(carrayName_x).toString();
            }
            if (comment == null || comment.length() == 0) {
                comment = carrayName_x;
            }
            tabelCarray = new TableCarray(carrayName, carrayName_d,
                    carrayName_x, carrayName_Java, carrayType, carrayType_d, comment);
            tableCarrays.add(tabelCarray);
        }
        return tableCarrays;
    }

    public static final List<TableIndex> getTableIndexs(Connection conn,
                                                        String tableName) throws Exception {
        List<Map> indexs = getIndexs(conn, tableName, false);
        Map<String, String> carrayTypes = getTableCarrayTypes(conn, tableName);
        Map<String, List<Map>> _index = new HashMap<String, List<Map>>();
        for (Map map : indexs) {
            String indexName = map.get("INDEX_NAME").toString(); // 索引名称
            List<Map> list = _index.remove(indexName);
            if (list == null) {
                list = new ArrayList<Map>();
            }
            list.add(map);
            _index.put(indexName, list);
        }

        List<TableIndex> tableIndexs = new ArrayList<TableIndex>();
        TableIndex tabelIndex = null;
        Iterator it = _index.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String indexName = e.getKey().toString();
            boolean unique = false;
            List<String> carrayNames = new ArrayList<String>();
            List<String> carrayNames_d = new ArrayList<String>();
            List<String> carrayNames_x = new ArrayList<String>();
            List<Map<String, String>> carrayNameMaps = new ArrayList<Map<String, String>>();
            String stringCarrayNames1 = "";
            String stringCarrayNames2 = "";
            String stringCarrayNames3 = "";
            String stringCarrayNames4 = "";
            String stringCarrayNames5 = "";
            String stringCarrayNames6 = "";
            List<Map> vals = (List<Map>) e.getValue();
            for (Map map : vals) {
                String carrayName = map.get("COLUMN_NAME").toString();
                unique = "false".equals(map.get("NON_UNIQUE").toString());
                String carrayName_d = StringUtils.upperFirst(carrayName);
                String carrayName_x = StringUtils.lowerFirst(carrayName);
                carrayNames.add(carrayName);
                carrayNames_d.add(carrayName_d);
                carrayNames_x.add(carrayName_x);
                Map<String, String> carrayNameMap = new HashMap<String, String>();
                carrayNameMap.put("carrayName", carrayName);
                carrayNameMap.put("carrayName_x", carrayName_x);
                carrayNameMaps.add(carrayNameMap);
                stringCarrayNames1 += carrayName_d;
                if (!"".equals(stringCarrayNames2)) {
                    stringCarrayNames2 += ", ";
                }
                if (!"".equals(stringCarrayNames3)) {
                    stringCarrayNames3 += ", ";
                }
                if (!"".equals(stringCarrayNames4)) {
                    stringCarrayNames4 += ", ";
                }
                if (!"".equals(stringCarrayNames5)) {
                    stringCarrayNames5 += ", ";
                }
                stringCarrayNames2 += carrayName;
                stringCarrayNames3 += carrayTypes.get(carrayName_d) + " "
                        + carrayName_x;
                stringCarrayNames4 += carrayName_x;
                stringCarrayNames5 += String.format("%s=#%s#", carrayName,
                        carrayName_x);
                stringCarrayNames6 += StringUtils.tableFieldName2JavaFieldName(stringCarrayNames1);
            }
            tabelIndex = new TableIndex(indexName, carrayNames, carrayNames_d,
                    carrayNames_x, carrayNameMaps, stringCarrayNames1,
                    stringCarrayNames2, stringCarrayNames3, stringCarrayNames4,
                    stringCarrayNames5, stringCarrayNames6, unique);
            tableIndexs.add(tabelIndex);
        }
        return tableIndexs;
    }

    public static final Map<String, String> getTableCarrayTypes(
            Connection conn, String tableName) throws Exception {
        Map<String, String> tableCarrayTypes = new HashMap<String, String>();
        List<Map<String, Object>> carrays = getCarrays(conn, tableName);
        for (Map<String, Object> map : carrays) {
            String columnLabel = map.get("columnLabel").toString();
            String carrayName_d = StringUtils.upperFirst(columnLabel);// 首字母大写
            String carrayType = map.get("javaForType").toString();// 字段类型
            tableCarrayTypes.put(carrayName_d, carrayType);
        }
        return tableCarrayTypes;
    }

    public static final List<TableBind> getTableBinds(Connection conn,
                                                      String tableName) throws Exception {
        List<TableBind> tableBinds = new ArrayList<TableBind>();
        TableBind tableBind = null;
        Map map = getBinds(conn, tableName);// 表主外键

        String keyName = "";
        String keyType = "";
        String carrayName = "";
        String table_Name = "";
        String carrayName_d = "";
        String carrayName_x = "";
        String table_Name_d = "";
        String table_Name_x = "";

        List<Map> exportedKeys = (List<Map>) map.get("ExportedKeys");
        for (Map exportedKey : exportedKeys) {
            keyName = exportedKey.get("FK_NAME").toString();
            keyType = "exportedKey";
            carrayName = exportedKey.get("FKCOLUMN_NAME").toString();
            table_Name = exportedKey.get("FKTABLE_NAME").toString();
            carrayName_d = StringUtils.upperFirst(carrayName);
            carrayName_x = StringUtils.lowerFirst(carrayName);
            table_Name_d = StringUtils.upperFirst(table_Name);
            table_Name_x = StringUtils.lowerFirst(table_Name);
            tableBind = new TableBind(keyName, keyType, table_Name_d,
                    table_Name_x, carrayName_d, carrayName_x);
            tableBinds.add(tableBind);
        }
        List<Map> importedKeys = (List<Map>) map.get("ImportedKeys");
        for (Map importedKey : importedKeys) {
            keyName = importedKey.get("FK_NAME").toString();
            keyType = "importedKey";
            carrayName = importedKey.get("FKCOLUMN_NAME").toString();
            table_Name = importedKey.get("PKTABLE_NAME").toString();
            carrayName_d = StringUtils.upperFirst(carrayName);
            carrayName_x = StringUtils.lowerFirst(carrayName);
            table_Name_d = StringUtils.upperFirst(table_Name);
            table_Name_x = StringUtils.lowerFirst(table_Name);
            tableBind = new TableBind(keyName, keyType, table_Name_d,
                    table_Name_x, carrayName_d, carrayName_x);
            tableBinds.add(tableBind);
        }
        return tableBinds;
    }
}
