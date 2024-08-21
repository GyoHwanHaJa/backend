package com.exchangeBE.exchange.service.trip;

import com.exchangeBE.exchange.entity.trip.UploadFileEntity;
import com.exchangeBE.exchange.repository.trip.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadFileService {

    @Autowired
    private UploadFileRepository uploadFileRepository;

    // 모든 파일 조회
    public List<UploadFileEntity> getAllFiles() {
        return uploadFileRepository.findAll();
    }

    // 파일 ID로 조회
    public UploadFileEntity getFileById(Long id) {
        return uploadFileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    // 파일 삭제
    public void deleteFile(Long id) {
        UploadFileEntity uploadFile = getFileById(id);
        // 파일이 실제로 서버에서 삭제되어야 한다면, 이곳에서 처리합니다.
        // 예: FileStore에서 파일 삭제 로직 호출
        uploadFileRepository.deleteById(id);
    }
}
