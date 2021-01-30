package com.boot.rest.api.book.war.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	/* UPLOAD_DIRECTORY Where upload our file : static path */
//	public final String UPLOAD_DIRECTORY = "R:\\Rids\\JavaTrainingProjects\\SpringBootInSTS\\SBRestAPIBookDemo\\src\\main\\resources\\static\\image";

	/* UPLOAD_DIRECTORY Where upload our file : dynamic path */
	public final String UPLOAD_DIRECTORY = new ClassPathResource("static/image/").getFile().getAbsolutePath();

	public FileUploadHelper() throws IOException {

	}

	public boolean isUploadFile(MultipartFile multipartFile) {
		boolean flag = false;
		try {
//			/* Way 1 : Using Stream API */
//			InputStream inputStream = multipartFile.getInputStream();
//			byte data[] = new byte[inputStream.available()];
//			inputStream.read(data); // read data
//			/* write data */
//			FileOutputStream fos = new FileOutputStream(UPLOAD_DIRECTORY + "\\" + multipartFile.getOriginalFilename());
//			fos.write(data);
//			fos.flush();
//			fos.close();

			/* Way 2 */
			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIRECTORY + File.separator + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
}
