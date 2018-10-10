package com.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import com.util.validator.ValidatorUtil;

/**
 * 文件
 *
 * @author 李小勇
 */
public class FileUtil {

	public static boolean upload(InputStream inputStream, String pathAndName) {
		File filePt = new File(pathAndName);
		boolean flag = false;
		if (!filePt.getParentFile().exists()) {
			flag = filePt.getParentFile().mkdirs();
		}
		if ((filePt.getParentFile().exists()) || (flag)) {
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(pathAndName);
				byte[] bt = new byte[1024];
				int length;
				while ((length = inputStream.read(bt)) != -1) {
					out.write(bt, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 单个文件上传
	 *
	 * @param content
	 *            上传的文件内�?
	 * @param pathAndName
	 *            路径和文件名
	 * @return
	 */
	public static boolean upload(String content, String pathAndName) {
		// 上传文件的目�?
		File filePt = new File(pathAndName);
		boolean flag = false;
		// 判断文件目录是否存在，没有就创建
		if (!filePt.getParentFile().exists()) {
			flag = filePt.getParentFile().mkdirs();
		}
		// 存在或创建目录成�?
		if (filePt.getParentFile().exists() || flag) {
			// 输出�?
			OutputStreamWriter out = null;
			try {
				out = new OutputStreamWriter(new FileOutputStream(pathAndName),
						"UTF-8");
				out.write(content);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (null != out) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 单个文件上传
	 *
	 * @param file
	 *            上传的文�?
	 * @param pathAndName
	 *            路径和文件名
	 * @return
	 */
	public static boolean upload(File file, String pathAndName) {
		// 上传文件的目�?
		File filePt = new File(pathAndName);
		boolean flag = false;
		// 判断文件目录是否存在，没有就创建
		if (!filePt.getParentFile().exists()) {
			flag = filePt.getParentFile().mkdirs();
		}
		// 存在或创建目录成�?
		if (filePt.exists() || flag) {
			// 输出�?
			OutputStream os = null;
			// 输入�?
			InputStream is = null;
			try {
				// 获得输出�?
				os = new FileOutputStream(pathAndName);
				// 获得文件输入�?
				is = new FileInputStream(file);
				byte[] bt = new byte[1024];
				int length;
				while ((length = is.read(bt)) != -1) {
					// 写文�?
					os.write(bt, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 单个文件上传
	 *
	 * @param file上传的文
	 *            �?
	 * @param fileName上传的文件名
	 * @param path文件保存的路
	 *            �?
	 * @return
	 */
	public static boolean upload(File file, String fileName, String path) {
		// 上传文件的目�?
		File filePt = new File(path);
		boolean flag = false;
		// 判断文件目录是否存在，没有就创建
		if (!filePt.exists()) {
			flag = filePt.mkdirs();
		}
		// 存在或创建目录成�?
		if (filePt.exists() || flag) {
			// 输出�?
			OutputStream os = null;
			// 输入�?
			InputStream is = null;
			try {
				// 获得输出�?
				os = new FileOutputStream(path + fileName);
				// 获得文件输入�?
				is = new FileInputStream(file);
				byte[] bt = new byte[1024];
				int length;
				while ((length = is.read(bt)) != -1) {
					// 写文�?
					os.write(bt, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 单个文件上传自定义文件名
	 *
	 * @param file上传的文
	 *            �?
	 * @param fileName上传的文件名
	 * @param path文件保存的路
	 *            �?
	 * @param param保存的文件名
	 * @return
	 */
	public static boolean upload(File file, String fileName, String path,
			String param) {
		// 文件后缀
		String hz = fileName.substring(fileName.lastIndexOf("."));
		// 上传文件的目�?
		File filePt = new File(path);
		boolean flag = false;
		// 判断文件目录是否存在，没有就创建
		if (!filePt.exists()) {
			flag = filePt.mkdirs();
		}
		// 存在或创建目录成�?
		if (filePt.exists() || flag) {

			// 输出�?
			OutputStream os = null;
			// 输入�?
			InputStream is = null;
			try {
				// 获得输出�?
				os = new FileOutputStream(path + param + hz);
				// 获得文件输入�?
				is = new FileInputStream(file);
				byte[] bt = new byte[1024];
				int length;
				while ((length = is.read(bt)) != -1) {
					// 写文�?
					os.write(bt, 0, length);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (null != os) {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 新建目录
	 *
	 * @param folderPath
	 */
	public static boolean newFolder(String folderPath) {
		try {
			File myFilePath = new File(folderPath);
			if (!myFilePath.exists()) {
				return myFilePath.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 删除文件或空文件�?
	 *
	 * @param filePath
	 */
	public static boolean delFile(String filePath) {
		try {
			File myDelFile = new File(filePath);
			return myDelFile.exists() && myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * �?��文件/目录是否存在
	 *
	 * @param file文件
	 *            /目录路径
	 * @return<boolean>
	 */
	public static boolean exist(final String file) {
		final File newFile = new File(file);
		// �?��目录是否存在
		return newFile.exists();
	}

	/**
	 * 读文�?
	 *
	 * @param file文件
	 * @return<BufferedReader>
	 */
	public static BufferedReader read(final File file) {
		if (null == file) {
			return null;
		}
		try {
			return new BufferedReader(new FileReader(file));
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 读文�?
	 *
	 * @param fielPath文件
	 * @return<BufferedReader>
	 */
	public static BufferedReader read(final String fielPath) {
		if (fielPath == null) {
			return null;
		}
		if (fielPath.equals("")) {
			return null;
		}
		return FileUtil.read(new File(fielPath));
	}

	/**
	 * 读文�?
	 *
	 * @param fielPath文件绝对路径
	 * @return String
	 */
	public static String readFile(final String fielPath) {
		File file;
		FileReader fr;
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		String temp;
		try {
			// 生成对象 准备读取文件
			try {
				file = new File(fielPath);
				if (!file.exists()) {
					return null;
				}
				fr = new FileReader(file); // 打开�?
				br = new BufferedReader(fr);
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			try {
				br.close(); // 关闭�?
			} catch (final IOException e) {
				e.printStackTrace();
				return null;
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return sb.toString();
	}

	/**
	 * 读文�?
	 *
	 * @param bufferedReader文件
	 *            �?
	 * @return
	 */
	public static String readFile(final BufferedReader bufferedReader) {
		StringBuilder sb = new StringBuilder();
		try {
			String temp;
			while ((temp = bufferedReader.readLine()) != null) {
				sb.append(temp);
			}
			try {
				bufferedReader.close(); // 关闭�?
			} catch (final IOException e) {
				e.printStackTrace();
				return null;
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return sb.toString();
	}

	public static String getRandomFileName(String fileName) {
		try {
			String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String ext = getFileExtension(fileName);
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 4; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
			return sb.toString() + System.currentTimeMillis() + "." + ext;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取文件后缀
	 *
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		try {
			if (!ValidatorUtil.isNull(fileName)) {
				// 文件后缀
				return fileName.substring(fileName.lastIndexOf(".") + 1);
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 文件夹分�?
	 *
	 * @param number
	 * @return "/number/number/"
	 */
	public static String getFileShunt(final long number) {
		try {
			if (0 < number) {
				// 文件�?
				long tmpPath = FileUtil.getShuntPath(number - 1, 2000, 1);
				// 文件�?
				long tmpPathRoot = FileUtil.getShuntPath(tmpPath - 1, 2000, 1);
				return File.separator + tmpPathRoot + File.separator
						+ (tmpPath - (tmpPathRoot - 1) * 2000) + File.separator;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "";
	}

	/**
	 * 文件夹分�?
	 *
	 * @param num随机
	 *            �?
	 * @param base基数
	 * @param add增量
	 * @return
	 */
	private static long getShuntPath(long num, long base, long add) {
		return num / base + add;
	}

}
