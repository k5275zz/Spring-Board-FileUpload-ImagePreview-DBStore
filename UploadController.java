package org.jht.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.jht.dto.BoardAttachDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	// 현재 날짜를 구합니다.-> (폴더명)
	private String getFolder() {
		// 현재날짜의 형식을 지정합니다.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 월의 M은 대문자로 적어주어야합니다. 시간의 minute과 헷갈릴수 있기때문입니다.
		// 현재날짜
		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}

	@PostMapping("/uploadFormAction")
	@ResponseBody
	public ResponseEntity<ArrayList<BoardAttachDTO>> postUpload(MultipartFile[] uploadFile) {
		// 어떤 폴더에 실제로 업로드를 할 것인지에 대한 폴더 경로
		System.out.println("uploadFormAction="+uploadFile);
		System.out.println("uploadFile길이 = "+uploadFile.length);
		// AttachFileDTO클래스를 list배열로 생성
		ArrayList<BoardAttachDTO> list = new ArrayList<>();
		// 어떤 폴더에 실제로 업로드를 할 것인지에 대한 폴더 경로
		String uploadFolder = "D:\\uploadFolder";
		String uploadFolderPath = getFolder();
		// 날짜별 폴더 생성
		// File uploadPath = new File(파일 업로드 경로, 파일명);
		File uploadPath = new File(uploadFolder, uploadFolderPath);

		// 똑같은 폴더명을 만들 필요가 없기 때문에 exist() 함수를 통해 존재여불를 파악하여 없으면(false) 폴더를 생성합니다.
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs(); // 폴더를 만듭니다.
		}
		// 향상된 for(타입이 배열타입 일때만 사용가능)
		for (MultipartFile files : uploadFile) {
			logger.info("사용자가 선택한 파일명 : " + files.getOriginalFilename());
			logger.info("사용자가 선택한 파일크기 : " + files.getSize());

			BoardAttachDTO attach = new BoardAttachDTO();
			String uploadFileName = files.getOriginalFilename();
			// BoardAttachDTO 클래스에 fileName변수에 파일이름 저장
			attach.setFileName(uploadFileName);
			// UUID클래스를 이용하여 중복되지 않는 랜덤값을 생성
			UUID uuid = UUID.randomUUID();
			logger.info("uuid=" + uuid);
			uploadFileName = uuid.toString() + "_" + files.getOriginalFilename();
			try {
				// File saveFile = new File(파일 업로드 경로, 파일명);
				File saveFile = new File(uploadPath, uploadFileName);
				logger.info("saveFile" + saveFile);
				files.transferTo(saveFile);
				// BoardAttachDTO 클래스에 uploadPath변수에 날짜포함된 이미지저장위치
				attach.setUploadPath(uploadFolderPath);
				// BoardAttachDTO 클래스에 UUID변수에 UUID 저장
				attach.setUuid(uuid.toString());
				list.add(attach);
				logger.info("list=" + list);
			} catch (Exception e) {
				e.getMessage();
			} //

		} // for문 끝
		return new ResponseEntity<>(list, HttpStatus.OK);
	}// uploadFormAction post end

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFiles(String fileName) {
		File file = new File("D:\\uploadFolder\\" + fileName);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
