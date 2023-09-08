package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdS3DTO;
import com.fiveis.andcrowd.service.crowd.CrowdS3Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/crowd/s3")
public class CrowdS3Controller {

    private final CrowdS3Service crowdS3Service;

    public CrowdS3Controller(CrowdS3Service crowdS3Service) {
        this.crowdS3Service = crowdS3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<CrowdS3DTO>> uploadImages(
            @RequestParam("crowdId") int crowdId,
            @RequestParam("fileType") String fileType,
            @RequestParam("files") List<MultipartFile> files) {
        List<CrowdS3DTO> uploadedFiles = crowdS3Service.uploadFile(crowdId, fileType, files);
        return ResponseEntity.ok(uploadedFiles);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteImage(
            @RequestParam("uploadFilePath") String uploadFilePath,
            @RequestParam("uuidFileName") String uuidFileName) {
        String result = crowdS3Service.deleteFiles(uploadFilePath, uuidFileName);
        if ("success".equals(result)) {
            return ResponseEntity.ok("File deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }

}
