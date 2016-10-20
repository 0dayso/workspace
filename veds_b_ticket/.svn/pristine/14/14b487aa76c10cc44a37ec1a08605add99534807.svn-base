package cn.vetech.vedsb.utils;

import java.util.ArrayList;

/**
 * Struts1中ActionForm自动赋值集合
 * 
 * 使用该集合可以像struts2一样，页面name命名为xxxList[0].xxx
 * 
 * 实现原理：
 * 
 * 首先说明一下在struts1中，当点击submit后，把表单中的数据全部提交，在后台是用一个Map来接收表单中的数据，struts对ActionForm中的数据赋值也是从这个map里取值的。比如：
 * 
 * 1：如果key是简单的'userName',直接form.setUserName(map.get('userName'));
 * 
 * 2：如果key是'user.userName', 执行的操作是 form.getUser().setUserName(map.get('user.userName');
 * 
 * 3：如果key是'list[0].userName', 它可以对应到数据或集合中,如对于数组 form.list[0].name=map.get('list[0].userName');
 * 
 * 对于集合(List) form.getList().get(0).setUserName(map.get('list[0].UserName'))
 * 
 * 根据上面的可以看出，在ActionForm中如果有对象或列表的话，必须要初始化，拿2来说吧，如果有一个User user;对象，User里有两个属性：userName和passWord。struts1赋值是通过
 * form.getUser().setUserName(map.get('user.userName'));但是User没有初始化，form.getUser()就会报空指针异常。
 * 
 * 对于一个普通的对象，直接初始化就好了，但是对于一个list来说，最好写一个通过的生成list里面对象的一个类，因为每加一条记录就要增加一个对象，如果像普通对象在类似构造方法里加的话就比较麻烦，所以最好用一个java的回调的功能(Class对象)，新写一个类专门用于生成这个list中的对象
 * 
 * 系统中应用典型实例：批量转机票
 * 
 * 
 * @author lkh
 * @version [版本号, Mar 14, 2014]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class AutoArrayList<T> extends ArrayList<T> {

	private static final long serialVersionUID = -1760189235283678144L;

	/**
	 * 泛型CLASS
	 */
	private Class<T> entityClass;

	public AutoArrayList(Class<T> clazz) {
		this.entityClass = clazz;
	}

	@Override
	public T get(int index) {
		while (index >= this.size()) {
			try {
				this.add(this.entityClass.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
				throw new NullPointerException();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				throw new NullPointerException();
			}
		}
		return super.get(index);
	}

}
