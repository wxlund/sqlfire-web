package vmware.au.se.sqlfireweb.dao.tables;

public interface Constants 
{
	public static final String USER_TABLES = 
			"select tableschemaname, tablename, servergroups, datapolicy, loader, writer " +
			"FROM SYS.SYSTABLES t, sys.sysschemas s " +
			"WHERE s.schemaid = t.schemaid " +
			"and   t.TABLESCHEMANAME = ? " +
			"and   t.tablename like ? " + 
			"and   t.tabletype != 'V' " +
			"order by 1, 2";
	
	public static String DROP_TABLE = "drop table %s.\"%s\"";
	
	public static String TRUNCATE_TABLE = "truncate table %s.\"%s\"";
	
	public static String TABLE_DATA_LOCATION = 
			"select dsid() Member, count(*) memberRowCount from %s.\"%s\" group by dsid()";

	public static String TABLE_MEMORY_USAGE = 
			"SELECT * FROM sys.memoryAnalytics where table_name = '%s.%s'";
	
	public static String LOAD_TABLE_SCRIPT = 
			"connect client 'localhost:1527;load-balance=false;read-timeout=0'; \n" +
            "ELAPSEDTIME on; \n" +
            "call syscs_util.import_table_ex('%s' /* schema */, \n"  +
            "'%s' /* table */, \n" + 
            "'/fullpathtoloadfile/%s.csv' /* file path as seen by server */, \n" +
            "',' /* field separator */, \n" + 
            "NULL, \n" + 
            "NULL, \n" + 
            "0, \n" + 
            "0 /* don't lock the table */, \n" +
            "6 /* number of threads */, \n" +
            "0, \n" + 
            "NULL /* custom class for data transformation or NULL to use the default inbuilt Import class */, \n" + 
            "NULL); \n";
	
	public static String VIEW_PARTITION_ATTRS =
			"select partitionattrs " +
			"FROM   SYS.SYSTABLES t, sys.sysschemas s " + 
			"WHERE  s.schemaid = t.schemaid " + 
			"and    t.TABLESCHEMANAME = ? " + 
			"and    t.tablename = ?";

	public static String VIEW_EVICTION_EXPIRATION_ATTRS =
			"select expirationattrs, evictionattrs " +
			"FROM   SYS.SYSTABLES t, sys.sysschemas s " + 
			"WHERE  s.schemaid = t.schemaid " + 
			"and    t.TABLESCHEMANAME = '%s' " + 
			"and    t.tablename = '%s'";

	public static String VIEW_ALL_TABLE_COLUMNS =
			"select * " +
			"FROM   SYS.SYSTABLES t " + 
			"WHERE  t.TABLESCHEMANAME = '%s' " + 
			"and    t.tablename = '%s'";
	
	public static String VIEW_TABLE_STRUCTURE =
			"select c.columnname, CAST(c.COLUMNDATATYPE AS VARCHAR(100)) as \"Type\" " + 
			"FROM SYS.SYSCOLUMNS c, sys.systables t " + 
			"where c.referenceid = t.tableid " +
			"and t.tableschemaname = '%s' " +
			"and t.tablename = '%s' " + 
			"order by c.columnnumber";
	
}
