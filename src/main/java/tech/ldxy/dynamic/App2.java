package tech.ldxy.dynamic;

import java.util.Map;

import com.itranswarp.compiler.JavaStringCompiler;

/**
 * 动态代码没有实现接口, 直接反射调用方法
 * 
 * 注: 此处为练习使用, 建议给动态代码定义接口
 * 
 * @author hxulin
 *
 */
public class App2 {
	
	private static final String SOURCE_CODE = "package tech.ldxy.dynamic.service.impl;"
			+ "public class ServiceImpl2 {"
			+ "public void start() {"
			+ "System.out.println(\"--- 开启服务 ---\");"
			+ "}"
			+ "public void stop() {"
			+ "System.out.println(\"--- 停止服务 ---\");"
			+ "}"
			+ "}";
	
    public static void main(String[] args) {
    	
    	JavaStringCompiler compiler = new JavaStringCompiler();  // 创建动态编译器
    	
    	try {
    		// 动态编译 Java 文件, 生成字节码
        	Map<String, byte[]> result = compiler.compile("ServiceImpl2.java", SOURCE_CODE);
        	Class<?> clazz = compiler.loadClass("tech.ldxy.dynamic.service.impl.ServiceImpl2", result);
        	
        	Object obj = clazz.newInstance();  // 使用反射创建对象
        	
        	clazz.getMethod("start").invoke(obj);  // 调用 ServiceImpl2 无参的 start 方法
        	clazz.getMethod("stop").invoke(obj);  // 调用 ServiceImpl2 无参的 stop 方法
        	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
}
