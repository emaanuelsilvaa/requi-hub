package br.com.imd.requihub.controller;

import br.com.imd.requihub.usecase.UploadDownloadImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class FileController {
    private static final String path = "/home/user/Desktop/files/";

    private final UploadDownloadImpl uploadDownload;
    @PostMapping("/upload")
    public ResponseEntity<List<String>> fileUpload
            (@RequestParam("file") MultipartFile file,  @RequestParam String id)
            throws Exception {

        return new ResponseEntity<>(uploadDownload.uploadFile(file, id),
                HttpStatus.OK);

    }

    @GetMapping(path = "/download/{name}")
    public ResponseEntity<Resource> download(@PathVariable("name") String name) throws IOException {

        File file = new File(path + name);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok().headers(this.headers(name)).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

    private HttpHeaders headers(String name) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;
    }
}
