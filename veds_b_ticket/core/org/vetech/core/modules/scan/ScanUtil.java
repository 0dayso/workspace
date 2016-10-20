package org.vetech.core.modules.scan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 扫描工具类
 * @author 章磊
 *
 */
public class ScanUtil {
	/**
	 * 基于classpath目录得到所有class对象
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Set<Class> scan(String path,Class cls) throws IOException, ClassNotFoundException{
		Set<Class> set = new HashSet<Class>();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory =
				new CachingMetadataReaderFactory(resourcePatternResolver);
		Resource[] resources = resourcePatternResolver.getResources(path);
		for(Resource resource : resources){
			MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
			ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
			sbd.setResource(resource);
			sbd.setSource(resource);
			Class clsnew  = sbd.resolveBeanClass(ScanUtil.class.getClassLoader());
			
			if(cls==null){
				set.add(clsnew);
			}else{
				boolean isFind = false;
				if(clsnew.getInterfaces()!=null){
					for(Class interfac : clsnew.getInterfaces()){
						if(interfac.getName().equals(cls.getName())){
							isFind = true;
							break;
						}
					}
				}
				if(cls.isAssignableFrom(clsnew)){
					isFind = true;
				}
				if(isFind)
				set.add(clsnew);
			}
		}
		return set;
	}
	public static String[] findPackages(String path){
		try{
			List<String> list = new ArrayList<String>();
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			MetadataReaderFactory metadataReaderFactory =
					new CachingMetadataReaderFactory(resourcePatternResolver);
			//"classpath*:cn/vetech/**/entity"
			String classpath =  "classpath*:"+path;
			Resource[] resources = resourcePatternResolver.getResources(classpath);
			String reg = path.replace("**",".*");
			reg = path.replace("*",".*");
			Pattern pattern = Pattern.compile(reg);
			for(Resource resource : resources){
				Matcher matcher =pattern.matcher(resource.getURL().getPath());
				if(matcher.find()){
					list.add(matcher.group(0));
				}

			}
			return list.toArray(new String[]{});
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String aa[] = ScanUtil.findPackages("cn/vetech/**/entity/*.class");
		System.out.println(aa.length);
		Set a = ScanUtil.scan("cn/vetech/**/entity/*.class",AbstractPageEntity.class);
		System.out.println(a.size());
		System.out.println(a);
		
	}
}
