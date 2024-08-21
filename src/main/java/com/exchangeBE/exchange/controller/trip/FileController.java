package com.exchangeBE.exchange.controller.trip;

import com.exchangeBE.exchange.dto.trip.UploadFile;
import com.exchangeBE.exchange.entity.trip.UploadFileEntity;
import com.exchangeBE.exchange.service.trip.UploadFileService;
import com.exchangeBE.exchange.util.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileStore fileStore;

    @Autowired
    private UploadFileService uploadFileService;

    // 파일 업로드
    @PostMapping("/upload")
    public List<UploadFile> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws IOException {
        List<UploadFileEntity> storedFiles = fileStore.storeFiles(files);
        return storedFiles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 모든 파일 조회
    @GetMapping
    public List<UploadFile> getAllFiles() {
        return uploadFileService.getAllFiles().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // 특정 파일 조회
    @GetMapping("/{id}")
    public UploadFile getFileById(@PathVariable Long id) {
        return convertToDto(uploadFileService.getFileById(id));
    }

    // 파일 삭제
    @DeleteMapping("/{id}")
    public void deleteFile(@PathVariable Long id) {
        uploadFileService.deleteFile(id);
    }

    private UploadFile convertToDto(UploadFileEntity entity) {
        return new UploadFile(entity.getUploadFileName(), entity.getStoreFileName());
    }
}
