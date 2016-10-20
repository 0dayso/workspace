>>>>>>>SVN属性设置：
core http://192.168.107.68:100/svn/veds/trunk/veds_core/core
WebRoot/static/core http://192.168.107.68:100/svn/veds/trunk/veds_core/WebRoot/static/core
WebRoot/WEB-INF/tld http://192.168.107.68:100/svn/veds/trunk/veds_core/WebRoot/WEB-INF/tld
WebRoot/WEB-INF/lib http://192.168.107.68:100/svn/veds/trunk/veds_core/WebRoot/WEB-INF/lib
commonb http://192.168.107.68:100/svn/veds/trunk/veds_b/common



>>>>>>>包路径
包路径,不同数据源的业务放到不同的包下面，源文件夹是src
商户系统：cn.vetech.vedsb.common
机票系统：cn.vetech.vedsb.jp
Controller路径：cn.vetech.web.vedsb，源文件夹是web，按照业务模块在vedsb下建立目录，然后再创建Controller文件




>>>>>>>slf4j日志
日志记录用slf4j,拷贝的时候不要弄错类了，任何时候，包括开发测试环境 ，禁止用system。
1、 需要指定目录保存单独业务的,请在cn.vetech.vedsb.utils.LogUtil 中参照getImpDd定义一个方法
        这个日志将会在tomcat/logs/ 目录下，再创建一个目录，并且日志文件每天生成一个。
2、 普通日志请用 	private final Logger logger = LoggerFactory.getLogger(VelogServiceImpl.class);
3、日志基本要素，人物：日志记录的对象，如：userId=11 地点：输入日志的方法   事件：具体做了什么，如：审核订单。 禁止输出没水平的日志。


>>>>>>>获取VE_CLASS数据字典的数据
1、通过类别获取到集合对象 ${vfc:getVeclassLb('10001')[0].mc }
2、通过ID获取到一个对象 ${vfc:getVeclass('0001028').mc }

>>>>>>>获取航空公司对象
1、通过航空公司二字码获取到航空公司对象${vfc:getHkgs(ezdh)}

>>>>>>>获取机场对象
1、通过机场三字码获取机场对象${vfc:getBcity(nbbh)}



测试环境
http://192.168.1.71/login
xiaoxin
xiaoxinadmin
a456789

http://192.168.1.71:88/login
007
123456