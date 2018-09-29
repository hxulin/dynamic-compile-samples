package tech.ldxy.dynamic;

import java.util.Map;

import com.itranswarp.compiler.JavaStringCompiler;

import tech.ldxy.dynamic.service.Service;

/**
 * 在静态代码(应用)中定义接口, 动态生成的代码去实现接口(推荐)
 * 动态对象就可以强转为该接口类型, 并调用其相关方法
 * 
 * @author hxulin
 *
 */
public class App {
	
	private static final String SOURCE_CODE = "package tech.ldxy.dynamic.service.impl;"
			+ "import tech.ldxy.dynamic.service.Service;"
			+ "public class ServiceImpl implements Service {"
			+ "public void start() {"
			+ "System.out.println(\"--- 开启服务 ---\");"
			+ "}"
			+ "public void stop() {"
			+ "System.out.println(\"--- 停止服务 ---\");"
			+ "}"
			+ "}";
	
    public static void main(String[] args) {
    	
    	Object obj = null;
    	JavaStringCompiler compiler = new JavaStringCompiler();  // 创建动态编译器
    	
    	try {
    		// 动态编译 Java 文件, 生成字节码
        	Map<String, byte[]> result = compiler.compile("ServiceImpl.java", SOURCE_CODE);
        	Class<?> clazz = compiler.loadClass("tech.ldxy.dynamic.service.impl.ServiceImpl", result);
        	
        	obj = clazz.newInstance();  // 使用反射创建对象
        	
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    	
    	if (obj instanceof Service) {
    		Service service = (Service) obj;
    		service.start();
    		service.stop();
    	} else {
    		System.err.println("对象创建失败。");
    	}
    }
}
