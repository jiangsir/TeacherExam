package jiangsir.teacherexam.Tools;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.*;
import jiangsir.teacherexam.Exceptions.DataException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class Uploader {
	public static final int MAX_FILESIZE = 50 * 1024 * 1024; // bytes
	private HashMap<String, ArrayList<String>> formfields = new HashMap<String, ArrayList<String>>();
	private HashMap<String, ArrayList<FileItem>> filefields = new HashMap<String, ArrayList<FileItem>>();
	private List<?> items = null;
	private boolean hasException;
	private String Exception;

	public boolean isHasException() {
		return hasException;
	}

	public void setHasException(boolean hasException) {
		this.hasException = hasException;
	}

	public String getException() {
		return Exception;
	}

	public void setException(String exception) {
		Exception = exception;
	}

	public Uploader(HttpServletRequest request, HttpServletResponse response)
			throws DataException {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory(MAX_FILESIZE,
				new File("/tmp"));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Set overall request size constraint
		upload.setSizeMax(Uploader.MAX_FILESIZE); // bytes

		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			e1.printStackTrace();
			throw new DataException("上傳錯誤！檔案上限為 " + Uploader.MAX_FILESIZE
					+ " bytes. 請勿上傳過大檔案。e=" + e1.getLocalizedMessage());
		}
		if (items == null) {
			throw new DataException("上傳錯誤(items==null)！檔案上限為 "
					+ Uploader.MAX_FILESIZE + " bytes. 請勿上傳過大檔案。");
		}

		Iterator<?> it = items.iterator();
		while (it.hasNext()) {
			FileItem item = (FileItem) it.next();
			if (item.isFormField()) {
				ArrayList<String> values = new ArrayList<String>();
				if (this.formfields.containsKey(item.getFieldName())) {
					values = this.formfields.get(item.getFieldName());
				}
				try {
					values.add(item.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				this.formfields.put(item.getFieldName(), values);
				// this.formfields.put(item.getFieldName(), item
				// .getString("UTF-8"));
			} else {
				ArrayList<FileItem> items = new ArrayList<FileItem>();
				if (this.filefields.containsKey(item.getFieldName())) {
					items = this.filefields.get(item.getFieldName());
				}
				items.add(item);
				this.filefields.put(item.getFieldName(), items);
			}
		}
	}

	// public String getFormField(String fieldname) {
	// return this.formfields.get(fieldname);
	// }

	/**
	 * 取得多個同名 field
	 * 
	 * @param fieldname
	 * @return
	 */
	public String[] getParameterValues(String fieldname) {
		String[] values = new String[this.formfields.get(fieldname).size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = this.formfields.get(fieldname).get(i);
		}
		return values;
	}

	/**
	 * 當確定該 fieldname 只有一個值的話，就直接取一個值比較簡便
	 * 
	 * @param fieldname
	 * @return
	 */
	public String getParameter(String fieldname) {
		if (this.formfields.containsKey(fieldname)) {
			return this.formfields.get(fieldname).get(0);
		}
		return null;
	}

	public ArrayList<FileItem> getFileItems(String fieldname) {
		ArrayList<FileItem> items = new ArrayList<FileItem>();
		ArrayList<FileItem> fieldnames = this.filefields.get(fieldname);
		if (fieldnames == null) {
			return items;
		}
		Iterator<FileItem> it = fieldnames.iterator();
		while (it.hasNext()) {
			items.add(it.next());
		}
		return items;
	}

	public ArrayList<String> getFilenames(String fieldname) {
		ArrayList<String> filenames = new ArrayList<String>();
		Iterator<?> it = this.filefields.get(fieldname).iterator();
		while (it.hasNext()) {
			filenames.add(((FileItem) it.next()).getName());
		}
		return filenames;
	}

	public ArrayList<String> getFileContentTypes(String fieldname) {
		ArrayList<String> ContentTypes = new ArrayList<String>();
		Iterator<?> it = this.filefields.get(fieldname).iterator();
		while (it.hasNext()) {
			ContentTypes.add(((FileItem) it.next()).getContentType());
		}
		return ContentTypes;
	}

	public ArrayList<Long> getFileSizes(String fieldname) {
		ArrayList<Long> filesizes = new ArrayList<Long>();
		Iterator<?> it = this.filefields.get(fieldname).iterator();
		while (it.hasNext()) {
			filesizes.add(((FileItem) it.next()).getSize());
		}
		return filesizes;
	}

	public void checkUploadFiles(ArrayList<FileItem> fileItems)
			throws DataException {
		// Iterator<?> it = uploader.getFileItems("Executable").iterator();
		Iterator<?> it = fileItems.iterator();
		while (it.hasNext()) {
			FileItem fileItem = (FileItem) it.next();
			System.out.println("getContentType()=" + fileItem.getContentType()
					+ ", fileItem.getFieldName()=" + fileItem.getFieldName()
					+ ", fileItem.getSize()=" + fileItem.getSize());
			String name = fileItem.getName();
			if (name != null) {
				name = FilenameUtils.getName(name);
			}
			this.checkUploadFile(fileItem);
		}
	}

	private boolean hasFullSize(String s) {
		if (s.getBytes().length != s.length()) {
			return true;
		}
		return false;
	}

	public void checkUploadFile(FileItem fileItem) throws DataException {
		if (fileItem.getSize() > Uploader.MAX_FILESIZE) {
			throw new DataException("檔案超過上限");
		}
		if (this.hasFullSize(fileItem.getName())) {
			throw new DataException("不接受中文檔名");
		}
	}

	/**
	 * 檔名加上 prefix 才能保證檔名不會相同造成覆蓋, 一般可以傳入 upfilesid
	 * 
	 * @param fieldname
	 * @param prefix
	 * @throws AccessException
	 * @throws AccessException
	 */
	public void uploadFile(FileItem fileItem, File uploadedfile)
			throws DataException {
		this.checkUploadFile(fileItem);
		System.out.println("!FormField: item.getFieldName()="
				+ fileItem.getFieldName() + " -- 欄位名稱");
		System.out.println("!FormField: item.getName()=" + fileItem.getName()
				+ " -- 使用者上傳檔案的檔名");
		System.out.println("!FormField: item.getContentType()="
				+ fileItem.getContentType() + " -- ");
		System.out.println("!FormField: item.isInMemory()="
				+ fileItem.isInMemory() + " -- ");
		System.out.println("!FormField: item.getSize()=" + fileItem.getSize()
				+ " -- ");
		// File uploadedFile = new File("/tmp/", "test.doc");

		// File uploadedFile = new File(path, filename);
		System.out.println("寫入檔案 " + uploadedfile.getName());
		System.out.println("uploadedFile=" + uploadedfile.getPath());
		if (!uploadedfile.getParentFile().exists()) {
			if (!uploadedfile.getParentFile().mkdirs()) {
				throw new DataException("無法建立目錄"
						+ uploadedfile.getParentFile().getPath() + ", 請檢查存取權限！");
			}
		}
		try {
			fileItem.write(uploadedfile);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException(e.getLocalizedMessage());
		}
	}
}
