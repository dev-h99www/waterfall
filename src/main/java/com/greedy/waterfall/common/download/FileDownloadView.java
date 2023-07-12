package com.greedy.waterfall.common.download;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * <pre>
 * Class : FileDownloadView
 * Comment : AbstractView를 상속받아, 다운로드를 담당할 view 클래스.
 * 
 * History
 * 2022. 2. 21.  (홍성원)
 * </pre>
 * @version 1
 * @author 홍성원
 */
public class FileDownloadView extends AbstractView{

	/* 다운로드의 언어 설정을 UTF-8로 설정한다. */
	public FileDownloadView() {
        setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/* Map형태에 저장된 파일의정보를 String변수에 대입한다.  */
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("downloadFile");
		
		String filePath = (String) fileInfo.get("filePath");
		String fileOriginName = (String) fileInfo.get("fileOriginName");
		String fileRandomName = (String) fileInfo.get("fileRandomName");
		
		/* 전달받은 파일의 저장이름과, 저장경로를 전달해, File클래스의 변수를 생성한다. */
		File file = new File(filePath, fileRandomName);
		
		/* 응답헤더의 설정을 해준다. */
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		String userAgent = request.getHeader("User-Agent");
		String fileName = null;
		
		if(userAgent.indexOf("MSIE") > -1) {
            fileName = URLEncoder.encode(fileOriginName, "utf-8");
		} else {
			fileName = new String(fileOriginName.getBytes("UTF-8"), "iso-8859-1");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
       
        /* 설정한 정보를 사용자에게 내보내기한다. */
        OutputStream out = response.getOutputStream();
 
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } finally {
            if(fis != null)
                try {
                    fis.close();
                } catch(IOException ex) {
                	
                }
        }
        
        out.flush();
	}
}
