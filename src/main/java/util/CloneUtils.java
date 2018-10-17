package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Description:TODO
 * @author LiuBo
 * @date 2018年10月17日  上午10:33:18
 */
public class CloneUtils {

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) throws IOException, ClassNotFoundException {
		T cloneObj = null;
		
		//写入字节流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(obj);
		oos.close();
		//分配内存，写入原始对象，生成新对象
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(in);
		
		//返回生成的新对象
		cloneObj = (T) ois.readObject();
		ois.close();
		
		return cloneObj;
	}
	
}
